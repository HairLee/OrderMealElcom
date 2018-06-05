package com.elcom.com.quickbloxex.view.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.elcom.com.quickbloxex.R;
import com.elcom.com.quickbloxex.core.base.BaseActivity;
import com.elcom.com.quickbloxex.databinding.ActivityMainBinding;
import com.elcom.com.quickbloxex.model.data.Article;
import com.elcom.com.quickbloxex.view.adapter.NewsAdapter;
import com.elcom.com.quickbloxex.viewmodel.MainViewModel;

import java.util.ArrayList;

public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> implements Toolbar.OnMenuItemClickListener, NewsAdapter.ItemSelectedListener {



    static final String APP_ID = "71384";
    static final String AUTH_KEY = "uVSv7X7T4Op4NDE";
    static final String AUTH_SECRET = "QuQ6BEYSv58jGTh";
    static final String ACCOUNT_KEY = "fgsu3KmxXmJmffRe5AoF";

    private NewsAdapter mAdapter;
    @Override
    protected Class<MainViewModel> getViewModel() {

        return MainViewModel.class;
    }

    @Override
    protected void onCreate(Bundle instance, MainViewModel viewModel, ActivityMainBinding binding) {
        mAdapter = new NewsAdapter();
        mAdapter.setOnItemClickListener(this);
        binding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
        binding.recyclerView.setAdapter(mAdapter);
        init(viewModel);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void onItemSelected(View view, Article item) {

    }

    private void init(MainViewModel viewModel) {
        viewModel.getArticleList().observe(this, articles -> mAdapter.setData((ArrayList<Article>) articles));
        viewModel.getSourceList().observe(this, sources -> {
            if (sources == null || sources.size() < 1) {
//                SourcesActivity.start(this);
            }
        });
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
