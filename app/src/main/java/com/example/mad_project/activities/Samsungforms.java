package com.example.mad_project.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mad_project.R;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Samsungforms extends AppCompatActivity {
    WebView web3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samsungforms);
        web3 = findViewById(R.id.webView3);
        WebSettings webSettings3 = web3.getSettings();
        webSettings3.setJavaScriptEnabled(true);
        web3.setWebViewClient(new Callback3());
        web3.loadUrl("https://www.samsung.com/sg/smartphones/galaxy-s21-ultra-5g/");

    }

    private class Callback3 extends WebViewClient {
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) { return false; }
    }
}