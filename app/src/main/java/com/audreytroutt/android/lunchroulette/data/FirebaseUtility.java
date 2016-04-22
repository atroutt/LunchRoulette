package com.audreytroutt.android.lunchroulette.data;

import android.preference.PreferenceManager;
import android.util.Log;

import com.audreytroutt.android.lunchroulette.LunchRouletteApplication;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
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
    private String currentUserDisplayName;
    private String currentUserID;
    private LunchPerson currentPerson;

    private FirebaseUtility() {
        baseFirebaseRef = new Firebase("https://scorching-inferno-6471.firebaseio.com/");
    }

    private Firebase getPeoplePath() {
        return baseFirebaseRef.child("people");
    }

    private Firebase getCurrentPersonRef() {
        return getPeoplePath().child(currentUserID);
    }

    public void authenticateTwitterSessionWithResultHandler(final TwitterSession session, final Firebase.AuthResultHandler authResultHandler) {
        currentUserDisplayName = session.getUserName();
        currentUserID = "" + session.getUserId();

        Map<String, String> options = new HashMap<String, String>();
        options.put("oauth_token", session.getAuthToken().token);
        options.put("oauth_token_secret", session.getAuthToken().secret);
        options.put("user_id", currentUserID);
        baseFirebaseRef.authWithOAuthToken("twitter", options, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Firebase ref = getCurrentPersonRef();
                ref.child("authProvider").setValue(authData.getProvider());
                ref.child("displayName").setValue(currentUserDisplayName);
                ref.child("id").setValue(currentUserID);

                // Sync the current person profile once
                getCurrentPersonRef().addValueEventListener(new PostAuthenticationFireBaseListener(authResultHandler));
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                authResultHandler.onAuthenticationError(firebaseError);
            }
        });
    }

    private class PostAuthenticationFireBaseListener implements ValueEventListener {

        private Firebase.AuthResultHandler authResultHandler;

        public PostAuthenticationFireBaseListener(Firebase.AuthResultHandler authResultHandler) {
            this.authResultHandler = authResultHandler;
        }

        @Override
        public void onDataChange(DataSnapshot snapshot) {
            currentPerson = snapshot.getValue(LunchPerson.class);

            PreferenceManager.getDefaultSharedPreferences(LunchRouletteApplication.CONTEXT).edit().putString("display_name", currentUserDisplayName);

            Log.d("TEMP", "-------gots sync");

            // call back to the original result handler
            authResultHandler.onAuthenticated(null);
        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {
            System.out.println("The read failed: " + firebaseError.getMessage());
            // call back to the original result handler
            authResultHandler.onAuthenticationError(firebaseError);
        }
    }
}
