package com.example.p01comicslibrary.model.db

import androidx.room.*
import com.example.p01comicslibrary.model.db.Constants.CHARACTER_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Query("Select * from $CHARACTER_TABLE order by id ASC")
    fun getCharacter(): Flow<List<DbCharacter>>

    @Query("Select * from $CHARACTER_TABLE where id = :characterId")
    fun getCharacter(characterId: Int): Flow<DbCharacter>

    @Insert(onConflict = OnConflictStrategy.IGNORE) //room's process
    fun addCharacter(character: DbCharacter)

    @Update
    fun updateCharacter(character: DbCharacter)

    @Delete
    fun deleteCharacter(character: DbCharacter)
}