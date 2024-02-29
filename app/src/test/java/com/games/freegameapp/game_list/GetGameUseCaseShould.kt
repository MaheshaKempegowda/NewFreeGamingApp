
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
class GetGameUseCaseShould {

    private lateinit var getFreeGameRepository: FreeGamesRepository
    private lateinit var getGamesUseCase: FreeGameUseCase
    private val gamesList  = mock <Flow<Resource<List<FreeGames>>>>()

    @Before
    fun setUp(){
        getFreeGameRepository = mock()
        getGamesUseCase = FreeGameUseCase(getFreeGameRepository)

    }

    @Test
    fun returnMovieFromUseCaseInSuccess() = runTest{
        `when`(getFreeGameRepository.getFreeGames()).thenReturn(
            gamesList
        )
        getGamesUseCase.invoke().onEach {
            Assert.assertEquals(gamesList,it.data)
        }
    }

    @Test
    fun returnErrorFromUseCaseInSuccess() = runTest{
        `when`(getFreeGameRepository.getFreeGames()).thenReturn(
            flow {
                emit(Resource.Error("Something Went Wrong"))
            }
        )
        getGamesUseCase.invoke().onEach {
            Assert.assertEquals("Something Went Wrong",it.msg)
        }
    }
}