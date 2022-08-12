package be.bf.android.shoppinglistapp.dal.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import be.bf.android.shoppinglistapp.dal.entities.DetailList
import kotlinx.coroutines.flow.Flow


@Dao
interface DetailListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun addDetailList(detailList: DetailList)

    @Query("SELECT * FROM detailList_table")
    fun readAllDetailList(): Flow<List<DetailList>>

    @Query("SELECT * FROM detailList_table WHERE list_id = :list_Id")
    fun readDetailListById(list_Id :Int): Flow<List<DetailList>>


}