package com.example.zhaogaofei.transitiontest.ui.transition;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.widget.TextView;

import com.example.zhaogaofei.transitiontest.R;

public class ShareElementTransitionNextActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, ShareElementTransitionNextActivity.class));
    }

    public static void start(Context context, Pair[] pairs) {
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, pairs).toBundle();
        ActivityCompat.startActivity(context, new Intent(context, ShareElementTransitionNextActivity.class), bundle);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_element_transition_next);

        setShareElementTransition();

        TextView textView = findViewById(R.id.tv_share_element_next);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.finishAfterTransition(ShareElementTransitionNextActivity.this);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setShareElementTransition() {
        getWindow().setSharedElementEnterTransition(new ChangeBounds());
        getWindow().setSharedElementExitTransition(new Fade());
        getWindow().setSharedElementReenterTransition(new Slide());
        getWindow().setSharedElementReturnTransition(new Explode());
    }
}
