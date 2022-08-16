package be.bf.android.shoppinglistapp.dal

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import be.bf.android.shoppinglistapp.dal.dao.DetailListDao
import be.bf.android.shoppinglistapp.dal.entities.DetailList
import kotlinx.coroutines.flow.Flow
import kotlin.properties.Delegates

class DetailRepository(private val detailListDao: DetailListDao) {
    //private var list_id: Long?=null

    var readAllDetail: Flow<List<DetailList>> = detailListDao.readAllDetailList()

//    var readAllDetailById: LiveData<List<DetailList>> = detailListDao.readDetailListById(list_id!!)


//    suspend fun addDetailList(detailList: DetailList){
//        detailListDao.addDetailList(detailList)
//    }
    suspend fun addDetailList(detailList: DetailList) = detailListDao.addDetailList(detailList)


    fun getListDetail(id: Int) : Flow<List<DetailList>> {
        return detailListDao.readDetailListById(id)
    }

    suspend fun updateDetailList(detailList: DetailList){
        detailListDao.updateDetailList(detailList)
    }

    suspend fun deleteDetailList(detailList: DetailList){
        detailListDao.deleteDetailList(detailList)
    }




}