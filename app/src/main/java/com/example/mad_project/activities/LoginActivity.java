package com.example.mad_project.activities;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_project.R;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;

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
    public Button showhide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        captcha = findViewById(R.id.captcha_btn);
        captcha.setChecked(true);//for now
    }
    public void onLoginBuyer(View v) {
        if (captcha.isChecked()) startActivity((new Intent(LoginActivity.this, MainPageBuyerActivity.class)));
        else Toast.makeText(LoginActivity.this, "bot alert\npls complete ur captcha", Toast.LENGTH_SHORT).show();
    }
    public void onLoginSeller(View v) {
        if (captcha.isChecked())startActivity((new Intent(LoginActivity.this, MainPageSellerActivity.class)));
        else Toast.makeText(LoginActivity.this, "bot alert\npls complete ur captcha", Toast.LENGTH_SHORT).show();
    }
    public void onRegisterSeller(View v) {
        startActivity((new Intent(LoginActivity.this, RegisterActivity.class)));
    }
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
                    captcha.setChecked(true);
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