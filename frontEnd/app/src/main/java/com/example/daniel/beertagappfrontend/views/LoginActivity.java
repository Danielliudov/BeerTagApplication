package com.example.daniel.beertagappfrontend.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.daniel.beertagappfrontend.R;
import com.example.daniel.beertagappfrontend.models.User;
import com.example.daniel.beertagappfrontend.utils.enums.ErrorCode;

import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import studios.codelight.smartloginlibrary.LoginType;
import studios.codelight.smartloginlibrary.SmartLogin;
import studios.codelight.smartloginlibrary.SmartLoginCallbacks;
import studios.codelight.smartloginlibrary.SmartLoginConfig;
import studios.codelight.smartloginlibrary.SmartLoginFactory;
import studios.codelight.smartloginlibrary.UserSessionManager;
import studios.codelight.smartloginlibrary.users.SmartUser;
import studios.codelight.smartloginlibrary.util.SmartLoginException;

public class LoginActivity extends AppCompatActivity implements SmartLoginCallbacks,
        LoginContracts.View {

    @BindView(R.id.rellayot1)
    RelativeLayout mFirstRelativeLayout;

    @BindView(R.id.rellayot2)
    RelativeLayout mSecondRelativeLayout;

    @BindView(R.id.ed_username)
    EditText mUsername;

    @BindView(R.id.ed_password)
    EditText mPassword;

    @BindView(R.id.btn_login)
    Button mLogin;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;


    SmartUser mCurrentUser;
    SmartLoginConfig mConfig;
    SmartLogin mSmartLogin;
    private LoginContracts.Presenter mPresenter;
    private AlertDialog mAlertDialog;

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


        mConfig = new SmartLoginConfig(this, this);
        mPresenter = new LoginPresenter(this);


        handler.postDelayed(runnable, 2000);

        //  mPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());


    }


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mCurrentUser = UserSessionManager.getCurrentUser(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mSmartLogin != null)
            mSmartLogin.onActivityResult(requestCode, resultCode, data, mConfig);
    }





    @OnClick(R.id.btn_login)
    void customLoginClicked() {
        mSmartLogin = SmartLoginFactory.build(LoginType.CustomLogin);
        mSmartLogin.login(mConfig);
    }



    @Override
    public void setPresenter(LoginContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setRegisterErrors(Set<ErrorCode> errors, Map<String, TextInputLayout> tils) {
        for (ErrorCode errorCode : errors) {
            switch (errorCode) {
                case USERNAME_NULL:
                case USERNAME_TOO_SIMPLE:
                    tils.get("username").setError(errorCode.getLabel(getBaseContext()));
                    break;
                case USERNAME_OK:
                    tils.get("username").setError(null);
                    break;
                case PASSWORD_NULL:
                case PASSWORD_TOO_SIMPLE:
                    tils.get("password_one").setError(errorCode.getLabel(getBaseContext()));
                    tils.get("password_two").setError(errorCode.getLabel(getBaseContext()));
                    break;
                case PASSWORDS_DONT_MATCH:
                    tils.get("password_one").setError(null);
                    tils.get("password_two").setError(errorCode.getLabel(getBaseContext()));
                    break;
                case PASSWORD_OK:
                    tils.get("password_two").setError(null);
                    break;
            }
        }

    }

    @Override
    public void showError(Throwable throwable) {

        String errorExplained = throwable.getMessage().trim();
        switch (errorExplained) {
            case "401":
                errorExplained = "Invalid credentials. Check your email and password.";
                break;
        }
        Toast.makeText(this, "Error: " + errorExplained, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public Activity getActivity()
    {
        return this;
    }

    @Override
    public void navigateToHome(User user) {
        Toast.makeText(this, "Username is logged successfuly", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoginSuccess(SmartUser user) {

    }

    @Override
    public void onLoginFailure(SmartLoginException e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public SmartUser doCustomLogin() {
        mPresenter.login(
                mUsername.getText().toString(),
                mPassword.getText().toString());
        SmartUser customUser = new SmartUser();
        customUser.setUsername(mUsername.getText().toString());

        return customUser;
    }

    @Override
    public SmartUser doCustomSignup() {
        return null;
    }






}
