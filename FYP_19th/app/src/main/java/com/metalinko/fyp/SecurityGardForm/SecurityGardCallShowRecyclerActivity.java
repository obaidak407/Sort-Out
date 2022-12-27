package com.metalinko.fyp.SecurityGardForm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.metalinko.fyp.Admin.AdminStudentComplaintActivity;
import com.metalinko.fyp.Adopter.AdminStudentAdopter;
import com.metalinko.fyp.Adopter.SecurityGardCallAdopter;
import com.metalinko.fyp.Model.SecurityGardCallModel;
import com.metalinko.fyp.Model.StudentFormModel;
import com.metalinko.fyp.R;
import com.metalinko.fyp.databinding.ActivitySecurityGardCallShowRecyclerBinding;

import java.util.ArrayList;

public class SecurityGardCallShowRecyclerActivity extends AppCompatActivity {
            ActivitySecurityGardCallShowRecyclerBinding binding;
    public ArrayList<SecurityGardCallModel> arrayList;
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private SecurityGardCallAdopter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySecurityGardCallShowRecyclerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mDatabase = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();
        adapter = new SecurityGardCallAdopter(arrayList, this);
        recyclerView.setAdapter(adapter);

        show_data();



    }


    private void show_data() {


        Query query = mDatabase.child("securitycall");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Data Reterive
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    SecurityGardCallModel studentFormModel = new SecurityGardCallModel();

                    studentFormModel.setSd_name(dataSnapshot.child("sd_name").getValue().toString());
                    studentFormModel.setPhone_number(dataSnapshot.child("phone_number").getValue().toString());
                    //  studentFormModel.setSt_name(dataSnapshot.child("st_dec").getValue().toString());



                    // For Intent

                    arrayList.add(studentFormModel);

                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


                Toast.makeText(SecurityGardCallShowRecyclerActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}