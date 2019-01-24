package com.example.zhaogaofei.transitiontest.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintSet;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ArcMotion;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeScroll;
import android.transition.ChangeTransform;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.PatternPathMotion;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.transition.Visibility;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhaogaofei.transitiontest.R;

/**
 * https://blog.csdn.net/u013475663/article/details/64440819
 * <p>
 * 基本使用
 */
public class TransitionOneActivity extends AppCompatActivity {
    private LinearLayout rootLayout;
    private TextView textView;

    public static void start(Context context) {
        context.startActivity(new Intent(context, TransitionOneActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_one);

        initView();

        initAutoTransitionView();

        initChangeBoundsView();

        initChangeClipBoundsView();

        initChangeImageTransformView();

        initChangeScrollView();

        initChangeTransformView();

        initExplodeView();

        initTransitionSetView();

        initPathMotionView();
    }

    private void initView() {
        rootLayout = findViewById(R.id.root_layout);
        textView = findViewById(R.id.tv_one);

        Button button = findViewById(R.id.bt_one);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                setTextViewShow();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setTextViewShow() {
        // 默认就为autoTransition
        TransitionManager.beginDelayedTransition(rootLayout);
        textView.setVisibility(textView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    // AutoTransition
    private void initAutoTransitionView() {
        final ImageView imageView = findViewById(R.id.iv_auto_transition);
        final LinearLayout linearLayout = findViewById(R.id.frame_auto_transition);
        final LinearLayout innerLayout = findViewById(R.id.ll_inner_layout);
        Button button = findViewById(R.id.bt_auto_transition);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                initAutoTransition(imageView, linearLayout);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initAutoTransition(ImageView imageView, LinearLayout frameLayout) {
        TransitionManager.beginDelayedTransition(frameLayout, new AutoTransition());

        // 改变view的宽高
                /*ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                int width = layoutParams.width;
                if (width < 200) {
                    layoutParams.width = layoutParams.height = 450;
                } else {
                    layoutParams.width = layoutParams.height = 150;
                }
                imageView.setLayoutParams(layoutParams);*/

        // 改变父类布局的padding值
        int paddingLeft = frameLayout.getPaddingLeft();
        if (paddingLeft > 100) {
            frameLayout.setPadding(50, 50, 50, 50);
        } else {
            frameLayout.setPadding(200, 200, 200, 200);
        }
    }

    /**
     * ChangeBounds
     * 对应xml tag为changeBounds
     * 根据前后布局界限的变化执行动画
     */
    private void initChangeBoundsView() {
        final FrameLayout frameLayout = findViewById(R.id.frame_change_bounds);
        final TextView textView = findViewById(R.id.tv_change_bounds);
        Button button = findViewById(R.id.bt_change_bounds);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                initChangeBounds(textView, frameLayout);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initChangeBounds(TextView textView, FrameLayout frameLayout) {
        ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(2000);
        TransitionManager.beginDelayedTransition(frameLayout, bounds);

        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) textView.getLayoutParams();
        if ((lp.gravity & Gravity.BOTTOM) == Gravity.BOTTOM) {
            lp.gravity = Gravity.TOP | Gravity.END;
        } else {
            lp.gravity = Gravity.BOTTOM | Gravity.START;
        }
        textView.setLayoutParams(lp);
    }

    private void initChangeClipBoundsView() {
        final FrameLayout frameLayout = findViewById(R.id.frame_change_clip_bounds);
        final ImageView imageView = findViewById(R.id.iv_change_clip_bounds);
        Button button = findViewById(R.id.bt_change_clip_bounds);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                initChangeClipBounds(frameLayout ,imageView);
            }
        });
    }

    /**
     * 使用Rect rect = new Rect(150, 150, 150, 150)时，（1）
     * 或者Rect rect = new Rect(50, 50, 50, 50)时，（2）
     * 或者Rect rect = new Rect(50, 150, 150, 150)，（3）
     * 会出现动画执行完，回到初始位置的情况
     *
     * （1）（2）是屏幕上的一个点，（3）是一条线
     * 因此必须是一个矩形
     *
     * 对应xml tag为changeClipBounds
     * 作用对象：View的getClipBounds()值
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initChangeClipBounds(FrameLayout frameLayout, ImageView imageView) {
        Rect rect = new Rect(50, 50, 150, 150);
        TransitionManager.beginDelayedTransition(frameLayout, new ChangeClipBounds());
        if (rect.equals(ViewCompat.getClipBounds(imageView))) {
            ViewCompat.setClipBounds(imageView, null);
        } else {
            ViewCompat.setClipBounds(imageView, rect);
        }

    }

    /**
     * ChangeImageTransform
     * 对应xml tag为changeImageTransform
     * 作用对象：ImageView的matrix
     */
    private void initChangeImageTransformView() {
        final LinearLayout linearLayout = findViewById(R.id.ll_change_image_transform);
        final ImageView imageView = findViewById(R.id.iv_change_image_transform);
        Button btOne = findViewById(R.id.bt_change_image_transform);
        btOne.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                initChangeImageTransform(linearLayout, imageView);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initChangeImageTransform(LinearLayout linearLayout, ImageView imageView) {
        TransitionManager.beginDelayedTransition(linearLayout, new ChangeImageTransform());
        ImageView.ScaleType scaleType = imageView.getScaleType();
        if (scaleType == ImageView.ScaleType.CENTER) {
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        } else if (scaleType == ImageView.ScaleType.FIT_XY) {
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        } else if (scaleType == ImageView.ScaleType.CENTER_INSIDE) {
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else if (scaleType == ImageView.ScaleType.CENTER_CROP) {
            imageView.setScaleType(ImageView.ScaleType.FIT_START);
        } else if (scaleType == ImageView.ScaleType.FIT_START) {
            imageView.setScaleType(ImageView.ScaleType.FIT_END);
        } else if (scaleType == ImageView.ScaleType.FIT_END) {
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        } else if (scaleType == ImageView.ScaleType.FIT_CENTER) {
            imageView.setScaleType(ImageView.ScaleType.MATRIX);
        } else if (scaleType == ImageView.ScaleType.MATRIX) {
            imageView.setScaleType(ImageView.ScaleType.CENTER);
        }
    }

    /**
     * ChangeScroll
     * 很奇怪，如果TextView设置为wrap_content时，移动会把TextView移动消失
     * 如果设置为match_parent时，移动的只是文字的区域，view并没有移动
     * 设置ImageView也是这样的效果
     *
     * 解决方式是给这个TextView外面包裹一层parent view，相当于TextView在这个parent view中移动
     *
     * 移动布局内部的东西，
     * 如果是一个TextView，则移动内部的文字
     * ImageView，移动内部图片
     * ViewGroup，移动child view
     * 有可能View的scroll就是这样实现的
     *
     * 对应xml tag为changeScroll
     * 作用对象：View的scroll属性值
     */
    private void initChangeScrollView() {
        final FrameLayout frameLayout = findViewById(R.id.frame_change_scroll);
        final FrameLayout innerFrame = findViewById(R.id.frame_inner_change_scroll);
        final TextView textView = findViewById(R.id.tv_change_scroll);
        final ImageView imageView = findViewById(R.id.iv_change_scroll);
        Button button = findViewById(R.id.bt_change_scroll);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                initChangeScroll(frameLayout, textView, imageView, innerFrame);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initChangeScroll(FrameLayout frameLayout, TextView textView, ImageView imageView, FrameLayout inner) {
        TransitionManager.beginDelayedTransition(frameLayout, new ChangeScroll());
        inner.scrollBy(-100, 50);
        // imageView.scrollBy(-100, 50);
        // textView.scrollBy(-100, 50);
    }

    /**
     * ChangeTransform
     * 对应xml tag 为changeTransform
     * 作用对象：View的scale和rotation
     */
    private void initChangeTransformView() {
        final LinearLayout linearLayout = findViewById(R.id.ll_change_transform);
        final ImageView imageView = findViewById(R.id.iv_change_transform);
        final FrameLayout innerLayout1 = findViewById(R.id.frame_inner_change_transform1);
        final FrameLayout innerLayout2 = findViewById(R.id.frame_inner_change_transform2);
        Button button = findViewById(R.id.bt_change_transform);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                initChangeTransform(linearLayout, imageView, innerLayout1, innerLayout2);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initChangeTransform(LinearLayout linearLayout, ImageView imageView, FrameLayout inner1, FrameLayout inner2) {
        TransitionManager.beginDelayedTransition(linearLayout, new ChangeTransform());

        // 每次旋转90
        float rotation = imageView.getRotation();
        imageView.setRotation(rotation + 90);

        if (inner2.getChildCount() > 0) {
            inner2.removeAllViews();
            inner1.addView(imageView);
        } else {
            inner1.removeAllViews();
            inner2.addView(imageView);
        }
    }

    /**
     * Explode
     * 对应xml tag为explode，
     * 作用对象：View的Visibility
     *
     * Fade
     * 对应xml tag为fade
     * 作用对象：View的Visibility
     * 可以在初始化是指定IN或者OUT分别对应淡入和淡出，若不指定默认为淡入淡出效果
     *
     * Slide
     * 对应xml tag为slide
     * 作用对象：View的Visibility
     */
    private void initExplodeView() {
        final FrameLayout frameLayout = findViewById(R.id.frame_explode);
        final TextView tvOne = findViewById(R.id.tv_explode_one);
        final TextView tvTwo = findViewById(R.id.tv_explode_two);
        final TextView tvThree = findViewById(R.id.tv_explode_three);
        final TextView tvFour = findViewById(R.id.tv_explode_four);
        final View[] views = new View[]{tvOne, tvTwo, tvThree, tvFour};
        Button button = findViewById(R.id.bt_explode);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                initExplode(frameLayout, views);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initExplode(FrameLayout frameLayout, View[] views) {
        // TransitionManager.beginDelayedTransition(frameLayout, new Explode());
        // TransitionManager.beginDelayedTransition(frameLayout, new Fade());
        TransitionManager.beginDelayedTransition(frameLayout, new Slide());

        int visibility = views[0].getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE;
        for (View view : views) {
            view.setVisibility(visibility);
        }
    }

    /**
     * TransitionSet
     * 对应xml tag为transitionSet
     */
    private void initTransitionSetView() {
        final LinearLayout linearLayout = findViewById(R.id.ll_transition_set);
        final ImageView imageView = findViewById(R.id.iv_transition_set);
        final FrameLayout innerLayout1 = findViewById(R.id.frame_inner_transition_set1);
        final FrameLayout innerLayout2 = findViewById(R.id.frame_inner_transition_set2);
        Button button = findViewById(R.id.bt_transition_set);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                initTransitionSet(linearLayout, imageView, innerLayout1, innerLayout2);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initTransitionSet(LinearLayout linearLayout, ImageView imageView, FrameLayout inner1, FrameLayout inner2) {
        ChangeTransform changeTransform = new ChangeTransform();
        ChangeImageTransform changeImageTransform = new ChangeImageTransform();
        TransitionSet set = new TransitionSet()
                .addTransition(changeTransform)
                .addTransition(changeImageTransform)
                .setOrdering(TransitionSet.ORDERING_TOGETHER);

        TransitionManager.beginDelayedTransition(linearLayout, set);

        // 每次旋转180
        float rotation = imageView.getRotation();
        imageView.setRotation(rotation + 180);

        if (inner2.getChildCount() > 0) {
            inner2.removeAllViews();
            inner1.addView(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER);
        } else {
            inner1.removeAllViews();
            inner2.addView(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }

    /**
     * Transition的辅助工具，
     * 以path的方式指定过渡效果，两个具体实现类ArcMotion和PatternPathMotion
     *
     * ArcMotion
     * PatternPathMotion
     * 会形成一定的圆弧路径
     */
    private void initPathMotionView() {
        final FrameLayout frameLayout = findViewById(R.id.frame_inner_path_motion);
        final TextView textView = findViewById(R.id.tv_path_motion);
        final TextView textView2 = findViewById(R.id.tv_path_motion2);
        Button button = findViewById(R.id.bt_path_motion);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                initPathMotion(frameLayout, textView);
                initPathMotion2(frameLayout, textView2);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initPathMotion(FrameLayout frameLayout, TextView textView) {
        Transition autoTransition = new ChangeBounds();
        autoTransition.setDuration(1000);
        ArcMotion arcMotion = new ArcMotion();
        // arcMotion.setMinimumHorizontalAngle(60);
        // arcMotion.setMinimumVerticalAngle(60);
        // arcMotion.setMaximumAngle(90);
        autoTransition.setPathMotion(arcMotion);

        TransitionManager.beginDelayedTransition(frameLayout, autoTransition);
        textView.setLayoutParams(getLayoutParams(textView));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initPathMotion2(FrameLayout frameLayout, TextView textView) {
        AutoTransition autoTransition = new AutoTransition();
        autoTransition.setDuration(1000);
        autoTransition.setPathMotion(new PatternPathMotion());

        TransitionManager.beginDelayedTransition(frameLayout, autoTransition);
        textView.setLayoutParams(getLayoutParams(textView));
    }

    private FrameLayout.LayoutParams getLayoutParams(View view) {
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) view.getLayoutParams();
        if ((lp.gravity & Gravity.BOTTOM) == Gravity.BOTTOM) {
            lp.gravity = Gravity.TOP | Gravity.END;
        } else {
            lp.gravity = Gravity.BOTTOM | Gravity.START;
        }
        return lp;
    }

}
