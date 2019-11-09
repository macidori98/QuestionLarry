package com.example.questionlarry;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ChooseFragment extends Fragment {

    public static final String TAG = ChooseFragment.class.getSimpleName();

    private Button btn_form, btn_list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.choose_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_form = view.findViewById(R.id.btn_form);
        btn_list = view.findViewById(R.id.btn_list);

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToList();
            }
        });

        btn_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToForm();
            }
        });
    }

    public void goToList(){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragment_content, new ListFragment(), ListFragment.TAG);
        fragmentTransaction.commit();
    }

    void goToForm(){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragment_content, new FormFragment(), FormFragment.TAG);
        fragmentTransaction.commit();
    }


}
