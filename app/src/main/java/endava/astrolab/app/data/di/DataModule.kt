package endava.astrolab.app.data.di

import endava.astrolab.app.data.repository.DailyImageRepository
import endava.astrolab.app.data.repository.DailyImageRepositoryImpl
import endava.astrolab.app.data.repository.FakeLessonRepositoryImpl
import endava.astrolab.app.data.repository.LessonRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    single<LessonRepository> {
        FakeLessonRepositoryImpl(bgDispatcher = Dispatchers.IO)
    }
    single<DailyImageRepository> {
        DailyImageRepositoryImpl(dailyImageService = get(), bgDispatcher = Dispatchers.IO)
    }
}
