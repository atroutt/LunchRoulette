package com.audreytroutt.android.lunchroulette.data;

import android.util.Log;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.User;

/**
 * Created by audrey on 4/22/16.
 */
public class TwitterUtility {
    private static TwitterUtility ourInstance = new TwitterUtility();

    public static TwitterUtility getInstance() {
        return ourInstance;
    }

    private TwitterUtility() {

    }

    public void getCurrentUserProfileImage(final Callback<User> userInfoCallback) {
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        twitterApiClient.getAccountService().verifyCredentials(false, false, new Callback<User>() {
            // TODO rather than creating another callback here, push this out to an implementation that is passed in

            @Override
            public void success(Result<User> userResult) {
                String name = userResult.data.name;
                String email = userResult.data.email;
                // _normal (48x48px) | _bigger (73x73px) | _mini (24x24px)
                String photoUrlBiggerSize   = userResult.data.profileImageUrl.replace("_normal", "_bigger");

                // TODO store on current user in memory and persist to firebase

                userInfoCallback.success(userResult);
            }

            @Override
            public void failure(TwitterException exc) {
                Log.d("TwitterKit", "Verify Credentials Failure", exc);
                userInfoCallback.failure(exc);
            }
        });
    }
}
