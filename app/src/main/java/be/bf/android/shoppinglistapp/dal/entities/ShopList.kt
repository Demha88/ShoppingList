package be.bf.android.shoppinglistapp.dal.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "shopping_table")
data class ShopList @Ignore constructor(
    @ColumnInfo(name="listName")
    val listName: String,
    @ColumnInfo(name="tagName")
    val tagName: String,

    ){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "list_id")
    var id: Int=0

    constructor(id:Int, listName: String, tagName: String): this(listName,tagName){
        this.id=id
    }
}