package com.example.mad_project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mad_project.R;

public class Applewatchforms extends AppCompatActivity {
    WebView web1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applewatchforms);
        web1 = findViewById(R.id.webView1);
        WebSettings webSettings1 = web1.getSettings();
        webSettings1.setJavaScriptEnabled(true);
        web1.setWebViewClient(new Callback1());
        web1.loadUrl("https://www.apple.com/sg/shop/buy-watch/apple-watch");
    }

    private class Callback1 extends WebViewClient {
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return true;
        }
    }
}