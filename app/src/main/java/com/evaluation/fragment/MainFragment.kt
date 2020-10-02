package com.evaluation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.evaluation.App
import com.evaluation.R
import com.evaluation.adapter.CustomListAdapter
import com.evaluation.model.room.NewsTableItem
import kotlinx.android.synthetic.main.main_layout.*
import javax.inject.Inject


/**
 * @author Vladyslav Havrylenko
 * @since 09.03.2020
 */
class MainFragment : Fragment(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    private lateinit var adapter: CustomListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.mainComponent?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.bindView(this)
        presenter.provideData()
        initView()
    }

    private fun initView() {
        initToolBar()
        initList()
    }

    private fun initToolBar() {
        toolBar.inflateMenu(R.menu.menu)

        toolBar.setNavigationOnClickListener {
            // Handle navigation icon press
        }

        toolBar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.search -> {
                    val searchView = item.actionView as SearchView
                    searchView.queryHint = getString(R.string.search)
                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            Toast.makeText(context, query, Toast.LENGTH_SHORT).show()
                            return false
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            Toast.makeText(context, newText, Toast.LENGTH_SHORT).show()
                            return false
                        }

                    })
                }
            }
            true
        }
    }

    private fun initList() {
        adapter = CustomListAdapter()
        listView.layoutManager = LinearLayoutManager(context)
        listView.itemAnimator = DefaultItemAnimator()
        listView.adapter = adapter
    }

    override fun showList(newsItem: List<NewsTableItem>) {
        adapter.mNewsList = newsItem
    }

    override fun showLoading() {
        progressSpinner.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressSpinner.visibility = View.GONE
    }

    override fun onDestroyView() {
        presenter.end()
        super.onDestroyView()
    }

    override fun onDestroy() {
        App.clearComponent()
        super.onDestroy()
    }

}