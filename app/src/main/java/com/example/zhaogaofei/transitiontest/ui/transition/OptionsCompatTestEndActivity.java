package com.example.zhaogaofei.transitiontest.ui.transition;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.zhaogaofei.transitiontest.R;

public class OptionsCompatTestEndActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, OptionsCompatTestEndActivity.class));
    }

    public static void startWithCustomAnimation(Context context, int enterResId, int exitResId) {
        /*Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(context, enterResId, exitResId).toBundle();
        ActivityCompat.startActivity(context, new Intent(context, OptionsCompatTestEndActivity.class), bundle);*/

        ActivityOptionsCompat compat = ActivityOptionsCompat.makeCustomAnimation(context, enterResId, exitResId);
        Intent intent = new Intent(context, OptionsCompatTestEndActivity.class);
        ActivityCompat.startActivity(context, intent, compat.toBundle());
    }

    public static void startWithClipRevealAnimation(Context context, @NonNull View source, int startX, int startY, int width, int height) {
        Bundle bundle = ActivityOptionsCompat.makeClipRevealAnimation(source, startX, startY, width, height).toBundle();
        ActivityCompat.startActivity(context, new Intent(context, OptionsCompatTestEndActivity.class), bundle);
    }

    /**
     * 这里的startX和startY是指的偏移量
     * makeScaleUpAnimation()方法会根据当前view的位置加上startX和startY的值的位置为开始动画位置
     *
     * 如果想从当前位置开始可以将startX和startY的值设置为0
     */
    public static void startWithScaleUpAnimation(Context context, @NonNull View source, int startX, int startY, int startWidth, int startHeight) {
        Bundle bundle = ActivityOptionsCompat.makeScaleUpAnimation(source, startX, startY, startWidth, startHeight).toBundle();
        ActivityCompat.startActivity(context, new Intent(context, OptionsCompatTestEndActivity.class), bundle);
    }

    public static void startWithThumbnailScaleUpAnimation(Context context, @NonNull View source, @NonNull Bitmap thumbnail, int startX, int startY) {
        Bundle bundle = ActivityOptionsCompat.makeThumbnailScaleUpAnimation(source, thumbnail, startX, startY).toBundle();
        ActivityCompat.startActivity(context, new Intent(context, OptionsCompatTestEndActivity.class), bundle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_compat_test_end);

        initView();
    }

    private void initView() {
        findViewById(R.id.tv_options_compat_finish).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                ActivityCompat.finishAfterTransition(OptionsCompatTestEndActivity.this);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
    }
}
