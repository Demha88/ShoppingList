package be.bf.android.shoppinglistapp.dal

import androidx.lifecycle.LiveData
import be.bf.android.shoppinglistapp.dal.dao.DetailListDao
import be.bf.android.shoppinglistapp.dal.entities.DetailList

class DetailRepository(private val detailListDao: DetailListDao) {

    var readAllDetail: LiveData<List<DetailList>> = detailListDao.readAllDetailList()

    fun addDetailList(detailList: DetailList){
        detailListDao.addDetailList(detailList)
    }


}