package com.udacity.shoestore

import androidx.databinding.InverseMethod
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class SharedViewModel: ViewModel() {

    private lateinit var shoes: MutableList<Shoe>

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()

    val shoeList: LiveData<MutableList<Shoe>>
        get() =_shoeList

    private val _shoe = MutableLiveData<Shoe>()

    val shoe: LiveData<Shoe>
        get() = _shoe

//    private val _shoeName = MutableLiveData<String>()
//    val shoeName: LiveData<String>
//        get() = _shoeName
//
//    private val _shoeCompany = MutableLiveData<String>()
//    val shoeCompany: LiveData<String>
//        get() = _shoeCompany
//
//    private val _shoeSize = MutableLiveData<String>()
//    val shoeSize: LiveData<String>
//        get() = _shoeSize
//
//    private val _shoeDescription = MutableLiveData<String>()
//    val shoeDescription: LiveData<String>
//        get() = _shoeDescription

    init {
        createList()
        _shoeList.value = shoes
    }

    private fun createList(){
        shoes = mutableListOf(
            Shoe("The one", 35.0, "Adidas", "The best shoe ever"),
            Shoe("Alrpargatas", 37.0, "llaneras", "The best alpargatas ever"),
            Shoe("bailarinas", 39.0, "tu marca", "la bailarinas delicadas"),
            Shoe("tacon de cenicienta", 36.0, "hada madrina company", "los tacones de cristal"),
        )
    }

    fun addShoe(shoe: Shoe){
        shoes.add(shoe)
        _shoeList.value = shoes
    }
}