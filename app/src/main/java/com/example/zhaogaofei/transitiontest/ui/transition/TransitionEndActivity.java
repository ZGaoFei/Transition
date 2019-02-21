package com.example.zhaogaofei.transitiontest.ui.transition;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.zhaogaofei.transitiontest.R;

public class TransitionEndActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, TransitionEndActivity.class));
    }

    public static void startWithTransition(Activity activity) {
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle();
        ActivityCompat.startActivity(activity, new Intent(activity, TransitionEndActivity.class), bundle);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_end);

        initEnterTransition();
        initReturnTransition();

        initView();
    }

    // 进入动画
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initEnterTransition() {
        Explode explode = new Explode();
        explode.setDuration(1000);
        getWindow().setEnterTransition(explode);
    }

    // 返回动画
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initReturnTransition() {
        Slide slide = new Slide();
        slide.setDuration(1000);
        slide.setSlideEdge(Gravity.LEFT);
        getWindow().setReturnTransition(slide);
    }

    /**
     * 点击调用finish()方法，会导致end activity的退出动画无效果
     * start activity重新进入动画无效果
     *
     * 点击返回虚拟机就不会出现这个问题
     */
    private void initView() {
        TextView textView = findViewById(R.id.tv_return_top);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.finishAfterTransition(TransitionEndActivity.this);
            }
        });
    }
}
