package com.elcom.com.quickbloxex.repository;

import android.arch.lifecycle.MutableLiveData;

import com.elcom.com.quickbloxex.core.AppSchedulerProvider;
import com.elcom.com.quickbloxex.core.BaseViewModel;
import com.elcom.com.quickbloxex.core.base.ArticleUtils;
import com.elcom.com.quickbloxex.model.api.Api;
import com.elcom.com.quickbloxex.model.data.Article;
import com.elcom.com.quickbloxex.model.data.Articles;
import com.elcom.com.quickbloxex.model.data.Source;
import com.elcom.com.quickbloxex.model.data.Sources;
import com.elcom.com.quickbloxex.model.db.AppDatabase;
import com.elcom.com.quickbloxex.model.db.ArticleDao;
import com.elcom.com.quickbloxex.model.db.SourceDao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author ihsan on 12/28/17.
 */

public class OrderMealRepository implements BaseViewModel {

    private final List<Source> sourceList;
    private CompositeDisposable disposables = new CompositeDisposable();
    private final Api api;
    private final MutableLiveData<List<Article>> articleMutableLiveData;
    private final MutableLiveData<List<Source>> sourceMutableLiveData;
    private final AppSchedulerProvider schedulerProvider;
    private final SourceDao sourceDao;
    private final ArticleDao articleDao;

    @Inject
    OrderMealRepository(AppDatabase database, Api api, AppSchedulerProvider schedulerProvider) {
        this.api = api;
        this.schedulerProvider = schedulerProvider;
        sourceDao = database.sourceDao();
        articleDao = database.articleDao();
        sourceList = sourceDao.getAllList();
        articleMutableLiveData = new MutableLiveData<>();
        sourceMutableLiveData = new MutableLiveData<>();
        sourceMutableLiveData.postValue(sourceList);
        articleMutableLiveData.postValue(articleDao.getAll());
    }

    public void insertSource(Source item) {
        sourceDao.insert(item);
    }

    public void deleteSource(String id) {
        sourceDao.delete(id);
    }

    public MutableLiveData<List<Source>> getSourceLiveList() {
        api.sources()
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .map(sources -> {
                    if (sourceList != null) {
                        for (Source item : sources.getSources()) {
                            for (Source data : sourceList) {
                                if (item.getId().equals(data.getId())) {
                                    item.setSelected(true);
                                }
                            }
                        }
                    }
                    return sources;
                })
                .subscribe(new Observer<Sources>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(Sources sources) {
                        sourceMutableLiveData.postValue(sources.getSources());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return sourceMutableLiveData;
    }

    public MutableLiveData<List<Date>> getDatesBetweenUsingJava7() {


        Calendar calSt = Calendar.getInstance();
        calSt.set(Calendar.YEAR, 2018);
        calSt.set(Calendar.MONTH, Calendar.JUNE);
        calSt.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calSt.getTime();

        Calendar calEnd = Calendar.getInstance();
        calEnd.set(Calendar.YEAR, 2018);
        calEnd.set(Calendar.MONTH, Calendar.JUNE);
        calEnd.set(Calendar.DAY_OF_MONTH, 30);
        Date endDate = calEnd.getTime();

        MutableLiveData<List<Date>> datesInRange = new MutableLiveData<>();
        List<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);

        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        datesInRange.postValue(dates);

        return datesInRange;
    }

    public MutableLiveData<List<Article>> getArticleLiveList() {
        api.topHeadlines("bbc-news"+getQuery())
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .map(ArticleUtils::formatDate)
                .subscribe(new Observer<Articles>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(Articles articles) {
                        articleDao.clear();
                        articleDao.insert(articles.getArticles());
                        articleMutableLiveData.postValue(articleDao.getAll());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return articleMutableLiveData;
    }

    private String getQuery() {
        StringBuilder builder = new StringBuilder();
        if (sourceList != null && sourceList.size() > 0) {
            for (Source item : sourceList) {
                builder.append(item.getId()).append(",");
            }
            builder.deleteCharAt(builder.lastIndexOf(","));
        }
        return builder.toString();
    }

    @Override
    public void onClear() {
        disposables.clear();
    }
}
