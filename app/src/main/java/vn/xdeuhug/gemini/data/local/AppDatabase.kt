package vn.xdeuhug.gemini.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import vn.xdeuhug.gemini.data.local.dao.TextAnalysisDao
import vn.xdeuhug.gemini.model.TextAnalysisResult

/**
 * @Author: NGUYEN XUAN DIEU
 * @Date: 08 / 09 / 2024
 */
@Database(entities = [TextAnalysisResult::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun textAnalysisDao(): TextAnalysisDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "gemini_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}