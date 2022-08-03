package be.bf.android.shoppinglistapp.fragments.shopList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import be.bf.android.shoppinglistapp.R
import be.bf.android.shoppinglistapp.R.layout.*
import be.bf.android.shoppinglistapp.databinding.FragmentShopListBinding
import kotlinx.android.synthetic.main.fragment_shop_list.view.*


class ShopListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_shop_list, container, false)

//        binding.floatingActionButton.setOnClickListener {
//            findNavController().navigate(R.id.action_shopListFragment_to_shopAddFragment)
//     }
        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_shopListFragment_to_shopAddFragment)
        }
        return view
    }


}