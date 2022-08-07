package be.bf.android.shoppinglistapp.dal.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import be.bf.android.shoppinglistapp.dal.entities.ShopList
import be.bf.android.shoppinglistapp.dal.entities.ShopListWithDetail


@Dao
interface ShopListDao {

@Insert(onConflict = OnConflictStrategy.REPLACE)
fun addShopList(shopList: ShopList)


@Transaction
@Query("SELECT * FROM shopping_table")
fun readAllShoplistWithDetail(): LiveData<List<ShopListWithDetail>>





}