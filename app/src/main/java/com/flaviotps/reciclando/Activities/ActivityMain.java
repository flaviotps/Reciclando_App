package com.flaviotps.reciclando.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.flaviotps.reciclando.Adapter.CustomFragmentManagerAdapter;
import com.flaviotps.reciclando.Custom.CustomViewPager;
import com.flaviotps.reciclando.Data.DataRequestManager;
import com.flaviotps.reciclando.Fragments.FragmentMain;
import com.flaviotps.reciclando.Fragments.FragmentMaterialMenu;
import com.flaviotps.reciclando.Fragments.FragmentProduct;
import com.flaviotps.reciclando.Interfaces.IPageViewSetup;
import com.flaviotps.reciclando.R;
import com.flaviotps.reciclando.Utils.Constants;
import com.flaviotps.reciclando.Utils.Helper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class ActivityMain extends AppCompatActivity implements ViewPager.OnPageChangeListener, IPageViewSetup {


    private DataRequestManager mDataRequestManager;
    private CustomFragmentManagerAdapter mCustomFragmentManagerAdapter;
    private CustomViewPager mViewPager;
    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {

                case R.id.main:
                    setViewPager(Constants.MAIN_FRAGMENT_MAIN);
                    return true;
                case R.id.material:
                    setViewPager(Constants.MAIN_FRAGMENT_MATERIAL);
                    return true;

                case R.id.products:
                    setViewPager(Constants.MAIN_FRAGMENT_PRODUCTS);
                    return true;

                case R.id.location:
                    Intent intent = new Intent(ActivityMain.this, ActivityLocation.class);
                    startActivity(intent);
                    return true;

            }


            return false;

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);
        //actionBar.setSubtitle(R.string.app_sub_name);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        //actionBar.setIcon(R.drawable.ic_launcher);


        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mCustomFragmentManagerAdapter = new CustomFragmentManagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.CustomViewPager);
        mViewPager.setEnableTouch(true);
        mViewPager.addOnPageChangeListener(this);

        setupViewPager(mViewPager);

        Helper.PreLoadMap(getApplicationContext());


    }


    public void ChangeMenuCurrentItem(int id) {
        navigation.setSelectedItemId(id);
    }


    @Override
    public void setupViewPager(ViewPager viewPager) {
        CustomFragmentManagerAdapter adapter = new CustomFragmentManagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new CustomFragmentManagerAdapter.Page(new FragmentMain()));
        adapter.AddFragment(new CustomFragmentManagerAdapter.Page(new FragmentMaterialMenu()));
        adapter.AddFragment(new CustomFragmentManagerAdapter.Page(new FragmentProduct()));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void setViewPager(int fragmentNumber) {
        mViewPager.setCurrentItem(fragmentNumber);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }

    @Override
    public void onPageSelected(int position) {

        switch (position) {

            case 0:
                navigation.setSelectedItemId(R.id.main);
                break;
            case 1:
                navigation.setSelectedItemId(R.id.material);
                break;

            case 2:
                navigation.setSelectedItemId(R.id.products);
                break;

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {

    }


}