package be.bf.android.shoppinglistapp.dal.entities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.bf.android.shoppinglistapp.dal.DetailRepository
import be.bf.android.shoppinglistapp.dal.ShopDatabase
import be.bf.android.shoppinglistapp.dal.ShopRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShopListViewModel(val database : ShopDatabase): ViewModel() {

    //val readAllData: LiveData<List<ShopList>>
    val readAllData: LiveData<List<ShopListWithDetail>>
    private val repository: ShopRepository

    // DetailList
    var readAllDetail: LiveData<List<DetailList>>
    var repo: DetailRepository

    init {
        val shopListDao = database.ShopListDao()
        repository = ShopRepository(shopListDao)
        readAllData = repository.readAllData

        //DetailList
        val detailListDao = database.DetailListDao()
        repo = DetailRepository(detailListDao)
        readAllDetail = repo.readAllDetail
    }

    fun addShopList(shopList: ShopList){
        viewModelScope.launch(Dispatchers.IO){
            repository.addShopList(shopList)
        }
    }

    fun getLastShopId(shopList: ShopList): LiveData<Long> {
        return repository.getLastShopId(shopList)
    }


    fun addDetailList(detailList: DetailList){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addDetailList(detailList)
        }
    }

    fun getDetailList(detailListId : Long) {
        viewModelScope.launch(Dispatchers.IO) {
            readAllDetail = repo.getListDetail(detailListId)
        }
    }

}