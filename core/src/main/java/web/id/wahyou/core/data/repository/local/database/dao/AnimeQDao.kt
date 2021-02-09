package web.id.wahyou.core.data.repository.local.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import web.id.wahyou.core.data.repository.local.entity.AnimeEntity
import web.id.wahyou.core.data.repository.local.entity.MangaEntity

@Dao
interface AnimeQDao {

    /** Query Anime **/

    @Query("SELECT * FROM anime")
    fun getAnime(): Flow<List<AnimeEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAnime(anime: List<AnimeEntity>)

    @Query("SELECT * FROM anime where isFavorite = 1")
    fun getFavoriteAnime(): Flow<List<AnimeEntity>>

    @Update
    fun updateFavoriteAnime(anime: AnimeEntity)

    /** Query Manga **/

    @Query("SELECT * FROM manga" )
    fun getManga(): Flow<List<MangaEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertManga(manga: List<MangaEntity>)

    @Query("SELECT * FROM manga where isFavorite = 1")
    fun getFavoriteManga(): Flow<List<MangaEntity>>

    @Update
    fun updateFavoriteManga(manga: MangaEntity)


}