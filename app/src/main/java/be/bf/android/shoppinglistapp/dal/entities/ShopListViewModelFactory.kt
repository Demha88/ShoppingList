package be.bf.android.shoppinglistapp.dal.entities

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.bf.android.shoppinglistapp.dal.ShopDatabase

class ShopListViewModelFactory(val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShopListViewModel(ShopDatabase.getDatabase(context)) as T
    }
}
