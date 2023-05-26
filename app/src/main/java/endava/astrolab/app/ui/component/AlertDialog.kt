package endava.astrolab.app.ui.component

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import endava.astrolab.app.R

data class AlertDialogData(
    val title: String,
    val content: String,
    val confirmButtonText: String,
    val onConfirmClick: () -> Unit,
    val dismissButtonText: String?,
    val onDismissClick: (() -> Unit)?,
)

@Composable
fun Alert(showDialog: MutableState<Boolean>, alertDialogData: AlertDialogData) {
    if (showDialog.value) {

        AlertDialog(
            onDismissRequest = {
                showDialog.value = false
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

@Preview
@Composable
fun AlertDialogPreview() {
    val showDialog = remember { mutableStateOf(false) }

    val alertDialogData = AlertDialogData(
        "Title",
        "This is content",
        "OK",
        {
            showDialog.value = false
        },
        null,
        null
    )

    Button(onClick = {
        showDialog.value = true
    }) {
        Text("Click me")
    }

    Alert(showDialog, alertDialogData)
}
