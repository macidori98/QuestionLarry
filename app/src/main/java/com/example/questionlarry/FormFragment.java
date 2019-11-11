package com.example.questionlarry;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


public class FormFragment extends Fragment {

    public static final String TAG = FormFragment.class.getSimpleName();
    public String birthDateSelected;
    public static int ID = 0;

    FirebaseStorage storage;
    StorageReference storageReference;

    private Spinner locationSpinner;
    private Uri imageUri;
    private Spinner departmentSpinner;
    private EditText et_name, et_expectation;
    private Button buttonDatePicker;
    private RadioGroup radioGroupGender, radioGroupYear;
    private ImageButton imageButton_myImage;
    private Button submitButton;
    private LinearLayout linearLayoutCheckbox;
    private DatabaseReference mDatabase;
    private String locationSelected, yearSelected, longAnswer, departmentSelected;
    public String dateSelected;
    private List<String> hobbiesSelected;
    private String selectedGender, selectedYear;
    private Uri selectedImage;

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
        linearLayoutCheckbox = view.findViewById(R.id.checkbox_layout);
        radioGroupYear = view.findViewById(R.id.radio_group_study_year);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        hobbiesSelected = new ArrayList<>();
        et_expectation = view.findViewById(R.id.et_expectation);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        imageButton_myImage = view.findViewById(R.id.imageButton_profile_picture);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case 1:
                    //data.getData returns the content URI for the selected Image
                    selectedImage = data.getData();
                    imageButton_myImage.setImageURI(selectedImage);
                    break;
            }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        final ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.location));
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);

        final ArrayAdapter<String> departmentAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.department));
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentSpinner.setAdapter(departmentAdapter);

        final List<String> hobbies = Arrays.asList(getResources().getStringArray(R.array.hobbies));
        for(int i = 0; i < hobbies.size(); ++i){
            CheckBox cb = new CheckBox(getActivity().getApplicationContext());
            cb.setText(hobbies.get(i));
            cb.setId(i);
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                        if (compoundButton.isChecked()){
                            String element = compoundButton.getText().toString();
                            hobbiesSelected.add(element);
                            Toast.makeText(getContext(), "added", Toast.LENGTH_SHORT).show();
                        } else {
                            hobbiesSelected.remove(compoundButton.getText().toString());
                            Toast.makeText(getContext(),"removed", Toast.LENGTH_SHORT).show();
                        }
                }
            });
            linearLayoutCheckbox.addView(cb);

        }
        final List<String> gender = Arrays.asList(getResources().getStringArray(R.array.gender));
        for (int i = 0; i < gender.size(); ++i){
            RadioButton rb = new RadioButton(getActivity().getApplicationContext());
            rb.setText(gender.get(i));
            rb.setId(i);
            rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (compoundButton.isChecked()){
                        MainActivity ma =(MainActivity) getActivity();
                        selectedGender = compoundButton.getText().toString();
                        Toast.makeText(getContext(), ma.getDate(), Toast.LENGTH_SHORT).show();
                    } else {
                        selectedGender = "";
                    }
                }
            });
            radioGroupGender.addView(rb);
        }

        final List<String> yearOfStudy = Arrays.asList(getResources().getStringArray(R.array.yearOfStudy));
        for (int i = 0; i < yearOfStudy.size(); ++i){
            RadioButton rb = new RadioButton(getActivity().getApplicationContext());
            rb.setText(yearOfStudy.get(i));
            rb.setId(i);
            rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (compoundButton.isChecked()){
                        selectedYear = compoundButton.getText().toString();
                    } else {
                        selectedYear = "";
                    }
                }
            });
            radioGroupYear.addView(rb);
        }

        buttonDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment newFragment;
                newFragment = new DatePickerFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "date picker");
            }
        });

        imageButton_myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File f = new File(android.os.Environment.getExternalStorageDirectory(),"temp.jpg");
                Uri uri = FileProvider.getUriForFile(getContext().getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider", f);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, 1);*/
                //Create an Intent with action as ACTION_PICK
                Intent intent=new Intent(Intent.ACTION_PICK);
                // Sets the type as image/*. This ensures only components of type image are selected
                intent.setType("image/*");
                //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
                String[] mimeTypes = {"image/jpeg", "image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
                // Launching the Intent
                startActivityForResult(intent,1);
            }
        });



        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idx;
                String nameSelected = et_name.getText().toString();

                idx = (int) locationSpinner.getSelectedItemId();
                locationSelected = locationAdapter.getItem(idx);

                //image

                //dateselected
                MainActivity ma =(MainActivity) getActivity();
                dateSelected = ma.DATE;

                //gender selectedgender

                //hobbiesselected

                //department
                idx = (int) departmentSpinner.getSelectedItemId();
                departmentSelected = departmentAdapter.getItem(idx);

                //year study selectyear

                String expectation = et_expectation.getText().toString();



                writeNewForm(String.valueOf(ID), nameSelected, locationSelected, dateSelected, selectedGender, hobbiesSelected,
                        departmentSelected, selectedYear, expectation);
            }
        });
    }

    private void writeNewForm(String formID,final String name,final String location,final String birthDate,final String gender,
                              final List<String> hobbies, final String department, final String yearOfStudy,
                              final String longAnswer) {

        if(selectedImage != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            final StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(selectedImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            if (taskSnapshot.getMetadata() != null) {
                                if (taskSnapshot.getMetadata().getReference() != null) {
                                    Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                                    result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            progressDialog.dismiss();
                                            String imageUrl = uri.toString();
                                            //createNewPost(imageUrl);
                                            Toast.makeText(getActivity().getApplicationContext(), imageUrl, Toast.LENGTH_SHORT).show();
                                            Form form = new Form(name, location, birthDate, gender, hobbies, department, yearOfStudy, longAnswer, imageUrl);
                                            mDatabase.child("forms").child(mDatabase.push().getKey()).setValue(form);
                                        }
                                    });
                                }
                            }
                        }})
                    /*
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            String imageUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                            // String imageUrl = taskSnapshot.getUploadSessionUri().toString();
                            Toast.makeText(getActivity().getApplicationContext(), imageUrl, Toast.LENGTH_SHORT).show();
                            Form form = new Form(name, location, birthDate, gender, hobbies, department, yearOfStudy, longAnswer, imageUrl);
                            mDatabase.child("forms").child(mDatabase.push().getKey()).setValue(form);
                        }
                    })*/

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity().getApplicationContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

}
