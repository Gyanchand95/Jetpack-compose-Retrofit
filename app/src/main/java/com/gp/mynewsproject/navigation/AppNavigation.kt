package com.gp.mynewsproject.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gp.mynewsproject.screens.AppSplashScreen
import com.gp.mynewsproject.screens.MainScreen
import com.gp.mynewsproject.screens.MasterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.name ){
        composable(AppScreens.SplashScreen.name){
            AppSplashScreen(navController = navController)
        }

        composable(AppScreens.MainScreen.name){
            val viewModel = hiltViewModel<MasterViewModel>()
            MainScreen(navController = navController,viewModel)
        }
    }
}