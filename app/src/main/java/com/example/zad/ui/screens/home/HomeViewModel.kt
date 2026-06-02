package com.example.zad.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    private val _effects = Channel<HomeEffect>()
    val effects = _effects.receiveAsFlow()

    fun onIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.SectionClicked -> onSectionClicked(intent.sectionTitle)
        }
    }

    private fun onSectionClicked(sectionTitle: String) {
        _state.update { state ->
            state.copy(greeting = "Opening $sectionTitle soon")
        }

        viewModelScope.launch {
            _effects.send(HomeEffect.ShowMessage("$sectionTitle is not implemented yet"))
        }
    }
}
