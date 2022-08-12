package be.bf.android.shoppinglistapp.fragments.detailList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.bf.android.shoppinglistapp.R
import be.bf.android.shoppinglistapp.dal.DetailRepository
import be.bf.android.shoppinglistapp.dal.ShopDatabase
import be.bf.android.shoppinglistapp.dal.dao.DetailListDao
import be.bf.android.shoppinglistapp.dal.entities.ShopListViewModel
import be.bf.android.shoppinglistapp.dal.entities.ShopListViewModelFactory
import be.bf.android.shoppinglistapp.dal.entities.ShopListWithDetail
import be.bf.android.shoppinglistapp.databinding.FragmentDetailBinding
import be.bf.android.shoppinglistapp.fragments.shopList.ListAdapter

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding?=null
    private val binding get() =_binding!!


    private val detailListViewModel : ShopListViewModel by activityViewModels { ShopListViewModelFactory(requireContext()) }
//    private lateinit var detailListViewModel: ShopListViewModel

    // private var listId : Long = 0

    var bund : Int = 0



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_detail, container, false)

        _binding = FragmentDetailBinding.inflate(inflater, container, false)

//        val bundle = Bundle().apply {
//            listId = arguments?.get("position") as Long
//        }



//        var bund :Long?=null
//        recupId(bund!!)

        bund = arguments?.get("position") as Int
         // bund = requireArguments().get("position") as Int
        // Recyclerview
        val adapter = DetailAdapter()
        val recyclerView = binding.recyclerview
        recyclerView.adapter=adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())





        // DetailListViewModel
//        detailListViewModel = ViewModelProvider(this).get(ShopListViewModel::class.java)
        detailListViewModel.readAllDetail.observe(viewLifecycleOwner, Observer {
            Log.d("data", it.toString())
            adapter.updateDetail(it)
        })

        //detailListViewModel.getDetailList(bund)
//        detailListViewModel.readAllDetail.observe(viewLifecycleOwner, Observer {
//            detailList -> adapter.updateDetail(detailList)
//        })

//        detailListDao.readDetailListById(bund).observe(viewLifecycleOwner, Observer {
//            detailList -> adapter.updateDetail(detailList)
//        })


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




        binding.floatingActionButton2.setOnClickListener {
            //val bundle = bundleOf("test" to id)
            val bundle = bundleOf("position" to bund)
            findNavController().navigate(R.id.action_detailFragment_to_addDetailFragment, bundle)

        }




        return binding.root
    }

    // Récupérer l'arg
//    override fun onResume() {
//        super.onResume()
//        val pos = arguments?.get("position")
//
//        //Toast.makeText(requireContext(), "You clicked on item no. $pos", Toast.LENGTH_SHORT).show()
//
//    }

    override fun onResume() {
        super.onResume()
        detailListViewModel.getDetailList(bund)
    }
}