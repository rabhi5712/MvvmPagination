package com.example.mvvmpagination

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmpagination.adapters.ItemAdapter
import com.example.mvvmpagination.databinding.ActivityMainBinding
import com.example.mvvmpagination.model.DataModel
import com.example.mvvmpagination.viewmodels.DataViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val viewModel : DataViewModel by viewModels()
    private var isLoading = false
    private var currentPage = 1
    private val pageSize = 20
    private var totalItemCount = 0
    private var lastVisibleItemPosition = 0
    lateinit var dataModel : DataModel
    lateinit var aadapter : ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setObservers()
    }

    private fun setObservers() {
        binding.progressBar.isVisible = true
        viewModel.getPosts(currentPage)

        viewModel.getLiveData.observe(this@MainActivity) { jsonArray ->
                dataModel = Gson().fromJson(jsonArray.toString(), DataModel::class.java)
                binding.progressBar.isVisible = false
                binding.deviceRecyclerView.isVisible = true
                binding.deviceRecyclerView.adapter = ItemAdapter(dataModel.posts)
                binding.deviceRecyclerView.addOnScrollListener(object  : RecyclerView.OnScrollListener(){
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                        totalItemCount = layoutManager.itemCount
                        lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                        if (!isLoading && lastVisibleItemPosition == totalItemCount - 1) {
                            currentPage++
                            binding.progressBar.isVisible = true
                            loadPage(currentPage)
                        }
                    }
                })
        }
    }

    private fun loadPage(page: Int) {
        isLoading = true
            try {
                viewModel.getPosts(page)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
                binding.progressBar.isVisible = false
            }
    }
}