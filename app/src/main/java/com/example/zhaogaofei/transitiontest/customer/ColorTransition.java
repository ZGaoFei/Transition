package com.example.zhaogaofei.transitiontest.customer;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.transition.Transition;
import android.transition.TransitionValues;

/**
 * 其工作原理是在captureStartValues和captureEndValues中分别记录View的属性值，
 * 官网建议确保属性值不冲突，属性值的命名格式参考：
 * package_name:transition_name:property_name
 */

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class ColorTransition extends Transition {

    @Override
    public void captureStartValues(TransitionValues transitionValues) {

    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {

    }
}
