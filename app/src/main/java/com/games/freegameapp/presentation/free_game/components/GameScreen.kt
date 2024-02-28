package com.games.freegameapp.presentation.free_game.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.games.freegameapp.domain.model.FreeGames
import com.games.freegameapp.presentation.free_game.state.FreeGameState
import com.games.freegameapp.presentation.free_game.viewmodel.FreeGameViewModel

@Composable
fun GameScreen(freeGameViewModel: FreeGameViewModel, modifier: Modifier) {

    val state = freeGameViewModel.freeGameState.collectAsStateWithLifecycle()
    val freeGameState: FreeGameState = state.value

    when {
        freeGameState.isLoading -> {
            // Handle loading state
            Box(modifier = modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
            }
        }

        freeGameState.freeGames?.isNotEmpty()!! -> {
            LazyColumn {
                items(freeGameState.freeGames) {
                    FreeGameItem(modifier, it)
                }
            }
        }
    }
}