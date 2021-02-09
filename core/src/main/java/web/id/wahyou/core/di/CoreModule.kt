package web.id.wahyou.core.di

import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import web.id.wahyou.core.data.network.ApiService
import web.id.wahyou.core.data.repository.DataRepository
import web.id.wahyou.core.data.repository.local.LocalDataSource
import web.id.wahyou.core.data.repository.local.database.AnimeDatabase
import web.id.wahyou.core.data.repository.remote.RemoteDataSource
import web.id.wahyou.core.domain.repository.Repository
import web.id.wahyou.core.utils.AppExecutors
import java.util.concurrent.TimeUnit

object CoreModule {

    val databaseModule = module {
        factory { get<AnimeDatabase>().animeDao() }
        single {
            Room.databaseBuilder(
                androidContext(),
                AnimeDatabase::class.java, "Anime.db"
            ).fallbackToDestructiveMigration().build()
        }
    }

    val networkModule = module {
        single {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(ChuckerInterceptor(androidContext()))
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
        }
        single {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://kitsu.io/api/edge/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
            retrofit.create(ApiService::class.java)
        }
    }

    val repositoryModule = module {
        single {
            LocalDataSource(
                get()
            )
        }
        single { RemoteDataSource(get()) }
        factory { AppExecutors() }
        single<Repository> {
            DataRepository(
                get(),
                get(),
                get()
            )
        }
    }
}