package be.bf.android.shoppinglistapp.dal.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import be.bf.android.shoppinglistapp.dal.entities.ShopList


@Dao
interface ShopListDao {

@Insert(onConflict = OnConflictStrategy.REPLACE)
fun addShopList(shopList: ShopList)

@Query("SELECT * FROM shopping_table")
fun readAll(): LiveData<List<ShopList>>


}