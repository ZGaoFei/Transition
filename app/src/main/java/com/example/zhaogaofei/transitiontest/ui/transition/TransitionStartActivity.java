package com.example.zhaogaofei.transitiontest.ui.transition;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.widget.TextView;

import com.example.zhaogaofei.transitiontest.R;

public class TransitionStartActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, TransitionStartActivity.class));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_start);

        setExitTransition();
        setReenterTransition();

        initView();
    }

    // 离开动画
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setExitTransition() {
        Fade fade = new Fade(Fade.MODE_OUT);
        fade.setDuration(1000);
        getWindow().setExitTransition(fade);
    }

    // 重新进入动画
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setReenterTransition() {
        Fade fade = new Fade(Fade.MODE_IN);
        fade.setDuration(1000);
        getWindow().setReenterTransition(fade);
    }

    private void initView() {
        TextView textView = findViewById(R.id.tv_skip_to_next);
        textView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                TransitionEndActivity.startWithTransition(TransitionStartActivity.this);
            }
        });
    }
}
