package com.it5.custombehavior.refresh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.it5.custombehavior.R;

public class RefreshMainActivity extends Activity implements View.OnClickListener {

    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=this;
        setContentView(R.layout.refresh_activity_main);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                //TODO implement
                startActivity(new Intent(activity,Activity1.class));
                break;
            case R.id.btn2:
                //TODO implement
                startActivity(new Intent(activity,Activity2.class));
                break;
        }
    }
}
