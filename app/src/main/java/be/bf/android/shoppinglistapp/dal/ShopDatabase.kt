package be.bf.android.shoppinglistapp.dal

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import be.bf.android.shoppinglistapp.dal.dao.ShopListDao
import be.bf.android.shoppinglistapp.dal.entities.ShopList


@Database(
    entities = [ShopList::class],
    version = 1,
    exportSchema = false
)

abstract class ShopDatabase(): RoomDatabase() {
    companion object {
        private var instance: ShopDatabase? = null
        fun getDatabase(context: Context): ShopDatabase {
            if (instance == null) {
                val room = Room
                    .databaseBuilder(context, ShopDatabase::class.java, "shopping_table")
                Log.d("Databases", "instance: $room")
                room.allowMainThreadQueries()
                instance = room.build()
            }
            return instance!!
        }
    }
    abstract fun ShopListDao(): ShopListDao


}

