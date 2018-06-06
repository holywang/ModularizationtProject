package com.holy.modularizationtproject.component.anim;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by DR on 2018/6/6.
 * 抖动动画
 */

public class ShackAnim extends Animation {
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        t.getMatrix().setTranslate(
                (float) Math.sin(interpolatedTime * 50) * 5,
                (float) Math.sin(interpolatedTime * 50) * 5
        );// 50越大频率越高，8越小振幅越小
        super.applyTransformation(interpolatedTime, t);
    }
}