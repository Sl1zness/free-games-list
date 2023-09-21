package com.slcode.freegameslist.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slcode.freegameslist.model.GamesListState
import com.slcode.freegameslist.model.api.FreeGamesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

const val TAG = "RESPONSE_ERROR"

/**
 * Вьюмодель экрана со списком всех бесплатных игр
 *
 * @property api API для отправки запроса на сервер (по умолчанию генерируется через Koin)
 */
class GamesListViewModel(private val api: FreeGamesApi) : ViewModel() {
    private var _uiState = MutableStateFlow(GamesListState())
    val uiState = _uiState.asStateFlow()

    init {
        getGamesList()
    }

    /**
     * Метод для получения списка бесплатных игр
     */
    fun getGamesList() = viewModelScope.launch(Dispatchers.IO) {
        val response = try {
            api.getFreePcGames()
        } catch (e: IOException) {
            Log.e(TAG, "IOException. Common reason: no internet connection")
            return@launch
        } catch (e: HttpException) {
            Log.e(TAG, "HTTPException. Common reason: unexpected response")
            return@launch
        }

        if (response.isSuccessful && response.body() != null) {
            _uiState.update {
                it.copy(
                    gamesList = response.body()!!
                )
            }
        }
    }
}