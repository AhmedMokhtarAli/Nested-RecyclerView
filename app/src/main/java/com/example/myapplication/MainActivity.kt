package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Constant.Companion.DEPARTMENT_ITEM_TYPE
import com.example.myapplication.Constant.Companion.OFFER_ITEM_TYPE
import com.example.myapplication.adapter.MainAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.models.MainModel
import com.example.myapplication.models.Model

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    val offers= mutableListOf<Model>()
    val departments= mutableListOf<Model>()

    val items= mutableListOf<MainModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

    }
    fun addOffers() {
        val imgId = R.drawable.ic_launcher_foreground
        for (i in 1..10) {
            offers.add(Model(i, "offer $i",imgId))
        }
    }
    fun addDepartments() {
        val imgId = R.drawable.ic_launcher_background
        for (i in 1..10) {
            departments.add(Model(i, "department $i", imgId))
        }
    }
    fun setupRecyclerView() {
        addOffers()
        addDepartments()

        items.add(OFFER_ITEM_TYPE,MainModel(OFFER_ITEM_TYPE,offers))
        items.add(DEPARTMENT_ITEM_TYPE,MainModel(DEPARTMENT_ITEM_TYPE,departments))
        val mainAdapter = MainAdapter()

        mainAdapter.items.submitList(items)
        binding.nestedRV.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }


}