package be.bf.android.shoppinglistapp.dal.entities

import androidx.lifecycle.LiveData
import androidx.room.Embedded
import androidx.room.Relation

data class ShopListWithDetail (
    @Embedded val shopList: ShopList,
    @Relation(
        parentColumn ="list_id",
        entityColumn = "list_id"
    )
    val detailList: List<DetailList>

)