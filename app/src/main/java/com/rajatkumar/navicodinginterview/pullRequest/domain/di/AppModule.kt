package com.rajatkumar.navicodinginterview.pullRequest.domain.di

import com.rajatkumar.navicodinginterview.pullRequest.data.PullRepositoryImpl
import com.rajatkumar.navicodinginterview.common.network.BasicAuthInterceptor
import com.rajatkumar.navicodinginterview.pullRequest.data.network.GithubAPI
import com.rajatkumar.navicodinginterview.common.domain.Constants.BASE_URL
import com.rajatkumar.navicodinginterview.pullRequest.domain.PullRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(BasicAuthInterceptor())
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun getGithubService(retrofit: Retrofit): GithubAPI = retrofit.create(GithubAPI::class.java)

    @Singleton
    @Provides
    fun providePullRequestRepository(githubAPI: GithubAPI): PullRepository = PullRepositoryImpl(githubAPI)
}
