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
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhaogaofei.transitiontest.R;

public class ShareElementTransitionEndActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;

    public static void start(Context context) {
        context.startActivity(new Intent(context, ShareElementTransitionEndActivity.class));
    }

    public static void start(Context context, Pair[] pairs) {
        /*Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pair).toBundle();
        ActivityCompat.startActivity(context, new Intent(activity, ShareElementTransitionEndActivity.class), bundle);*/

        Intent intent = new Intent(context, ShareElementTransitionEndActivity.class);
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, pairs);
        ActivityCompat.startActivity(context, intent,  compat.toBundle());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_element_transition_end);

        setTransitions();

        initView();
    }

    private void initView() {
        imageView = findViewById(R.id.iv_share_element_end);
        textView = findViewById(R.id.tv_share_element_end);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pair[] pairs = new Pair[]{Pair.create(imageView, "share_element_image_view"),
                        Pair.create(textView, "share_element_text_view")};
                ShareElementTransitionNextActivity.start(ShareElementTransitionEndActivity.this, pairs);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.finishAfterTransition(ShareElementTransitionEndActivity.this);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setTransitions() {
        Fade fade = new Fade();//渐隐
        fade.setDuration(500);
//        Slide slide = new Slide(Gravity.END);//右边平移
//        slide.setDuration(500);
        Explode explode = new Explode();//展开回收
        explode.setDuration(500);
        getWindow().setEnterTransition(explode);
        getWindow().setReenterTransition(fade);
        // getWindow().setExitTransition(explode);
        getWindow().setAllowEnterTransitionOverlap(false);
        getWindow().setAllowReturnTransitionOverlap(false);
    }

}
