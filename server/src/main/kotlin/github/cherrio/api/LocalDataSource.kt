package github.cherrio.api

import com.cherrio.sheetsdb.database.create
import com.cherrio.sheetsdb.init.SheetsDb
import com.cherrio.sheetsdb.init.getTable
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

val localDataSource by lazy { LocalDataSource(CIO.create()) }

class LocalDataSource(private val clientEngine: HttpClientEngine) {
    private val client by lazy {
        HttpClient(clientEngine) {
            install(ContentNegotiation){
                json(Json { ignoreUnknownKeys = true })
            }
        }
    }

    private val sheetDb = SheetsDb {
        sheetId = "1SAB5AidBWZuKOoITcyqJeMg6Nj1HiyfNs6BjEXrnMBY"
    }
    private var movieTable = sheetDb.getTable<Movie>()

    suspend fun setMovie(movie: Movie){
        val createMovie = movieTable.create(movie)
        if (!createMovie){
            println("Error creating movie")
        }
    }



    suspend fun refreshToken(){
        val response = client.submitForm(System.getenv("GOOGLE_TOKEN_URL"),
            formParameters = Parameters.build {
                append("grant_type", "refresh_token")
                append("clientId", System.getenv("CLIENT_ID"))
                append("client_secret", System.getenv("CLIENT_SECRET"))
                append("refresh_token", System.getenv("REFRESH_TOKEN"))
            },
            encodeInQuery = true
        ){
            method = HttpMethod.Post
        }
        if (response.status.isSuccess()) {
            val token = response.body<ResponseGD>()
            println(token.accessToken.substring(0, 12))
            movieTable.token = token.accessToken
        }
    }
}

@Serializable
data class ResponseGD(
    @SerialName("access_token") val accessToken: String
)