package com.example.daniel.beertagappfrontend.views.BeerDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daniel.beertagappfrontend.R;
import com.example.daniel.beertagappfrontend.models.Beer;
import com.example.daniel.beertagappfrontend.models.User;
import com.example.daniel.beertagappfrontend.utils.Constants;
import com.example.daniel.beertagappfrontend.views.home.HomePage;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BeerDetails extends AppCompatActivity {
    private Beer mBeer;
    private User mUser;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_details);


        ButterKnife.bind(this, this);

        //Get the intent extra
        mBeer = (Beer) getIntent().getSerializableExtra(Constants.BEER_OBJ_EXTRA);
        mUser = (User)  getIntent().getSerializableExtra(Constants.USER_OBJ_EXTRA);

        //Hide the action bar
        getSupportActionBar().hide();


    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomePage.class);
        intent.putExtra(Constants.USER_OBJ_EXTRA, mUser);
        startActivity(intent);
        finish();
    }
}
