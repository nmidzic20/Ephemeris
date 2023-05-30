package endava.astrolab.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import endava.astrolab.app.data.repository.LessonRepository
import endava.astrolab.app.mock.LessonsMock
import endava.astrolab.app.model.Lesson
import endava.astrolab.app.ui.component.AlertDialogData
import endava.astrolab.app.ui.home.mapper.HomeMapper
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val lessonRepository: LessonRepository,
    homeScreenMapper: HomeMapper,
) : ViewModel() {

    val showDialog = MutableStateFlow(false)
    private val selectedLesson = MutableStateFlow(Lesson.EMPTY)
    val _alertDialogData =
        showDialog.flatMapLatest { showDialog ->
            selectedLesson.mapLatest { selectedLesson ->
                AlertDialogData(
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
        }

    var initialAlertDialogData : AlertDialogData = AlertDialogData(false, "","","",{},"",{})


    val homeViewState: StateFlow<HomeViewState> =
        _alertDialogData
            .flatMapLatest {alertDialogData ->
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

    fun onCompletedClick(lessonId: Int) {
        println("Completed / incompleted for lesson ${lessonId}")
        viewModelScope.launch { lessonRepository.toggleCompleted(lessonId) }
    }

    fun toggleAlertDialog() {
        println("Before toggle ${showDialog.value}")
        showDialog.update { show -> !show }
        println("After toggle ${showDialog.value}")

    }

    fun onLessonLongClick(lessonId : Int) {
        println(lessonId)
        viewModelScope.launch {
            selectedLesson.update { lessonRepository.lesson(lessonId) }
            println("Selected lesson is now ${selectedLesson.value}")
            toggleAlertDialog()
            println("show dialog is now ${showDialog.value}")
            println("_alert dialog data is now ${_alertDialogData.first().show} ${_alertDialogData.first().title}")
            //alertDialogData = _alertDialogData.first()
            //println("alert dialog data is now ${alertDialogData.show} ${alertDialogData.title}")

        }
        //toggleAlertDialog()
    }
}