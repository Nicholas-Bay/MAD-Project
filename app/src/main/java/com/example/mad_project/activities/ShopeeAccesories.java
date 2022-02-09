package com.example.mad_project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mad_project.R;

public class ShopeeAccesories extends AppCompatActivity {

    WebView web9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopee_accesories);
        web9 = findViewById(R.id.webView9);
        WebSettings webSettings9 = web9.getSettings();
        webSettings9.setJavaScriptEnabled(true);
        web9.setWebViewClient(new Callback9());
        web9.loadUrl("https://shopee.sg/Jewellery-Accessories-cat.11013077");
    }

    private class Callback9 extends WebViewClient {
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }
}