package com.dlolhd.superhero

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dlolhd.superhero.model.Hero
import com.dlolhd.superhero.model.HeroesRepository.heroes
import com.dlolhd.superhero.ui.theme.SuperHeroTheme

@Composable
fun HeroesScreen() {
    LazyColumn {
        items(heroes) { item: Hero ->
            HeroItem(
                hero = item,
                modifier = Modifier
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
 * Composable that displays what the UI of the app looks like in light theme in the design tab.
 */
@Preview
@Composable
fun WoofPreview() {
    SuperHeroTheme(darkTheme = false) {
        HeroesScreen()
    }
}

@Preview
@Composable
fun WoofDarkThemePreview() {
    SuperHeroTheme(darkTheme = true) {
        HeroesScreen()
    }
}







