package be.bf.android.shoppinglistapp.dal

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import be.bf.android.shoppinglistapp.dal.dao.DetailListDao
import be.bf.android.shoppinglistapp.dal.dao.ShopListDao
import be.bf.android.shoppinglistapp.dal.entities.DetailList
import be.bf.android.shoppinglistapp.dal.entities.ShopList


@Database(
//    entities = [ShopList::class],
//    version = 1,
//    exportSchema = false
    entities = [ShopList::class, DetailList::class], version = 1
)

abstract class ShopDatabase(): RoomDatabase() {
    companion object {
        const val DB_NAME = "shopping_database"
        private var instance: ShopDatabase? = null
        fun getDatabase(context: Context): ShopDatabase {
            if (instance == null) {
               // val room = Room
                    instance= Room
                    //.databaseBuilder(context, ShopDatabase::class.java, "shopping_table")
                    .databaseBuilder(context, ShopDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
//                Log.d("Databases", "instance: $room")
//                room.allowMainThreadQueries()
//                instance = room.build()
            }
            return instance!!
        }
    }
    abstract fun ShopListDao(): ShopListDao
    abstract fun DetailListDao(): DetailListDao

}

//@Database(entities = [ShopList::class, DetailList::class], version = 1)
//abstract class MyDatabase : RoomDatabase() {
//
//    abstract fun ShopListDao(): ShopListDao
//    abstract fun DetailListDao(): DetailListDao
//}


