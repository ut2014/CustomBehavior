package com.it5.custombehavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by IT5 on 2016/6/22.
 */
public class DependentBehavior extends CoordinatorLayout.Behavior {

    public DependentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //绑定指定类型的View,既关心的哪个veiw
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof TextView;
//        return super.layoutDependsOn(parent,child,dependency);
    }

    //View状态发生变化的时
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        int offset = dependency.getTop() - child.getTop();//偏移量
        ViewCompat.offsetTopAndBottom(child, offset);
        return super.onDependentViewChanged(parent, child, dependency);
    }
}