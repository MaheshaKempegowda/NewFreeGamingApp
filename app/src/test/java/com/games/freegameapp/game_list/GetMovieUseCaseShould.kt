package com.example.movieapp.movie_list


import com.games.freegameapp.core.common.Resource
import com.games.freegameapp.domain.model.FreeGames
import com.games.freegameapp.domain.repository.FreeGamesRepository
import com.games.freegameapp.domain.usecase.FreeGameUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class GetMovieUseCaseShould {


    private lateinit var getMovieRepository: FreeGamesRepository
    private lateinit var getMovieUseCase: FreeGameUseCase
    private val movieList  = mock <Flow<Resource<List<FreeGames>>>>()

    @Before
    fun setUp(){
        getMovieRepository = mock()
        getMovieUseCase = FreeGameUseCase(getMovieRepository)

    }

    @Test
    fun returnMovieFromUseCaseInSuccess() = runTest{
        `when`(getMovieRepository.getFreeGames()).thenReturn(
            movieList
        )
        getMovieUseCase.invoke().onEach {
            Assert.assertEquals(movieList,it.data)
        }
    }

    @Test
    fun returnErrorFromUseCaseInSuccess() = runTest{
        `when`(getMovieRepository.getFreeGames()).thenReturn(
            flow {
                emit(Resource.Error("Something Went Wrong"))
            }
        )
        getMovieUseCase.invoke().onEach {
            Assert.assertEquals("Something Went Wrong",it.msg)
        }
    }

}