package com.example.daniel.beertagappfrontend.views.BeerDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daniel.beertagappfrontend.R;
import com.example.daniel.beertagappfrontend.models.Beer;
import com.example.daniel.beertagappfrontend.models.User;
import com.example.daniel.beertagappfrontend.utils.Constants;
import com.example.daniel.beertagappfrontend.views.home.HomePage;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BeerDetails extends AppCompatActivity implements BeerDetailsContracts.View {
    private Beer mBeer;
    private User mUser;

    private BeerDetailsContracts.Presenter mPresenter;

    @BindView(R.id.beerdetail_image)
    ImageView mBeerImage;

    @BindView(R.id.beer_country_tv)
    TextView mBeerCountry;

    @BindView(R.id.beer_brand_tv)
    TextView mBeerBrand;

    @BindView(R.id.beer_abv_tv)
    TextView mBeerAbv;

    @BindView(R.id.beer_style_tv)
    TextView mBeerStyle;

    @BindView(R.id.beer_description_tv)
    TextView mBeerDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_details);


        ButterKnife.bind(this, this);


        //Presenter
        mPresenter = new BeerDetailsPresenter(this);
        //Get the intent extra
        mBeer = (Beer) getIntent().getSerializableExtra(Constants.BEER_OBJ_EXTRA);
        mUser = (User) getIntent().getSerializableExtra(Constants.USER_OBJ_EXTRA);

        //Hide the action bar
        getSupportActionBar().hide();


    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mBeerCountry.setText("Country: "+mBeer.getCountry());
        mBeerBrand.setText("Brand: "+mBeer.getBrand());
        mBeerAbv.setText("Abv: "+mBeer.getAbv());
        mBeerStyle.setText("Style: "+mBeer.getStyle());
        mBeerDescription.setText("Description: "+mBeer.getDescription());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomePage.class);
        intent.putExtra(Constants.USER_OBJ_EXTRA, mUser);
        startActivity(intent);
        finish();
    }

    @Override
    public void showBeer(Beer beer) {
        mBeerCountry.setText("Country: "+mBeer.getCountry());
        mBeerBrand.setText(mBeer.getBrand());
        mBeerAbv.setText(mBeer.getAbv());
        mBeerStyle.setText(mBeer.getStyle());
        mBeerDescription.setText(mBeer.getDescription());


    }

    @Override
    public void setPresenter(BeerDetailsContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
