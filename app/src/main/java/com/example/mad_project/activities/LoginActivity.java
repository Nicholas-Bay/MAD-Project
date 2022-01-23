package com.example.mad_project.activities;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mad_project.R;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {
    final String SiteKey = "6LeAUfsdAAAAAN3u6auncZyCok7xyLf6b55J8soi";
    final String SecretKey = "6LeAUfsdAAAAAMiAUZKnUZ--iiyis1COH0vpfIaL";
    public String TAG = "Login Activity";
    public String userResponseToken;
    private CheckBox captcha;
    //hooks
    TextInputLayout editUsername , editPassword;
    public Button BuyerSignIn , SellerSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        captcha = findViewById(R.id.captcha_btn);
        captcha.setChecked(false); // change to `captcha.setChecked(false) when submission
        editUsername = findViewById(R.id.textinputLayout2);
        editPassword = findViewById(R.id.textInputLayout);

    }
    private Boolean validateUsername(){
        String val = editUsername.getEditText().getText().toString();
        if(val.isEmpty()){
            editUsername.setError("Username cannot be Empty");
            return false;
        }
        else
        {
            editUsername.setError(null);
            editUsername.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword(){
        String val = editPassword.getEditText().getText().toString();
        if (val.isEmpty()){
            editPassword.setError("Password cannot be Empty");
            return false;

        }
        else{
            editPassword.setError(null);
            editPassword.setErrorEnabled(false);
            return true;
        }
    }
    public void loginBuyer(View view ){
        //for validating actual login info
        if(!validateUsername() | !validatePassword())
        {
            return;
        }
        else
        {
            isBuyer();
        }
    }
    public void LoginSeller(View view ){
        //for validating actual login info
        if(!validateUsername() | !validatePassword())
        {
            return;
        }
        else
        {
            isSeller();
        }
    }
    private void isSeller(){
        final String userEnteredUsername = editUsername.getEditText().getText().toString().trim();
        final String userEnteredPassword = editPassword.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Seller");

        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    editUsername.setError(null);
                    editUsername.setErrorEnabled(false);
                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);
                    if(passwordFromDB.equals(userEnteredPassword)){
                        editUsername.setError(null);
                        editUsername.setErrorEnabled(false);
                        String usernameFromDB = snapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String phoneNoFromDB =snapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
                        String emailFromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);
                        String nameFromDB = snapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        if (captcha.isChecked()){//captcha validation

                        Intent intent = new Intent(getApplicationContext(),MainPageSellerActivity.class);
                        intent.putExtra("username",usernameFromDB);
                        intent.putExtra("password",passwordFromDB);
                        intent.putExtra("name",nameFromDB);
                        intent.putExtra("email",emailFromDB);
                        intent.putExtra("phoneNo",phoneNoFromDB);
                        startActivity(intent);}
                        else Toast.makeText(LoginActivity.this, "bot alert\npls complete ur captcha", Toast.LENGTH_SHORT).show();


                    }
                    else{
                        editPassword.setError("Wrong Password");
                        editPassword.requestFocus();
                    }
                }
                else{
                    editUsername.setError("No Such User");
                    editUsername.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

    }
    private void isBuyer() {
        final String userEnteredUsername = editUsername.getEditText().getText().toString().trim();
        final String userEnteredPassword = editPassword.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Buyer");

        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    editUsername.setError(null);
                    editUsername.setErrorEnabled(false);
                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);
                    if(passwordFromDB.equals(userEnteredPassword)){
                        editUsername.setError(null);
                        editUsername.setErrorEnabled(false);
                        String usernameFromDB = snapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String phoneNoFromDB =snapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
                        String emailFromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);
                        String nameFromDB = snapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        if (captcha.isChecked()){
                        Intent intent = new Intent(getApplicationContext(),MainPageBuyerActivity.class);
                        intent.putExtra("username",usernameFromDB);
                        intent.putExtra("password",passwordFromDB);
                        intent.putExtra("name",nameFromDB);
                        intent.putExtra("email",emailFromDB);
                        intent.putExtra("phoneNo",phoneNoFromDB);
                        startActivity(intent);}
                        else Toast.makeText(LoginActivity.this, "bot alert\npls complete ur captcha", Toast.LENGTH_SHORT).show();


                    }
                    else{
                        editPassword.setError("Wrong Password");
                        editPassword.requestFocus();
                    }
                }
                else{
                    editUsername.setError("No Such User");
                    editUsername.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });


    }
    public void onRegister(View v) {
        startActivity((new Intent(LoginActivity.this, RegisterActivity.class)));
    }
    //captcha stuff
    public void connect(View v) {
        captcha.setChecked(false);
        SafetyNet.getClient(this).verifyWithRecaptcha(SiteKey)
                .addOnSuccessListener(this,
                        response -> {
                            // Indicates communication with reCAPTCHA service was successful.
                            userResponseToken = response.getTokenResult();
                            Log.d(TAG,"response "+userResponseToken);
                            if (!userResponseToken.isEmpty()) new Check().execute();
                        })
                .addOnFailureListener(this, e -> {
                    if (e instanceof ApiException) {
                         // Error occurred when communicating with reCAPTCHA service, check Logcat
                        ApiException apiException = (ApiException) e;
                        int statusCode = apiException.getStatusCode();
                        Log.d(TAG, "Error: " + CommonStatusCodes.getStatusCodeString(statusCode));
                    }
                    else Log.d(TAG, "Error: " + e.getMessage());
                });
        }
        //captcha stuff
    public class Check extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Please Wait ");
            progressDialog.show();
        }
        @Override
        protected String doInBackground(String... strings) {
            String isSuccess = "";
            InputStream is;
            String API = "https://www.google.com/recaptcha/api/siteverify?";
            String newAPI = API + "secret=" + SecretKey + "&response=" + userResponseToken;
            Log.d(TAG," API  " + newAPI);
            try {
                URL url = new URL(newAPI);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(8000 /* ms */);
                httpURLConnection.setConnectTimeout(4000 /* ms */);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);
                // Starts the query
                httpURLConnection.connect();
                int response = httpURLConnection.getResponseCode();
                progressDialog.dismiss();
                System.out.println(response);
                is = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                String result = stringBuilder.toString();
                Log.d("Api", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    System.out.println("Result Object :  " + jsonObject);
                    isSuccess = jsonObject.getString("success");
                    System.out.println("obj "+isSuccess);
                    captcha.setChecked(false);
                } catch (Exception e) {
                    Log.d("Exception: ", e.getMessage());
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
            } catch (Exception e) {
                e.printStackTrace();
                progressDialog.dismiss();
            }
            return isSuccess;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                if (s != null) {
                    switch (s) {
                        case "true":
                            captcha.setChecked(true);
                            Toast.makeText(LoginActivity.this,"Verified", Toast.LENGTH_SHORT).show();
                            return;
                        case "socketexception":
                            Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
                            return;
                        default:
                            Toast.makeText(LoginActivity.this, "something occcured " + s, Toast.LENGTH_SHORT).show();
                            return;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}