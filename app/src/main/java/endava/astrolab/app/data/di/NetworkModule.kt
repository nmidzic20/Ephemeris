package endava.astrolab.app.data.di

import android.util.Log
import endava.astrolab.app.data.network.DailyImageService
import endava.astrolab.app.data.network.DailyImageServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single<DailyImageService> { DailyImageServiceImpl(client = get()) }
    single {
        HttpClient(Android) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("HTTP", message)
                    }
                }
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                        encodeDefaults = false
                    }
                )
            }
        }
    }
}
