package be.bf.android.shoppinglistapp.fragments.detailList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.bf.android.shoppinglistapp.R
import be.bf.android.shoppinglistapp.dal.dao.DetailListDao
import be.bf.android.shoppinglistapp.dal.entities.ShopListViewModel
import be.bf.android.shoppinglistapp.databinding.FragmentDetailBinding
import be.bf.android.shoppinglistapp.fragments.shopList.ListAdapter

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding?=null
    private val binding get() =_binding!!

    private lateinit var detailListViewModel: ShopListViewModel
    private lateinit var detailListDao: DetailListDao

    private var listId : Long = 0



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

        val bund = arguments?.get("position") as Long
//        var bund :Long?=null
//        recupId(bund!!)


        // Recyclerview
        val adapter = DetailAdapter()
        val recyclerView = binding.recyclerview
        recyclerView.adapter=adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())





        // DetailListViewModel
//        detailListViewModel = ViewModelProvider(this).get(ShopListViewModel::class.java)
//        detailListViewModel.readAllDetail.observe(viewLifecycleOwner, Observer {
//            detailList -> adapter.updateDetail(detailList)
//        })
        detailListDao.readDetailListById(bund).observe(viewLifecycleOwner, Observer {
            detailList -> adapter.updateDetail(detailList)
        })


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
    override fun onResume() {
        super.onResume()
        val pos = arguments?.get("position")

        //Toast.makeText(requireContext(), "You clicked on item no. $pos", Toast.LENGTH_SHORT).show()

    }


}