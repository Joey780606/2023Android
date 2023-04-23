package com.example.p01comicslibrary.model.db

import kotlinx.coroutines.flow.Flow

// 重點: interface, suspend, Flow
interface CollectionDbRepo {
    suspend fun getCharactersFromRepo(): Flow<List<DbCharacter>>

    suspend fun getCharacterFromRepo(characterId: Int): Flow<DbCharacter>

    suspend fun addCharacterToRepo(character: DbCharacter)

    suspend fun updateCharacterInRepo(character: DbCharacter)

    suspend fun deleteCharacterFromRepo(character: DbCharacter)
}