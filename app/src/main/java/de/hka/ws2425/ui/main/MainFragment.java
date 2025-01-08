package de.hka.ws2425.ui.main;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import de.hka.ws2425.api.RandomJoke;
import de.hka.ws2425.ui.map.MapFragment;
import de.hka.ws2425.R;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        /*TextView txtMessage = this.getView().findViewById(R.id.txt_message);
        txtMessage.setText("This is a stupid way to set a text ....");*/

        Button btnDoSomething = this.getView().findViewById(R.id.btn_do_something);
        btnDoSomething.setOnClickListener((view) -> {
            FragmentTransaction fragmentTransaction = this.getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack("MainFragment");
            fragmentTransaction.replace(R.id.container, new MapFragment());
            fragmentTransaction.commit();
        });

        TextView txtMessage = this.getView().findViewById(R.id.txt_message);

        mViewModel.getResult().observe(this.getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txtMessage.setText(s);
            }
        });

    }
}