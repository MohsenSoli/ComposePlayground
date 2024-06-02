package com.mohsen.composeplayground

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@SmallTest
@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class WidgetsDaoTest {

    private lateinit var widgetsDao: WidgetsDao
    private lateinit var db: WidgetsDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, WidgetsDatabase::class.java,
        ).allowMainThreadQueries().build()
        widgetsDao = db.widgetsDao()
    }

    @Test
    fun when_add_to_widgets_with_same_checksum_THEN_replace_the_old_one() = runTest {
        widgetsDao.upsert("1", byteArrayOf(0x01), 10)
        widgetsDao.upsert("1", byteArrayOf(0x10), 10)
        val checksums = widgetsDao.fetchAllChecksums()
        val widget = widgetsDao.fetchWidget("1")
        assertEquals(1, checksums.size)
        assertEquals("1", checksums.first())
        assert(widget.data.contentEquals(byteArrayOf(0x10)))
    }


    @Test
    fun when_add_multiple_widgets_THEN_order_by_time() = runTest {
        val list = (1..10).map { it.toString() }
        list.forEach {
            widgetsDao.upsert(it, byteArrayOf(0x10), 20)
        }
        assertEquals(list, widgetsDao.fetchAllChecksums())
    }

    @Test
    fun when_widget_more_and_capacity_is_full_THEN_old_item_will_be_replaced() = runTest {
        val list = (1..10).map { it.toString() }
        list.forEach {
            widgetsDao.upsert(it, byteArrayOf(0x10), 10)
        }
        widgetsDao.upsert("11", byteArrayOf(0x10), 10)
        assertEquals("11", widgetsDao.fetchAllChecksums().last())
        assertEquals(10, widgetsDao.fetchAllChecksums().size)
    }

    @Test
    fun when_add_multiple_widgets_more_than_capacity_THEN_old_items_will_be_replaced() = runTest {
        val list = (1..20).map { it.toString() }
        list.forEach {
            widgetsDao.upsert(it, byteArrayOf(0x10), 10)
        }
        assertEquals(list.subList(10,20), widgetsDao.fetchAllChecksums())
    }

    @Test
    fun when_delete_THEN_order_by_time() = runTest {
        val list = (1..10).map { it.toString() }
        list.forEach {
            widgetsDao.upsert(it, byteArrayOf(0x10), 20)
        }
        widgetsDao.delete("1")
        assertEquals("2", widgetsDao.fetchAllChecksums().first())
        assertEquals(9, widgetsDao.fetchAllChecksums().size)
        assertTrue(widgetsDao.widgetExists("1") == 0)
    }

    @Test
    fun when_delete_multiple_THEN_order_by_time() = runTest {
        val list = (1..10).map { it.toString() }
        list.forEach {
            widgetsDao.upsert(it, byteArrayOf(0x10), 20)
        }
        widgetsDao.delete(listOf("1", "2", "3"))
        assertEquals("4", widgetsDao.fetchAllChecksums().first())
        assertEquals(7, widgetsDao.fetchAllChecksums().size)
    }

    @Test
    fun when_delete_all_THEN_order_by_time() = runTest {
        val list = (1..10).map { it.toString() }
        list.forEach {
            widgetsDao.upsert(it, byteArrayOf(0x10), 20)
        }
        widgetsDao.clear()
        assertTrue(widgetsDao.fetchAllChecksums().isEmpty())
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}