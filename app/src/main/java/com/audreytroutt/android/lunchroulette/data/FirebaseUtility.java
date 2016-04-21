package com.audreytroutt.android.lunchroulette.data;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

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

    private Firebase getPersonData(String personName) {
        return baseFirebaseRef.child("people").child(personName);
    }

    public boolean isExistingUser(String personName) {
        return false;
    }

    public void authenticateTwitterUserWithResultHandler(long twitterUserId, Firebase.AuthResultHandler authResultHandler) {
        // setup the OAuth options for Twitter
        Map<String, String> options = new HashMap<String, String>();
        options.put("oauth_token", "<OAuth token>");
        options.put("oauth_token_secret", "<OAuth token secret>");
        options.put("user_id", "" + twitterUserId);
        baseFirebaseRef.authWithOAuthToken("twitter", options, authResultHandler);
    }
}
