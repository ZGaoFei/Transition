package com.example.zhaogaofei.transitiontest.ui.transition;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhaogaofei.transitiontest.R;

public class OptionsCompatTestStartActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;

    private TextView tvCustomerAnimation;
    private TextView tvClipRevealAnimation;
    private TextView tvScaleUpAnimation;
    private TextView tvThumbnailScaleUpAnimation;

    private ImageView ivSmall;
    private int startX = 0, startY = 0;
    private int startWidth, startHeight;
    private Bitmap bitmap;

    public static void start(Context context) {
        context.startActivity(new Intent(context, OptionsCompatTestStartActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_compat_test_start);

        context = this;

        initView();
        getImageViewParams();
    }

    private void initView() {
        tvCustomerAnimation = findViewById(R.id.tv_options_compat_one);
        tvClipRevealAnimation = findViewById(R.id.tv_options_compat_two);
        tvScaleUpAnimation = findViewById(R.id.tv_options_compat_three);
        tvThumbnailScaleUpAnimation = findViewById(R.id.tv_options_compat_four);
        tvCustomerAnimation.setOnClickListener(this);
        tvClipRevealAnimation.setOnClickListener(this);
        tvScaleUpAnimation.setOnClickListener(this);
        tvThumbnailScaleUpAnimation.setOnClickListener(this);

        ivSmall = findViewById(R.id.iv_options_compat);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_options_compat_one:
                OptionsCompatTestEndActivity.startWithCustomAnimation(context, R.anim.activity_center_out, R.anim.activity_center_in);
                break;
            case R.id.tv_options_compat_two:
                OptionsCompatTestEndActivity.startWithClipRevealAnimation(context, ivSmall, startX, startY, 100, 100);
                break;
            case R.id.tv_options_compat_three:
                OptionsCompatTestEndActivity.startWithScaleUpAnimation(context, ivSmall, startX, startY, startWidth, startHeight);
                break;
            case R.id.tv_options_compat_four:
                // getLocation();
                getBitmap();
                OptionsCompatTestEndActivity.startWithThumbnailScaleUpAnimation(context, ivSmall, bitmap, startX, startY);
                break;
            default:
                break;
        }
    }

    private void getImageViewParams() {
        ViewGroup.LayoutParams layoutParams = ivSmall.getLayoutParams();
        startWidth = layoutParams.width;
        startHeight = layoutParams.height;

        Log.e("zgf", "start width: " + startWidth + " start height: " + startHeight + " start x: " + startX + " start y: " + startY);
    }

    private void getLocation() {
        int[] location = new int[2];
        ivSmall.getLocationInWindow(location);
        startX = location[0];
        startY = location[1];
    }

    private void getBitmap() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.timu2);
    }

}
