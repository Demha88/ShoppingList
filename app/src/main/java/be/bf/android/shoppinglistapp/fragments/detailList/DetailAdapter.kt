package be.bf.android.shoppinglistapp.fragments.detailList

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.compose.ui.graphics.Paint
import androidx.core.os.bundleOf
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import be.bf.android.shoppinglistapp.dal.entities.DetailList
import be.bf.android.shoppinglistapp.dal.entities.ShopListWithDetail
import be.bf.android.shoppinglistapp.databinding.AdapterDetailBinding
import be.bf.android.shoppinglistapp.fragments.shopList.ListAdapter
import be.bf.android.shoppinglistapp.fragments.shopList.ShopListFragmentDirections

class DetailAdapter(private val onDeleteClickListener: OnDeleteClickListener) : RecyclerView.Adapter<DetailAdapter.MyViewHolder>() {

interface OnDeleteClickListener{
    fun onDeleteClickListener(detailList: DetailList)
}

    private var detailList = emptyList<DetailList>()
    private var totalCheckedBox:Int=0

    class MyViewHolder(val binding: AdapterDetailBinding): RecyclerView.ViewHolder(binding.root){
        fun bindin(get: DetailList ){
            binding.detailName.text = get.detailName
            binding.textCategorie.text = get.categorie
            binding.textQtite.text = get.quantite.toString()
            //binding.checkBox3.isChecked = get.isChecked

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = AdapterDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindin(detailList[position])
        var pos = detailList[position]
        holder.binding.detailName.text = pos.detailName
        holder.binding.textQtite.text = pos.quantite.toString()



        holder.itemView.apply {
            holder.binding.detailName.text = pos.detailName
            holder.binding.checkBox3.isChecked = pos.isChecked
            detailNameStrikeThrough(holder.binding.detailName, pos.isChecked)
//            holder.binding.checkBox3.setOnCheckedChangeListener { _, isChecked ->
//                detailNameStrikeThrough(holder.binding.detailName, isChecked)
//                pos.isChecked = !pos.isChecked
//            }
            holder.binding.checkBox3.setOnClickListener {
                if (holder.binding.checkBox3.isChecked){
                    detailNameStrikeThrough(holder.binding.detailName, true)
                   totalCheckedBox +=totalCheckedBox

                } else {
                    detailNameStrikeThrough(holder.binding.detailName, false)
                    totalCheckedBox-=totalCheckedBox
                }
                pos.isChecked = !pos.isChecked


            }
            holder.binding.updateDetail.setOnClickListener {
                val actionUpdate = DetailFragmentDirections.actionDetailFragmentToUpdateDetailFragment(pos)
                holder.itemView.findNavController().navigate(actionUpdate)
            }

            holder.binding.deleteDetail.setOnClickListener {
                onDeleteClickListener.onDeleteClickListener(pos)
            }

        }

//        holder.binding.updateDetail.setOnClickListener {
//            val actionUpdate = DetailFragmentDirections.actionDetailFragmentToUpdateDetailFragment(pos)
//            holder.itemView.findNavController().navigate(actionUpdate)
//        }
    }

    override fun getItemCount(): Int {
        return detailList.size
    }

    fun updateDetail(detailList: List<DetailList>){
        this.detailList = detailList
        notifyDataSetChanged()
    }

    private fun detailNameStrikeThrough(textDetail: TextView, isChecked: Boolean){

        if(isChecked){
            textDetail.paintFlags = textDetail.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG

        } else {
            textDetail.paintFlags = textDetail.paintFlags and android.graphics.Paint.STRIKE_THRU_TEXT_FLAG.inv()


        }
    }


}