package com.elcom.com.quickbloxex.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;


import com.elcom.com.quickbloxex.di.ViewModelKey;
import com.elcom.com.quickbloxex.viewmodel.MainViewModel;
import com.elcom.com.quickbloxex.viewmodel.OrderMealViewModel;
import com.elcom.com.quickbloxex.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * @author ihsan on 12/27/17.
 */

@SuppressWarnings("WeakerAccess")
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindsMainViewModel(MainViewModel mainViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(OrderMealViewModel.class)
    abstract ViewModel bindsOrderMealViewModel(OrderMealViewModel orderMealViewModel);



    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);

}
