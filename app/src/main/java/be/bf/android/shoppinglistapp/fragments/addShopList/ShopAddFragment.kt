package be.bf.android.shoppinglistapp.fragments.addShopList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import be.bf.android.shoppinglistapp.R
import be.bf.android.shoppinglistapp.dal.ShopDatabase
import be.bf.android.shoppinglistapp.dal.entities.ShopList
import be.bf.android.shoppinglistapp.databinding.FragmentShopAddBinding
import com.google.android.material.snackbar.Snackbar

class ShopAddFragment : Fragment() {

    private var _binding: FragmentShopAddBinding?=null
    private val binding get() = _binding!!

    private lateinit var dBase : ShopDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_shop_add, container, false)

        _binding = FragmentShopAddBinding.inflate(inflater, container, false)

        dBase = ShopDatabase.getDatabase(requireContext())
        binding.addShpButton.setOnClickListener {
            InsertDataShoptoDB()
        }

        binding.cancelShpButton.setOnClickListener {
            findNavController().navigate(R.id.action_shopAddFragment_to_homeFragment)
        }

        return binding.root

    }

    private fun InsertDataShoptoDB() {
        val listAddName = binding.listAddName.text.toString()
        val listAddTag = binding.listAddTag.text.toString()

        if (listAddName.isNotEmpty() && listAddTag.isNotEmpty()){
            val shopList = ShopList(listAddName, listAddTag)
            // insertion dans DB
            dBase.ShopListDao().addShopList(shopList)
            Toast.makeText(requireContext(), "Ajouté avec succès!", Toast.LENGTH_LONG).show()
            //Snackbar.make(binding.root,"Ajouté avec succès!", Snackbar.LENGTH_LONG ).show()


            // retour vers liste principale
            findNavController().navigate(R.id.action_shopAddFragment_to_shopListFragment)

        } else {
            //Toast.makeText(requireContext(), "Entrez les données !", Toast.LENGTH_LONG).show()
            Snackbar.make(binding.root,"Entrez les données !", Snackbar.LENGTH_LONG ).show()
        }
    }


}