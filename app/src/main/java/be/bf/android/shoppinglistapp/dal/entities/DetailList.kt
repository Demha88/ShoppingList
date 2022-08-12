package be.bf.android.shoppinglistapp.dal.entities

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import be.bf.android.shoppinglistapp.dal.entities.ShopList

//@Entity(tableName = "detailList_table", foreignKeys = [
//    ForeignKey(entity = ShopList::class,
//               parentColumns =  ["list_id"],
//               childColumns = ["list_id"],
//                onDelete = CASCADE
//        )])
//data class DetailList(
//    @PrimaryKey(autoGenerate = true)
//    val id: Long,
//    @ColumnInfo(name="detailName")
//    val detailName: String,
//    @ColumnInfo(name = "categorie")
//    val categorie: String,
//    @ColumnInfo(name="quantite")
//    val quantite: Int,
//    @ColumnInfo(name="list_id") var shopListId: Long,
//    )

@Entity(tableName = "detailList_table")
data class DetailList @Ignore constructor(
    @ColumnInfo(name="detailName")
    val detailName: String,
    @ColumnInfo(name = "categorie")
    val categorie: String,
    @ColumnInfo(name="quantite")
    val quantite: Int,
    @ColumnInfo(name="list_id") val shopList_Id: Int,


//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "detailList_id")
//    var id: Int=0

){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "detailList_id")
    var id: Int=0

    constructor(id:Int, detailName: String, categorie: String, quantite: Int, shopList_Id: Int): this(detailName,categorie, quantite, shopList_Id){
        this.id=id
    }


}