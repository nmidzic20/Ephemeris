package endava.astrolab.app.data.di

import androidx.room.Room
import endava.astrolab.app.data.database.AstrolabDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val APP_DATABASE_NAME = "astrolab_db.db"

val databaseModule = module {
    single {
        Room
            .databaseBuilder(
            androidApplication(),
            AstrolabDatabase::class.java,
            APP_DATABASE_NAME,
        )
            .createFromAsset("astrolab_db.db")
            .build()
    }
}