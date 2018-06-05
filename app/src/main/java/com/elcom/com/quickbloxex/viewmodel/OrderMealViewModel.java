package com.elcom.com.quickbloxex.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.elcom.com.quickbloxex.model.data.Article;
import com.elcom.com.quickbloxex.model.data.Source;
import com.elcom.com.quickbloxex.model.db.DataRepository;
import com.elcom.com.quickbloxex.repository.OrderMealRepository;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * @author ihsan on 12/27/17.
 */

public class OrderMealViewModel extends ViewModel {

    private final LiveData<List<Article>> articleList;
    private final LiveData<List<Source>> sourceList;
    private final LiveData<List<Date>> dayList;
    private final OrderMealRepository repository;

    @Inject
    OrderMealViewModel(OrderMealRepository repository) {
        this.repository = repository;
        articleList = repository.getArticleLiveList();
        sourceList = repository.getSourceLiveList();
        dayList = repository.getDatesBetweenUsingJava7();
    }

    public LiveData<List<Article>> getArticleList() {
        return articleList;
    }

    public LiveData<List<Source>> getSourceList() {
        return sourceList;
    }

    public LiveData<List<Date>> getDayList() {
        return dayList;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
