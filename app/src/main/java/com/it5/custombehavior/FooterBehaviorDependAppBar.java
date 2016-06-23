package com.it5.custombehavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by IT5 on 2016/6/22.
 */
public class FooterBehaviorDependAppBar extends CoordinatorLayout.Behavior<View> {


    public FooterBehaviorDependAppBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //指绑定到指定的view上
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }


    //当指定的View发生更变时触发
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float translationY = Math.abs(dependency.getTranslationY());
        child.setTranslationY(translationY);
        return true;
    }
}
