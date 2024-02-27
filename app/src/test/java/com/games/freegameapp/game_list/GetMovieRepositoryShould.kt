package com.example.movieapp.movie_list

import com.games.freegameapp.MainCoroutineRule
import com.games.freegameapp.data.remote.FreeGameApi
import com.games.freegameapp.data.remote.dto.FreeGamesDto
import com.games.freegameapp.data.repository.FreeGameRepositoryImpl
import com.games.freegameapp.domain.repository.FreeGamesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`


@OptIn(ExperimentalCoroutinesApi::class)
class GetMovieRepositoryShould {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()
    private lateinit var freeGamesRepository: FreeGamesRepository
    private lateinit var apiService: FreeGameApi
    private val gamesList  = mock <List<FreeGamesDto>>()

    @Before
    fun setUp(){
        apiService = mock()
        freeGamesRepository = FreeGameRepositoryImpl(apiService)
    }

    @Test
    fun returnSuccessWhenGetDataFromBackend() = runTest{
        `when`(apiService.getFreeGame()).thenReturn(
            gamesList
        )
        freeGamesRepository.getFreeGames().onEach {
            Assert.assertEquals(gamesList,it.data)
        }

    }
}