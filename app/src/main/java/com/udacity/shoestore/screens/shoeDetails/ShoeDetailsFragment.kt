package com.udacity.shoestore.screens.shoeDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.SharedViewModel
import com.udacity.shoestore.databinding.ShoeDetailsFragmentBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.screens.shoeList.ShoeListFragmentDirections
import kotlinx.android.synthetic.main.shoe_details_fragment.*

class ShoeDetailsFragment: Fragment() {
    private lateinit var  binding: ShoeDetailsFragmentBinding

    private val model: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.shoe_details_fragment, container, false)

        binding.saveButton.setOnClickListener{

            val size = if (binding.sizeEditText.text.isEmpty()) 0.0 else binding.sizeEditText.text.toString().toDouble()
            val name = if (binding.nameEditText.text.isEmpty()) "shoeName" else binding.nameEditText.text.toString()
            val company = if (binding.companyEditText.text.isEmpty()) "shoeCompany" else binding.companyEditText.text.toString()
            val description = if(binding.descriptionEditText.text.isEmpty()) "shoeDescription" else binding.descriptionEditText.text.toString()

            val shoe = Shoe(name, size, company, description)
            model.addShoe(shoe)
            findNavController().popBackStack()
        }

        binding.cancelButton.setOnClickListener{
            findNavController().popBackStack()
        }
        return binding.root
    }
}