package di

import data.remote.MovieDataSource
import data.remote.MovieRepoImpl
import domain.repo.MovieRepo
import domain.usecase.GetTitleDetailsUseCase
import domain.usecase.GetTitlesUseCase
import io.ktor.client.engine.cio.*
import kotlinx.coroutines.MainScope
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module
import presentation.home.AppViewModel


fun appModule() = module {
    single{ CIO.create() }
    single { MovieDataSource(get()) }
    single<MovieRepo>{ MovieRepoImpl(get())}
    single{ GetTitlesUseCase(get()) }
    single { MainScope() }
    single { AppViewModel(get(), get(), get()) }
    single{ GetTitleDetailsUseCase(get())}
}

fun initKoin(): KoinApplication {
    return startKoin {
        modules(appModule())
    }
}