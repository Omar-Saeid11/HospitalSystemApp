package com.example.hospitalsystem.presentation.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import com.example.hospitalsystem.R
import com.example.hospitalsystem.core.NetworkMonitor

import com.example.hospitalsystem.core.UserPreferences
import com.example.hospitalsystem.presentation.viewmodels.profile.ProfileViewModel
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Preview(showBackground = true)
@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        delay(1000)
        viewModel.getProfile(UserPreferences.getUserId())
    }

    DisposableEffect(Unit) {
        val networkMonitor = NetworkMonitor(context)
        networkMonitor.isConnected.observe(context as LifecycleOwner) { isConnected ->
            if (isConnected) {
                viewModel.getProfile(UserPreferences.getUserId())
            }
        }
        onDispose {
            networkMonitor.unregisterCallback()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF00BFA5))
    ) {
        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Loading...",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }

            uiState.profile != null -> {
                val profile = uiState.profile?.data

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "My Profile",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Card(
                            shape = RoundedCornerShape(24.dp),
                            elevation = CardDefaults.cardElevation(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 60.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = 60.dp,
                                        start = 16.dp,
                                        end = 16.dp,
                                        bottom = 16.dp
                                    )
                            ) {
                                profile?.let {
                                    Text(
                                        text = "${it.firstName} ${it.lastName}",
                                        color = Color(0xFF22C7B8),
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(16.dp)
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    ProfileItem(
                                        icon = R.drawable.ic_specialist,
                                        text = it.specialist ?: ""
                                    )
                                    ProfileItem(icon = R.drawable.ic_gender, text = it.gender ?: "")
                                    ProfileItem(
                                        icon = R.drawable.ic_calendar,
                                        text = it.birthday ?: ""
                                    )
                                    ProfileItem(
                                        icon = R.drawable.ic_location,
                                        text = it.address ?: ""
                                    )
                                    ProfileItem(icon = R.drawable.ic_status, text = it.status ?: "")
                                    ProfileItem(icon = R.drawable.ic_email, text = it.email ?: "")
                                    ProfileItem(icon = R.drawable.ic_phone, text = it.mobile ?: "")
                                }
                            }
                        }

                        // Profile Image
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape)
                                .background(Color.Transparent) // Offset to overlap with the card
                                .border(
                                    width = 4.dp,
                                    brush = Brush.radialGradient(
                                        colors = listOf(Color(0xFF04302C), Color(0xFF00BFA5))
                                    ),
                                    shape = CircleShape
                                )
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.user), // Replace with your image resource
                                contentDescription = "Profile Image",
                                modifier = Modifier
                                    .size(90.dp)
                                    .clip(CircleShape)
                            )
                        }
                    }
                }
            }

            uiState.error != null -> {
                // Handle error state
                Text(
                    text = "Error loading profile: ${uiState.error}",
                    color = Color.Red,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}


@Composable
fun ProfileItem(icon: Int, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color(0xFF22C7B8),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}