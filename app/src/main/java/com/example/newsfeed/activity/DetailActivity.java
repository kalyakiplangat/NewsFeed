package com.example.newsfeed.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.newsfeed.R;
import com.example.newsfeed.databinding.ActivityDetailBinding;

import static android.widget.Toast.LENGTH_LONG;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;
    private WebView webView;
    private String mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        int duration = LENGTH_LONG;

        webView = binding.webview;

        Intent intent = getIntent();
        mWebView = intent.getStringExtra("webview");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(mWebView);

    }
}

















