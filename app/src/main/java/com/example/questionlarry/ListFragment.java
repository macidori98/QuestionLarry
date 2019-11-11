package com.example.questionlarry;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    public static final String TAG = ListFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private ListAdapter mAdapter;
    private View view;
    private TextView tv_name, tv_birthDate;
    private DatabaseReference mDatabase;
    private List<Form> formsList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.listRecyclerView);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("forms");
        formsList = new ArrayList<>();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()){
                    String birthDate = item.child("birthDate").getValue().toString();
                    String department = item.child("department").getValue().toString();
                    String gender = item.child("gender").getValue().toString();
                    List<String> hobbies = new ArrayList<>();
                    for (DataSnapshot hobby : item.child("hobbies").getChildren()){
                        hobbies.add(hobby.getValue().toString());
                    }
                    String location = item.child("location").getValue().toString();
                    String longAnswer = item.child("longAnswer").getValue().toString();
                    String name = item.child("name").getValue().toString();
                    String yearOfStudy = item.child("yearOfStudy").getValue().toString();

                    String image = item.child("imageUri").getValue().toString();
                   // String image = "https://firebasestorage.googleapis.com/v0/b/questionlarryformdata.appspot.com/o/images%2Ff38bec1f-39de-4853-aba8-d2bc734345bd?alt=media&token=1712a55d-27b6-4e11-8955-7e3e15a903b2";
                    Form form = new Form(name, location, birthDate,gender, hobbies, department, yearOfStudy, longAnswer, image);
                    formsList.add(form);
                }
                mAdapter = new ListAdapter(getContext(),formsList);
                mAdapter.setOnClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        //uj fragment
                        //Toast.makeText(getContext(),"tftyfty",Toast.LENGTH_SHORT).show();

                        DetailsDialog dialog = new DetailsDialog(formsList.get(position),position);
                        dialog.show(getActivity().getSupportFragmentManager(),"details");
                    }
                });
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
