package edu.illinois.cs465.waterwedoing.whatsthemove;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ScreenSlidePageFragment extends Fragment {

    private static final String RESULT = "result";
    private static final String POSITION = "position";

//    private int String_idx = 0;
    private SearchResult searchResult;
    private int position;
    private boolean mShowingBack;

    public ScreenSlidePageFragment() {

    }

    public static ScreenSlidePageFragment newInstance(SearchResult res, int pos) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putParcelable(RESULT, res);
        args.putInt(POSITION, pos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            searchResult = getArguments().getParcelable(RESULT);
            position = getArguments().getInt(POSITION);
            if (savedInstanceState == null) {
                CardFront front = CardFront.newInstance(searchResult);
                getChildFragmentManager()
                        .beginTransaction()
                        .add(R.id.container, front)
                        .commit();
                mShowingBack = false;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState)
    {
//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View custom = inflater.inflate(R.layout.fragment_screen_slide_page, null);
//        TextView tv = (TextView)custom.findViewById(R.id.text);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page,
                container, false);

        if (position == 0) {
            ImageView sLeft = (ImageView)rootView.findViewById(R.id.swipe_left);
            sLeft.setVisibility(View.INVISIBLE);
        }

        Resources res = getResources();
        if (position == res.getInteger(R.integer.num_pages) - 1){
            ImageView sRight = (ImageView)rootView.findViewById(R.id.swipe_right);
            sRight.setVisibility(View.INVISIBLE);
        }

        rootView.findViewById(R.id.flipButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CLICK_CLICK", "container clicked");
                flipCard();
            }
        });

//        float scale = rootView.getResources().getDisplayMetrics().density;
//        rootView.setCameraDistance(8000 * scale);

        return rootView;
    }

    private void flipCard() {
        if (mShowingBack) {
            getChildFragmentManager().popBackStack();
            mShowingBack = false;
            return;
        }

        // Flip to the back

        mShowingBack = true;
        CardBack back = CardBack.newInstance(searchResult);

        // Create and commit a new fragment transaction that adds the fragment for
        // the back of the card, uses custom animations, and is part of the fragment
        // manager's back stack.

        getChildFragmentManager()
                .beginTransaction()

                // Replace the default fragment animations with animator resources
                // representing rotations when switching to the back of the card, as
                // well as animator resources representing rotations when flipping
                // back to the front (e.g. when the system Back button is pressed).
                .setCustomAnimations(
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)

                // Replace any fragments currently in the container view with a
                // fragment representing the next page (indicated by the
                // just-incremented currentPage variable).
                .replace(R.id.container, back)

                // Add this transaction to the back stack, allowing users to press
                // Back to get to the front of the card.
                .addToBackStack(null)

                // Commit the transaction.
                .commit();
    }
}
