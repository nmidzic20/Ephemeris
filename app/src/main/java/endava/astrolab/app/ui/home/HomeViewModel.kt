package endava.astrolab.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import endava.astrolab.app.data.repository.LessonRepository
import endava.astrolab.app.model.Lesson
import endava.astrolab.app.ui.component.AlertDialogViewState
import endava.astrolab.app.ui.home.mapper.HomeMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val lessonRepository: LessonRepository,
    homeScreenMapper: HomeMapper,
) : ViewModel() {

    private val showDialog = MutableStateFlow(false)
    private val selectedLesson = MutableStateFlow(Lesson.EMPTY)
    private var initialAlertDialogData: AlertDialogViewState = AlertDialogViewState.EMPTY

    private val alertDialogViewState: StateFlow<AlertDialogViewState> =
        showDialog.flatMapLatest { showDialog ->
            selectedLesson.mapLatest { selectedLesson ->
                AlertDialogViewState(
                    showDialog,
                    selectedLesson.title,
                    "Lesson ${selectedLesson.id}",
                    "Mark as ${if (selectedLesson.isCompleted) "incomplete" else "complete"}",
                    {
                        onCompletedClick(selectedLesson.id)
                        toggleAlertDialog()
                    },
                    "Cancel",
                    {
                        toggleAlertDialog()
                    },
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(1000),
            initialValue = initialAlertDialogData,
        )

    val homeViewState: StateFlow<HomeViewState> =
        alertDialogViewState
            .flatMapLatest { alertDialogData ->
                lessonRepository
                    .lessons()
                    .map { lessons ->
                        homeScreenMapper.toHomeViewState(lessons, alertDialogData)
                    }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(1000),
                initialValue = HomeViewState(emptyList(), initialAlertDialogData),
            )

    fun onLessonLongClick(lessonId: Int) {
        viewModelScope.launch {
            selectedLesson.update { lessonRepository.lesson(lessonId) }
        }
        toggleAlertDialog()
    }

    private fun onCompletedClick(lessonId: Int) {
        viewModelScope.launch { lessonRepository.toggleCompleted(lessonId) }
    }

    private fun toggleAlertDialog() {
        showDialog.update { show -> !show }
    }
}
