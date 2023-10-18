package com.dlolhd.superhero.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dlolhd.superhero.ui.view.HeroesViewModel
import com.dlolhd.superhero.ui.view.SuperHeroScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroApp() {
    Scaffold {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            val heroViewModel: HeroesViewModel =
                viewModel(factory = HeroesViewModel.Factory)
            SuperHeroScreen(
                heroUiState = heroViewModel.heroUiState,
                retryAction = heroViewModel::getAllHeroes
            )
        }
    }
}
