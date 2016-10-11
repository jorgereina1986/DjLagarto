package jorgereina1986.c4q.nyc.djlagarto.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jorgereina1986.c4q.nyc.djlagarto.R;

public class SoundcloudFragment extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.soundcloud_fragment, container, false);


        return rootView;
    }
}
