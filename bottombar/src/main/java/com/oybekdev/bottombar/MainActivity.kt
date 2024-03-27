package com.oybekdev.bottombar

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.oybekdev.bottombar.ui.theme.GreenJC
import com.oybekdev.bottombar.ui.theme.JetpackBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackBasicsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyBottomAppBar()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomAppBar() {
    val navigationController = rememberNavController()
    val context = LocalContext.current.applicationContext
    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = GreenJC
            ) {
                IconButton(onClick = {
                    selected.value = Icons.Default.Home
                    navigationController.navigate(Screens.Home.screens) {
                        popUpTo(0)
                    }
                }, modifier = Modifier.weight(1f)) {
                    Icon(
                        Icons.Default.Home,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Home) Color.White else Color.DarkGray
                    )
                }
                IconButton(onClick = {
                    selected.value = Icons.Default.Search
                    navigationController.navigate(Screens.Search.screens) {
                        popUpTo(0)
                    }
                }, modifier = Modifier.weight(1f)) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Search) Color.White else Color.DarkGray
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    FloatingActionButton(onClick = {
                        Toast.makeText(context, "Open Bottom Sheet", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = GreenJC)
                    }
                }
            }
        }
    ) {paddingValues ->
        NavHost(navController = navigationController,
                startDestination = Screens.Home.screens,
            modifier = Modifier.padding(paddingValues)
            ){
            composable(Screens.Home.screens){ Home()}
            composable(Screens.Search.screens){ Search() }
            composable(Screens.Notification.screens){ Notification() }
            composable(Screens.Profile.screens){ Profile() }
        }
    }
}

@Preview
@Composable
fun MyBottomBarPreview(){
    JetpackBasicsTheme {
        MyBottomAppBar()
    }
}