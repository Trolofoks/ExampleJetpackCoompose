package com.honey.examplejetpackcoompose.navigation

import android.os.Parcelable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import kotlinx.android.parcel.Parcelize

//    id 'kotlin-android-extensions'
@Parcelize
data class Word(val value: String) : Parcelable

@Composable
fun SearchScreen(
    navController: NavController
) {
    val testArray = listOf(
        Word("Hello"), Word("World"), Word("Earth"), Word("VK"), Word("Instagram"),
        Word("Hello"), Word("World"), Word("Earth"), Word("VK"), Word("Instagram"),
        Word("Hello"), Word("World"), Word("Earth"), Word("VK"), Word("Instagram"),
    )

    //не думаю что тут нужен Scaffold но вообще в идеале спросить
    Scaffold {
        val a = it
        LazyColumn() {
            testArray.map {word ->
                item {
                    Text(
                        text = word.value,
                        modifier = Modifier
                            .padding(48.dp)
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("details", bundleOf("WORD_KEY" to word))
                            }
                    )
                }
            }
        }
    }
}