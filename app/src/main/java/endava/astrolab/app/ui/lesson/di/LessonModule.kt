package endava.astrolab.app.ui.lesson.di

import endava.astrolab.app.ui.home.HomeViewModel
import endava.astrolab.app.ui.home.mapper.HomeMapper
import endava.astrolab.app.ui.home.mapper.HomeMapperImpl
import endava.astrolab.app.ui.lesson.LessonViewModel
import endava.astrolab.app.ui.lesson.mapper.LessonMapper
import endava.astrolab.app.ui.lesson.mapper.LessonMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val lessonModule = module {
    viewModel { (lessonId: Int) ->
        LessonViewModel(
            lessonId,
            lessonRepository = get(),
            lessonMapper = get()
        )
    }
    single<LessonMapper> { LessonMapperImpl() }
}