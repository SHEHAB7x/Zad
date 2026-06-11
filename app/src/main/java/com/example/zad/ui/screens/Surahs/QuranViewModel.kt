package com.example.zad.ui.screens.Surahs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zad.domain.repository.QuranRepository
import com.example.zad.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuranViewModel @Inject constructor(
    private val repository: QuranRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(QuranUiState())
    val uiState: StateFlow<QuranUiState> = _uiState.asStateFlow()

    init {
        getCachedSurahs()
    }

    private fun getCachedSurahs() {
        viewModelScope.launch {
            repository.getSurahList().collect { resource ->
                when(resource){
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true, error = null) }
                    }
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                surahs = resource.data ?: emptyList(),
                                error = null
                            )
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                surahs = resource.data ?: emptyList(),
                                error = resource.message
                            )
                        }
                    }
                }
            }
        }
    }
}