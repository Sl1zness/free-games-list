package com.slcode.freegameslist.model

/**
 * Дата-класс состояния экрана со списком игр
 *
 * @property gamesList список бесплатных игр, полученных через API
 */
data class GamesListState(val gamesList: List<FreeGame> = emptyList())