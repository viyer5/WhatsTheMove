package edu.illinois.cs465.waterwedoing.whatsthemove;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CardFront.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CardFront#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardFront extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String RESULT = "result";

    private SearchResult searchResult;

    private OnFragmentInteractionListener mListener;

    public CardFront() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param result The searchResult to display.
     * @return A new instance of fragment CardFront.
     */
    // TODO: Rename and change types and number of parameters
    public static CardFront newInstance(SearchResult result) {
        CardFront fragment = new CardFront();
        Bundle args = new Bundle();
        args.putParcelable(RESULT, result);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            searchResult = getArguments().getParcelable(RESULT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_card_front, container, false);
        TextView titleView = (TextView) rootView.findViewById(R.id.titleText);
        titleView.setText(searchResult.getTitle());

        ImageView pictureView = (ImageView) rootView.findViewById(R.id.image);
        Resources res = getResources();
        pictureView.setImageDrawable(res.getDrawable(searchResult.getImageId()));

        TextView descriptionView = (TextView) rootView.findViewById(R.id.descriptionText);
        descriptionView.setText(searchResult.getDescription());

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CLICK_CLICK", "card front has been clicked");
            }
        });

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
//        else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
