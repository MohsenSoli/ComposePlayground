package com.mohsen.composeplayground.collapsingsearchbox

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohsen.composeplayground.ui.theme.ComposePlaygroundTheme

@Composable
fun SearchBox(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    onSearchClick: () -> Unit = {},
) {

    BoxWithConstraints(
        modifier = modifier
            .padding(4.dp)
            .background(color = Color.Cyan, shape = RoundedCornerShape(3.dp))
            .border(width = 4.dp, color = Color.DarkGray, shape = RoundedCornerShape(3.dp))
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            value = value,
            onValueChange = onValueChange,
            maxLines = 1,
            singleLine = true,
            placeholder = { Text("Search") },
            trailingIcon = {
                IconButton(onClick = { onSearchClick() }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "")
                }
            },
        )
    }
}

@Preview
@Composable
fun PreviewSB() {

    ComposePlaygroundTheme {
        Surface {
            SearchBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                onValueChange = {}
            )
        }
    }
}