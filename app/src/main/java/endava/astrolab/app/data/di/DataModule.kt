package endava.astrolab.app.data.di

import endava.astrolab.app.data.database.AstrolabDatabase
import endava.astrolab.app.data.database.LessonDAO
import endava.astrolab.app.data.repository.DailyImageRepository
import endava.astrolab.app.data.repository.DailyImageRepositoryImpl
import endava.astrolab.app.data.repository.FakeLessonRepositoryImpl
import endava.astrolab.app.data.repository.LessonRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    single<LessonDAO> {
        val database = get<AstrolabDatabase>()
        database.lessonDao()
    }
    single<LessonRepository> {
        FakeLessonRepositoryImpl(lessonDao = get(), bgDispatcher = Dispatchers.IO)
    }
    single<DailyImageRepository> {
        DailyImageRepositoryImpl(dailyImageService = get(), bgDispatcher = Dispatchers.IO)
    }
}
