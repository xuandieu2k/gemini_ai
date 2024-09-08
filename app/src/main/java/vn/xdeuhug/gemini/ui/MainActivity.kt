package vn.xdeuhug.gemini.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.HandlerCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import vn.xdeuhug.gemini.data.local.AppDatabase
import vn.xdeuhug.gemini.data.repository.TextAnalysisRepository
import vn.xdeuhug.gemini.databinding.ActivityMainBinding
import vn.xdeuhug.gemini.ui.adapter.TextAnalysisAdapter

/**
 * @Author: NGUYEN XUAN DIEU
 * @Date: 08 / 09 / 2024
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TextAnalysisViewModel
    private lateinit var repository: TextAnalysisRepository

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        repository = TextAnalysisRepository(AppDatabase.getDatabase(this).textAnalysisDao())
        val viewModelFactory = TextAnalysisViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[TextAnalysisViewModel::class.java]

        val adapter = TextAnalysisAdapter()
        binding.recyclerViewResults.adapter = adapter
        binding.recyclerViewResults.layoutManager = LinearLayoutManager(this)

        binding.buttonAnalyze.setOnClickListener {
            val text = binding.editText.text.toString()
            binding.editText.setText("")
            binding.textViewResult.isVisible = true
            binding.textViewResult.text = "Analysis Result: ${text}..."
            viewModel.analyzeText(text)
        }

        viewModel.analysisResult.observe(this) { result ->
            //
        }

        viewModel.results.observe(this) { results ->
            adapter.setResults(results)
//            Handler(Looper.getMainLooper()).postDelayed({
            binding.textViewResult.isVisible = false
//            }, 1000)
            binding.recyclerViewResults.scrollToPosition(adapter.itemCount - 1)
        }
        viewModel.getLocalResults()
    }
}