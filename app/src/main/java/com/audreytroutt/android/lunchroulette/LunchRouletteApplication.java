package com.audreytroutt.android.lunchroulette;

import android.app.Application;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

import com.firebase.client.Firebase;
import com.firebase.client.core.Context;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

/**
 * Created by audrey on 4/21/16.
 */
public class LunchRouletteApplication extends Application {

    public static android.content.Context CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();

        TwitterAuthConfig authConfig = new TwitterAuthConfig(getString(R.string.twitter_key), getString(R.string.twitter_secret));
        Fabric.with(this, new Twitter(authConfig), new Crashlytics());

        Firebase.setAndroidContext(this);

        LunchRouletteApplication.CONTEXT = this.getApplicationContext();
    }
}
