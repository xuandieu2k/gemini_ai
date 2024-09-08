package vn.xdeuhug.gemini.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import vn.xdeuhug.gemini.data.repository.TextAnalysisRepository

/**
 * @Author: NGUYEN XUAN DIEU
 * @Date: 08 / 09 / 2024
 */
class TextAnalysisViewModelFactory(
    private val repository: TextAnalysisRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TextAnalysisViewModel::class.java)) {
            return TextAnalysisViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}