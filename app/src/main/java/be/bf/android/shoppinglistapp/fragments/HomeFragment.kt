package be.bf.android.shoppinglistapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import be.bf.android.shoppinglistapp.R
import be.bf.android.shoppinglistapp.databinding.FragmentHomeBinding
import be.bf.android.shoppinglistapp.databinding.FragmentShopListBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_home, container, false)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // vers ListFragment
        binding.listButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_shopListFragment)
        }

        // vers addList fragment
        binding.addlistButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_shopAddFragment2)
        }

        return binding.root



    }


}