package com.example.daniel.beertagappfrontend.views.home;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.daniel.beertagappfrontend.R;
import com.example.daniel.beertagappfrontend.models.User;
import com.example.daniel.beertagappfrontend.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomePage extends AppCompatActivity {


    @BindView(R.id.drawer_lt)
    DrawerLayout mDrawerLayout;


    private User mUser;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ButterKnife.bind(this, this);

        mUser = (User) getIntent().getSerializableExtra(Constants.USER_OBJ_EXTRA);


        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);



        NavigationView navigationView = (NavigationView) findViewById(R.id.navigator_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.tv_username);
        navUsername.setText(mUser.getUsername());




        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().show();







    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void selectIterDrawer(MenuItem menuItem){
        Fragment myFragment = null;

        Class fragmentClass;

        switch (menuItem.getItemId()){
            case R.id.add_beer:
                fragmentClass = AddBeerFragment.class;
                break;

                default:
                    fragmentClass = addBeerFragment.class;
        }



    }
}
