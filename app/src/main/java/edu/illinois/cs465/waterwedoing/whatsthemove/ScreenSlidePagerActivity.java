package edu.illinois.cs465.waterwedoing.whatsthemove;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

public class ScreenSlidePagerActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;
    private String[] stringArr = {"heyou", "lulz", "hello"};
    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onBackPressed() {
//        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
//        } else {
//            // Otherwise, select the previous step.
//            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
//        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }
        private String[] ActTitles = {"Krannert Art Museum","UI Ice Arena",
                "Unviersity of Illinois Observatory", "Cravings", "Taco Bell"};
        private int[] ActRatings = {4,4,5,4,3};
        private String[] ActDescs = {"The Krannert Art Museum is an art museum " +
                "located at the University of Illinois at Urbana-Champaign in Champaign, " +
                "Illinois, United States. It has 48,000 square feet of space devoted to " +
                "all periods of art, dating from ancient Egypt to contemporary photography.",
                "University of Illinois Ice Arena, also known as the Big Pond, is an ice " +
                "arena and recreational sport facility in Champaign, Illinois, and owned" +
                " and operated by the University of Illinois at Urbana–Champaign.",
                "The University of Illinois Observatory is a great place to look at the night" +
                " sky and to hear about new astronomical results. The University of Illinois" +
                " Astronomical Society (UIAS) has run open houses at the observatory for decades."+
                "It is free to attend on the second Friday of every month (times depend" +
                " on the season).",
                "Pared-down venue offering an extensive menu of hearty Chinese staples, plus other"+
                " Asian dishes.",
                "Fast-food chain serving Mexican-inspired fare such as tacos," +
                " quesadillas & nachos."};
        private int[] ActTime = {2,3,2,1,1};
        @Override
        public Fragment getItem(int position) {
            Fragment cardDeck = new ScreenSlidePageFragment();
            ((ScreenSlidePageFragment) cardDeck).setTitle(ActTitles[position]);
            ((ScreenSlidePageFragment) cardDeck).setFirst(position == 0);
            ((ScreenSlidePageFragment) cardDeck).setLast(position == (NUM_PAGES - 1));
            ((ScreenSlidePageFragment) cardDeck).setRating(ActRatings[position]);
            ((ScreenSlidePageFragment) cardDeck).setDesc(ActDescs[position]);
            ((ScreenSlidePageFragment) cardDeck).setTiming(ActTime[position]);
            return cardDeck;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
