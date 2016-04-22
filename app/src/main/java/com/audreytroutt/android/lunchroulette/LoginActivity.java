package com.audreytroutt.android.lunchroulette;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.audreytroutt.android.lunchroulette.data.FirebaseUtility;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.twitter_login_button)
    TwitterLoginButton loginButton;

    @Bind(R.id.lunch_roulette_logo)
    ImageView lunchRouletteLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        lunchRouletteLogo.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_lunch_table, null));
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session = result.data;
                String msg = "Welcome @" + session.getUserName() + "!";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                FirebaseUtility.getInstance().authenticateTwitterSessionWithResultHandler(session, new FirebaseNewUserAuthenticationHandler());
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
                Toast.makeText(getApplicationContext(), "Error authenticating with Twitter: " + exception.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        TwitterSession session = Twitter.getInstance().core.getSessionManager().getActiveSession();
        if (session != null) {
            loginButton.setVisibility(View.INVISIBLE);
            String msg = "Welcome Back @" + session.getUserName() + "!";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            FirebaseUtility.getInstance().authenticateTwitterSessionWithResultHandler(session, new FirebaseExistingUserAuthenticationHandler());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

    private class FirebaseExistingUserAuthenticationHandler implements Firebase.AuthResultHandler {
        @Override
        public void onAuthenticated(AuthData authData) {
            Intent i = new Intent(LoginActivity.this, LunchListActivity.class);
            startActivity(i);
        }

        @Override
        public void onAuthenticationError(FirebaseError firebaseError) {
            Log.d("Firebase", "Login with Firebase failure", firebaseError.toException());
            Toast.makeText(getApplicationContext(), "Error authenticating with Firebase: " + firebaseError.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private class FirebaseNewUserAuthenticationHandler implements Firebase.AuthResultHandler {
        @Override
        public void onAuthenticated(AuthData authData) {
            Intent i = new Intent(LoginActivity.this, SettingsActivity.class);
            startActivity(i);
        }

        @Override
        public void onAuthenticationError(FirebaseError firebaseError) {
            Log.d("Firebase", "Login with Firebase failure", firebaseError.toException());
            Toast.makeText(getApplicationContext(), "Error authenticating with Firebase: " + firebaseError.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}

