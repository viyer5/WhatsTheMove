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

public class ScreenSlidePageFragment extends Fragment {

//    private int String_idx = 0;
    private String titleString;
    private String descString;
    private boolean isFirst = false;
    private boolean isLast = false;
    private int rating;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState)
    {
//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View custom = inflater.inflate(R.layout.fragment_screen_slide_page, null);
//        TextView tv = (TextView)custom.findViewById(R.id.text);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page,
                container, false);
        TextView actTitle = (TextView)rootView.findViewById(R.id.Activity_Title);
        actTitle.setText(titleString);

        if (isFirst) {
            ImageView sLeft = (ImageView)rootView.findViewById(R.id.swipe_left);
            sLeft.setVisibility(View.INVISIBLE);
        }

        if (isLast){
            ImageView sRight = (ImageView)rootView.findViewById(R.id.swipe_right);
            sRight.setVisibility(View.INVISIBLE);
        }

        ImageView ratingImg = (ImageView)rootView.findViewById(R.id.Activity_Rating);
        Resources res = getResources();
        switch (rating){
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

    public void setString(String str) {
        titleString = str;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
