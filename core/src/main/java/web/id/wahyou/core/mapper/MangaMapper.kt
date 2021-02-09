package web.id.wahyou.core.mapper

import web.id.wahyou.core.data.model.manga.DataManga
import web.id.wahyou.core.data.repository.local.entity.MangaEntity
import web.id.wahyou.core.domain.model.manga.Manga
import web.id.wahyou.core.utils.checkNullString

object MangaMapper {

    fun mapResponsesToEntities(input: List<DataManga>): List<MangaEntity> {
        val mangaList = ArrayList<MangaEntity>()
        input.map {
            val manga = MangaEntity(
                id = it.id,
                canonicalTitle = it.attributes.canonicalTitle,
                averageRating = checkNullString(it.attributes.averageRating),
                synopsis = it.attributes.synopsis,
                posterImage = it.attributes.posterImage.medium,
                startDate = checkNullString(it.attributes.startDate),
                type = checkNullString(it.type),
                serialization = checkNullString(it.attributes.serialization),
                chapterCount = it.attributes.chapterCount,
                userCount = it.attributes.userCount,
                status = it.attributes.status,
                volumeCount = it.attributes.volumeCount,
                popularityRank = it.attributes.popularityRank,
                isFavorite = false
            )
            mangaList.add(manga)
        }
        return mangaList
    }

    fun mapEntitiesToDomain(input: List<MangaEntity>): List<Manga> =
        input.map {
            Manga(
                id = it.id,
                canonicalTitle = it.canonicalTitle,
                averageRating = it.averageRating!!,
                synopsis = it.synopsis,
                posterImage = it.posterImage,
                startDate = it.startDate!!,
                type = it.type,
                serialization = it.serialization!!,
                chapterCount = it.chapterCount,
                userCount = it.userCount,
                status = it.status,
                volumeCount = it.volumeCount,
                popularityRank = it.popularityRank,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Manga) = MangaEntity(
        id = input.id,
        canonicalTitle = input.canonicalTitle,
        averageRating = input.averageRating,
        synopsis = input.synopsis,
        posterImage = input.posterImage,
        startDate = input.startDate,
        type = input.type,
        serialization = input.serialization,
        chapterCount = input.chapterCount,
        userCount = input.userCount,
        status = input.status,
        volumeCount = input.volumeCount,
        popularityRank = input.popularityRank,
        isFavorite = input.isFavorite
    )
}