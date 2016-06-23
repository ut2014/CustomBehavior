package com.it5.custombehavior;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.TextView;

public class DependentActivity extends Activity  {

    private TextView depentent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dependent_);

        depentent = (TextView) findViewById(R.id.depentent);
        depentent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewCompat.offsetTopAndBottom(v, 5);
            }
        });


    }

}
