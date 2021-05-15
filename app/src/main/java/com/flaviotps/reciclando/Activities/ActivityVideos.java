package com.flaviotps.reciclando.Activities;

import android.os.Bundle;

import com.flaviotps.reciclando.Adapter.CustomFragmentManagerAdapter;
import com.flaviotps.reciclando.Adapter.CustomFragmentManagerAdapter.Page;
import com.flaviotps.reciclando.Data.DataRequestManager;
import com.flaviotps.reciclando.Data.PageModel;
import com.flaviotps.reciclando.Data.VideoModel;
import com.flaviotps.reciclando.Fragments.FragmentVideos;
import com.flaviotps.reciclando.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class ActivityVideos extends AppCompatActivity implements ViewPager.OnPageChangeListener {


    private CustomFragmentManagerAdapter mCustomFragmentManagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);


        mViewPager = findViewById(R.id.ViewPagerVideos);

        mCustomFragmentManagerAdapter = new CustomFragmentManagerAdapter(getSupportFragmentManager(), true);

        mViewPager.addOnPageChangeListener(this);

        setupViewPager(mViewPager);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(R.string.menu_videos);


    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }

    @Override
    public void onPageSelected(int position) {
        setViewPager(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public void setupViewPager(ViewPager viewPager) {


        if (DataRequestManager.data.videoList == null) {
            return;
        }

        List<PageModel> pages = VideoModel.GetUniquePages(DataRequestManager.data.videoList);


        for (int i = 0; i < pages.size(); i++) {

            mCustomFragmentManagerAdapter.AddFragment(new Page(FragmentVideos.newInstance(pages.get(i).getIntPage(), this), pages.get(i).Title, i));

        }

        viewPager.setAdapter(mCustomFragmentManagerAdapter);
        SmartTabLayout smartTabLayout = findViewById(R.id.smartTabLayout);
        smartTabLayout.setViewPager(viewPager);
    }


    public void setViewPager(int fragmentNumber) {
        mViewPager.setCurrentItem(fragmentNumber);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

