package com.example.zhaogaofei.transitiontest.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Explode;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.zhaogaofei.transitiontest.R;

/**
 * 利用Transition里面的类来实现一些其他的效果
 */
public class OtherAnimationEffectActivity extends AppCompatActivity implements View.OnClickListener {

    public static void start(Context context) {
        context.startActivity(new Intent(context, OtherAnimationEffectActivity.class));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_animation_effect);

        initSceneOneView();

        initSceneTwoView();

        initLOLView();

        initXMLView();
    }

    /**
     * scene1和scene2对应view的ID要一致
     */
    private boolean isScene1 = true;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initSceneOneView() {
        final FrameLayout frameLayout = findViewById(R.id.frame_scene_one);
        Button button = findViewById(R.id.bt_scene_one);

        final Scene scene1 = Scene.getSceneForLayout(frameLayout, R.layout.scene_one, this);
        final Scene scene2 = Scene.getSceneForLayout(frameLayout, R.layout.scene_two, this);
        TransitionManager.go(scene1);

        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                initSceneOne(scene1, scene2);
            }
        });
    }

    private boolean isScene3;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initSceneOne(Scene scene1, Scene scene2) {
        ChangeBounds changeBounds = new ChangeBounds();
        ChangeImageTransform changeImageTransform = new ChangeImageTransform();
        TransitionManager.go(isScene1 ? scene2 : scene1, changeBounds);
        isScene1 = !isScene1;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initSceneTwoView() {
        final FrameLayout frameLayout = findViewById(R.id.frame_scene_two);
        Button button = findViewById(R.id.bt_scene_two);

        final Scene scene1 = Scene.getSceneForLayout(frameLayout, R.layout.scene_one, this);
        final Scene scene2 = Scene.getSceneForLayout(frameLayout, R.layout.scene_three, this);
        TransitionManager.go(scene1);

        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                initSceneTwo(scene1, scene2);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initSceneTwo(Scene scene1, Scene scene2) {
        ChangeTransform changeTransform = new ChangeTransform();
        TransitionManager.go(isScene3 ? scene1 : scene2, changeTransform);
        isScene3 = !isScene3;
    }


    private FrameLayout frameLayout;
    private View ivTimo0;
    private View ivTimo1;
    private View ivAngel;
    private View ivJiansheng;

    private boolean isBigImage = false;
    private void initLOLView() {
        frameLayout = findViewById(R.id.frame_lol);
        ivTimo0 = findViewById(R.id.iv_timo0);
        ivTimo1 = findViewById(R.id.iv_timo1);
        ivAngel = findViewById(R.id.iv_angle);
        ivJiansheng = findViewById(R.id.iv_jiansheng);
        ivTimo0.setOnClickListener(this);
        ivTimo1.setOnClickListener(this);
        ivAngel.setOnClickListener(this);
        ivJiansheng.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initLOL(FrameLayout frameLayout, View clickView, View... views) {
        TransitionSet set = new TransitionSet().addTransition(new Explode()).addTransition(new ChangeBounds());
        TransitionManager.beginDelayedTransition(frameLayout, set);

        changeViewParams(clickView);

        for (View view : views) {
            view.setVisibility(view.getVisibility() == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
        }

        clickView.setVisibility(View.VISIBLE);
    }

    private void changeViewParams(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (isBigImage) {
            layoutParams.width = layoutParams.width - 100;
            layoutParams.height = layoutParams.height - 100;
        } else {
            layoutParams.width = layoutParams.width + 100;
            layoutParams.height = layoutParams.height + 100;
        }
        isBigImage = !isBigImage;
        view.setLayoutParams(layoutParams);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_timo0:
                initLOL(frameLayout, ivTimo0, ivTimo1, ivAngel, ivJiansheng);
                break;
            case R.id.iv_timo1:
                initLOL(frameLayout, ivTimo1, ivTimo0, ivAngel, ivJiansheng);
                break;
            case R.id.iv_angle:
                initLOL(frameLayout, ivAngel, ivTimo0, ivTimo1, ivJiansheng);
                break;
            case R.id.iv_jiansheng:
                initLOL(frameLayout, ivJiansheng, ivTimo0, ivTimo1, ivAngel);
                break;
            default:
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initXMLView() {
        final Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.transition_set);
        Button button = findViewById(R.id.bt_xml);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initXML(transition);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initXML(Transition transition) {
        TransitionManager.beginDelayedTransition(frameLayout, transition);

        changeViewParams(ivTimo0);
        View[] views = new View[]{ivTimo0, ivTimo1, ivAngel, ivJiansheng};
        for (View view : views) {
            view.setVisibility(view.getVisibility() == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
        }
        ivTimo0.setVisibility(View.VISIBLE);
    }

}
