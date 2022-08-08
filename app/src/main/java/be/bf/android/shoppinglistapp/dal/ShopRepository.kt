package be.bf.android.shoppinglistapp.dal

import androidx.lifecycle.LiveData
import be.bf.android.shoppinglistapp.dal.dao.ShopListDao
import be.bf.android.shoppinglistapp.dal.entities.ShopList
import be.bf.android.shoppinglistapp.dal.entities.ShopListWithDetail

class ShopRepository(private val shopListDao: ShopListDao) {
    //val readAllData: LiveData<List<ShopList>> = shopListDao.readAll()
      val readAllData: LiveData<List<ShopListWithDetail>> = shopListDao.readAllShoplistWithDetail()
//    suspend fun addUser(user: User){
//        userDao.addUser(user)
//    }

    fun addShopList(shopList: ShopList){
        shopListDao.addShopList(shopList)
    }

    fun getLastShopId(shopList: ShopList): LiveData<Long>{
        return shopListDao.getLastShopId()
    }

}