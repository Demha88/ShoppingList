package be.bf.android.shoppinglistapp.fragments.detailList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.bf.android.shoppinglistapp.R
import be.bf.android.shoppinglistapp.dal.entities.DetailListViewModel
import be.bf.android.shoppinglistapp.databinding.FragmentDetailBinding
import be.bf.android.shoppinglistapp.fragments.shopList.ListAdapter

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding?=null
    private val binding get() =_binding!!

    private lateinit var detailListViewModel: DetailListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_detail, container, false)

        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        // Recyclerview
        val adapter = DetailAdapter()
        val recyclerView = binding.recyclerview
        recyclerView.adapter=adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())



        // DetailListViewModel
        detailListViewModel = ViewModelProvider(this).get(DetailListViewModel::class.java)
        detailListViewModel.readAllDetail.observe(viewLifecycleOwner, Observer {
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
            findNavController().navigate(R.id.action_detailFragment_to_addDetailFragment)

        }




        return binding.root
    }


}