package endava.astrolab.app.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import endava.astrolab.app.ui.component.Alert
import endava.astrolab.app.ui.component.LessonItem

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
    )
}

@Composable
fun HomeScreen(
    homeViewState: HomeViewState,
    onLessonClick: (Int) -> Unit,
    onLessonLongClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val homeLessonViewStateList = homeViewState.homeLessonViewStateList
    val alertDialogDataViewState = homeViewState.alertDialogDataViewState

    Alert(alertDialogDataViewState)

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
