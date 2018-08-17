package com.marwane.coach.view;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.marwane.coach.R;
import com.marwane.coach.service.ServiceMP;

/**
 * FRAGMENT
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Counter.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Counter#newInstance} factory method to
 * create an instance of this fragment.
 *
 * Bronnen:
 * https://www.youtube.com/watch?v=UWkEIrBaXqw
 * https://www.youtube.com/watch?v=7d6iKupzkEg
 */
public class Counter extends android.support.v4.app.Fragment  {//implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View v;
    private TextView counterText;
    private ImageButton btnPlus;
    private ImageButton btnMin;
    private ImageButton btnReset;
    private int counter;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_counter, container, false);

        counterText = (TextView) v.findViewById(R.id.txtTime);
        btnPlus = (ImageButton) v.findViewById(R.id.btnPlus);
        //btnPlus.setOnClickListener(this);
        btnMin = (ImageButton) v.findViewById(R.id.btnMin);
        //btnMin.setOnClickListener(this);
        btnReset = (ImageButton) v.findViewById(R.id.btnReset);
        //btnReset.setOnClickListener(this);
        initCounter();
        // Inflate the layout for this fragment

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plusCounter();
            }
        });
        btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                minusCounter();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initCounter();
            }
        });





        return v;
    }

    private void initCounter() {
        counter = 0;
        counterText.setText(counter + "");
    }

    private void plusCounter() {
        counter++;
        counterText.setText(counter + "");
    }

    private void minusCounter() {

        if(counter > 0){
            counter--;
            counterText.setText(counter + "");
        }

    }


    private OnFragmentInteractionListener mListener;

    public Counter() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Counter.
     */
    // TODO: Rename and change types and number of parameters
    public static Counter newInstance(String param1, String param2) {
        Counter fragment = new Counter();
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
