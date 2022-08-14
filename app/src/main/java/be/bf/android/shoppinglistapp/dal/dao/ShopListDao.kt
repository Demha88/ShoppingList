package be.bf.android.shoppinglistapp.dal.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import be.bf.android.shoppinglistapp.dal.entities.ShopList
import be.bf.android.shoppinglistapp.dal.entities.ShopListWithDetail
import kotlinx.coroutines.flow.Flow


@Dao
interface ShopListDao {

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun addShopList(shopList: ShopList)

@Transaction
@Query("SELECT * FROM shopping_table")
fun readAllShoplistWithDetail(): Flow<List<ShopListWithDetail>>

@Query("SELECT list_id FROM shopping_table ORDER BY list_id DESC LIMIT 1")
fun getLastShopId(): Flow<Int>



}