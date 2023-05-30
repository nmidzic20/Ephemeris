package endava.astrolab.app.ui.home.mapper

import endava.astrolab.app.model.Lesson
import endava.astrolab.app.ui.component.AlertDialogData
import endava.astrolab.app.ui.home.HomeViewState

interface HomeMapper {
    fun toHomeViewState(lessons: List<Lesson>, alertDialogData : AlertDialogData): HomeViewState
}
