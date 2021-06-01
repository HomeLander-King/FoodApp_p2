package com.sunburt.foodapp_p1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.sunburt.foodapp_p1.R;
import com.sunburt.foodapp_p1.database.DBHelper;

public class RegisterActivity extends AppCompatActivity {

    private EditText editUsername, editPassword,  editRePassword;
    private Button btnRegister;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getControls();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editUsername.getText().toString();
                String password = editPassword.getText().toString();
                String rePassword = editRePassword.getText().toString();
                if (password.equals(rePassword)) {
                    boolean checkAddUser = db.addNewUser(username, password,rePassword);
                    Toast.makeText(RegisterActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Password and RePassword don't match",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void getControls() {
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        editRePassword = findViewById(R.id.editRePassword);
        btnRegister = findViewById(R.id.btnRegister);
        db = new DBHelper(this);
    }
}