package com.example.customdialog.ui.common.dialog

import androidx.compose.runtime.*
import kotlinx.coroutines.delay

data class DialogData(
    val isVisible: Boolean = false,
    val title: String = "",
    val message: String = "",
    val positiveButtonText: String = "OK",
    val negativeButtonText: String? = null,
    val onPositiveClick: (() -> Unit)? = null,
    val onNegativeClick: (() -> Unit)? = null,
    val showIcon: Boolean = true,
    val iconText: String = "ℹ️"
)

class DialogManager {
    private val _dialogState = mutableStateOf(DialogData())
    val dialogState: State<DialogData> = _dialogState

    fun showWelcomeDialog(screenName: String, description: String) {
        _dialogState.value = DialogData(
            isVisible = true,
            title = "Welcome to $screenName",
            message = description,
            positiveButtonText = "Got it!",
            showIcon = true,
            iconText = "👋"
        )
    }

    fun showInfoDialog(title: String, message: String) {
        _dialogState.value = DialogData(
            isVisible = true,
            title = title,
            message = message,
            positiveButtonText = "OK",
            showIcon = true,
            iconText = "ℹ️"
        )
    }

    fun showFeatureDialog(featureName: String, featureDescription: String) {
        _dialogState.value = DialogData(
            isVisible = true,
            title = "Feature: $featureName",
            message = featureDescription,
            positiveButtonText = "Explore",
            negativeButtonText = "Skip",
            showIcon = true,
            iconText = "🚀"
        )
    }

    fun hideDialog() {
        _dialogState.value = _dialogState.value.copy(isVisible = false)
    }

    fun showCustomDialog(
        title: String,
        message: String,
        positiveButtonText: String = "OK",
        negativeButtonText: String? = null,
        onPositiveClick: (() -> Unit)? = null,
        onNegativeClick: (() -> Unit)? = null,
        showIcon: Boolean = true,
        iconText: String = "ℹ️"
    ) {
        _dialogState.value = DialogData(
            isVisible = true,
            title = title,
            message = message,
            positiveButtonText = positiveButtonText,
            negativeButtonText = negativeButtonText,
            onPositiveClick = onPositiveClick,
            onNegativeClick = onNegativeClick,
            showIcon = showIcon,
            iconText = iconText
        )
    }
}

@Composable
fun rememberDialogManager(): DialogManager {
    return remember { DialogManager() }
}
