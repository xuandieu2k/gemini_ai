package vn.xdeuhug.gemini.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vn.xdeuhug.gemini.data.repository.TextAnalysisRepository
import vn.xdeuhug.gemini.model.TextAnalysisResult

/**
 * @Author: NGUYEN XUAN DIEU
 * @Date: 08 / 09 / 2024
 */
class TextAnalysisViewModel(
    private val repository: TextAnalysisRepository
) : ViewModel() {

    private val _analysisResult = MutableLiveData<TextAnalysisResult>()
    val analysisResult: LiveData<TextAnalysisResult> = _analysisResult

    private val _results = MutableLiveData<List<TextAnalysisResult>>()
    val results: LiveData<List<TextAnalysisResult>> = _results

    fun analyzeText(text: String) {
        viewModelScope.launch {
            try {
                val result = repository.analyzeText(text)
                _analysisResult.value = result
                _results.value = repository.getAllResults()
            } catch (e: Exception) {
                // Handle error
                Log.e("Error",e.message.toString())
            }
        }
    }

    fun getLocalResults() = viewModelScope.launch {
        _results.value = repository.getAllResults()
    }

}
