package com.example.zhaogaofei.transitiontest.ui.transition;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.zhaogaofei.transitiontest.R;

public class ShareElementTransitionNextActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, ShareElementTransitionNextActivity.class));
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static void start(Context context, Pair[] pairs) {
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, pairs).toBundle();
        context.startActivity(new Intent(context, ShareElementTransitionNextActivity.class), bundle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_element_transition_next);

        TextView textView = findViewById(R.id.tv_share_element_next);
        textView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                finishAfterTransition();
            }
        });
    }
}
