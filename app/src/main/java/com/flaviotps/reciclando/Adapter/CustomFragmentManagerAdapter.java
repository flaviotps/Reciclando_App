package com.flaviotps.reciclando.Adapter;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class CustomFragmentManagerAdapter extends FragmentStatePagerAdapter {



    private boolean ShowTitle = false;
    private List<Page> Pages = new ArrayList<Page>();

    public CustomFragmentManagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public CustomFragmentManagerAdapter(FragmentManager fm, boolean ShowTitle) {
        super(fm);
        this.ShowTitle = ShowTitle;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if (ShowTitle) {

            if (Pages.get(position) != null && Pages.get(position).getTitle().length() > 0) {
                return Pages.get(position).getTitle();
            } else {
                return null;

            }
        } else return null;
    }

    public List<Page> getPages() {
        return Pages;
    }

    @Override
    public Fragment getItem(int position) {
        return Pages.get(position).getFragment();
    }

    public void AddFragment(Page page) {
        Pages.add(page);
    }

    @Override
    public int getCount() {
        return Pages.size();
    }

    public static class Page {
        private int PageNumber;
        private Fragment fragment;
        private String title;

        public Page(Fragment fragment, String title) {
            this.fragment = fragment;
            this.title = title;

        }

        public Page(Fragment fragment, int PageNumber) {
            this.fragment = fragment;
            this.PageNumber = PageNumber;

        }

        public Page(Fragment fragment, String title, int PageNumber) {
            this.fragment = fragment;
            this.title = title;
            this.PageNumber = PageNumber;
        }

        public Page(Fragment fragment) {
            this.fragment = fragment;
        }

        public Fragment getFragment() {
            return fragment;
        }

        public String getTitle() {
            return title;
        }
    }


}
