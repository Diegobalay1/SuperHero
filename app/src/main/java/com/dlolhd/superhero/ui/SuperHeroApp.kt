package com.dlolhd.superhero.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dlolhd.superhero.R
import com.dlolhd.superhero.ui.theme.SuperHeroTheme
import com.dlolhd.superhero.ui.view.HeroesViewModel
import com.dlolhd.superhero.ui.view.SuperHeroScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroApp() {
    Scaffold(
        topBar = { SuperHeroTopAppBar() }
    ) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroTopAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.displayLarge) }
    )
}

/**
 * Composable that displays what the UI of the app looks like in light theme in the design tab.
 */
@Preview
@Composable
fun HeroPreview() {
    SuperHeroTheme(darkTheme = false) {
        SuperHeroApp()
    }
}

@Preview
@Composable
fun HeroDarkThemePreview() {
    SuperHeroTheme(darkTheme = true) {
        SuperHeroApp()
    }
}








