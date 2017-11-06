package com.example.user.randomapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 14/9/17.
 */

public class DoubleViewPagerActivity extends AppCompatActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.parent_view_pager)
    ViewPager viewPager;
    @BindView(R.id.news_layout)
    FrameLayout newsLayout;

    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.double_view_pager_activity);
        ButterKnife.bind(this);

        tabLayout.addTab(tabLayout.newTab().setText("All stories"));
        tabLayout.addTab(tabLayout.newTab().setText("City"));
        tabLayout.addTab(tabLayout.newTab().setText("Animals"));
        tabLayout.addTab(tabLayout.newTab().setText("Food"));
        tabLayout.addTab(tabLayout.newTab().setText("Sports"));
        tabLayout.addTab(tabLayout.newTab().setText("Technology"));

        ArrayList<String> newsData = new ArrayList<>();
        ArrayList<String> categoryList = new ArrayList<>();
        categoryList.add("City");
        categoryList.add("Animals");
        categoryList.add("Food");
        categoryList.add("Sports");
        categoryList.add("Technology");
        viewPagerAdapter = new ViewPagerAdapter(categoryList, newsData);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }
        });
    }

    class ViewPagerAdapter extends PagerAdapter {
        private List<String> categoryList;
        private VerticalViewPager verticalViewPager;
        private ArrayList<String> newsData;
        private VerticalViewPagerAdapter verticalViewPagerAdapter;

        private int selectedPage;

        public ViewPagerAdapter(ArrayList<String> categoryList, ArrayList<String> newsData) {
            this.categoryList = categoryList;
            this.newsData = newsData;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
            View view = inflater.inflate(R.layout.vertical_view_pager, container, false);
            if (view.getParent() == null) {
                container.addView(view);
            } else {
                ((ViewGroup) view.getParent()).removeView(view);
                container.addView(view);
            }
            verticalViewPager = (VerticalViewPager)
                    view.findViewById(R.id.vertical_view_pager);
            verticalViewPagerAdapter = new VerticalViewPagerAdapter(newsData);
            verticalViewPager.setAdapter(verticalViewPagerAdapter);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return categoryList.size();
        }

        public int getSelectedPage() {
            return selectedPage;
        }

        public void setSelectedPage(int selectedPage) {
            this.selectedPage = selectedPage;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return categoryList.get(position);
        }
    }

    class VerticalViewPagerAdapter extends PagerAdapter {

        private ArrayList<String> newsData;

        public VerticalViewPagerAdapter(ArrayList<String> newsData) {
            this.newsData = newsData;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());

            ViewGroup view = (ViewGroup) inflater.inflate(R.layout.inner_layout,
                    container, false);
            TextView title = (TextView) view.findViewById(R.id.tv_news_title);
            TextView description = (TextView) view.findViewById(R.id.tv_news_description);
            ImageView thumbnail = (ImageView) view.findViewById(R.id.iv_thumbnail);
            TextView fullStory = (TextView) view.findViewById(R.id.tv_full_story);
            TextView author = (TextView) view.findViewById(R.id.tv_author);
            TextView publishedOn = (TextView) view.findViewById(R.id.tv_date);

            if (view.getParent() == null) {
                container.addView(view);
            } else {
                ((ViewGroup) view.getParent()).removeView(view);
                container.addView(view);
            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return 50;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getItemPosition(Object object) {
            if (object instanceof View && newsData.size() != -1) {
                return newsData.indexOf(object);
            } else {
                return POSITION_NONE;
            }
        }
    }
}
