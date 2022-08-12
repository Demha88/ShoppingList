package be.bf.android.shoppinglistapp.fragments.addDetailList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import be.bf.android.shoppinglistapp.R
import be.bf.android.shoppinglistapp.dal.ShopDatabase
import be.bf.android.shoppinglistapp.dal.dao.DetailListDao
import be.bf.android.shoppinglistapp.dal.dao.ShopListDao
import be.bf.android.shoppinglistapp.dal.entities.*
import be.bf.android.shoppinglistapp.databinding.FragmentAddDetailBinding
import be.bf.android.shoppinglistapp.fragments.detailList.DetailFragment
import be.bf.android.shoppinglistapp.fragments.shopList.ListAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddDetailFragment : Fragment() {

    private var _binding: FragmentAddDetailBinding?=null
    private val binding get() = _binding!!

    private lateinit var dBase : ShopDatabase

    //private lateinit var shopListViewModel: ShopListViewModel

    private var listId : Int = 0


    private val categorie = arrayOf("Divers", "Nourriture","Nourriture Lait", "Hygiène et Santé", "Produit Ménagers", "Mode")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add_detail, container, false)

        _binding = FragmentAddDetailBinding.inflate(inflater, container, false)

        val bund = arguments?.get("position") as Long


//        dBase = ShopDatabase.getDatabase(requireContext())
//        binding.addButton.setOnClickListener {
//
//        }

        // test retrait id
        dBase = ShopDatabase.getDatabase(requireContext())
        //val shopList: ShopList
        val shopListDao: ShopListDao?=null
        //shopListViewModel = ViewModelProvider(this).get(ShopListViewModel::class.java)
        //val res = shopListViewModel.getLastShopId(shopList!!)
        val res = shopListDao?.getLastShopId()
        binding.testId.text = res.toString()

        val spinner = binding.catSpinner
        //spinner.adapter= ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, categorie)
        spinner.adapter = ArrayAdapter(requireContext(),R.layout.spinner_list, categorie )
         val adpat = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categorie)
        adpat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adpat

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
//                val type = p0?.getItemAtPosition(position).toString()
                val reslt = categorie[position]
                Toast.makeText(requireContext(), reslt, Toast.LENGTH_SHORT).show()
                //_binding?.textSpinner?.text  = reslt

                // Insertion dans DB
                dBase = ShopDatabase.getDatabase(requireContext())
                binding.addButton.setOnClickListener {
                    val detailName = binding.nomDetail.text.toString()
                    val quantite = binding.nbQuantite.text
                    val categorie = reslt.toString()
//                    // id ShopListFrag


                    //fin id ShopListFrag
                    if (detailName.isNotEmpty() && quantite.isNotEmpty()) {
//
                        val detailList = DetailList(
                            detailName,
                            categorie,
                            Integer.parseInt(quantite.toString()),
                            bund
                        )
                        GlobalScope.launch(Dispatchers.IO) {
                            dBase.DetailListDao().addDetailList(detailList)
                            //dBase.DetailListDao().readDetailListById(bund)
                        }
                        Toast.makeText(requireContext(), "Ajouté avec succès!", Toast.LENGTH_LONG)
                            .show()

                        //findNavController().navigate(R.id.action_addDetailFragment_to_detailFragment)
                        findNavController().navigate(R.id.action_addDetailFragment_to_shopListFragment)
                    } else {
                        //Toast.makeText(requireContext(), "Entrez les données !", Toast.LENGTH_LONG).show()
                        Snackbar.make(binding.root, "Entrez les données !", Snackbar.LENGTH_LONG)
                            .show()
                    }
                }


            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //println("Erreur !")
            }

        }

        return binding.root
    }

//    private fun InsertDetailToDatabase() {
//
//       val detailName = binding.nomDetail.text.toString()
//       val quantite = binding.nbQuantite.text
//       val categorie =
//
//        if (detailName.isNotEmpty() && quantite.isNotEmpty()){
//
//           val detailList = DetailList(detailName, categorie, Integer.parseInt(quantite.toString()), 0)
//            dBase.DetailListDao().addDetailList(detailList)
//            Toast.makeText(requireContext(), "Ajouté avec succès!", Toast.LENGTH_LONG).show()
//
//           // findNavController().navigate(R.id.action_addDetailFragment_to_detailFragment)
//        }
//        else {
//            //Toast.makeText(requireContext(), "Entrez les données !", Toast.LENGTH_LONG).show()
//            Snackbar.make(binding.root,"Entrez les données !", Snackbar.LENGTH_LONG ).show()
//        }
//    }

//    override fun onResume() {
//        super.onResume()
//        val pos = arguments?.get("position")
//
//        //Toast.makeText(requireContext(), "You clicked on item no. $pos", Toast.LENGTH_SHORT).show()
//
//    }

}