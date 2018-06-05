package com.elcom.com.quickbloxex.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.elcom.com.quickbloxex.model.data.Article;
import com.elcom.com.quickbloxex.model.data.Source;
import com.elcom.com.quickbloxex.model.db.DataRepository;


import java.util.List;

import javax.inject.Inject;

/**
 * @author ihsan on 12/27/17.
 */

public class MainViewModel extends ViewModel {

    private final LiveData<List<Article>> articleList;
    private final LiveData<List<Source>> sourceList;
    private final DataRepository repository;

    @Inject
    MainViewModel(DataRepository repository) {
        this.repository = repository;
        articleList = repository.getArticleLiveList();
        sourceList = repository.getSourceLiveList();
    }

    public LiveData<List<Article>> getArticleList() {
        return articleList;
    }

    public LiveData<List<Source>> getSourceList() {
        return sourceList;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
