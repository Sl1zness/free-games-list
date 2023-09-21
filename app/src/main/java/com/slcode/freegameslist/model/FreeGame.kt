package com.slcode.freegameslist.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Дата-класс для бесплатной игры, получаемой через GET запрос в API
 *
 * @property title название игры
 * @property thumbnail ссылка на обложку игры
 */
@JsonClass(generateAdapter = true)
data class FreeGame(
    @Json(name = "title")
    val title: String,
    @Json(name = "thumbnail")
    val thumbnail: String
)
