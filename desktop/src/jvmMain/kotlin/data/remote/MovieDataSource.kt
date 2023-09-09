package data.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.jsoup.Jsoup

val dataSource by lazy { MovieDataSource(CIO.create()) }
class MovieDataSource(private val clientEngine: HttpClientEngine) {
    private val client by lazy {
        HttpClient(clientEngine) {
            install(HttpCookies)
            install(ContentNegotiation){
                json(Json { ignoreUnknownKeys = true })
            }
            defaultRequest {
                url("https://www.thenetnaija.net/")
            }
        }
    }

    suspend fun getMovies(page: Int = 1) = coroutineScope {
        val hollywood = async { getMovie("videos/movies/page/$page").getOrElse { emptyList() } }
        val nollywood = async{ getMovie("videos/nollywood/page/$page").getOrElse { emptyList() } }
        val series = async { getMovie("videos/series/page/$page").getOrElse { emptyList() } }
        val yoruba = async{ getMovie("videos/yoruba/page/$page").getOrElse { emptyList() } }
        awaitAll(hollywood,nollywood,series,yoruba).flatten()
    }
    suspend fun getMovieByOption(url: String) = getMovie(url).getOrElse { emptyList() }

    suspend fun getMovieDetails(movie: Movie) =  kotlin.runCatching{
        val response = client.get(movie.url).bodyAsText()
        val parsed = Jsoup.parse(response).select(".post-body p")
        if (parsed.size > 2) {
            val (summary, _, genre, releaseDate, stars) = parsed
             movie.copy(
                summary = summary.text(),
                genre = genre.text(),
                releaseDate = releaseDate.text(),
                stars = stars.text()
            )
        }else{
            movie.copy(summary = parsed[0].text())
        }
    }

    suspend fun getSeriesDetails(url: String) =  coroutineScope{
        val response = client.get(url).bodyAsText()
        val seasons = Jsoup.parse(response).select(".video-seasons .vs-one")
            .map {
                val seasonName = it.text()
                val episodeLink =  it.select("a").attr("href")
                Pair(first = seasonName, second = episodeLink)
            }
        val series = seasons.map {
            async { getMovie(it.second, isSeries = true) }
        }.awaitAll()
        return@coroutineScope seasons.mapIndexed { index, pair ->
            val season = series.get(index).getOrElse { emptyList() }
            Series(Pair(pair.first, season))
        }
    }

    private suspend fun getEpisodes(url: String){
        val response = client.get(url).bodyAsText()
        val episodes = Jsoup.parse(response)
    }

     suspend fun getDownloadUrl(url: String): String{
        val response = client.get(url){
            headers {
                append("referer", url)
            }
        }
        return getStreamingUrl(response.request.url.toString())
    }

     private suspend fun getStreamingUrl(url: String): String{
        val fileId = getFileIdFromUrl(url)
        val response = client.get("https://api.sabishare.com/token/download/$fileId").body<StreamingData>()
        return response.data.url
    }

    private fun getFileIdFromUrl(url: String) =
        url.substringAfterLast("/").substringBefore("-")

    private fun getGenre(url: String) = when{
        url.contains("videos/movie") -> MovieType.HOLLYWOOD
        url.contains("videos/nollywood") -> MovieType.NOLLYWOOD
        url.contains("videos/series") -> MovieType.SERIES
        else -> MovieType.YORUBA
    }

    private suspend fun getMovie(url: String, isSeries: Boolean = false): Result<List<Movie>>  = kotlin.runCatching{
        val response = client.get(url).bodyAsText()
        val parsedData = Jsoup.parse(response).select(".file-one")
         parsedData.map {
            val nameAndImageElement = it.select("img")
            val name = if (isSeries) it.select(".title").text() else nameAndImageElement.attr("title")
            val image = nameAndImageElement.attr("src")
            val movieUrl = it.select("a").attr("href")
            Movie(name = name, url =  movieUrl, image = image, getGenre(url)).addId()
        }
    }
}

@Serializable
data class StreamingData(
    val data: StreamingUrl
){
    @Serializable
    data class StreamingUrl(
        val url: String
    )
}