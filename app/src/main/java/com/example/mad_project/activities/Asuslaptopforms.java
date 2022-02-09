package com.example.mad_project.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mad_project.R;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Asuslaptopforms extends AppCompatActivity {

    WebView web8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asuslaptopforms);
        web8 = findViewById(R.id.webView8);
        WebSettings webSettings8 = web8.getSettings();
        webSettings8.setJavaScriptEnabled(true);
        web8.setWebViewClient(new Callback());
        web8.loadUrl("https://sg.store.asus.com/laptop.html");



    }

    private class Callback extends WebViewClient {
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }
}