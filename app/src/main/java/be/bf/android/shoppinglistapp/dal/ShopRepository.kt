package be.bf.android.shoppinglistapp.dal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import be.bf.android.shoppinglistapp.dal.dao.ShopListDao
import be.bf.android.shoppinglistapp.dal.entities.ShopList
import be.bf.android.shoppinglistapp.dal.entities.ShopListWithDetail
import kotlinx.coroutines.flow.Flow

class ShopRepository(private val shopListDao: ShopListDao) {
    //val readAllData: LiveData<List<ShopList>> = shopListDao.readAll()
      val readAllData: Flow<List<ShopListWithDetail>> = shopListDao.readAllShoplistWithDetail()
//    suspend fun addUser(user: User){
//        userDao.addUser(user)
//    }





    suspend fun addShopList(shopList: ShopList){
        shopListDao.addShopList(shopList)
    }


    fun getLastShopId(shopList: ShopList): Flow<Int>{
        return shopListDao.getLastShopId()
    }

    fun searchShopList(searchQuery: String): Flow<List<ShopListWithDetail>> {
        return shopListDao.searchShopList(searchQuery)
    }

}