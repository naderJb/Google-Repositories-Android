package com.nader.googlerepositoriesandroid.base.di

import android.content.Context
import com.google.gson.Gson
import com.nader.googlerepositoriesandroid.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideVoiceRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }


    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        networkConnectionInterceptor: NetworkConnectionInterceptor
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) httpClient.addInterceptor(httpLoggingInterceptor)
        httpClient.addNetworkInterceptor(networkConnectionInterceptor)
        httpClient.addInterceptor { chain ->
            val original: Request = chain.request()

            val request: Request = original.newBuilder()
                .header("x-ratelimit-limit", "500")
                .build()

            chain.proceed(request)
        }
        httpClient.readTimeout(2, TimeUnit.MINUTES)
        httpClient.writeTimeout(3, TimeUnit.MINUTES)
        httpClient.connectTimeout(3, TimeUnit.MINUTES)
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Provides
    @Singleton
    fun getGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideGsonFactory(): GsonConverterFactory = GsonConverterFactory.create()


    @Provides
    @Singleton
    fun provideNetworkConnectionInterceptor(@ApplicationContext context: Context): NetworkConnectionInterceptor =
        NetworkConnectionInterceptor(context)
}