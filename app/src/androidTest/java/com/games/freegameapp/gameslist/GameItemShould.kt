package com.games.freegameapp.gameslist

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isRoot
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.printToLog
import com.games.freegameapp.domain.model.FreeGames
import com.games.freegameapp.presentation.free_game.components.FreeGameItem
import com.games.freegameapp.presentation.free_game.components.GameScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class GameItemShould {

    @get:Rule
    val composerule = createComposeRule()
    private lateinit var games: FreeGames

    @Before
    fun setUp() {
        games = FreeGames(
            "https://www.freetogame.com/open/overwatch-2",
            508,
            "Overwatch 2",
            "https://www.freetogame.com/g/540/thumbnail.jpg",
            "A hero-focused first-person team shooter from Blizzard"
        )
        composerule.setContent {
            FreeGameItem(modifier = Modifier, games)
        }
    }

    @Test
    fun beDispalyed(){
        composerule.onAllNodes(isRoot()).printToLog("currentLabelExists")
    }

    @Test
    fun containCorrectTitle(){
        composerule.onNodeWithText("A hero-focused first-person team shooter from Blizzard")
    }
}