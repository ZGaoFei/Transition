package com.example.zhaogaofei.transitiontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.zhaogaofei.transitiontest.ui.CustomerTransitionActivity;
import com.example.zhaogaofei.transitiontest.ui.OtherAnimationEffectActivity;
import com.example.zhaogaofei.transitiontest.ui.TransitionOneActivity;
import com.example.zhaogaofei.transitiontest.ui.transition.ShareElementTransitionStartActivity;
import com.example.zhaogaofei.transitiontest.ui.transition.TransitionStartActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        findViewById(R.id.tv_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionOneActivity.start(MainActivity.this);
            }
        });

        findViewById(R.id.tv_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerTransitionActivity.start(MainActivity.this);
            }
        });

        findViewById(R.id.tv_three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OtherAnimationEffectActivity.start(MainActivity.this);
            }
        });

        findViewById(R.id.tv_four).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionStartActivity.start(MainActivity.this);
            }
        });

        findViewById(R.id.tv_five).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareElementTransitionStartActivity.start(MainActivity.this);
            }
        });
    }

}
