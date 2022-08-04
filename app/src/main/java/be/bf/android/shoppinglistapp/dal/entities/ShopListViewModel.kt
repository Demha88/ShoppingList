package be.bf.android.shoppinglistapp.dal.entities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import be.bf.android.shoppinglistapp.dal.ShopDatabase
import be.bf.android.shoppinglistapp.dal.ShopRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShopListViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<ShopList>>
    private val repository: ShopRepository

    init {
        val shopListDao = ShopDatabase.getDatabase(application).ShopListDao()
        repository = ShopRepository(shopListDao)
        readAllData = repository.readAllData
    }

    fun addShopList(shopList: ShopList){
        viewModelScope.launch(Dispatchers.IO){
            repository.addShopList(shopList)
        }

    }



}