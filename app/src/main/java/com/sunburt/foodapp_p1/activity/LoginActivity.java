package com.sunburt.foodapp_p1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.sunburt.foodapp_p1.R;
import com.sunburt.foodapp_p1.database.DBHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText editUsername, editPassword;
    private Button btnLogin;
    private TextView txtReg;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getControls();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });

        txtReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void doLogin() {
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();
        boolean checkLogin = db.checkUser(username, password);
        if (checkLogin == true){
            Intent intent  = new Intent(getApplicationContext(), SplashScreenActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(LoginActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
        }
    }


    private void getControls() {
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtReg = findViewById(R.id.txtReg);
        db = new DBHelper(this);
    }
}