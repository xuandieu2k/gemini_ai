package vn.xdeuhug.gemini.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import vn.xdeuhug.gemini.model.TextAnalysisResult

/**
 * @Author: NGUYEN XUAN DIEU
 * @Date: 08 / 09 / 2024
 */
@Dao
interface TextAnalysisDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(result: TextAnalysisResult)

    @Query("SELECT * FROM text_analysis_results")
    suspend fun getAllResults(): List<TextAnalysisResult>
}
