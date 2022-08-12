package be.bf.android.shoppinglistapp.dal.entities

import android.util.Log
import androidx.lifecycle.*
import be.bf.android.shoppinglistapp.dal.DetailRepository
import be.bf.android.shoppinglistapp.dal.ShopDatabase
import be.bf.android.shoppinglistapp.dal.ShopRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ShopListViewModel(val database : ShopDatabase): ViewModel() {
//class ShopListViewModel(application: Application): AndroidViewModel(application) {


    //val readAllData: LiveData<List<ShopList>>
    private val _readAllData: MutableLiveData<List<ShopListWithDetail>> = MutableLiveData()
    val readAllData : LiveData<List<ShopListWithDetail>>
        get() = _readAllData
    private val repository: ShopRepository

    // DetailList
    private val _readAllDetail: MutableLiveData<List<DetailList>> = MutableLiveData()
    val readAllDetail : LiveData<List<DetailList>>
        get() = _readAllDetail
    lateinit var repo: DetailRepository

    //DetailListById


    init {
        val shopListDao = database.ShopListDao()
        val detailListDao = database.DetailListDao()
        repository = ShopRepository(shopListDao)
        repo = DetailRepository(detailListDao)
        viewModelScope.launch {
    //        val shopListDao = ShopDatabase.getDatabase(application).ShopListDao()
            repository.readAllData.collect() {
                _readAllData.value = it
            }
            //DetailList
    //        val detailListDao = ShopDatabase.getDatabase(application).DetailListDao()
            repo.readAllDetail.collect() {
                _readAllDetail.value = it
            }
            //readAllDetail = repo.readAllDetailById
        }

    }

    fun addShopList(shopList: ShopList){
        viewModelScope.launch {
            repository.addShopList(shopList)
        }
    }

//    fun getLastShopId(shopList: ShopList): LiveData<Long> {
//        return repository.getLastShopId(shopList)
//    }


    fun addDetailList(detailList: DetailList){
        viewModelScope.launch {
            repo.addDetailList(detailList)
        }
    }

    fun getDetailList(detailListId : Long) {
        viewModelScope.launch {
            Log.d("getDatailLis", "pass")
            repo.getListDetail(detailListId).collect() {
                _readAllDetail.value = it
            }
        }
    }

}