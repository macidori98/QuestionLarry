package com.example.questionlarry;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class FormFragment extends Fragment {

    public static final String TAG = FormFragment.class.getSimpleName();

    private Spinner locationSpinner;
    private Spinner departmentSpinner;
    private EditText et_name;
    private Button buttonDatePicker;
    private RadioGroup radioGroupGender;
    private RadioButton selectedRadioButton;
    private Button submitButton;
    private FrameLayout frameLayoutCheckbox;
    private DatabaseReference mDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.form_fragment, container, false);
        et_name = view.findViewById(R.id.editText_name);
        locationSpinner = view.findViewById(R.id.spinnerr_location);
        departmentSpinner = view.findViewById(R.id.spinner_department);
        buttonDatePicker = view.findViewById(R.id.btn_date_picker);
        et_name = view.findViewById(R.id.editText_name);
        radioGroupGender = view.findViewById(R.id.radio_group_gender);
        submitButton = view.findViewById(R.id.button_submit);
        frameLayoutCheckbox = view.findViewById(R.id.checkbox_layout);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.location));
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);

        ArrayAdapter<String> departmentAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.department));
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentSpinner.setAdapter(departmentAdapter);

        String[] hobbies = getResources().getStringArray(R.array.hobbies);
        for(int i = 0; i < hobbies.length; ++i){
            CheckBox cb = new CheckBox(getActivity().getApplicationContext());
            cb.setText(hobbies[i]);
            cb.setId(i);
            frameLayoutCheckbox.addView(cb);

        }

        buttonDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment newFragment;
                newFragment = new DatePickerFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "date picker");
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioButtonID = radioGroupGender.getCheckedRadioButtonId();
                View radioButton = radioGroupGender.findViewById(radioButtonID);
                int idx = radioGroupGender.indexOfChild(radioButton);
                selectedRadioButton = (RadioButton) radioGroupGender.getChildAt(idx);
                //contains gender
                String selectedText = selectedRadioButton.getText().toString();
            }
        });
    }
}
