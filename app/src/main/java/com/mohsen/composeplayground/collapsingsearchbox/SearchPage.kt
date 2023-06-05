package com.mohsen.composeplayground.collapsingsearchbox

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun SearchPage() {

    val offset = remember { mutableStateOf(0f) }
    val heightPx = remember { mutableStateOf(0f) }
    val heightDp = with(LocalDensity.current) { heightPx.value.toDp() }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                offset.value = (offset.value + delta).coerceIn(-heightPx.value, 0f)
                return Offset.Zero
            }
        }
    }

    Box(
        modifier = Modifier
        .nestedScroll(nestedScrollConnection)
        .fillMaxSize()
    ) {

        val text = remember { mutableStateOf("") }

        LazyColumn(
            contentPadding = PaddingValues(top = heightDp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            data.forEachIndexed { index, s ->
                item(
                    key = index,
                    contentType = s
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        text = s
                    )
                }
            }
        }

        SearchBox(
            modifier = Modifier
                .onGloballyPositioned { heightPx.value = it.size.height.toFloat() }
                .graphicsLayer { translationY = offset.value }
                .align(Alignment.TopCenter),
            value = text.value,
            onValueChange = { text.value = it },
            onSearchClick = { /*TODO*/ },
        )
    }
}


val data = listOf(
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
    "THREE",
    "ONE",
    "TWO",
)