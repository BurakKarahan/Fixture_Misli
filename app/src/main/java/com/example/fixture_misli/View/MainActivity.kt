package com.example.fixture_misli.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fixture_misli.R
import com.example.fixture_misli.ViewModel.FixtureViewModel
import com.example.fixture_misli.databinding.ActivityMainBinding
import com.fixture_misli.Adapter.ParentAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: FixtureViewModel
    private lateinit var parentAdapter: ParentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareRecyclerView()
        viewModel = ViewModelProvider(this)[FixtureViewModel::class.java]
        viewModel.getFixture()
        viewModel.observeFixtureLiveData().observe(this, Observer { fixtureList ->
            parentAdapter.setParentList(fixtureList)
        })
    }

    private fun prepareRecyclerView() {
        parentAdapter = ParentAdapter()
        binding.parentRecyclerView.apply {
            layoutManager = GridLayoutManager(applicationContext, 1)
            adapter = parentAdapter
        }
    }
}