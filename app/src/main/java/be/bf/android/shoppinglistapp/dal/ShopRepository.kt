package be.bf.android.shoppinglistapp.dal

import androidx.lifecycle.LiveData
import be.bf.android.shoppinglistapp.dal.dao.ShopListDao
import be.bf.android.shoppinglistapp.dal.entities.ShopList

class ShopRepository(private val shopListDao: ShopListDao) {
    val readAllData: LiveData<List<ShopList>> = shopListDao.readAll()

//    suspend fun addUser(user: User){
//        userDao.addUser(user)
//    }

    fun addShopList(shopList: ShopList){
        shopListDao.addShopList(shopList)
    }

}