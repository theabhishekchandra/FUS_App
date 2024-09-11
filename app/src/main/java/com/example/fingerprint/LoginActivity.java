package com.example.fingerprint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText uname, passwd;
    Button login_sub_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        uname = findViewById(R.id.uname);
        passwd = findViewById(R.id.passwd);
        login_sub_btn = findViewById(R.id.login_sub_btn);

        CheckUserExit();
        login_sub_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processLogin();
            }
        });

    }

    private void CheckUserExit() {
        SharedPreferences loginSP = getSharedPreferences("Credentials", MODE_PRIVATE);
        if (loginSP.contains("username")){
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        }

    }

    void processLogin(){

        String username = uname.getText().toString().trim();
        String password = passwd.getText().toString().trim();
        Call<ResponseModel> call = controller
                .getInstance()
                .getapi()
                .loginUser(username,password);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                Log.d("API_Response", "Response Code: " + response.code());

                if (response.isSuccessful() && response.body() != null) {
                    ResponseModel obj = response.body();
                    String output = obj.getMessage();
                    Toast.makeText(LoginActivity.this, "Login Failed1", Toast.LENGTH_SHORT).show();


                    if (output.equals("Login successful")) {
                        // Handle successful login
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                        SharedPreferences loginSP = getSharedPreferences("Credentials", MODE_PRIVATE);
                        SharedPreferences.Editor editor = loginSP.edit();
                        editor.putString("username", username);
                        editor.putString("password", password);
                        editor.commit();
                        editor.apply();

                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        finish();

                    } else {
                        // Handle login failure
                        Toast.makeText(LoginActivity.this, "Login Failed1", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Login 1 ", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                Log.e("LoginActivity","Login Failed" + t.getMessage());
                Toast.makeText(LoginActivity.this, "Login Failed2"+ username + password, Toast.LENGTH_SHORT).show();
                if(t instanceof IOException) {
                    // Network error, probably a timeout or no connection
                    Toast.makeText(LoginActivity.this, "Network Error! Please check your connection.", Toast.LENGTH_SHORT).show();

                } else {
                    // Other unknown errors
                    Toast.makeText(LoginActivity.this, "Something went wrong! Please try again later.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}