package com.honey.examplejetpackcoompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.honey.examplejetpackcoompose.example.mvi.Kitten3ViewModel
import com.honey.examplejetpackcoompose.example.testviewmodel.KittenViewModel
import com.honey.examplejetpackcoompose.example.testviewmodel.MainKittenScreen
import com.honey.examplejetpackcoompose.navigation.ListScreen
import com.honey.examplejetpackcoompose.navigation.PushScreen
import com.honey.examplejetpackcoompose.navigation.SearchScreen
import com.honey.examplejetpackcoompose.navigation.Word
import com.honey.examplejetpackcoompose.navigation.screens.DetailsScreen
import com.honey.examplejetpackcoompose.secondexample.productdetails.ProductScreen
import com.honey.examplejetpackcoompose.secondexample.productdetails.ProductViewModel
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
                    //начало
//                    val kittenViewModel by viewModels<KittenViewModel>()
//                    val kitten3ViewModel by viewModels<Kitten3ViewModel>()
//                    MainKittenScreen(kittenViewModel, kitten3ViewModel)

                    //верстка
//                    val productViewModel by viewModels<ProductViewModel>()
//                    ProductScreen(productViewModel)

                    //navigation
                    val navController = rememberNavController()
                    val bottomItems = listOf("list", "search", "push")

                    Scaffold(
                        bottomBar = {
                            BottomNavigation {
                                bottomItems.forEach {screen ->
                                    BottomNavigationItem(
                                        selected = false,
                                        onClick = {
                                            navController.navigate(screen)
                                        },
                                        label = { Text(screen) },
                                        icon = {

                                        }
                                    )
                                }
                            }
                        }
                    ) {padding->
                        Box(modifier = Modifier.padding(padding)){
                            NavHost(navController = navController, startDestination = "list"){
                                composable("list"){ ListScreen(modifier = Modifier.padding(24.dp))}
                                composable("search"){ SearchScreen(navController = navController)}
                                composable("push"){ PushScreen() }
                                composable("details"){
                                    navController.previousBackStackEntry?.arguments?.getParcelable<Word>("WORD_KEY")?.let {
                                        DetailsScreen(word = it)
                                    }
                                }
                            }
                        }
                    }
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