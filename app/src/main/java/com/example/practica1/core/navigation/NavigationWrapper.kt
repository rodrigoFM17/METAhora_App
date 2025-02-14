package com.example.practica1.core.navigation

import android.app.Activity
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
import com.example.practica1.printCompletedGoal.presentation.PrintCompletedGoalScreen
import com.example.practica1.printCompletedGoal.presentation.PrintCompletedGoalViewModel
import com.example.practica1.register.presentation.RegisterScreen
import com.example.practica1.register.presentation.RegisterViewModel
import com.example.practica1.storage.GoalCompletedStorage
import com.example.practica1.storage.UserStorage

@Composable
fun NavigationWrapper (activity: Activity) {
    val navController = rememberNavController()
    val userStorage = UserStorage(LocalContext.current)
    val goalStorage = GoalCompletedStorage(LocalContext.current)

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
        composable<Register> { RegisterScreen(RegisterViewModel({navController.navigate(Login)})) }
        composable<Home> {
            HomeScreen(
                HomeViewModel(userStorage, goalStorage),
                { navController.navigate(AddGoal)},
                { navController.navigate(PrintCompletedGoal)}
            )
        }
        composable<AddGoal> {
            AddGoalScreen( AddGoalViewModel( { navController.navigate(Home) }), userStorage )
        }
        composable<PrintCompletedGoal> {
            PrintCompletedGoalScreen( PrintCompletedGoalViewModel(goalStorage, activity))
        }
    }

}