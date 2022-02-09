package com.example.mad_project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mad_project.R;

public class ShopeeElectronics extends AppCompatActivity {
    WebView web11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopee_electronics);
        web11 = findViewById(R.id.webView11);
        WebSettings webSettings11 = web11.getSettings();
        webSettings11.setJavaScriptEnabled(true);
        web11.setWebViewClient(new Callback11());
        web11.loadUrl("https://shopee.sg/Computers-Peripherals-cat.11013247");
    }

    private class Callback11 extends WebViewClient {
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }
}