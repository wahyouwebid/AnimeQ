package web.id.wahyou.core.data.repository.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import web.id.wahyou.core.data.repository.local.database.dao.AnimeQDao
import web.id.wahyou.core.data.repository.local.entity.AnimeEntity
import web.id.wahyou.core.data.repository.local.entity.MangaEntity

@Database(entities = [
    AnimeEntity::class,
    MangaEntity::class], version = 2, exportSchema = false
)

abstract class AnimeDatabase : RoomDatabase() {

    abstract fun animeDao(): AnimeQDao

}