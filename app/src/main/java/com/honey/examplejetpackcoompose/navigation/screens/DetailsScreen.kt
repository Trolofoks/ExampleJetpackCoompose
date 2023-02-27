package com.honey.examplejetpackcoompose.navigation.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.honey.examplejetpackcoompose.navigation.Word

@Composable
fun DetailsScreen(
    word: Word
){
    Text(text = "Search Screen -> ${word.value}")
}