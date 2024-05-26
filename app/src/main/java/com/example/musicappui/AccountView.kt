package com.example.musicappui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AccountView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(modifier = Modifier.padding(16.dp)){
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
                Column {
                    Text(text = "Composable tutorial", modifier = Modifier.padding(start = 8.dp))
                    Text(text = "@tutorial", modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
        Row(modifier = Modifier.padding(16.dp)) {
                Icon(painter = painterResource(id = com.example.musicappui.R.drawable.baseline_library_music_24), contentDescription = null )
                Text(text = "My Music", modifier = Modifier.padding(start = 8.dp))

        }
       
    }
}