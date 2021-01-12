package com.americanairlines.thegymmanager.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.americanairlines.thegymmanager.R;
import com.americanairlines.thegymmanager.model.db.GymMember;
import com.americanairlines.thegymmanager.presenter.MemberAddPresenter;
import com.americanairlines.thegymmanager.presenter.MembershipPresenterContract;

import java.util.ArrayList;

import static com.americanairlines.thegymmanager.presenter.MembershipPresenterContract.*;

public class AddMemberActivity extends AppCompatActivity implements AddMemberView
{

    private MembershipPresenterContract.AddMemberPresenter presenter;

    private EditText etName, etAge;
    private Spinner spinStatus;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        etName = findViewById(R.id.et_name);
        etAge = findViewById(R.id.et_age);
        spinStatus = findViewById(R.id.spin_status);
        btnSubmit = findViewById(R.id.btn_submit);

        String [] statusSpinnerItems = {"Regular", "Gold", "Platinum"};

        presenter = new MemberAddPresenter(this);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, statusSpinnerItems);
        spinStatus.setAdapter(spinnerAdapter);

        btnSubmit.setOnClickListener(v->{
            if(checkEntry()){
                String name = etName.getText().toString();
                int age = Integer.parseInt(etAge.getText().toString());
                String status = spinStatus.getSelectedItem().toString();

                Log.d("TAG_X", "Status: " + status);

                presenter.insertMember(new GymMember(name, age, status));
                Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    private boolean checkEntry() {
        if(etName.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter a name!", Toast.LENGTH_SHORT).show();
        } else if (etAge.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter an age!", Toast.LENGTH_SHORT).show();
       /* } else if (spinStatus.getSelectedItem().toString().isEmpty()){
            Toast.makeText(this, "Please select a membership!", Toast.LENGTH_SHORT).show();*/
        } else {
            return true;
        }

        return false;
    }

    @Override
    public void displayError(String errorMessage) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog
                        .Builder(new ContextThemeWrapper(AddMemberActivity.this, R.style.Theme_TheGymManager))
                        .setTitle("Database Error")
                        .setMessage(errorMessage)
                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create()
                        .show();
            }
        });
    }

    @Override
    public void displaySuccess(String successMessage) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog
                        .Builder(new ContextThemeWrapper(AddMemberActivity.this, R.style.Theme_TheGymManager))
                        .setTitle("Database Success")
                        .setMessage(successMessage)
                        .setNegativeButton("Thanks", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create()
                        .show();
            }
        });

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}