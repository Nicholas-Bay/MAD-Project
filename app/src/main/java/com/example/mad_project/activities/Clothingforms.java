package com.example.mad_project.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mad_project.R;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Clothingforms extends AppCompatActivity {

    WebView web2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothingforms);
        web2 = findViewById(R.id.webView2);
        WebSettings webSettings2 = web2.getSettings();
        webSettings2.setJavaScriptEnabled(true);
        web2.setWebViewClient(new CallBack2());
        web2.loadUrl("https://www.uniqlo.com/sg/en/men");
    }

    private class CallBack2 extends WebViewClient {
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) { return false; }
    }
}