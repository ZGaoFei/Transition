package com.example.zhaogaofei.transitiontest.customer;

/**
 * Created by huchengyang on 2017/9/19.
 */

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;

/**
 * A sample implementation of support {@link Transition}.
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class ChangeColor extends Transition {

    /** Key to store a color value in TransitionValues object */
    private static final String PROPNAME_BACKGROUND = "com.hcy.huchengyang.transitionnote:change_color:background";

    /**
     * Convenience method: Add the background Drawable property value
     * to the TransitionsValues.value Map for a target.
     */
    private void captureValues(TransitionValues transitionValues) {
        // Capture the property values of views for later use
        transitionValues.values.put(PROPNAME_BACKGROUND, ((ColorDrawable) transitionValues.view.getBackground()).getColor());
    }

    @Override
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        if (transitionValues.view.getBackground() instanceof ColorDrawable) {
            captureValues(transitionValues);
        }
    }

    @Override
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        if (transitionValues.view.getBackground() instanceof ColorDrawable) {
            captureValues(transitionValues);
        }
    }

    // Create an animation for each target that is in both the starting and ending Scene. For each
    // pair of targets, if their background property value is a color (rather than a graphic),
    // create a ValueAnimator based on an ArgbEvaluator that interpolates between the starting and
    // ending color. Also create an update listener that sets the View background color for each
    // animation frame
    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        if (null == startValues || null == endValues) {
            return null;
        }
        final View view = endValues.view;
        int startBackground = (Integer) startValues.values.get(PROPNAME_BACKGROUND);
        int endBackground = (Integer) endValues.values.get(PROPNAME_BACKGROUND);

        if (startBackground != endBackground) {
            ValueAnimator animator = ValueAnimator.ofObject(new ArgbEvaluator(),
                    startBackground, endBackground);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Object value = animation.getAnimatedValue();
                    if (null != value) {
                        view.setBackgroundColor((Integer) value);
                    }
                }
            });
            return animator;
        }
        return null;
    }

    @Override
    public String[] getTransitionProperties() {
        return new String[]{
                PROPNAME_BACKGROUND
        };

    }

}
