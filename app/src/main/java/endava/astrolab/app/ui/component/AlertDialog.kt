package endava.astrolab.app.ui.component

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import endava.astrolab.app.R

data class AlertDialogViewState(
    val show: Boolean,
    val title: String,
    val content: String,
    val confirmButtonText: String,
    val onConfirmClick: () -> Unit,
    val dismissButtonText: String?,
    val onDismissClick: (() -> Unit)?,
) {
    companion object {
        val EMPTY = AlertDialogViewState(false, "", "", "", {}, "", {})
    }
}

@Composable
fun Alert(alertDialogViewState: AlertDialogViewState) {
    if (alertDialogViewState.show) {

        AlertDialog(
            onDismissRequest = {
                alertDialogViewState.onDismissClick?.let { it() }
            },
            title = {
                Text(text = alertDialogViewState.title)
            },
            text = {
                Text(alertDialogViewState.content)
            },
            confirmButton = {
                Button(
                    onClick = alertDialogViewState.onConfirmClick
                ) {
                    Text(alertDialogViewState.confirmButtonText)
                }
            },
            dismissButton = {
                alertDialogViewState.onDismissClick?.let {
                    Button(
                        onClick = it
                    ) {
                        Text(
                            alertDialogViewState.dismissButtonText
                                ?: stringResource(R.string.cancel)
                        )
                    }
                }
            }
        )
    }
}
