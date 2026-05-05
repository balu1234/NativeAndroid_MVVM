package com.example.mvvmapp.ui.employee.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.example.mvvmapp.data.remote.model.EmployeeResponse
import com.example.customdialog.ui.common.dialog.rememberDialogManager
import com.example.customdialog.ui.common.dialog.CustomDialog
import com.example.mvvmapp.ui.employee.viewmodel.EmployeeApiViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeApiScreen() {
    val viewModel: EmployeeApiViewModel = viewModel()
    
    val employees by viewModel.employees.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)
    val error by viewModel.error.observeAsState()
    val dialogManager = rememberDialogManager()
    
    LaunchedEffect(Unit) {
        viewModel.fetchEmployees()
        kotlinx.coroutines.delay(500)
        dialogManager.showWelcomeDialog(
            screenName = "Employees (API)",
            description = "View employee data fetched from a real API. This demonstrates network integration with real-time data synchronization and error handling."
        )
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Employees (API)", 
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ) 
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1976D2)
                ),
                actions = {
                    IconButton(onClick = { viewModel.fetchEmployees() }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(
                                color = Color(0xFF1976D2)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Loading employees...",
                                color = Color(0xFF757575)
                            )
                        }
                    }
                }
                
                error != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Error",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFF44336)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = error ?: "Unknown error occurred",
                                color = Color(0xFF757575),
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = { viewModel.fetchEmployees() },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF1976D2)
                                )
                            ) {
                                Text("Retry")
                            }
                        }
                    }
                }
                
                employees.isNullOrEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "No Employees Found",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF757575)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Try refreshing to load employees",
                                color = Color(0xFF757575)
                            )
                        }
                    }
                }
                
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFFF5F5F5)
                                )
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Text(
                                        text = "API Employees (${employees.count()})",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF1976D2)
                                    )
                                    Text(
                                        text = "Fetched from remote API",
                                        fontSize = 12.sp,
                                        color = Color(0xFF757575)
                                    )
                                }
                            }
                        }
                        
                        items(employees, key = { it.id }) { employee ->
                            EmployeeApiCard(employee = employee)
                        }
                    }
                }
            }
        }
    }
    
    // Custom Dialog Overlay
    CustomDialog(
        isVisible = dialogManager.dialogState.value.isVisible,
        onDismiss = { dialogManager.hideDialog() },
        title = dialogManager.dialogState.value.title,
        message = dialogManager.dialogState.value.message,
        positiveButtonText = dialogManager.dialogState.value.positiveButtonText,
        negativeButtonText = dialogManager.dialogState.value.negativeButtonText,
        onPositiveClick = dialogManager.dialogState.value.onPositiveClick,
        onNegativeClick = dialogManager.dialogState.value.onNegativeClick,
        showIcon = dialogManager.dialogState.value.showIcon,
        iconText = dialogManager.dialogState.value.iconText
    )
}

@Composable
fun EmployeeApiCard(employee: EmployeeResponse) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = employee.employeeName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF212121)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "ID: ${employee.id}",
                        fontSize = 14.sp,
                        color = Color(0xFF757575)
                    )
                }
                
                // Active Status Badge
                Surface(
                    modifier = Modifier.wrapContentSize(),
                    color = Color(0xFF4CAF50),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = "ACTIVE",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column {
                    Text(
                        text = "Salary",
                        fontSize = 12.sp,
                        color = Color(0xFF757575)
                    )
                    Text(
                        text = "$${employee.employeeSalary}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Column {
                    Text(
                        text = "Age",
                        fontSize = 12.sp,
                        color = Color(0xFF757575)
                    )
                    Text(
                        text = "${employee.employeeAge} years",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(4.dp))
            
            if (employee.profileImage.isNotEmpty()) {
                Text(
                    text = "Profile: Available",
                    fontSize = 12.sp,
                    color = Color(0xFF1976D2)
                )
            } else {
                Text(
                    text = "Profile: No Image",
                    fontSize = 12.sp,
                    color = Color(0xFF757575)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmployeeApiCardPreview() {
    val sampleEmployee = EmployeeResponse(
        id = 1,
        employeeName = "Tiger Nixon",
        employeeSalary = "320800",
        employeeAge = "61",
        profileImage = ""
    )
    
    EmployeeApiCard(employee = sampleEmployee)
}

@Preview(showBackground = true)
@Composable
fun EmployeeApiCardWithProfilePreview() {
    val sampleEmployee = EmployeeResponse(
        id = 2,
        employeeName = "Garrett Winters",
        employeeSalary = "170750",
        employeeAge = "63",
        profileImage = "https://example.com/profile.jpg"
    )
    
    EmployeeApiCard(employee = sampleEmployee)
}
