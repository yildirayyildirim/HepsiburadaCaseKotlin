package com.app.hepsiburada.view.fragment

import android.view.View
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.hepsiburada.R
import com.app.hepsiburada.core.base.BaseFragment
import com.app.hepsiburada.core.base.Constants.PAGING
import com.app.hepsiburada.databinding.ViewFragmentMainBinding
import com.app.hepsiburada.view.adapter.TunesAdapter
import com.app.hepsiburada.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class MainFragment : BaseFragment<ViewFragmentMainBinding, MainViewModel>() {

    private lateinit var jobAdapter: TunesAdapter
    private var pageNumber = PAGING

    override fun getContentView(): Int {
        return R.layout.view_fragment_main
    }

    override fun subScribe(view: View) {
        init()

        baseViewModel.entityValue.observe(viewLifecycleOwner, Observer {
            it?.let {
                refreshLoad(null)
            }
        })

        baseViewModel.searchValue.observe(viewLifecycleOwner, Observer {
            it?.let {
                refreshLoad(it)
            }
        })

        baseViewModel.tunesModel.observe(viewLifecycleOwner, Observer {
            it?.let {
                jobAdapter.data = it.results
                jobAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun init() {
        jobAdapter = TunesAdapter(baseViewModel.tunesModel.value?.results)
        dataBinding.rycHome.apply {
            layoutManager = GridLayoutManager(activity, 2)
            setHasFixedSize(true)
            adapter = jobAdapter
        }
        dataBinding.rycHome.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 && !recyclerView.canScrollVertically(2)) {
                    pageNumber += PAGING
                    refreshLoad(baseViewModel.searchValue.value)
                }
            }
        })
        dataBinding.chpMovie.setOnClickListener {
            pageNumber = PAGING
            baseViewModel.entityValue.value = getString(R.string.movie)
        }
        dataBinding.chpMusic.setOnClickListener {
            pageNumber = PAGING
            baseViewModel.entityValue.value = getString(R.string.musicVideo)
        }
        dataBinding.chpApps.setOnClickListener {
            pageNumber = PAGING
            baseViewModel.entityValue.value = getString(R.string.software)
        }
        dataBinding.chpBook.setOnClickListener {
            pageNumber = PAGING
            baseViewModel.entityValue.value = getString(R.string.ebook)
        }

        dataBinding.searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
                private var searchJob: Job? = null

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    searchJob?.cancel()
                    searchJob = coroutineScope.launch {
                        newText?.let {
                            delay(1000)
                            baseViewModel.searchValue.value = newText
                        }
                    }
                    return false
                }
            }
        )
    }

    private fun refreshLoad(term: String?) {
        term.let {
            if (it.isNullOrEmpty()) {
                baseViewModel.getAllTunesModelByTermAndEntity(getString(R.string.all),
                    baseViewModel.entityValue.value!!, pageNumber)
            } else {
                if (it.length >= 2)
                    baseViewModel.getAllTunesModelByTerm(it,
                        baseViewModel.entityValue.value!!) else showMessageBox(getString(R.string.EnAzIkiHarfGiriniz))

            }
        }
    }

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }
}