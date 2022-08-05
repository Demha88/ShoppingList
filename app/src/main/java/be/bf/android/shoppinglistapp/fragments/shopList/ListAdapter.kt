package be.bf.android.shoppinglistapp.fragments.shopList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import be.bf.android.shoppinglistapp.dal.entities.ShopListWithDetail
import be.bf.android.shoppinglistapp.databinding.AdapterShoplistBinding
//import kotlinx.android.synthetic.main.adapter_shoplist.view.*


class ListAdapter(): RecyclerView.Adapter<ListAdapter.MyViewHolder>()  {

    //private var shopList = emptyList<ShopList>()
    private var shopList = emptyList<ShopListWithDetail>()


    //class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){}
    class MyViewHolder(val binding: AdapterShoplistBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(get: ShopListWithDetail){
            binding.listName.text = get.shopList.listName
            binding.tagName.text = get.shopList.tagName
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

       // holder.bind(shopList.get(position))
        holder.bind(shopList.get(position))

    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    fun updateData(shopList: List<ShopListWithDetail>){
        this.shopList = shopList
        notifyDataSetChanged()
    }

}