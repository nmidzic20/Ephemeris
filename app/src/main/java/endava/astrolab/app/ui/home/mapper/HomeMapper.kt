package endava.astrolab.app.ui.home.mapper

import endava.astrolab.app.model.Lesson
import endava.astrolab.app.ui.component.AlertDialogViewState
import endava.astrolab.app.ui.home.HomeViewState

interface HomeMapper {
    fun toHomeViewState(lessons: List<Lesson>, alertDialogViewState: AlertDialogViewState): HomeViewState
}
