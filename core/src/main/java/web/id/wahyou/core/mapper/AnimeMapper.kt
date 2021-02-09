package web.id.wahyou.core.mapper

import web.id.wahyou.core.data.model.anime.DataAnime
import web.id.wahyou.core.data.repository.local.entity.AnimeEntity
import web.id.wahyou.core.domain.model.anime.Anime
import web.id.wahyou.core.utils.checkNullCoverImage
import web.id.wahyou.core.utils.checkNullPosterImage
import web.id.wahyou.core.utils.checkNullString

object AnimeMapper {

    fun mapResponsesToEntities(input: List<DataAnime>): List<AnimeEntity> {
        val data = ArrayList<AnimeEntity>()
        input.map {
            val anime = AnimeEntity(
                    id = it.id,
                    canonicalTitle = it.attributes.canonicalTitle,
                    averageRating = it.attributes.averageRating,
                    synopsis = it.attributes.synopsis,
                    posterImage = checkNullPosterImage(it.attributes.posterImage),
                    coverImage = checkNullCoverImage(it.attributes.coverImage),
                    youtubeVideoId = checkNullString(it.attributes.youtubeVideoId),
                    episodeCount = it.attributes.episodeCount,
                    startDate =  it.attributes.startDate,
                    status = it.attributes.status,
                    subtype = it.attributes.subtype,
                    totalDuration = it.attributes.totalLength,
                    type = it.type,
                    userCount = it.attributes.userCount,
                    isFavorite = false
            )
            data.add(anime)
        }
        return data
    }

    fun mapEntitiesToDomain(input: List<AnimeEntity>): List<Anime> =
        input.map {
            Anime(
                id = it.id,
                canonicalTitle = it.canonicalTitle,
                averageRating = it.averageRating,
                synopsis = it.synopsis,
                posterImage = it.posterImage,
                coverImage = it.coverImage,
                youtubeVideoId = it.youtubeVideoId,
                episodeCount = it.episodeCount,
                startDate = it.startDate,
                status = it.status,
                subtype = it.subtype,
                totalDuration = it.totalDuration,
                type = it.type,
                userCount = it.userCount,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Anime) = AnimeEntity(
            id = input.id,
            canonicalTitle = input.canonicalTitle,
            averageRating = input.averageRating,
            synopsis = input.synopsis,
            posterImage = input.posterImage,
            coverImage = input.coverImage,
            youtubeVideoId = input.youtubeVideoId,
            episodeCount = input.episodeCount,
            startDate = input.startDate,
            status = input.status,
            subtype = input.subtype,
            totalDuration = input.totalDuration,
            type = input.type,
            userCount = input.userCount,
            isFavorite = input.isFavorite
    )

    fun mapResponsesToDomain(input: List<DataAnime>): List<Anime> {
        val data = ArrayList<Anime>()
        input.map {
            val anime = Anime(
                    id = it.id,
                    canonicalTitle = it.attributes.canonicalTitle,
                    averageRating = it.attributes.averageRating,
                    synopsis = it.attributes.synopsis,
                    posterImage = it.attributes.posterImage.medium,
                    coverImage = checkNullCoverImage(it.attributes.coverImage),
                    youtubeVideoId = checkNullString(it.attributes.youtubeVideoId),
                    episodeCount = it.attributes.episodeCount,
                    startDate =  it.attributes.startDate,
                    status = it.attributes.status,
                    subtype = it.attributes.subtype,
                    totalDuration = it.attributes.totalLength,
                    type = it.type,
                    userCount = it.attributes.userCount,
                    isFavorite = false
            )
            data.add(anime)
        }
        return data
    }
}