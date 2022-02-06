package com.example.mad_project.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mad_project.R;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Googleforms extends AppCompatActivity {
    WebView web6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googleforms);
        getSupportActionBar().hide();
        web6 = findViewById(R.id.webView6);
        WebSettings webSettings6 = web6.getSettings();
        webSettings6.setJavaScriptEnabled(true);
        web6.setWebViewClient(new Callback6());
        web6.loadUrl("https://www.google.com");


    }

    private class Callback6 extends WebViewClient {
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) { return false; }
    }
}