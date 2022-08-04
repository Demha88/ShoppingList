package be.bf.android.shoppinglistapp.fragments.shopList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import be.bf.android.shoppinglistapp.R
import be.bf.android.shoppinglistapp.R.layout.*
import be.bf.android.shoppinglistapp.dal.entities.ShopList
import be.bf.android.shoppinglistapp.dal.entities.ShopListViewModel
import be.bf.android.shoppinglistapp.databinding.FragmentShopListBinding
import kotlinx.android.synthetic.main.fragment_shop_list.view.*


class ShopListFragment : Fragment() {

    private var _binding: FragmentShopListBinding?=null
    private val binding get() = _binding!!

    private lateinit var shopListViewModel: ShopListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val view = inflater.inflate(R.layout.fragment_shop_list, container, false)

        //Remplacer les extensions par view binding
        _binding = FragmentShopListBinding.inflate(inflater, container, false)


        //Recyclerview
        val adapter = ListAdapter()
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //ShopListViewModel
        shopListViewModel = ViewModelProvider(this).get(ShopListViewModel::class.java)
        shopListViewModel.readAllData.observe(viewLifecycleOwner, Observer {
                shopList -> adapter.updateData(shopList)
        })

//        view.floatingActionButton.setOnClickListener {
//            findNavController().navigate(R.id.action_shopListFragment_to_shopAddFragment)
//        }
//        return view
        // Remplacement de l'extension par view binding
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_shopListFragment_to_shopAddFragment)
        }

        return binding.root

    }

    // Mettre les _binding à nul lors de la destructin du fragment pr éviter leaks memory
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}