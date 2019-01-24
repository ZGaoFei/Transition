package com.example.zhaogaofei.transitiontest.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhaogaofei.transitiontest.R;
import com.example.zhaogaofei.transitiontest.customer.ChangeColor;
import com.example.zhaogaofei.transitiontest.customer.ColorTransition;

public class CustomerTransitionActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, CustomerTransitionActivity.class));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custome_transition);

        initChangeColorView();

        initChangeSceneColorView();
    }

    private void initChangeColorView() {
        final LinearLayout linearLayout = findViewById(R.id.ll_change_color);
        final TextView tvOne = findViewById(R.id.tv_change_color_one);
        final TextView tvTwo = findViewById(R.id.tv_change_color_two);
        final TextView tvThree = findViewById(R.id.tv_change_color_three);
        Button button = findViewById(R.id.bt_change_color);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                initChangeColor(linearLayout, tvOne, tvTwo, tvThree);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initChangeColor(LinearLayout linearLayout, TextView tvOne, TextView tvTwo, TextView tvThree) {
        TransitionManager.beginDelayedTransition(linearLayout, new ChangeColor());

        tvOne.setBackgroundColor(Color.RED);
        tvTwo.setBackgroundColor(Color.GREEN);
        tvThree.setBackgroundColor(Color.BLUE);
    }

    private int currentScenePosition;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initChangeSceneColorView() {

        final LinearLayout linearLayout = findViewById(R.id.ll_change_scene_color);
        final Scene[] scenes = new Scene[]{
                Scene.getSceneForLayout(linearLayout, R.layout.change_scene_one, CustomerTransitionActivity.this),
                Scene.getSceneForLayout(linearLayout, R.layout.change_scene_two, CustomerTransitionActivity.this),
                Scene.getSceneForLayout(linearLayout, R.layout.change_scene_three, CustomerTransitionActivity.this)
        };
        final ColorTransition colorTransition = new ColorTransition();
        TransitionManager.go(scenes[0], colorTransition);
        Button button = findViewById(R.id.bt_change_scene_color);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                initChangeSceneColor(colorTransition, scenes);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initChangeSceneColor(ColorTransition colorTransition, Scene[] scenes) {
        currentScenePosition = (currentScenePosition + 1) % scenes.length;
        TransitionManager.go(scenes[currentScenePosition], colorTransition);
    }
}
