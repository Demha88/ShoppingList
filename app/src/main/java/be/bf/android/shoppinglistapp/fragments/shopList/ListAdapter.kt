package be.bf.android.shoppinglistapp.fragments.shopList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import be.bf.android.shoppinglistapp.R
import be.bf.android.shoppinglistapp.dal.entities.ShopList
import be.bf.android.shoppinglistapp.databinding.AdapterShoplistBinding
//import kotlinx.android.synthetic.main.adapter_shoplist.view.*


class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>()  {

    private var shopList = emptyList<ShopList>()


    //class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){}
    class MyViewHolder(val binding: AdapterShoplistBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(get: ShopList){
            binding.listName.text = get.listName
            binding.tagName.text = get.tagName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_shoplist, parent, false))
        val binding = AdapterShoplistBinding.inflate(LayoutInflater.from(parent.context), parent, false )
        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val currentList = shopList[position]
//        holder.itemView.listName.text = currentList.listName.toString()
//        holder.itemView.tagName.text = currentList.tagName.toString()
        holder.bind(shopList.get(position))
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    fun updateData(shopList: List<ShopList>){
        this.shopList = shopList
        notifyDataSetChanged()
    }

}