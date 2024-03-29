package com.example.p01comicslibrary

import android.content.Context
import androidx.room.Room
import com.example.p01comicslibrary.model.api.ApiService
import com.example.p01comicslibrary.model.api.MarvelApiRepo
import com.example.p01comicslibrary.model.db.CharacterDao
import com.example.p01comicslibrary.model.db.CollectionDb
import com.example.p01comicslibrary.model.db.CollectionDbRepo
import com.example.p01comicslibrary.model.db.CollectionDbRepoImpl
import com.example.p01comicslibrary.model.db.Constants.DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {
    @Provides
    fun provideApiRepo() = MarvelApiRepo(ApiService.api)

    @Provides
    fun provideCollectionDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CollectionDb::class.java, DB).build()

    @Provides
    fun provideCharacterDao(collectionDb: CollectionDb) = collectionDb.characterDao()

    @Provides
    fun provideDbRepoImpl(characterDao: CharacterDao): CollectionDbRepo =
        CollectionDbRepoImpl(characterDao)
}