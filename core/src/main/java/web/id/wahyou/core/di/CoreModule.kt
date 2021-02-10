package web.id.wahyou.core.di

import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import web.id.wahyou.core.BuildConfig.BASE_URL
import web.id.wahyou.core.BuildConfig.HOST_NAME
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
            val passphrase = SQLiteDatabase.getBytes("wahyouwebid".toCharArray())
            val factory = SupportFactory(passphrase)

            Room.databaseBuilder(
                androidContext(),
                AnimeDatabase::class.java, "Anime.db"
            ).fallbackToDestructiveMigration()
                .openHelperFactory(factory)
                .build()
        }
    }

    val networkModule = module {
        single {
            val certificatePinner = CertificatePinner.Builder()
                .add(HOST_NAME, "sha256/mRjXIIcEJSE3kJl4YNqqfOS+COj4KG3VJPSJo6ymApk=")
                .add(HOST_NAME, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
                .add(HOST_NAME, "sha256/Y9mvm0exBk1JoQ57f9Vm28jKo5lFm/woKcVxrYxu80o=")
                .build()

            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(ChuckerInterceptor(androidContext()))
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .certificatePinner(certificatePinner)
                .build()
        }
        single {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
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