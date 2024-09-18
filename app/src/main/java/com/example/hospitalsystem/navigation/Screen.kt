package com.example.hospitalsystem.navigation

sealed class Screen(val route: String) {
    data object SplashScreen : Screen("splashScreen")
    data object LoginScreen : Screen("loginScreen")
    data object ReceptionistScreen : Screen("receptionistScreen")
    data object CallsScreen : Screen("callsScreen")
    data object CreateCallScreen : Screen("CreateCallScreen")
    data object RequestCanceledScreen : Screen("requestCanceledScreen")
    data object ProfileScreen : Screen("profileScreen")
    data object HrHomeScreen : Screen("hrHomeScreen")
    data object EmployeeScreen : Screen("employeeScreen")
    data object AddEmployeeScreen : Screen("addEmployeeScreen")
    data object DoctorSelectionScreen : Screen("doctorSelectionScreen")
    data object CallDetailsScreen : Screen("callDetailsScreen")
    data object ReportsScreen : Screen("reportsScreen")
    data object CreateReportsScreen : Screen("createReportsScreen")
    data object ReportDetailsScreen : Screen("reportDetailsScreen")
    data object DoctorHomeScreen : Screen("doctorHomeScreen")
    data object DoctorCallsScreen : Screen("doctorCallsScreen")
    data object CasesScreen : Screen("casesScreen")
    data object ShowCaseScreen : Screen("showCaseScreen")
    data object SelectionScreen : Screen("selectionScreen")
    data object MedicalMeasurementScreen : Screen("medicalMeasurementScreen")
    data object NurseHomeScreen : Screen("nurseHomeScreen")
    data object CaseDetailsNurseScreen : Screen("caseDetailsNurseScreen")
    data object TasksScreen : Screen("tasksScreen")
    data object TaskDetailsScreen : Screen("taskDetailsScreen")
    data object AnalysisScreen : Screen("analysisScreen")
    data object ManagerScreen : Screen("managerScreen")
    data object AddMeasurementScreen : Screen("addMeasurementScreen")
}