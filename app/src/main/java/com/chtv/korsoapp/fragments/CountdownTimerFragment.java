package com.chtv.korsoapp.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chtv.korsoapp.R;
import com.chtv.korsoapp.ViewModels.MeasurerViewModel;
import com.chtv.korsoapp.databinding.FragmentCountdownTimerBinding;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CountdownTimerFragment.OnCountDownTimerFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CountdownTimerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CountdownTimerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnCountDownTimerFragmentInteractionListener mListener;

    public CountdownTimerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CountdownTimerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CountdownTimerFragment newInstance() {
        CountdownTimerFragment fragment = new CountdownTimerFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentCountdownTimerBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_countdown_timer, container, false);
        View view = binding.getRoot();
        binding.setViewModel(mListener.getViewModel());
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            //mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCountDownTimerFragmentInteractionListener) {
            mListener = (OnCountDownTimerFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCountDownTimerFragmentInteractionListener");
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
    public interface OnCountDownTimerFragmentInteractionListener {
        // TODO: Update argument type and name
        MeasurerViewModel getViewModel();
    }
}
