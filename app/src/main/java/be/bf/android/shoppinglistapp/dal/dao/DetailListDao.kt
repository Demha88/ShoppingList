package be.bf.android.shoppinglistapp.dal.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import be.bf.android.shoppinglistapp.dal.entities.DetailList


@Dao
interface DetailListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDetailList(detailList: DetailList)

    @Query("SELECT * FROM detailList_table")
    fun readAllDetailList(): LiveData<List<DetailList>>




}