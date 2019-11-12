package com.javdiana.freebleticket.view.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.javdiana.freebleticket.R
import com.javdiana.freebleticket.view.model.repository.FestivalRepository
import com.javdiana.freebleticket.view.model.repository.FestivalRepositoryImpl
import com.javdiana.freebleticket.view.view.home.adapter.FestivalsAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext.get
import org.koin.core.context.startKoin
import org.koin.dsl.module

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initFestivals()
    }

    private fun initViewModel(){
        val appModule = module {
            single<FestivalRepository> { FestivalRepositoryImpl() }
            viewModel {HomeViewModel(get())}
        }

        startKoin{
            androidLogger()
            androidContext(context!!)
            modules(appModule)
        }
    }

    private fun initFestivals(){
        recylcerviewMyFestivals.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val adapter = FestivalsAdapter()
        adapter.submitList(homeViewModel.getList())
        recylcerviewMyFestivals.adapter = adapter
    }



}