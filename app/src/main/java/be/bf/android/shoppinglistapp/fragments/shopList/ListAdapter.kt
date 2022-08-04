package be.bf.android.shoppinglistapp.fragments.shopList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import be.bf.android.shoppinglistapp.R
import be.bf.android.shoppinglistapp.dal.entities.ShopList
import kotlinx.android.synthetic.main.adapter_shoplist.view.*


class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>()  {

    private var shopList = emptyList<ShopList>()


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_shoplist, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentList = shopList[position]
        holder.itemView.listName.text = currentList.listName.toString()
        holder.itemView.tagName.text = currentList.tagName.toString()
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    fun updateData(shopList: List<ShopList>){
        this.shopList = shopList
        notifyDataSetChanged()
    }

}