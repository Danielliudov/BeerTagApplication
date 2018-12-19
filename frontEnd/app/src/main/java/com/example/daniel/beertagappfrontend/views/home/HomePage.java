package com.example.daniel.beertagappfrontend.views.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daniel.beertagappfrontend.R;
import com.example.daniel.beertagappfrontend.models.Beer;
import com.example.daniel.beertagappfrontend.models.User;
import com.example.daniel.beertagappfrontend.utils.Constants;
import com.example.daniel.beertagappfrontend.views.BeerCreate.CreateBeerActivity;
import com.example.daniel.beertagappfrontend.views.BeerDetails.BeerDetails;
import com.example.daniel.beertagappfrontend.views.home.recycler.BeerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, HomePageContracs.View, BeerAdapter.OnItemClickListener, AdapterView.OnItemSelectedListener {
    private HomePageContracs.Navigator mNavigator;

    @BindView(R.id.drawer_lt)
    DrawerLayout mDrawerLayout;


    @BindView(R.id.rv_beers)
    RecyclerView mRecyclerView;

    @BindView(R.id.loading)
    ProgressBar mLoadingView;


    private User mUser;
    private HomePageContracs.Presenter mPresenter;
    private BeerAdapter mBeerAdapter;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ButterKnife.bind(this, this);

        //get intent
        mUser = (User) getIntent().getSerializableExtra(Constants.USER_OBJ_EXTRA);


        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);


        NavigationView navigationView = (NavigationView) findViewById(R.id.navigator_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.tv_username);
        navUsername.setText(mUser.getUsername());
        navigationView.setNavigationItemSelectedListener(this);

        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().show();

        //Presenter
        mPresenter = new HomePagePresenter(this);
        mBeerAdapter = new BeerAdapter();
        mBeerAdapter.setOnBeerClickListener(this);
        mRecyclerView.setAdapter(mBeerAdapter);
        LinearLayoutManager mContactsViewLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mContactsViewLayoutManager);


        //TODO Spinner adapter --->


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.add_beer) {
            Intent intent = new Intent(this, CreateBeerActivity.class);
            intent.putExtra(Constants.USER_OBJ_EXTRA, mUser);
            startActivity(intent);
            finish();

            //TODO implement the other MenuItems selection
        }

        return false;
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadBeer();
    }


    @Override
    public void setPresenter(HomePageContracs.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showBeers(List<Beer> beers) {
        mBeerAdapter.clear();
        mBeerAdapter.addAll(beers);
        mBeerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmptyBeerList() {
        Toast.makeText(this,
                "No Beers",
                Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void showLoading() {
        mRecyclerView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void showBeerDetails(Beer beer) {

    }


    //TODO beer_detail_activity and navigation to it (intent with extra beer details):
    /*
    @Override
    public void showBeerDetails(Beer beer) {

        mNavigator.navigateWith(beer);
    }

    void setNavigator(HomePageContracs.Navigator navigator) {
        mNavigator = navigator;
    }


    public void navigateWith(Beer beer) {


    }*/

    @Override
    public void onClick(Beer beer) {
        mPresenter.selectBeer(beer);

        Intent intent = new Intent(this, BeerDetails.class);
        intent.putExtra(Constants.BEER_OBJ_EXTRA, beer);
        intent.putExtra(Constants.USER_OBJ_EXTRA, mUser);
        startActivity(intent);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //TODO SPINNER

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}
