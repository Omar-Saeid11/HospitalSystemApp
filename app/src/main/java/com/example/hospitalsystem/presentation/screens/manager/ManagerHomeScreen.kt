package com.example.hospitalsystem.presentation.screens.manager

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.core.UserPreferences
import com.example.hospitalsystem.navigation.Screen
import com.example.hospitalsystem.presentation.composables.GridSection
import com.example.hospitalsystem.presentation.composables.TopSection

@Composable
fun ManagerHomeScreen(navController: NavController) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        TopSection(onClickProfileImg = {
            val userId = UserPreferences.getUserId()
            navController.navigate("${Screen.ProfileScreen.route}/$userId")
        })
        Spacer(modifier = Modifier.height(16.dp))
        GridSection(
            colorCard1 = 0xFF42A5F5,
            colorCard2 = 0xFF915FDC,
            colorCard3 = 0xFF5FDC89,
            colorCard4 = 0xFF26C6DA,
            colorCard5 = 0xFFFFA500,
            titleCard1 = "Cases",
            titleCard2 = "Reports",
            titleCard3 = "Tasks",
            titleCard4 = "Attendance\n -\n Leaving",
            titleCard5 = "Employee",
            iconCard1 = R.drawable.ic_cases,
            iconCard2 = R.drawable.ic_reports,
            iconCard3 = R.drawable.ic_tasks,
            iconCard4 = R.drawable.ic_attendance,
            iconCard5 = R.drawable.employee,
            onClickCard1 = { navController.navigate(Screen.CasesScreen.route) },
            onClickCard2 = { navController.navigate(Screen.ReportsScreen.route) },
            onClickCard3 = { navController.navigate(Screen.TasksScreen.route) },
            onClickCard4 = { navController.navigate(Screen.AttendanceAndLeavingScreen.route) },
            onClickCard5 = { navController.navigate(Screen.EmployeeScreen.route) },
            isCard5Visible = true,
            isPortrait = isPortrait
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Prev() {
    ManagerHomeScreen(navController = NavController((LocalContext.current)))
}