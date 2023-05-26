package endava.astrolab.app.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import endava.astrolab.app.mock.LessonsMock
import endava.astrolab.app.ui.theme.DarkGreen
import endava.astrolab.app.ui.theme.DarkRed
import endava.astrolab.app.ui.theme.spacing

data class LessonItemViewState(val title: String, val isCompleted: Boolean)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LessonItem(lessonItemViewState: LessonItemViewState, onClick: () -> Unit, onLongClick: () -> Unit, modifier: Modifier = Modifier) {

    val coloredSurfaceWidth = MaterialTheme.spacing.large
    var cardColor = if (lessonItemViewState.isCompleted) DarkGreen else DarkRed
    var subtitle = if (lessonItemViewState.isCompleted) "Completed" else ""

    Box(
        modifier = modifier
    ) {

        Card(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .combinedClickable(
                    onClick = onClick,
                    onLongClick = onLongClick,
                )
        ) {
            Column(
                modifier = Modifier.padding(start = coloredSurfaceWidth + MaterialTheme.spacing.medium)
            ) {
                Text(lessonItemViewState.title, fontSize = 16.sp, fontWeight = FontWeight.W700, modifier = Modifier.padding(MaterialTheme.spacing.medium))
                Text(subtitle, color = Color.Gray, modifier = Modifier.padding(start = MaterialTheme.spacing.medium))
            }
        }
        Box(
            Modifier
                .align(Alignment.TopStart)
                .fillMaxHeight()
                .width(
                    coloredSurfaceWidth
                )
                .background(cardColor)
        )
    }
}

@Preview
@Composable
private fun LessonItemPreview() {
    val lesson = LessonsMock.getLessonsList()[0]
    LessonItem(LessonItemViewState(lesson.title, lesson.isCompleted), {}, {})
}
