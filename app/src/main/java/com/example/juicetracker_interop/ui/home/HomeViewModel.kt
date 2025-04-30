package com.example.juicetracker_interop.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.juicetracker_interop.data.model.Juice
import com.example.juicetracker_interop.data.model.JuiceColor
import com.example.juicetracker_interop.data.repository.JuiceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class HomeScreenUi {
    data class Success(val juiceListStream: List<Juice>) : HomeScreenUi()
    data object Empty : HomeScreenUi()
}

class HomeViewModel(
    private val juiceRepository: JuiceRepository
) : ViewModel() {

    private val _homeScreenUiState = MutableStateFlow<HomeScreenUi>(HomeScreenUi.Empty)
    val homeScreenUiState: StateFlow<HomeScreenUi> = _homeScreenUiState.asStateFlow()

    init {
        loadHomeScreenData()
    }

    private fun loadHomeScreenData() {

        juiceRepository.getJuiceStream()
            .map { juiceList ->
                if (juiceList.isEmpty()) {
                    HomeScreenUi.Empty
                } else {
                    HomeScreenUi.Success(juiceListStream = juiceList)
                }
            }
            .onEach { uiState ->
                _homeScreenUiState.value = uiState
            }
            .launchIn(viewModelScope)
    }


    val emptyJuice = Juice(
        id = 0,
        name = "",
        description = "",
        color = JuiceColor.Red.name,
        rating = 3
    )
    private val _currentJuiceStream = MutableStateFlow(emptyJuice)
    val currentJuiceStream: StateFlow<Juice> = _currentJuiceStream.asStateFlow()

    fun resetCurrentJuice() {
        _currentJuiceStream.update { emptyJuice }
    }

    fun updateCurrentJuice(juice: Juice) {
        _currentJuiceStream.update { juice }
    }

    fun saveJuice() {
        viewModelScope.launch {
            if (_currentJuiceStream.value.id > 0) {
                juiceRepository.updateJuice(_currentJuiceStream.value)
            } else {
                juiceRepository.insertJuice(_currentJuiceStream.value)
            }
        }
    }

    fun deleteJuice(juice: Juice) {
        viewModelScope.launch {
            juiceRepository.deleteJuice(juice)
        }
    }
}