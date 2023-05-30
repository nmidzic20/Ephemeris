package endava.astrolab.app.ui.home.di

import endava.astrolab.app.ui.home.HomeViewModel
import endava.astrolab.app.ui.home.mapper.HomeMapper
import endava.astrolab.app.ui.home.mapper.HomeMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel {
        HomeViewModel(
            lessonRepository = get(),
            homeScreenMapper = get()
        )
    }
    single<HomeMapper> { HomeMapperImpl() }
}
