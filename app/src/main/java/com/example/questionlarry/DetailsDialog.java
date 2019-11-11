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

    private TextView tv_name, tv_birthdate, tv_location;

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

        tv_location.setText(form.getLocation());
        tv_birthdate.setText(form.getBirthDate());
        tv_name.setText(form.getName());

        doSomething(builder,view);
        return builder.create();
    }

    private void doSomething(AlertDialog.Builder builder, View view){
        builder.setView(view).setTitle("Dialog").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    }
}
