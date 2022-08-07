package be.bf.android.shoppinglistapp.dal.entities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import be.bf.android.shoppinglistapp.dal.DetailRepository
import be.bf.android.shoppinglistapp.dal.ShopDatabase


class DetailListViewModel(application: Application): AndroidViewModel(application) {

    var readAllDetail: LiveData<List<DetailList>>
    var repo: DetailRepository

    init {
        val detailListDao = ShopDatabase.getDatabase(application).DetailListDao()
        repo = DetailRepository(detailListDao)
        readAllDetail = repo.readAllDetail
    }


}