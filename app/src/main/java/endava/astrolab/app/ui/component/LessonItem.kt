package endava.astrolab.app.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import endava.astrolab.app.ui.theme.spacing

data class Lesson(val title: String, val completed: Boolean)
val lessons = listOf(
    Lesson("Lesson 1", false),
    Lesson("Lesson 2", true),
    Lesson("Lesson 3", true),
    Lesson("Lesson 4", false),
    Lesson("Lesson 5", false),
    Lesson("Lesson 6", false),
)

@Composable
fun LessonList(lessons: List<Lesson>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        lessons.forEach { student ->
            LessonItem(student, Modifier.height(110.dp))
        }
    }
}
@Composable
fun LessonItem(lesson: Lesson, modifier: Modifier = Modifier) {

    val coloredSurfaceWidth = MaterialTheme.spacing.large
    var cardColor = if (lesson.completed) Color(0xFF013220) else Color(0xFF8b0000)
    var subtitle = if (lesson.completed) "Completed" else ""

    Box(
        modifier = modifier
    ) {

        Card(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ) {
            Column(
                modifier = Modifier.padding(start = coloredSurfaceWidth + MaterialTheme.spacing.medium)
            ) {
                Text(lesson.title, fontSize = 25.sp, fontWeight = FontWeight.W700, modifier = Modifier.padding(MaterialTheme.spacing.medium))
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
private fun FavoriteButtonPreview() {
    LessonList(lessons)
    // LessonItem(lessons[0], Modifier.height(110.dp))
}
