package vn.xdeuhug.gemini.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vn.xdeuhug.gemini.databinding.ItemTextAnalysisBinding
import vn.xdeuhug.gemini.model.TextAnalysisResult

/**
 * @Author: NGUYEN XUAN DIEU
 * @Date: 08 / 09 / 2024
 */
class TextAnalysisAdapter : RecyclerView.Adapter<TextAnalysisAdapter.ViewHolder>() {

    private var results: List<TextAnalysisResult> = emptyList()

    fun setResults(results: List<TextAnalysisResult>) {
        this.results = results
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemTextAnalysisBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        holder.bind(result)
    }

    override fun getItemCount() = results.size

    class ViewHolder(private val binding: ItemTextAnalysisBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(result: TextAnalysisResult) {
            binding.tvRequest.text = result.text
            binding.tvResponse.text = result.analysisResult
        }
    }
}