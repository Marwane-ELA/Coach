package com.marwane.coach.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.marwane.coach.R;

/**
 * FRAGMENT
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Chrono.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Chrono#newInstance} factory method to
 * create an instance of this fragment.
 *
 * Bronnen:
 * https://www.youtube.com/watch?v=Dr-VtCbev10&index=14&list=PLUaDUKMTvImX6D-4OMDXF0PNLfGQWSlSU&t=0s
 */
public class Chrono extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageButton btnPlay;
    private ImageButton btnPause;
    private ImageButton btnLap;
    private ImageButton zero;
    private TextView txtTimer;
    private LinearLayout container2;
    View v;
    Handler customHandler = new Handler();


    long startTime=0L,timeInMilliseconds=0L,timeSwapBuff=0L,updateTime=0L;

    public void reset(){
        startTime=0L;
        timeInMilliseconds=0L;
        timeSwapBuff=0L;
        updateTime=0L;
        startTime = SystemClock.uptimeMillis();
        txtTimer.setText("0:00:000");
        container2.removeAllViews();


        //customHandler.postDelayed(updateTimeThread,0);

    }


    Runnable updateTimeThread = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis()-startTime;
            updateTime = timeSwapBuff+timeInMilliseconds;
            int secs = (int)(updateTime/100);
            int mins = secs/60;
            secs%=60;
            int milliseconds = (int) (updateTime%1000);
            txtTimer.setText(""+mins+":"+String.format("%02d",secs)+":"
                                        +String.format("%03d",milliseconds));
            customHandler.postDelayed(this,0);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        v =  inflater.inflate(R.layout.fragment_chrono, container, false);
        txtTimer = (TextView) v.findViewById(R.id.txtTime);
        btnPlay = (ImageButton) v.findViewById(R.id.btnPlay);
        btnPause = (ImageButton) v.findViewById(R.id.btnPause);
        btnLap = (ImageButton) v.findViewById(R.id.btnLap);
        zero = (ImageButton) v.findViewById(R.id.zero);
        container2 = (LinearLayout) v.findViewById(R.id.container);



        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime = SystemClock.uptimeMillis();

                customHandler.postDelayed(updateTimeThread,0);
            }
        });


        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeSwapBuff+=timeInMilliseconds;
                customHandler.removeCallbacks(updateTimeThread);

            }
        });

        btnLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater)v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View addView = inflater.inflate(R.layout.row,null);
                TextView txtValue = (TextView) addView.findViewById(R.id.txtContent);
                txtValue.setText(txtTimer.getText());
                container2.addView(addView);

            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reset();

            }
        });

        // Inflate the layout for this fragment
        return v;
    }
    private OnFragmentInteractionListener mListener;

    public Chrono() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Chrono.
     */
    // TODO: Rename and change types and number of parameters
    public static Chrono newInstance(String param1, String param2) {
        Chrono fragment = new Chrono();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
