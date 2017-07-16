package app.ittraing.com.ittrainingdemoapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.ittraing.com.ittrainingdemoapp.R;

/**
 * Created by ranja_000 on 6/13/2017.
 */

public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_profile, container, false );

        return view;
    }

}
