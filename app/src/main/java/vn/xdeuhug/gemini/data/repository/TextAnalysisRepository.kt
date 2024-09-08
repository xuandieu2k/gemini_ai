package vn.xdeuhug.gemini.data.repository

import android.util.Log
import com.google.ai.client.generativeai.GenerativeModel
import com.google.gson.Gson
import com.google.gson.JsonParser
import vn.xdeuhug.gemini.data.local.dao.TextAnalysisDao
import vn.xdeuhug.gemini.model.TextAnalysisResult

/**
 * @Author: NGUYEN XUAN DIEU
 * @Date: 08 / 09 / 2024
 */
class TextAnalysisRepository(
    private val dao: TextAnalysisDao
) {

    // Khởi tạo GenerativeModel với thông tin cần thiết
    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = "AIzaSyCW82lTIHqoqPiCuWSWaTJmdEXQNEbEHTM"
    )

    suspend fun analyzeText(text: String): TextAnalysisResult {
        val response = generativeModel.generateContent(text)

        // Convert response to JSON string
        val jsonResponse = Gson().toJson(response)
        Log.e("Data Res:", jsonResponse)

        val jsonObject = JsonParser.parseString(jsonResponse).asJsonObject
        val candidates = jsonObject.getAsJsonArray("candidates")
        val firstCandidate = candidates[0].asJsonObject
        val content = firstCandidate.getAsJsonObject("content")
        val parts = content.getAsJsonArray("parts")
        val analysisResult = parts[0].asJsonObject["text"].asString
        val result = TextAnalysisResult(text = text, analysisResult = analysisResult)
        dao.insertResult(result)

        return result
    }

    suspend fun getAllResults(): List<TextAnalysisResult> {
        return dao.getAllResults()
    }
}