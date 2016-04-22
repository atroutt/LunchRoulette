package com.audreytroutt.android.lunchroulette;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class TableSpinnerActivity extends AppCompatActivity {

    private final Handler mHideHandler = new Handler(Looper.getMainLooper());

    private final Runnable mTransition = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            tableImage.setAnimation(null);

            Intent i = new Intent(TableSpinnerActivity.this, LunchMatchActivity.class);
            startActivity(i);
        }
    };

    @Bind(R.id.fullscreen_content_controls)
    View mControlsView;

    @Bind(R.id.lunch_roulette_table)
    ImageView tableImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_table_spinner);
        ButterKnife.bind(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Hide UI first
        mControlsView.setVisibility(View.GONE);

        DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
        float width = metrics.widthPixels;
        float height = metrics.heightPixels;
        float xCenter = width / 2.0f;
        float yCenter = height / 2.0f;

        RotateAnimation anim = new RotateAnimation(0f, 360f, xCenter, yCenter);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(-1); // infinite
        anim.setDuration(1000);
        tableImage.startAnimation(anim);

        mHideHandler.removeCallbacks(mTransition);
        mHideHandler.postDelayed(mTransition, 3500);

    }
}
