package com.example.hospitalsystem.application.navigation

sealed class Screen(val route: String) {
    data object SplashScreen : Screen("splashScreen")
    data object LoginScreen : Screen("loginScreen")
    data object RegisterScreen : Screen("registerScreen")
    data object ReceptionistScreen : Screen("receptionistScreen")
    data object CallsScreen : Screen("callsScreen")
    data object CreateCallScreen : Screen("CreateCallScreen")
    data object RequestCanceledScreen : Screen("requestCanceledScreen")
    data object ProfileScreen : Screen("profileScreen")
    data object HrHomeScreen : Screen("hrHomeScreen")
    data object EmployeeScreen : Screen("employeeScreen")
    data object AddEmployeeScreen:Screen("addEmployeeScreen")
}