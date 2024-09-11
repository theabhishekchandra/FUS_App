package com.example.fingerprint;

// Import necessary libraries
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener {
    protected Button cam_scanner, cam_login;
    public String storedResult;
    RecyclerView recycle_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recycle_view = findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        layoutManager.setItemPrefetchEnabled(false);
        recycle_view.setLayoutManager(layoutManager);
        MyAdapter adapter = new MyAdapter(new ArrayList<>());
        adapter.setOnItemClickListener(this); // Setting the click listener
        recycle_view.setAdapter(adapter);

        processData();

        cam_scanner = findViewById(R.id.cam_scanner);
        cam_scanner.setOnClickListener(v -> {
            codeScane();
        });

        cam_login = findViewById(R.id.cam_login);
        cam_login.setOnClickListener(v -> {
            if (storedResult != null && !storedResult.isEmpty()) {
                webupdate(storedResult);
            }
        });
    }

    void user_act_up(String status) {
        SharedPreferences loginSP = getSharedPreferences("Credentials", MODE_PRIVATE);
        String username = loginSP.getString("username", "");
        Call<ResponseModel> call = controller
                .getInstance()
                .getapi()
                .user_act_up(username, status);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });
    }

    void webupdate(String webcode) {
        SharedPreferences loginSP = getSharedPreferences("Credentials", MODE_PRIVATE);
        String username = loginSP.getString("username", "");

        Call<ResponseModel> call = controller
                .getInstance()
                .getapi()
                .updatewebcode(username, webcode);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });
    }

    private void codeScane() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Beep On");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
            storedResult = result.getContents();
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
            builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss()).show();
        }
    });

    public void processData() {
        Call<List<ResponseModel>> call = controller
                .getInstance()
                .getapi()
                .getdata();
        call.enqueue(new Callback<List<ResponseModel>>() {
            @Override
            public void onResponse(Call<List<ResponseModel>> call, Response<List<ResponseModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ResponseModel> log_data = response.body();
                    MyAdapter adapter = new MyAdapter(log_data);
                    recycle_view.setAdapter(adapter);
                    recycle_view.addItemDecoration(new ItemDecoration(16));
                }
            }

            @Override
            public void onFailure(Call<List<ResponseModel>> call, Throwable t) {

            }
        });
    }

    public void onItemButtonClicked(String username, String status) {
        // Call your API method here
//        user_act_up(username, status);
    }

    @Override
    public void onItemButtonClick(String status) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
