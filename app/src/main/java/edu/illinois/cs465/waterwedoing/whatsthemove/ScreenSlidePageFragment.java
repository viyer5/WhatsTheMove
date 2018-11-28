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

//    private int String_idx = 0;
    private String titleString = "Title";
    private String descString = "Description Description Description";
    private boolean isFirst = false;
    private boolean isLast = false;
    private int rating = 5;
    private int timing = 1;
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

        TextView actDesc = (TextView)rootView.findViewById(R.id.Activity_Description);
        actDesc.setText(descString);

        TextView actTime = (TextView)rootView.findViewById(R.id.Activity_Timing);
        actTime.setText("This activity will take " + timing + " hour(s)");

        if (isFirst) {
            ImageView sLeft = (ImageView)rootView.findViewById(R.id.swipe_left);
            sLeft.setVisibility(View.INVISIBLE);
        }

        if (isLast){
            ImageView sRight = (ImageView)rootView.findViewById(R.id.swipe_right);
            sRight.setVisibility(View.INVISIBLE);
        }

        ImageView activityImg = (ImageView)rootView.findViewById(R.id.Activity_Image);
        Resources res = getResources();
        switch(titleString){
            case "Krannert Art Museum":
                activityImg.setImageDrawable(res.getDrawable(R.drawable.krannert));
                break;
            case "UI Ice Arena":
                activityImg.setImageDrawable(res.getDrawable(R.drawable.ice_arena));
                break;
            case "Unviersity of Illinois Observatory":
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
        res = getResources();
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

    public void setTitle(String str) {
        titleString = str;
    }

    public void setDesc(String str) {
        descString = str;
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

    public void setTiming(int timing) {
        this.timing = timing;
    }
}
