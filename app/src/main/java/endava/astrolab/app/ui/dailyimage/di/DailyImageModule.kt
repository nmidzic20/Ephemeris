package endava.astrolab.app.ui.dailyimage.di

import endava.astrolab.app.ui.dailyimage.DailyImageViewModel
import endava.astrolab.app.ui.dailyimage.mapper.DailyImageMapper
import endava.astrolab.app.ui.dailyimage.mapper.DailyImageMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dailyImageModule = module {
    viewModel {
        DailyImageViewModel(

        )
    }
    single<DailyImageMapper> { DailyImageMapperImpl() }
}