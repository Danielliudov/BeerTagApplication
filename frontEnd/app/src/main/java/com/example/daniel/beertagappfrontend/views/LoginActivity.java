package com.example.daniel.beertagappfrontend.views;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.daniel.beertagappfrontend.R;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.rellayot1)
    RelativeLayout mFirstRelativeLayout;

    @BindView(R.id.rellayot2)
    RelativeLayout mSecondRelativeLayout;

    @BindView(R.id.ed_password)
    EditText mPassword;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
           mFirstRelativeLayout.setVisibility(View.VISIBLE);
           mSecondRelativeLayout.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this, this);

        handler.postDelayed(runnable, 2000);

      //  mPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());


    }
}
