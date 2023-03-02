package com.erenalparslan.trytesting.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.erenalparslan.trytesting.api.RetrofitApi
import com.erenalparslan.trytesting.db.ArtDatabase
import com.erenalparslan.trytesting.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
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

}
