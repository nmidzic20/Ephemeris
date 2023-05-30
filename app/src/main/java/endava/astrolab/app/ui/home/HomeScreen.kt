package endava.astrolab.app.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import endava.astrolab.app.mock.LessonsMock
import endava.astrolab.app.ui.component.Alert
import endava.astrolab.app.ui.component.AlertDialogData
import endava.astrolab.app.ui.component.LessonItem
import endava.astrolab.app.ui.home.mapper.HomeMapper
import endava.astrolab.app.ui.home.mapper.HomeMapperImpl


@Composable
fun HomeRoute(
    onNavigateToLesson: (Int) -> Unit,
    viewModel: HomeViewModel
) {
    val homeViewState: HomeViewState by viewModel.homeViewState.collectAsState()

    HomeScreen(
        homeViewState = homeViewState,
        onLessonClick = onNavigateToLesson,
        onLessonLongClick = viewModel::onLessonLongClick,
        //onCompletedClick = viewModel::onCompletedClick
    )
}

@Composable
fun HomeScreen(
    homeViewState: HomeViewState,
    onLessonClick: (Int) -> Unit,
    onLessonLongClick: (Int) -> Unit,
    //onCompletedClick : (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val homeLessonViewStateList = homeViewState.homeLessonViewStateList
    val alertData = homeViewState.alertDialogData

    /*val showDialog = remember { mutableStateOf(false) }
    val selectedLesson = remember { mutableStateOf(LessonsMock.getLessonsList()[0]) }

    var alertDialogData = AlertDialogData(
        showDialog,
        selectedLesson.value.title,
        "Lesson ${selectedLesson.value.id}",
        "Mark as ${if (selectedLesson.value.isCompleted) "incomplete" else "complete"}",
        {
            onCompletedClick(selectedLesson.value.id)
            showDialog.value = false
        },
        "Cancel",
        {
            showDialog.value = false
        },
    )

    val onLessonLongClick = { id: Int ->
        selectedLesson.value = LessonsMock.getLessonsList().first { lesson -> lesson.id == id }
        showDialog.value = true
    }*/

    //Alert(showDialog = showDialog, alertDialogData = alertDialogData)
    Alert(alertDialogData = alertData)

    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        homeLessonViewStateList.forEach { lesson ->
            LessonItem(
                lesson.lessonItemViewState,
                { onLessonClick(lesson.id) },
                { onLessonLongClick(lesson.id) },
                Modifier.height(80.dp)
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    val onLessonClick = { id: Int ->
        println("Open webview for lesson $id")
    }
    val homeMapper: HomeMapper = HomeMapperImpl()
    //val homeViewState = homeMapper.toHomeViewState(LessonsMock.getLessonsList())

    //HomeScreen(homeViewState = homeViewState, onLessonClick, {}, {})
}
