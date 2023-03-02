package com.erenalparslan.trytesting.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.erenalparslan.trytesting.R
import com.erenalparslan.trytesting.api.RetrofitApi
import com.erenalparslan.trytesting.dao.ArtDao
import com.erenalparslan.trytesting.db.ArtDatabase
import com.erenalparslan.trytesting.repo.ArtRepository
import com.erenalparslan.trytesting.repo.ArtRepositoryInterface
import com.erenalparslan.trytesting.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context:Context) = Room.databaseBuilder(
        context,
        ArtDatabase::class.java,
        "ArtDB").build()


    @Singleton
    @Provides
    fun injectDao(database: ArtDatabase)=database.artDao()




    @Singleton
    @Provides
    fun injectRetrofitAPI():RetrofitApi{

        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build().create(RetrofitApi::class.java)

    }

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions( RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
        )

    @Singleton
    @Provides
    fun injectNormalRepo(dao : ArtDao, api:RetrofitApi) = ArtRepository(dao, api) as ArtRepositoryInterface




}
