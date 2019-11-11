package com.example.questionlarry;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DetailsDialog extends AppCompatDialogFragment {

    private Form form;
    private int position;

    private TextView tv_name, tv_birthdate, tv_location, tv_gender, tv_hobby, tv_department,
        tv_yearOfStudy, tv_expactation ;

    public DetailsDialog(){}

    public DetailsDialog(Form form, int position){
        this.form = form;
        this.position = position;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.details, null);

        tv_birthdate = view.findViewById(R.id.detail_birthdate);
        tv_name = view.findViewById(R.id.detail_name);
        tv_location = view.findViewById(R.id.detail_location);
        tv_gender = view.findViewById(R.id.detail_gender);
        tv_hobby = view.findViewById(R.id.detail_hobby);
        tv_department = view.findViewById(R.id.detail_department);
        tv_yearOfStudy = view.findViewById(R.id.detail_year);
        tv_expactation = view.findViewById(R.id.detail_expactation);

        tv_location.setText(form.getLocation());
        tv_birthdate.setText(form.getBirthDate());
        tv_name.setText(form.getName());

        tv_gender.setText(form.getGender());

        String hobbylist="";
        for (int i = 0; i < form.getHobbies().size(); ++i){
            if (i == form.getHobbies().size()-1){
                hobbylist = hobbylist + form.getHobbies().get(i) ;
            } else {
                hobbylist = hobbylist + form.getHobbies().get(i) + "\n";
            }

        }

        tv_hobby.setText(hobbylist);

        tv_department.setText(form.getDepartment());
        tv_yearOfStudy.setText(form.getYearOfStudy());
        tv_expactation.setText(form.getLongAnswer());

        doSomething(builder,view);
        return builder.create();
    }

    private void doSomething(AlertDialog.Builder builder, View view){
        builder.setView(view).setTitle("Details").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    }
}
