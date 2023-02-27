package com.honey.examplejetpackcoompose.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


//это именно разные экраны со своими ViewModel и состояниями, со своими полноэкранными состояниями
@Composable
fun ListScreen(
    modifier: Modifier = Modifier
){
    Column(modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        repeat(20){
            Card(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = Color.Gray)
                .padding(10.dp)
            ) {
                Text(text = "List $it")
            }
        }
    }

}