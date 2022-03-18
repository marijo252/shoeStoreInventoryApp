package com.udacity.shoestore

import android.widget.LinearLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class SharedViewModel: ViewModel() {

    private lateinit var shoes: MutableList<Shoe>

    private val _isLogged = MutableLiveData<Boolean>()

    val isLogged: LiveData<Boolean>
        get() = _isLogged

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()

    val shoeList: LiveData<MutableList<Shoe>>
        get() =_shoeList

    init {
        createList()
        _shoeList.value = shoes
        _isLogged.value = false
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