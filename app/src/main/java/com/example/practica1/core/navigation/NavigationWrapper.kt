package com.example.practica1.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practica1.addGoal.presentation.AddGoalScreen
import com.example.practica1.addGoal.presentation.AddGoalViewModel
import com.example.practica1.home.presentation.HomeScreen
import com.example.practica1.home.presentation.HomeViewModel
import com.example.practica1.login.presentation.LoginScreen
import com.example.practica1.login.presentation.LoginViewModel
import com.example.practica1.register.presentation.RegisterScreen
import com.example.practica1.register.presentation.RegisterViewModel
import com.example.practica1.storage.UserStorage

@Composable
fun NavigationWrapper () {
    val navController = rememberNavController()
    val userStorage = UserStorage(LocalContext.current)

    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            LoginScreen(
                LoginViewModel(
                    navigateToHome = {navController.navigate(Home)},
                    userStorage = userStorage
                ),
                {navController.navigate(Register) }
            )
        }
        composable<Register> { RegisterScreen(RegisterViewModel()) }
        composable<Home> {
            HomeScreen(
                HomeViewModel(userStorage),
                { navController.navigate(AddGoal)}
            )
        }
        composable<AddGoal> {
            AddGoalScreen( AddGoalViewModel( { navController.navigate(Home) }) )
        }
    }

}