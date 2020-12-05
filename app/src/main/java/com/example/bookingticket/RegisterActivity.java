package com.example.bookingticket;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.bookingticket.Retrofit2.APIUtils;
import com.example.bookingticket.Retrofit2.DataClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private CircleImageView imgAva;
    private EditText txtUsername,txtPassword,txtEmail;
    private Button btnRegister;

    int REQUEST_CODE_IMAGE = 123;

    private static final int RESCODE = 123;
    private String realPath = "";

    private String user,pass,email;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
        imgAva.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    private void init() {
        imgAva = findViewById(R.id.imgAva);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtEmail = findViewById(R.id.txtEmail);
        btnRegister = findViewById(R.id.btnRegister);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgAva:
                getAva();
                break;
            case R.id.btnRegister:
                Submit();
                break;
        }
    }

    private void Submit() {
        user = txtUsername.getText().toString();
        pass = txtPassword.getText().toString();
        email = txtEmail.getText().toString();
        if(user.length() > 0 && pass.length() > 0 && email.length() > 0 ){
            File file = new File(realPath);
            String filePath = file.getAbsolutePath();
            String[] arrayFile = filePath.split("\\.");

            filePath = arrayFile[0] + System.currentTimeMillis() + "." + arrayFile[1];

            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);//kiểu dữ liệu

            MultipartBody.Part body = MultipartBody.Part.createFormData("uploaded_file", filePath, requestBody);

            Log.d("TAG", filePath);

            DataClient dataClient = APIUtils.getData();//tạo kết nối gửi lên và trả dữ liệu về

            Call<String> callback = dataClient.uploadAva(body);
            callback.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response != null) {
                        String msg = response.body();
                        if(msg.length() > 0){
                            DataClient insertUser = APIUtils.getData();
                            Call<String> callback1 = insertUser.insertUser(user,pass,email,APIUtils.baseUrl + "user/" + msg);
                            callback1.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    String result = response.body();
                                    Log.d("TAG", "onResponse: "+result);
                                    if(result.equals("Success")){
                                        Toast.makeText(RegisterActivity.this,"Sign up successed !",Toast.LENGTH_LONG).show();
                                        finish();
                                    }
                                    if (result.equals("Fail")){
                                        Toast.makeText(RegisterActivity.this,"Your USERNAME or EMAIL have been used !",Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {

                                }
                            });
                        }
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d("bbb", "Fail");
                    Log.d("bbb", t.getMessage());

                };
            });
        }else {
            Toast.makeText(RegisterActivity.this,"Please check your information again !",Toast.LENGTH_LONG).show();
        }
    }

    private void getAva() {
        if(hasPermission()) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent,REQUEST_CODE_IMAGE);
        }else{
            requestPermission();
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent,REQUEST_CODE_IMAGE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            realPath = getRealPathFromURI(uri);
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgAva.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public String getRealPathFromURI (Uri contentUri) {
        String path = null;
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }

    private boolean hasPermission(){
        int code = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return code == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this,
                new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                },
                RESCODE);
    }
}
