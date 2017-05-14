package com.chtv.korsoapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.chtv.korsoapp.Models.ContestSession;
import com.chtv.korsoapp.R;
import com.chtv.korsoapp.adapters.SessionListAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import io.realm.OrderedRealmCollection;
import io.realm.OrderedRealmCollectionSnapshot;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewSessionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewSessionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewSessionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private OnFragmentInteractionListener mListener;
    private AppCompatEditText sessionName;

    public NewSessionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment NewSessionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewSessionFragment newInstance(String param1) {
        NewSessionFragment fragment = new NewSessionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_session, container, false);

        ((TextView)v.findViewById(R.id.event_name)).setText(mParam1);
        sessionName = (AppCompatEditText) v.findViewById(R.id.session_name_edittext);

        ((Button) v.findViewById(R.id.button_ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveButtonCLick();
            }
        });

        ((Button) v.findViewById(R.id.cancel_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelClick();
            }
        });


        return v;
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


    public void saveButtonCLick(){
        String sName = sessionName.getText().toString();
        mListener.onSaveSessionClick(sName);
    }

    public void cancelClick() {
        mListener.onCancelCLick();
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
        void onSaveSessionClick(String sessionName);
        void onCancelCLick();
    }
}
