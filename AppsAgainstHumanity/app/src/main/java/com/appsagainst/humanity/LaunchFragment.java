package com.appsagainst.humanity;

/**
 * Created by User on 09/05/2015.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LaunchFragment extends Fragment {

    @InjectView(R.id.hostGame)
    Button hostGame;

    @InjectView(R.id.joinGame)
    Button joinGame;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_launch, container, false);

        ButterKnife.inject(this, view);
        return view;
    }

    @OnClick(R.id.hostGame)
    public void hostGameClicked(Button button) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.holder, new HostGameFragment()).commit();
    }

    @OnClick(R.id.joinGame)
    public void joinGameClicked(Button button) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.holder, new JoinGameFragment()).commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}