package br.com.bmgsistemas.supermarket.ui.wizard.intro;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


//http://www.tellmehow.co/material-view-pager-dots-indicator/
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import br.com.bmgsistemas.supermarket.R;


public class PresentationActivity extends AppCompatActivity {

    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ViewPager mViewPager = findViewById(R.id.viewPager);

        //set the adapter that will create the individual pages
        mViewPager.setAdapter(new MyPagesAdapter());


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {/*empty*/}

            @Override
            public void onPageSelected(int position) {
//                pageIndicatorView.setSelection(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {/*empty*/}
        });


        SpringDotsIndicator indicator = (SpringDotsIndicator ) findViewById(R.id.dots_indicator);
        indicator.setViewPager(mViewPager);
    }

    class MyPagesAdapter extends PagerAdapter {

        public MyPagesAdapter() {
        }

        @Override
        public int getCount() {
            //Return total pages, here one for each data item
            return 3;//data.length;
        }

        //Create the given page (indicated by position)
        @Override
        public Object instantiateItem(final ViewGroup container, int position) {
            View page = null;

            switch (position){
                case 0:
                    page = inflater.inflate(R.layout.presentation_intro_page_01, null);
                    break;
                case 1:
                    page = inflater.inflate(R.layout.presentation_intro_page_02, null);
                    break;
                case 2:
                    page = inflater.inflate(R.layout.presentation_intro_page_03, null);
                    break;
            }

            //Add the page to the front of the queue
            ((ViewPager) container).addView(page, 0);
            return page;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            //See if object from instantiateItem is related to the given view
            //required by API
            return arg0 == (View) arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
            object = null;
        }
    }
}