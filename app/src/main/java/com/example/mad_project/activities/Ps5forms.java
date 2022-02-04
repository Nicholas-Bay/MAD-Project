package com.example.mad_project.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mad_project.R;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Ps5forms extends AppCompatActivity {
    WebView web4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ps5forms);
        web4 = findViewById(R.id.webView4);
        WebSettings webSettings4 = web4.getSettings();
        webSettings4.setJavaScriptEnabled(true);
        web4.setWebViewClient(new Callback4());
        web4.loadUrl("https://www.playstation.com/en-sg/ps5/");
    }

    private class Callback4 extends WebViewClient {
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }
}