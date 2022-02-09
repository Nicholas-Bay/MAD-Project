package com.example.mad_project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mad_project.R;

public class ShopeeClothing extends AppCompatActivity {

    WebView web10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopee_clothing);
        web10 = findViewById(R.id.webView10);
        WebSettings webSettings10 = web10.getSettings();
        webSettings10.setJavaScriptEnabled(true);
        web10.setWebViewClient(new Callback10());
        web10.loadUrl("https://shopee.sg/Men's-wear-cat.11012963");

    }

    private class Callback10 extends WebViewClient {
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }
}