package com.elcom.com.quickbloxex.di;

import com.elcom.com.quickbloxex.view.ui.activity.MainActivity;
import com.elcom.com.quickbloxex.view.ui.activity.OrderMealScheduleActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author ihsan on 12/2/17.
 */

@Module
abstract class ActivityBuilder {
//
//    @ContributesAndroidInjector
//    abstract SourcesActivity bindSourceActivity();
//
    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract OrderMealScheduleActivity bindOrderMealScheduleActivity();
//
//    @ContributesAndroidInjector
//    abstract DetailActivity bindDetailActivity();

}
