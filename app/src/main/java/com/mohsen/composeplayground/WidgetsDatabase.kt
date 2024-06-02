package com.mohsen.composeplayground

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Transaction

@Database(
    entities = [WidgetEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class WidgetsDatabase : RoomDatabase() {
    abstract fun widgetsDao(): WidgetsDao
}

@Entity(tableName = "widgets_cache")
data class WidgetEntity(
    @PrimaryKey
    val checksum: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val data: ByteArray,
    @ColumnInfo
    val index: Long,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WidgetEntity

        if (checksum != other.checksum) return false
        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = checksum.hashCode()
        result = 31 * result + data.contentHashCode()
        return result
    }
}

@Dao
interface WidgetsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: WidgetEntity)

    @Query("select * from widgets_cache where checksum=:checksum limit 1")
    suspend fun fetchWidget(checksum: String): WidgetEntity

    @Query("select exists (select * from widgets_cache where checksum=:checksum limit 1)")
    suspend fun widgetExists(checksum: String): Int

    @Query("select checksum from widgets_cache order by `index` asc")
    suspend fun fetchAllChecksums(): List<String>

    @Query("select `index` from widgets_cache order by `index` desc limit 1")
    suspend fun getLastIndex(): Long?

    @Transaction
    suspend fun upsert(checksum: String, data: ByteArray, tableLimit: Int) {
        val lastIndex = getLastIndex() ?: 0
        insert(WidgetEntity(checksum, data, lastIndex + 1))
        removeOldData(tableLimit)
    }

    @Query("delete from widgets_cache where checksum = :checksum")
    suspend fun delete(checksum: String)

    @Query("delete from widgets_cache where checksum in (:checksums)")
    suspend fun delete(checksums: List<String>)

    @Query("delete from widgets_cache")
    suspend fun clear()

    @Query(
        "delete from widgets_cache where checksum not in " +
                "(select checksum from widgets_cache order by `index` desc limit :limit)"
    )
    suspend fun removeOldData(limit: Int)
}