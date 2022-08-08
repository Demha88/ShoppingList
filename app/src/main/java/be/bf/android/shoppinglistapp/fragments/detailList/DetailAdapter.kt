package be.bf.android.shoppinglistapp.fragments.detailList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import be.bf.android.shoppinglistapp.dal.entities.DetailList
import be.bf.android.shoppinglistapp.dal.entities.ShopListWithDetail
import be.bf.android.shoppinglistapp.databinding.AdapterDetailBinding

class DetailAdapter() : RecyclerView.Adapter<DetailAdapter.MyViewHolder>() {



    private var detailList = emptyList<DetailList>()

    class MyViewHolder(val binding: AdapterDetailBinding): RecyclerView.ViewHolder(binding.root){
        fun bindin(get: DetailList ){
            binding.detailName.text = get.detailName
            binding.textCategorie.text = get.categorie
            binding.textQtite.text = get.quantite.toString()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = AdapterDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindin(detailList[position])
    }

    override fun getItemCount(): Int {
        return detailList.size
    }

    fun updateDetail(detailList: List<DetailList>){
        this.detailList = detailList
        notifyDataSetChanged()
    }
}