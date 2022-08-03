package be.bf.android.shoppinglistapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import be.bf.android.shoppinglistapp.R
import be.bf.android.shoppinglistapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
//        val bind = FragmentHomeBinding.inflate(inflater, container, false)
//        return bind.root

    }


}