package be.bf.android.shoppinglistapp.fragments.shopList

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import be.bf.android.shoppinglistapp.R
import be.bf.android.shoppinglistapp.dal.entities.*
import be.bf.android.shoppinglistapp.databinding.AdapterDetailBinding
import be.bf.android.shoppinglistapp.databinding.AdapterShoplistBinding
import be.bf.android.shoppinglistapp.databinding.FragmentShopListBinding
import be.bf.android.shoppinglistapp.fragments.detailList.DetailAdapter

//import kotlinx.android.synthetic.main.fragment_shop_list.view.*


class ShopListFragment : Fragment()/*, SearchView.OnQueryTextListener*/ {

    private var _binding: FragmentShopListBinding?=null
    private val binding get() = _binding!!
    private var _binding2: AdapterShoplistBinding?=null
    private val binding2 get()= _binding2!!


    private val shopListViewModel: ShopListViewModel by activityViewModels() { ShopListViewModelFactory(requireContext()) }
    //private val myListAdapter: ListAdapter by lazy { ListAdapter() }

    private var shopList : List<ShopListWithDetail> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //val view = inflater.inflate(R.layout.fragment_shop_list, container, false)

        //Remplacer les extensions par view binding
        _binding = FragmentShopListBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true);


        //Recyclerview
        val adapter = ListAdapter()
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //binding.recyclerview.adapter = myListAdapter
        // Ajout pour onCLick Recycler
        adapter.setOnItemClickListener(object : ListAdapter.onItemClicklistener{
            override fun onItemClick(position: Int) {

                //val bundle = bundleOf("position" to position)
                //val bundle = bundleOf("position" to shopList[position.toInt()-1].shopList.id)
                val bundle = bundleOf(
                    "position" to shopList[position.toInt()-1].shopList.id,
                    "titre_liste" to shopList[position.toInt()-1].shopList.listName,
                    "tag_liste" to shopList[position.toInt()-1].shopList.tagName,
                    "nbre_elemt" to shopList[position.toInt()-1].detailList.size,

                )
                shopListViewModel.loadShopList(shopList[position.toInt()-1])
                findNavController().navigate(R.id.action_shopListFragment_to_detailFragment/*, bundle*/)
            }
        })



        //ShopListViewModel
//        shopListViewModel = ViewModelProvider(this).get(ShopListViewModel::class.java)
        shopListViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            //shopList -> adapter.updateData(shopList)
            if (it != null) {
                shopList = it
                adapter.updateData(it)
                //myListAdapter.updateData(it)
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
// rechercheListe
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//         inflater.inflate(R.menu.shoplist_menu, menu)
//        val search = menu.findItem(R.id.menu_search)
//        val searchView = search?.actionView as? SearchView
//        searchView?.isSubmitButtonEnabled = true
//        searchView?.setOnQueryTextListener(this)
////        inflater.inflate(R.menu.menu_font_share, menu)
////        super.onCreateOptionsMenu(menu,inflater)
//    }
//
//    override fun onQueryTextSubmit(query: String?): Boolean {
//        if(query != null){
//            searchShopDataBase(query)
//        }
//        return true
//    }
//
//    override fun onQueryTextChange(query: String?): Boolean {
//        if(query != null){
//            searchShopDataBase(query)
//        }
//        return true
//    }
//
//    private fun searchShopDataBase(query: String){
//        val searchQuery = "%$query%"
//        shopListViewModel.searchShopList(searchQuery).observe(this) { list ->
//            list.let {
//                myListAdapter.updateData(it)
//            }
//        }
//    }

}