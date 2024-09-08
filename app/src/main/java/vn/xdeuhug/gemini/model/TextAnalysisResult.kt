package vn.xdeuhug.gemini.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author: NGUYEN XUAN DIEU
 * @Date: 08 / 09 / 2024
 */
@Entity(tableName = "text_analysis_results")
data class TextAnalysisResult(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text: String,
    val analysisResult: String
)