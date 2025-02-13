package com.example.musicappui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeView() {
    val category = listOf<String>("Test1","Test2","Test3","Test4","Test5","Test6","Test7")
    val groupBy = listOf<String>("Group1","Group2","Group3","Group4").groupBy { it[0] }
    LazyColumn {
        groupBy.forEach{
            stickyHeader {
               Text(text = it.value[0], modifier = Modifier.padding(8.dp))
                LazyRow {
                    items(category) {
                        cat->
                        BrowserItem(cat = cat, drawable = R.drawable.baseline_home_24)

                    }
                }
            }
        }
    }
}


@Composable
fun BrowserItem(cat: String, drawable: Int) {
   androidx.compose.material.Card (
       modifier = Modifier
           .padding(16.dp)
           .size(200.dp),
       border = BorderStroke(3.dp, color = Color.DarkGray)
   ){
       Column(
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           Text(text = cat)
           Image(painter = painterResource(id = drawable), contentDescription = "")
       }
   }
}
