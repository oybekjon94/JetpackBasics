package com.oybekdev.bottombar

sealed class Screens(val screens: String){
     object Home: Screens("home")
     object Search: Screens("search")
     object Notification: Screens("notification")
     object Profile: Screens("profile")
}