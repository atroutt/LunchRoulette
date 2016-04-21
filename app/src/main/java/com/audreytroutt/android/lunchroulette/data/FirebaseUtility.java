package com.audreytroutt.android.lunchroulette.data;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by audrey on 4/21/16.
 */
public class FirebaseUtility {
    private static FirebaseUtility ourInstance = new FirebaseUtility();

    public static FirebaseUtility getInstance() {
        return ourInstance;
    }

    private Firebase baseFirebaseRef;

    private FirebaseUtility() {
        baseFirebaseRef = new Firebase("https://scorching-inferno-6471.firebaseio.com/");
    }

    private Firebase getPeoplePath() {
        return baseFirebaseRef.child("people");
    }

    private Firebase getPersonData(String twitterUserId) {
        return getPeoplePath().child(twitterUserId);
    }

    public void authenticateTwitterSessionWithResultHandler(final TwitterSession session, final Firebase.AuthResultHandler authResultHandler) {
        // setup the OAuth options for Twitter
        Map<String, String> options = new HashMap<String, String>();
        options.put("oauth_token", session.getAuthToken().token);
        options.put("oauth_token_secret", session.getAuthToken().secret);
        options.put("user_id", "" + session.getUserId());
        baseFirebaseRef.authWithOAuthToken("twitter", options, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("provider", authData.getProvider());
                map.put("displayName", session.getUserName());

                Firebase ref = getPersonData("" + session.getUserId());
                ref.setValue(map);

                // call back to the original result handler
                authResultHandler.onAuthenticated(authData);
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                authResultHandler.onAuthenticationError(firebaseError);
            }
        });
    }
}
