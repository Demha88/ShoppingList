package be.bf.android.shoppinglistapp.fragments.shopList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import be.bf.android.shoppinglistapp.R
import be.bf.android.shoppinglistapp.dal.entities.*
import be.bf.android.shoppinglistapp.databinding.FragmentShopListBinding
//import kotlinx.android.synthetic.main.fragment_shop_list.view.*


class ShopListFragment : Fragment() {

    private var _binding: FragmentShopListBinding?=null
    private val binding get() = _binding!!

    private val shopListViewModel: ShopListViewModel by activityViewModels() { ShopListViewModelFactory(requireContext()) }

    private var shopList : List<ShopListWithDetail> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //val view = inflater.inflate(R.layout.fragment_shop_list, container, false)

        //Remplacer les extensions par view binding
        _binding = FragmentShopListBinding.inflate(inflater, container, false)


        //Recyclerview
        val adapter = ListAdapter()
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        // Ajout pour onCLick Recycler
        adapter.setOnItemClickListener(object : ListAdapter.onItemClicklistener{
            override fun onItemClick(id: Long) {

                val bundle = bundleOf("position" to id)
               // val bundle = bundleOf("position" to shopList[id.toInt()-1].shopList.id)
                findNavController().navigate(R.id.action_shopListFragment_to_detailFragment, bundle)

            }


        })


        //ShopListViewModel
//        shopListViewModel = ViewModelProvider(this).get(ShopListViewModel::class.java)
        shopListViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            //shopList -> adapter.updateData(shopList)
            if (it != null) {
                shopList = it
                adapter.updateData(it)
            }
        })

//        view.floatingActionButton.setOnClickListener {
//            findNavController().navigate(R.id.action_shopListFragment_to_shopAddFragment)
//        }
//        return view
        // Remplacement de l'extension par view binding
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_shopListFragment_to_shopAddFragment)
        }

        // ajout séparateur entre les listes recyclerview
        // create a vertical layout manager
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                layoutManager.orientation
            )
        )
        // Fin ajout



        return binding.root
    }

    // Mettre les _binding à nul lors de la destruction du fragment pr éviter leaks memory
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}