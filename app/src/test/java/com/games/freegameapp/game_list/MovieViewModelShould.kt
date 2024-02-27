package com.example.movieapp.movie_list

import com.games.freegameapp.MainCoroutineRule
import com.games.freegameapp.core.common.Resource
import com.games.freegameapp.domain.model.FreeGames
import com.games.freegameapp.domain.usecase.FreeGameUseCase
import com.games.freegameapp.presentation.free_game.viewmodel.FreeGameViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class MovieViewModelShould {

    @get:Rule
    val mainCoroutineRule =  MainCoroutineRule()

    private val getMovieUseCase : FreeGameUseCase = mock()
    private lateinit var movieViewModel : FreeGameViewModel
    private val movieList : List<FreeGames>? = mock()

    @Before
    fun setup(){
        movieViewModel =  FreeGameViewModel(getMovieUseCase)
    }

    @Test
    fun validateUserWillSeeProgressBarInitially() = runTest {
        `when`(getMovieUseCase.invoke()).thenReturn(
            flow {
                emit(Resource.Loading())
            }
        )
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals(true,movieViewModel.freeGameState.value.isLoading)
    }

    @Test
    fun validateSuccessStateIsStoredInMovieList() = runTest {
        `when`(getMovieUseCase.invoke()).thenReturn(
            flow {
                emit(Resource.Success(movieList))
            }
        )
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals(movieList,movieViewModel.freeGameState.value.freeGames)
    }

    @Test
    fun throwErrorInErrorCase() = runTest {
        `when`(getMovieUseCase.invoke()).thenReturn(
            flow {
                emit(Resource.Error("Something Went Wrong"))
            }
        )
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals("Something Went Wrong",movieViewModel.freeGameState.value.errorMsg)
    }

}