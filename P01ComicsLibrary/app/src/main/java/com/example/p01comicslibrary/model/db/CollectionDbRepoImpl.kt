package com.example.p01comicslibrary.model.db

import kotlinx.coroutines.flow.Flow

// interface CollectionDbRepo 的實作
class CollectionDbRepoImpl(private val characterDao: CharacterDao): CollectionDbRepo {
    override suspend fun getCharactersFromRepo(): Flow<List<DbCharacter>> = characterDao.getCharacter()

    // 原始
    //override suspend fun getCharacterFromRepo(characterId: Int): Flow<DbCharacter> {
    //    TODO("Not yet implemented")
    //}

    override suspend fun getCharacterFromRepo(characterId: Int): Flow<DbCharacter> = characterDao.getCharacter(characterId)

    override suspend fun addCharacterToRepo(character: DbCharacter) = characterDao.addCharacter(character)

    override suspend fun updateCharacterInRepo(character: DbCharacter) =
        characterDao.updateCharacter(character)

    override suspend fun deleteCharacterFromRepo(character: DbCharacter) =
        characterDao.deleteCharacter(character)
}