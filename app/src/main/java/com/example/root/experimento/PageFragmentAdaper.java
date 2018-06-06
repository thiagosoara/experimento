package com.example.root.experimento;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.example.root.experimento.fragments.PageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16/05/17.
 */

class PageFragmentAdaper extends FragmentPagerAdapter {

    String[] titles;
    List<PageFragment> pages = new ArrayList<PageFragment>();


    public PageFragmentAdaper(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
        this.pages.add(new PageFragment());
        this.pages.add(new PageFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return pages.get(position);
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
