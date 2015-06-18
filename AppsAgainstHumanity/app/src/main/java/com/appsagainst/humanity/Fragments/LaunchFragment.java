package com.appsagainst.humanity.Fragments;

/**
 * Created by User on 09/05/2015.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.appsagainst.humanity.R;

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
        Bundle args = new Bundle();
        args.putBoolean("isHost",true);

        LobbyFragment lobbyFragment =  new LobbyFragment();
        lobbyFragment.setArguments(args);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.holder, lobbyFragment).addToBackStack("1").commit();
    }

    @OnClick(R.id.joinGame)
    public void joinGameClicked(Button button) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.holder, new FindServerFragment()).addToBackStack("2").commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}