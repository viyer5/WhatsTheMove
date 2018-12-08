package edu.illinois.cs465.waterwedoing.whatsthemove;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CardBack.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CardBack#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardBack extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String RESULT = "result";

    // TODO: Rename and change types of parameters
    private SearchResult searchResult;

    private OnFragmentInteractionListener mListener;

    public CardBack() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param result Parameter 1.
     * @return A new instance of fragment CardBack.
     */
    // TODO: Rename and change types and number of parameters
    public static CardBack newInstance(SearchResult result) {
        CardBack fragment = new CardBack();
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_card_back, container, false);
        Resources res = getResources();

        TextView titleView = (TextView) rootView.findViewById(R.id.titleText);
        titleView.setText(searchResult.getTitle());

        TextView timeView = (TextView) rootView.findViewById(R.id.timeText);
        timeView.setText(getString(R.string.duration, searchResult.getTime()));

        ImageView priceView = (ImageView) rootView.findViewById(R.id.priceImage);
        priceView.setImageDrawable(res.getDrawable(searchResult.getPriceImageId()));

        ImageView ratingView = (ImageView) rootView.findViewById(R.id.ratingImage);
        ratingView.setImageDrawable(res.getDrawable(searchResult.getRatingImageId()));

        final ScreenSlidePageFragment parent = (ScreenSlidePageFragment) getParentFragment();
        rootView.findViewById(R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parent.flipCard();
            }
        });

        GoogleMapOptions mapOptions = new GoogleMapOptions();
        SupportMapFragment mapFragment = SupportMapFragment.newInstance(mapOptions);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.mapContainer, mapFragment);
        fragmentTransaction.commit();
        mapFragment.getMapAsync(this);

//        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);

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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng activityLocation = new LatLng(searchResult.getLatitude(), searchResult.getLongitude());
        Marker activityMarker = googleMap.addMarker(new MarkerOptions()
                .position(activityLocation)
                .title(searchResult.getTitle()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(activityLocation, 18));
        activityMarker.showInfoWindow();
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
