package com.example.scrolltotop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.example.scrolltotop.ui.theme.ScrollToTopTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScrollToTopTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    val contentList = (0..20).map {
                        "Item $it"
                    }
                    val listState = rememberLazyListState()
                    val scope = rememberCoroutineScope()
                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        itemsIndexed(contentList) { index, item ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                val height = if(index == contentList.lastIndex) 20.dp else 120.dp
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(height)
                                        .background(Color.LightGray)
                                        .border(2.dp, Color.Red)
                                ) {
                                    Text(item)
                                }
                            }

                        }
                        item {
                            Button(onClick = { scope.launch { listState.animateScrollToItem(0) } }) {
                                Text(text = "Scroll")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ScrollToTopTheme {
        Greeting("Android")
    }
}