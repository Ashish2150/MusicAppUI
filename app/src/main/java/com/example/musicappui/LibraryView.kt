package com.example.musicappui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun LibraryView() {
   LazyColumn {
       items(library) {
           lib->
           Row (
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(16.dp),
               horizontalArrangement = Arrangement.SpaceBetween
           ){
               Row {
                   Icon(painter = painterResource(id = lib.icon), contentDescription = "")
                   Text(text = lib.name)
               }
               Icon(painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24), contentDescription = "")
           }
           Divider(color = Color.LightGray)
       }
   }
}

