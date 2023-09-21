package com.slcode.freegameslist.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.slcode.freegameslist.R
import com.slcode.freegameslist.model.FreeGame
import com.slcode.freegameslist.ui.theme.FreeGamesListTheme
import com.slcode.freegameslist.viewmodel.GamesListViewModel
import org.koin.androidx.compose.getViewModel

/**
 * Composable функция для экрана со списком игр
 */
@Composable
fun GamesListScreen() {
    val viewModel = getViewModel<GamesListViewModel>()
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    Column {
        Text(
            text = stringResource(id = R.string.screen_games_list_title),
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(dimensionResource(id = R.dimen.padding_small))
        )

        if (state.gamesList.isEmpty()) {
            Text(
                text = stringResource(id = R.string.loading_games_list),
                textAlign = TextAlign.Center,
                fontSize = 28.sp,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize()
            )
        } else {
            LazyColumn(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))) {
                items(state.gamesList.size) {
                    GameCard(game = state.gamesList[it])
                }
            }
        }
    }
}

/**
 * Composable функция карточки с конкретной игрой
 *
 * @param game объект игры с данными о ней
 * @param modifier модификатор элемента
 */
@Composable
fun GameCard(game: FreeGame, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.game_card_corners_default)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.game_card_elevation_default),
            pressedElevation = dimensionResource(id = R.dimen.game_card_elevation_pressed)
        ),
        modifier = modifier.padding(vertical = dimensionResource(id = R.dimen.padding_small))
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = game.thumbnail,
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = game.title,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_small))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GamesListScreenPreview() {
    FreeGamesListTheme {
        GamesListScreen()
    }
}