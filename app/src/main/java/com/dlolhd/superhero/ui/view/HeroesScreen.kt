package com.dlolhd.superhero.ui.view

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dlolhd.superhero.R
import com.dlolhd.superhero.model.Hero
import com.dlolhd.superhero.ui.theme.SuperHeroTheme


@Composable
fun SuperHeroScreen(
    heroUiState: HeroUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when(heroUiState) {
        is HeroUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is HeroUiState.Success -> HeroesListScreen(heroes = heroUiState.heroes, modifier = modifier)
        is HeroUiState.Error -> ErrorScreen(retryAction = retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun HeroesListScreen(
    heroes: List<Hero>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(heroes) { item: Hero ->
            HeroItem(
                hero = item,
                modifier = modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Composable
fun HeroItem(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_medium))
            .sizeIn(minHeight = 72.dp)
        ) {
            HeroDescription(hero.nameRes, hero.descriptionRes, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(16.dp))
            HeroImage(hero.imageRes, hero.nameRes)
        }
    }
}

@Composable
fun HeroImage(
    @DrawableRes imageRes: Int,
    @StringRes heroRes: Int,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            modifier = modifier
                .size(dimensionResource(id = R.dimen.image_size))
                .clip(MaterialTheme.shapes.small),
            contentScale = ContentScale.Crop,
            painter = painterResource(imageRes),
            contentDescription = stringResource(id = heroRes)
        )
    }
}

@Composable
fun HeroDescription(
    @StringRes nameRes: Int, 
    @StringRes descriptionRes: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(text = stringResource(id = nameRes), style = MaterialTheme.typography.displaySmall)
        Text(
            text = stringResource(id = descriptionRes),
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

/**
 * The hero screen displaying the loading message.
 */
@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = stringResource(id = R.string.loading)
    )
}

/**
 * The hero screen displaying error message with re-attempt button.
 */
@Composable
fun ErrorScreen(
    retryAction: () -> Unit, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = stringResource(id = R.string.connection_error)
        )
        Text(text = stringResource(id = R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

/**
 * Composable that displays what the UI of the app looks like in light theme in the design tab.
 */
@Preview
@Composable
fun HeroPreview() {
    SuperHeroTheme(darkTheme = false) {
        //HeroesListScreen()
    }
}

@Preview
@Composable
fun HeroDarkThemePreview() {
    SuperHeroTheme(darkTheme = true) {
        //HeroesListScreen()
    }
}







