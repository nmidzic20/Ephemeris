package endava.astrolab.app.ui.component

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import endava.astrolab.app.R
import kotlinx.coroutines.flow.StateFlow

data class AlertDialogData(
    val show : Boolean,
    val title: String,
    val content: String,
    val confirmButtonText: String,
    val onConfirmClick: () -> Unit,
    val dismissButtonText: String?,
    val onDismissClick: (() -> Unit)?,
)

@Composable
fun Alert(/*showDialog: MutableState<Boolean>, */alertDialogData: AlertDialogData) {
    if (alertDialogData.show) {

        AlertDialog(
            onDismissRequest = {
                //toggleDialog()
            },
            title = {
                Text(text = alertDialogData.title)
            },
            text = {
                Text(alertDialogData.content)
            },
            confirmButton = {
                Button(
                    onClick = alertDialogData.onConfirmClick
                ) {
                    Text(alertDialogData.confirmButtonText)
                }
            },
            dismissButton = {
                alertDialogData.onDismissClick?.let {
                    Button(
                        onClick = it
                    ) {
                        Text(
                            alertDialogData.dismissButtonText
                                ?: stringResource(R.string.cancel)
                        )
                    }
                }
            }
        )
    }
}


