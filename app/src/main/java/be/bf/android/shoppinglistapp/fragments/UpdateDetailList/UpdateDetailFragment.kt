package be.bf.android.shoppinglistapp.fragments.UpdateDetailList

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import be.bf.android.shoppinglistapp.R
import be.bf.android.shoppinglistapp.dal.ShopDatabase
import be.bf.android.shoppinglistapp.dal.entities.DetailList
import be.bf.android.shoppinglistapp.dal.entities.ShopListViewModel
import be.bf.android.shoppinglistapp.dal.entities.ShopListViewModelFactory
import be.bf.android.shoppinglistapp.databinding.FragmentDetailBinding
import be.bf.android.shoppinglistapp.databinding.FragmentUpdateDetailBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [UpdateDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpdateDetailFragment : Fragment() {

    private var _binding: FragmentUpdateDetailBinding?=null
    private val binding get() =_binding!!

    private val args by navArgs<UpdateDetailFragmentArgs>()

    private lateinit var dBase : ShopDatabase
    private val detailListViewModel : ShopListViewModel by activityViewModels { ShopListViewModelFactory(requireContext()) }



    private val categorie = arrayOf("Divers", "Nourriture","Fruits", "Légues", "Hygiène/Santé", "Prod Ménagers", "Mode")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
      //return inflater.inflate(R.layout.fragment_update_detail, container, false)
        _binding = FragmentUpdateDetailBinding.inflate(inflater, container, false)


        binding.nomDetailUpdate.setText(args.currentDetailList.detailName)
        binding.nbQuantiteUpdate.setText(args.currentDetailList.quantite.toString())

        val spinner = binding.catSpinnerUpdate
        spinner.adapter = ArrayAdapter(requireContext(),R.layout.spinner_list, categorie )
        val adpat = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categorie)
        adpat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adpat
        //binding.catSpinnerUpdate.adapter.getItem(args.currentDetailList.categorie.get(id))
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val reslt = categorie[position]
               // Toast.makeText(requireContext(), reslt, Toast.LENGTH_SHORT).show()


                dBase = ShopDatabase.getDatabase(requireContext())

                binding.updateButton.setOnClickListener {

                    val updateDetailName = binding.nomDetailUpdate.text.toString()
                    val updateDetailQuantite = binding.nbQuantiteUpdate.text
                    val categorie = reslt.toString()
                    val id = args.currentDetailList.id
                    val id2 = args.currentDetailList.shopList_Id


                    if (updateDetailName.isNotEmpty() && updateDetailQuantite.isNotEmpty()) {
                        val updateDetailList = DetailList(id,
                            updateDetailName,
                            categorie,
                            Integer.parseInt(updateDetailQuantite.toString()),
                            false,
                            id2
                        )

                        //Update DetailList
//                        GlobalScope.launch {
//                            dBase.DetailListDao().updateDetailList(updateDetailList)
//                        }
                        detailListViewModel.updateDetailList(updateDetailList)
                        Toast.makeText(
                            requireContext(),
                            "Mise à jour effectuée!",
                            Toast.LENGTH_LONG
                        ).show()
                        //Navigate vers DetailList fragment // findNavController().navigate(R.id.action_updateDetailFragment_to_detailFragment)
                        findNavController().navigate(R.id.action_updateDetailFragment_to_shopListFragment)
                    } else {
                        Snackbar.make(binding.root, "Entrez les données !", Snackbar.LENGTH_LONG)
                            .show()
                    }
                }

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        binding.cancelButton.setOnClickListener {
            //val pos = args.currentDetailList.shopList_Id
//            val bundle = bundleOf(
//                "position" to detailList[]
//                "titre_liste" to shopList[position.toInt()-1].shopList.listName,
//              "tag_liste" to shopList[position.toInt()-1].shopList.tagName,
//               "nbre_elemt" to shopList[position.toInt()-1].detailList.size,
//
//                )
//            detailListViewModel.itemId.observe(viewLifecycleOwner, Observer {
//                id -> Toast.makeText(requireContext(), "Id est $id", Toast.LENGTH_LONG).show()
//            })
            findNavController().navigate(R.id.action_updateDetailFragment_to_detailFragment)
        }





        return binding.root
    }




}