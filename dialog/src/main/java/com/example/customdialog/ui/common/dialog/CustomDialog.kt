package com.example.customdialog.ui.common.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDialog(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    title: String,
    message: String,
    positiveButtonText: String = "OK",
    negativeButtonText: String? = null,
    onPositiveClick: (() -> Unit)? = null,
    onNegativeClick: (() -> Unit)? = null,
    showIcon: Boolean = true,
    iconText: String = "ℹ️"
) {
    if (isVisible) {
        Dialog(onDismissRequest = onDismiss) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (showIcon) {
                        Text(
                            text = iconText,
                            fontSize = 48.sp,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                    
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1976D2),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    
                    Text(
                        text = message,
                        fontSize = 16.sp,
                        color = Color(0xFF424242),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 24.dp)
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        if (negativeButtonText != null) {
                            Button(
                                onClick = {
                                    onNegativeClick?.invoke()
                                    onDismiss()
                                },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF757575)
                                )
                            ) {
                                Text(
                                    text = negativeButtonText,
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                            }
                        }
                        
                        Button(
                            onClick = {
                                onPositiveClick?.invoke()
                                onDismiss()
                            },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF1976D2)
                            )
                        ) {
                            Text(
                                text = positiveButtonText,
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WelcomeDialog(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    screenName: String,
    description: String
) {
    CustomDialog(
        isVisible = isVisible,
        onDismiss = onDismiss,
        title = "Welcome to $screenName",
        message = description,
        positiveButtonText = "Got it!",
        showIcon = true,
        iconText = "👋"
    )
}

@Composable
fun InfoDialog(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    title: String,
    message: String
) {
    CustomDialog(
        isVisible = isVisible,
        onDismiss = onDismiss,
        title = title,
        message = message,
        positiveButtonText = "OK",
        showIcon = true,
        iconText = "ℹ️"
    )
}

@Composable
fun FeatureDialog(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    featureName: String,
    featureDescription: String
) {
    CustomDialog(
        isVisible = isVisible,
        onDismiss = onDismiss,
        title = "Feature: $featureName",
        message = featureDescription,
        positiveButtonText = "Explore",
        negativeButtonText = "Skip",
        showIcon = true,
        iconText = "🚀"
    )
}
