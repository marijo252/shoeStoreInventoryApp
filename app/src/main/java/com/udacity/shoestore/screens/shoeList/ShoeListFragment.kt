package com.udacity.shoestore.screens.shoeList

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.SharedViewModel
import com.udacity.shoestore.databinding.ShoeCardBinding
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment: Fragment() {
    private lateinit var  binding: ShoeListFragmentBinding

    private val model: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.shoe_list_fragment, container, false)

        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment())
        }
        setHasOptionsMenu(true)

        binding.lifecycleOwner = this
        model.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->
            createShoeCards(shoeList)
        })
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.loginFragment -> {
                findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
                true
            }
            else -> false
        }
        //return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
        //        || super.onOptionsItemSelected(item)
    }

    private fun createShoeCards(shoeList: MutableList<Shoe>){
        for(shoe in shoeList){
            val shoeCardBinding = ShoeCardBinding.inflate(LayoutInflater.from(requireContext()), binding.shoelistLinearLayout, false)
            shoeCardBinding.apply {
                this.shoeNameCard.text = shoe.name
                this.shoeCompanyCard.text = shoe.company
                this.shoeSizeCard.text = shoe.size.toString()
                this.shoeDescriptionCard.text = shoe.description
            }
            binding.shoelistLinearLayout.addView(shoeCardBinding.root)
        }
    }
}