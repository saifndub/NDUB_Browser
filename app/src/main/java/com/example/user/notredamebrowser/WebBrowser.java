package com.example.user.notredamebrowser;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebBrowser extends AppCompatActivity {

    WebView wv ;
    EditText edtUrl;
    Button btnGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_browser);

        wv = findViewById(R.id.web_browser);
        edtUrl = findViewById(R.id.edt_url);
        btnGo = findViewById(R.id.btn_go);

        WebViewClient wvc = new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                edtUrl.setText(wv.getUrl());
            }
        };
        wv.setWebViewClient(wvc);
        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);
        wv.loadUrl("https://google.com");

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = edtUrl.getText().toString();

                String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
                Pattern pattern = Pattern.compile(regex);
                Matcher m = pattern.matcher(url);
                if (m.matches()){

                    wv.loadUrl(url);
                }else{

                    wv.loadUrl("https://www.google.com/search?q="+url);
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        if(wv.canGoBack()){
            wv.goBack();
        }else{
            super.onBackPressed();
        }
    }
}
