package com.dlolhd.superhero.ui.view

import android.net.http.HttpException
import android.os.Build
import android.os.ext.SdkExtensions
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.dlolhd.superhero.SuperHeroApplication
import com.dlolhd.superhero.model.Hero
import com.dlolhd.superhero.model.HeroRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException


/**
 * UI state for the Home screen
 */
sealed interface HeroUiState {
    data class Success(val heroes: List<Hero>) : HeroUiState
    object Error : HeroUiState
    object Loading : HeroUiState
}

class HeroesViewModel(
    private val heroRepository: HeroRepository
) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var heroUiState: HeroUiState by mutableStateOf(HeroUiState.Loading)
        private set

    /**
     * Call getAllHeroes() on init so we can display status immediately.
     */
    init {
        getAllHeroes()
    }

    /**
     * Gets Hero information from the repository
     */
    fun getAllHeroes() {
        viewModelScope.launch {
            heroUiState = HeroUiState.Loading
            delay(500)
            heroUiState = try {
                val listResult = heroRepository.getAllHeroes()
                HeroUiState.Success(listResult)
            } catch (e: IOException) {
                HeroUiState.Error
            } catch (e: Exception) {
                HeroUiState.Error
            }
        }
    }

    /**
     * Factory for [HeroesViewModel] that takes [HeroRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as SuperHeroApplication)
                val heroRepository = application.container.heroRepository
                HeroesViewModel(heroRepository = heroRepository)
            }
        }
    }

}






















