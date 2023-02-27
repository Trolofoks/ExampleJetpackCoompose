package com.honey.examplejetpackcoompose.secondexample.productdetails

import android.net.wifi.aware.Characteristics
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val brownGreyColor = Color(0xFF959595)
val whiteTwo = Color(0xFFf9f9f9)
val veryLightPink = Color(0xFFeaeaea)

@Composable
fun SubTitle5(text: String, modifier: Modifier = Modifier) = Text(
    modifier = modifier,
    text = text,
    style = TextStyle(color = Color.Black, fontSize = 14.sp, fontWeight = FontWeight.Normal)
)

@Composable
fun Caption(text: String, modifier: Modifier = Modifier) = Text(
    modifier = modifier,
    text = text,
    style = TextStyle(color = brownGreyColor, fontSize = 12.sp, fontWeight = FontWeight.Normal)
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductScreen(
    productViewModel: ProductViewModel = ProductViewModel(),
) {
    val sku = productViewModel.sku.collectAsState()
    val title = productViewModel.title.collectAsState()

    LazyColumn(
        content = {
            //toolbar
            stickyHeader {
                Toolbar()
            }
            item { ImageHeader() }
            item {
                Text(
                    text = sku.value, modifier = Modifier.padding(top = 24.dp, start = 16.dp),
                    style = TextStyle(
                        color = brownGreyColor,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                )
            }
            item {
                Text(
                    text = title.value,
                    modifier = Modifier
                        .padding(top = 4.dp, start = 16.dp, end = 24.dp),
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp
                    )
                )
            }
            item { RatingRowView() }
            item { PriceView(productViewModel = productViewModel) }
            item { CountView(productViewModel = productViewModel) }
            item { HeaderView(height = 68.dp, title = "Способы получения") }
            item { DeliveryPickupView(productViewModel = productViewModel) }
            item { HeaderView(height = 68.dp, title = "Характеристики") }
            item { CharacteristicsView(productViewModel = productViewModel) }

        },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun CharacteristicsView(productViewModel: ProductViewModel) {
    val characteristics = productViewModel.characteristics.collectAsState()

    Column(modifier = Modifier.fillMaxWidth()) {
        characteristics.value.map { CharacteristicsCell(model = it) }
    }
}

@Composable
fun CharacteristicsCell(model: CharacteristicsModel) {
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 24.dp)
                .height(68.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = model.title, modifier = Modifier.weight(0.6f), style = TextStyle(color = brownGreyColor))
            Text(text = model.value, modifier = Modifier
                .weight(0.4f)
                .padding(8.dp), style = TextStyle(color = Black))
        }
    }
    Divider(color = veryLightPink)
}

@Composable
fun DeliveryPickupView(productViewModel: ProductViewModel) {
    val pickupStoresCount = productViewModel.pickupStoresCount.collectAsState()

    Row(
        modifier = Modifier.padding(start = 16.dp, end = 24.dp, top = 22.dp, bottom = 22.dp)
    ) {
        Box(
            modifier = Modifier
                .background(color = whiteTwo)
                .size(24.dp)
        )
        Column(
            Modifier.padding(start = 24.dp)
        ) {
            SubTitle5(text = "Доставка * Завтра, 25 июля")
            Caption(text = "На складе 112шт.", Modifier.padding(top = 2.dp))
            if (pickupStoresCount.value > 0) {
                SubTitle5(text = "Самовывоз * Сегодня", Modifier.padding(top = 16.dp))
                Caption(
                    text = "Доступно в ${pickupStoresCount.value} магазинах",
                    Modifier.padding(top = 2.dp)
                )
            }

        }
    }
}

@Composable
fun HeaderView(height: Dp, title: String) {
    Box(
        modifier =
        Modifier
            .fillMaxWidth()
            .height(height = height)
            .background(color = whiteTwo)
            .padding(start = 16.dp, bottom = 16.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Text(text = title, style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 16.sp))
    }
}

@Composable
fun PriceView(productViewModel: ProductViewModel) {
    val itemsInCart = productViewModel.itemsInCart.collectAsState()

    Row(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "13 686,00", modifier = Modifier.padding(start = 16.dp),
            style = TextStyle(color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Medium)
        )
        Text(
            "₽/шт.", modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp), style = TextStyle(
                color = brownGreyColor, fontSize = 12.sp
            )
        )

        if (itemsInCart.value == 0) {
            Button(
                onClick = {
                    productViewModel.addToCart()
                },
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.background,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .height(48.dp)
                    .width(160.dp)
                    .padding(end = 24.dp),
            ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text(
                        "В корзину",
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        )
                    )
                }
            }
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(48.dp)
                    .width(160.dp)
                    .padding(end = 24.dp)
            ) {
                Text("${itemsInCart.value}")
            }
        }
    }
}

@Composable
fun CountView(productViewModel: ProductViewModel) {
    val availableCount = productViewModel.availableCount.collectAsState()
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp)
            .padding(end = 24.dp, start = 16.dp)
    ) {
        Box(
            Modifier
                .background(color = brownGreyColor)
                .size(24.dp)
        )
        Column(
            Modifier
                .weight(1f)
                .padding(horizontal = 24.dp)
        ) {
            Text(text = "Рассчитать колличество", style = TextStyle(color = Black))
            Text(
                text = "В наличии ${availableCount.value}",
                style = TextStyle(color = brownGreyColor, fontSize = 12.sp)
            )
        }
        Box(
            Modifier
                .background(color = brownGreyColor)
                .size(24.dp)
        )
    }

}

@Composable
fun RatingRowView() {
    Box(
        modifier = Modifier
            .background(color = White)
            .height(52.dp)
            .fillMaxWidth()
    )
}

@Composable
fun ImageHeader() {
    Box(
        modifier = Modifier
            .background(color = White)
            .height(300.dp)
            .fillMaxWidth()
    )
}

@Composable
fun Toolbar() {
    Row(
        modifier = Modifier
            .height(44.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background)
    ) {
        Text(text = "Back", modifier = Modifier.weight(1f))
        Text(text = "Menu")
    }
}
// https://youtu.be/ACKz8_uoF6A?list=PL_RkZ4J60MDn4y00uF4sslWUdYMHEDM_6&t=1764