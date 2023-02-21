package com.honey.examplejetpackcoompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.honey.examplejetpackcoompose.example.mvi.Kitten3ViewModel
import com.honey.examplejetpackcoompose.example.testviewmodel.KittenViewModel
import com.honey.examplejetpackcoompose.example.testviewmodel.MainKittenScreen
import com.honey.examplejetpackcoompose.ui.theme.ExampleJetpackCoomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleJetpackCoomposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    MainKittenScreen(KittenViewModel(), Kitten3ViewModel())
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExampleJetpackCoomposeTheme {
        Greeting("Android")
    }
}