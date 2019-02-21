package com.example.zhaogaofei.transitiontest.ui.transition;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhaogaofei.transitiontest.R;

public class ShareElementTransitionStartActivity extends AppCompatActivity {
    private TextView textView;
    private ImageView imageView;

    public static void start(Context context) {
        context.startActivity(new Intent(context, ShareElementTransitionStartActivity.class));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_share_element_transition_start);

        setTransitions();

        initView();
    }

    private void initView() {
        textView = findViewById(R.id.tv_share_element_start);
        imageView = findViewById(R.id.iv_share_element_start);

        TextView tv = findViewById(R.id.tv_share_element_start_next);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pair[] pairs = new Pair[]{Pair.create(imageView, "share_element_image_view"),
                        Pair.create(textView, "share_element_text_view")};
                ShareElementTransitionEndActivity.start(ShareElementTransitionStartActivity.this, pairs);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setTransitions() {
        Fade fade = new Fade();//渐隐
        fade.setDuration(500);
        Explode explode = new Explode();//展开回收
        explode.setDuration(500);
        getWindow().setReenterTransition(fade);
        getWindow().setExitTransition(explode);
        getWindow().setAllowEnterTransitionOverlap(false);
        getWindow().setAllowReturnTransitionOverlap(false);
    }
}
