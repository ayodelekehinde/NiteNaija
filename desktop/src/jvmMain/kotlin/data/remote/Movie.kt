package data.remote

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val name: String,
    val url: String,
    val image: String,
    val movieType: MovieType,
    val id: Int = 0,
    val summary: String = "",
    val genre: String = "",
    val releaseDate: String = "",
    val stars: String = "",
    val downloadUrl: String = "",
    val streamingUrl: String = "",
    val subtitleUrl: String = ""
){
    fun isComplete() = summary.isNotEmpty() && streamingUrl.isNotEmpty()
    fun addId() = copy(id = generateNextId())
    companion object {
        private var currentId = 0
    }
    // Use a synchronized function to generate the next ID safely
    private fun generateNextId(): Int {
        synchronized(this) {
            currentId++
            return currentId
        }
    }
}

enum class MovieType{
    HOLLYWOOD, NOLLYWOOD, YORUBA, SERIES
}


@Serializable
data class Series(
    val seasons: Pair<String, List<Movie>>
)

