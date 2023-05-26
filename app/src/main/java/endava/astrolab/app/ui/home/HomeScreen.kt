package endava.astrolab.app.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import endava.astrolab.app.mock.lessons
import endava.astrolab.app.ui.component.Alert
import endava.astrolab.app.ui.component.AlertDialogData
import endava.astrolab.app.ui.component.LessonItem
import endava.astrolab.app.ui.home.mapper.HomeMapper
import endava.astrolab.app.ui.home.mapper.HomeMapperImpl

private val homeMapper: HomeMapper = HomeMapperImpl()
// multiple view states if required
val homeViewState = homeMapper.toHomeViewState(lessons)

@Composable
fun HomeRoute(
    /*onNavigateToLesson: (Int) -> Unit,
    viewModel: HomeViewModel*/
) {
    // val homeViewState: HomeViewState by viewModel.homeViewState.collectAsState()

    HomeScreen(
        homeViewState
    )
}

@Composable
fun HomeScreen(
    homeViewState: HomeViewState,
    /*onLessonItemClick: (Int) -> Unit,*/
    modifier: Modifier = Modifier
) {
    val lessons = homeViewState.homeMovieViewStateList
    val onLessonClick = { id: Int ->
        println("Open webview for lesson $id")
    }

    val showDialog = remember { mutableStateOf(false) }

    val onLessonLongClick = { id: Int ->
        println("open completion alert for lesson $id")
        showDialog.value = true
    }

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
    Alert(showDialog = showDialog, alertDialogData = alertDialogData)

    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        lessons.forEach { lesson ->
            LessonItem(lesson.lessonItemViewState, { onLessonClick(lesson.id) }, { onLessonLongClick(lesson.id) }, Modifier.height(90.dp))
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(homeViewState = homeViewState)
}
