package edu.illinois.cs465.waterwedoing.whatsthemove;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ScreenSlidePageFragment extends Fragment {

    private static final String RESULT = "result";
    private static final String POSITION = "position";

//    private int String_idx = 0;
    private SearchResult searchResult;
    private int position;

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
        TextView actTitle = (TextView)rootView.findViewById(R.id.Activity_Title);
        actTitle.setText(searchResult.getTitle());

        TextView actDesc = (TextView)rootView.findViewById(R.id.Activity_Description);
        actDesc.setText(searchResult.getDescription());

        TextView actTime = (TextView)rootView.findViewById(R.id.Activity_Timing);
        actTime.setText("This activity will take " + searchResult.getTime() + " hour(s)");

        if (position == 0) {
            ImageView sLeft = (ImageView)rootView.findViewById(R.id.swipe_left);
            sLeft.setVisibility(View.INVISIBLE);
        }

        Resources res = getResources();
        if (position == res.getInteger(R.integer.num_pages) - 1){
            ImageView sRight = (ImageView)rootView.findViewById(R.id.swipe_right);
            sRight.setVisibility(View.INVISIBLE);
        }

        ImageView activityImg = (ImageView)rootView.findViewById(R.id.Activity_Image);
        switch(searchResult.getTitle()){
            case "Krannert Art Museum":
                activityImg.setImageDrawable(res.getDrawable(R.drawable.krannert));
                break;
            case "UI Ice Arena":
                activityImg.setImageDrawable(res.getDrawable(R.drawable.ice_arena));
                break;
            case "University of Illinois Observatory":
                activityImg.setImageDrawable(res.getDrawable(R.drawable.uofiobservatory));
                break;
            case "Cravings":
                activityImg.setImageDrawable(res.getDrawable(R.drawable.cravings));
                break;
            case "Taco Bell":
                activityImg.setImageDrawable(res.getDrawable(R.drawable.tacobell));
                break;
        }

        ImageView ratingImg = (ImageView)rootView.findViewById(R.id.Activity_Rating);
        switch (searchResult.getRating()){
            case 1:
                ratingImg.setImageDrawable(res.getDrawable(R.drawable.stars_1));
                break;
            case 2:
                ratingImg.setImageDrawable(res.getDrawable(R.drawable.stars_2));
                break;
            case 3:
                ratingImg.setImageDrawable(res.getDrawable(R.drawable.stars_3));
                break;
            case 4:
                ratingImg.setImageDrawable(res.getDrawable(R.drawable.stars_4));
                break;
            case 5:
                ratingImg.setImageDrawable(res.getDrawable(R.drawable.stars_5));
                break;
            default:
                ratingImg.setImageDrawable(res.getDrawable(R.drawable.stars_1));
                break;
        }
        return rootView;
    }
}
