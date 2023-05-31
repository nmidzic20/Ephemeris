package endava.astrolab.app

import android.app.Application
import android.util.Log
import endava.astrolab.app.data.di.dataModule
import endava.astrolab.app.data.di.databaseModule
import endava.astrolab.app.data.di.networkModule
import endava.astrolab.app.ui.dailyimage.di.dailyImageModule
import endava.astrolab.app.ui.home.di.homeModule
import endava.astrolab.app.ui.lesson.di.lessonModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AstrolabApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AstrolabApp)
            modules(
                dataModule,
                databaseModule,
                networkModule,
                lessonModule,
                dailyImageModule,
                homeModule
            )
        }

        Log.d("Astrolab", "App started")
    }
}
