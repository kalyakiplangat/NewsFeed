package com.example.newsfeed.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.newsfeed.R;
import com.example.newsfeed.model.Articles;

public class DetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView titleText;
    private TextView authorText;
    private TextView dateText;
    private WebView webView;

    private String mImage;
    private String mTitle;
    private String mAuthor;
    private String mDate;

    private String mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = (ImageView) findViewById(R.id.news_image);
        titleText = (TextView) findViewById(R.id.title_text);
        authorText = (TextView) findViewById(R.id.author_text);
        dateText = (TextView) findViewById(R.id.date_text);
        webView = (WebView)findViewById(R.id.webview);

        Intent intent = getIntent();
        mImage = intent.getStringExtra("image");
        mTitle = intent.getStringExtra("title");
        mWebView = intent.getStringExtra("url");
        mAuthor = intent.getStringExtra("author");
        mDate = intent.getStringExtra("date");


        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);


        Glide.with(getApplicationContext())
                .load(mImage)
                .apply(options)
                .into(imageView);

        titleText.setText(mTitle);
        authorText.setText(mAuthor);
        dateText.setText(mDate);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(mWebView);

    }
}


















