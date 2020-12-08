package com.example.bookingticket;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookingticket.Retrofit2.APIUtils;
import com.example.bookingticket.Retrofit2.DataClient;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtUsername,txtPassword;
    private Button btnLogin, btnRegister;
    private String pass,user;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

    }

    private void init() {
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    // Register
    private void register() {
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

    // Login
    private void login() {
        user = txtUsername.getText().toString();
        pass = txtPassword.getText().toString();

        if(user.length() > 0 && pass.length() > 0){
            DataClient dataClient = APIUtils.getData();
            Call<List<Account>> callback = dataClient.userLogin(user,pass);
            callback.enqueue(new Callback<List<Account>>() {
                @Override
                public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                    ArrayList<Account> arrayUser = (ArrayList<Account>) response.body();
                    if(arrayUser.size()>0){
                        Intent intent = new Intent(LoginActivity.this,DashboardActivity.class);
                        intent.putExtra("arrayUser",arrayUser);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this,"Success",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Account>> call, Throwable t) {
                    Toast.makeText(LoginActivity.this,"Please check your USERNAME or PASSWORD again! ",Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                login();
                break;
            case R.id.btnRegister:
                register();
                break;
        }
    }
}
