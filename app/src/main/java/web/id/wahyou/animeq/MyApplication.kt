package web.id.wahyou.animeq

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import web.id.wahyou.animeq.di.useCaseModule
import web.id.wahyou.animeq.di.viewModelModule
import web.id.wahyou.core.di.CoreModule.databaseModule
import web.id.wahyou.core.di.CoreModule.networkModule
import web.id.wahyou.core.di.CoreModule.repositoryModule

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}