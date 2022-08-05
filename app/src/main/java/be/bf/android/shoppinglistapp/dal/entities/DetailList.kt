package be.bf.android.shoppinglistapp.dal.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "detailList_table")
data class DetailList constructor(
    @ColumnInfo(name="detailName")
    val detailName: String,
    @ColumnInfo(name="quantite")
    val quantite: Int,
    @ColumnInfo(name="list_id") val shopList_Id: Int,

    @PrimaryKey(autoGenerate = true)
@ColumnInfo(name = "detailList_id")
var id: Int=0

){
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "detailList_id")
//    var id: Int=0

//    constructor(id:Int, detailName: String, quantite: Int, shopList_Id: Int): this(detailName,quantite){
//        this.id=id
//    }

}