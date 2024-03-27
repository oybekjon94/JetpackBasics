package com.oybekdev.dropdownmenu.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oybekdev.dropdownmenu.ui.theme.JetpackBasicsTheme

data class Country(val name:String)
@Composable
fun HomeScreen(){
    var selectedCountry by remember { mutableStateOf (Country("Select Country")) }
    var selectedCity by remember { mutableStateOf("select City") }
    var expandedCountry by remember { mutableStateOf(false) }
    var expandedCity by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .clickable {
                    expandedCountry = true
                }
        ){
            Text(
                text = selectedCountry.name,
                color = Color.White,
                modifier = Modifier
                    .padding(16.dp)
                    .padding(end = 16.dp)
            )
            DropdownMenu(
                expanded = expandedCountry,
                onDismissRequest = { expandedCountry = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .background(Color.White)
            ) {
                val countries = listOf(
                    Country("United States"),
                    Country("United Kingdom"),
                    Country("Japan"),
                    Country("China"),
                    Country("Korea"),
                )

                countries.forEach{
                    DropdownMenuItem(
                        text = {
                               Text(text = it.name, color = Color.Black)
                               },
                        onClick = {
                            selectedCountry = it
                            selectedCity = "Select City"
                            expandedCountry = false
                        })
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .clickable {
                    expandedCity = true
                }
        ){
            Text(
                text = selectedCity,
                color = Color.White,
                modifier = Modifier
                    .padding(16.dp)
                    .padding(end = 16.dp)
            )
            DropdownMenu(
                expanded = expandedCity,
                onDismissRequest = { expandedCity = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .background(Color.White)
            ) {
                val cities = when(selectedCountry.name){
                    "United States" -> listOf("New York","los Angeles","Chicago")
                    "United Kingdom" -> listOf("London","Manchester","Birminghim")
                    "Japan" -> listOf("Tokyo","Osaka","Kyoto")
                    "China" -> listOf("Beijing","Shanghai","Guangzhou")
                    "Korea" -> listOf("Seoul","Busan")
                    else -> listOf("Select City")
                }

                cities.forEach{
                    DropdownMenuItem(
                        text = {
                            Text(text = it, color = Color.Black)
                        },
                        onClick = {
                            selectedCity = it
                            expandedCity = false
                        })
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    JetpackBasicsTheme{
        HomeScreen()
    }
}