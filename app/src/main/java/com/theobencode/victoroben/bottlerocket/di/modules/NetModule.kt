package com.theobencode.victoroben.bottlerocket.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.theobencode.victoroben.bottlerocket.common.Constants.Companion.APP_DEBUG
import com.theobencode.victoroben.bottlerocket.common.Constants.Companion.BASE_URL
import com.theobencode.victoroben.bottlerocket.common.RestApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Provides dependencies for networking
 */
@Module
class NetModule {

    companion object {
        const val HTTP_REQUEST_TIMEOUT = 60L
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): RestApiService = retrofit.create(RestApiService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient,
                        converterFactory: GsonConverterFactory,
                        adapterFactory: RxJava2CallAdapterFactory): Retrofit {

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(adapterFactory)
                .build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()

        // Set log level for Debug or Release
        if (APP_DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        val builder = OkHttpClient.Builder()
        builder.connectTimeout(HTTP_REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(HTTP_REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .followRedirects(true)
                .addInterceptor(loggingInterceptor)

        return builder.build()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)


    @Provides
    @Singleton
    fun providesRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()
}
