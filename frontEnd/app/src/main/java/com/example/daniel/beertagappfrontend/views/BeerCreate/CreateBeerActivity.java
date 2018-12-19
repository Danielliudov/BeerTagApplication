package com.example.daniel.beertagappfrontend.views.BeerCreate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.daniel.beertagappfrontend.R;
import com.example.daniel.beertagappfrontend.models.Beer;
import com.example.daniel.beertagappfrontend.models.User;
import com.example.daniel.beertagappfrontend.utils.Constants;
import com.example.daniel.beertagappfrontend.views.home.HomePage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateBeerActivity extends AppCompatActivity implements CreateBeerContracts.View {
    private User mUser;


    private CreateBeerContracts.Presenter mPresenter;

    @BindView(R.id.progress_bar_save_beer)
    ProgressBar mProgressiveBar;

    @BindView(R.id.et_name)
    EditText mBeerName;

    @BindView(R.id.et_brand)
    EditText mBeerBrand;

    @BindView(R.id.et_country)
    EditText mBeerCoutry;

    @BindView(R.id.et_abv)
    EditText mAbv;

    @BindView(R.id.et_description)
    EditText mBeerDescription;

    @BindView(R.id.et_style)
    EditText mBeerStyle;

    @BindView(R.id.btn_add_picture)
    Button mAddBeerPicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_beer);


        mPresenter = new CreatebeerPresenter(this);
        mUser = (User) getIntent().getSerializableExtra(Constants.USER_OBJ_EXTRA);

        ButterKnife.bind(this, this);


    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }


    @OnClick(R.id.btn_save)
    public void onBeerSaveClicked() {
        String beername = mBeerName.getText().toString();
        String brand = mBeerBrand.getText().toString();
        String country = mBeerCoutry.getText().toString();
        String abv = mAbv.getText().toString();
        String description = mBeerDescription.getText().toString();
        String style = mBeerStyle.getText().toString();
        Beer beer = new Beer(beername, brand, country, abv, description, style);
        //TODO impement validation about beer fields

        mPresenter.save(beer);

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomePage.class);
        intent.putExtra(Constants.USER_OBJ_EXTRA, mUser);
        startActivity(intent);
        finish();
    }

    @Override
    public void setPresenter(CreateBeerContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(this, HomePage.class);
        intent.putExtra(Constants.USER_OBJ_EXTRA, mUser);
        startActivity(intent);
        finish();
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, "Error: " + throwable.getMessage(), Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void hideLoading() {
        mProgressiveBar.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        mProgressiveBar.setVisibility(View.VISIBLE);
    }
}
