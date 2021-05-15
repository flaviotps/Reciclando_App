package com.flaviotps.reciclando.Activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.flaviotps.reciclando.Data.MaterialModel;
import com.flaviotps.reciclando.R;
import com.flaviotps.reciclando.Utils.Constants;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class ActivityMaterialDetails extends AppCompatActivity implements View.OnClickListener {


    private WebView mWebView;
    private MaterialModel materialModel;
    private ImageView materialImage;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;


    private void LoadWebView() {

        mWebView = findViewById(R.id.webview);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.getSettings().setJavaScriptEnabled(true);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setBuiltInZoomControls(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setDomStorageEnabled(true);
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.setScrollbarFadingEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        mWebView.setWebChromeClient(new WebChromeClient());

        String site =
                "<html lang=\"pt-br\">" +
                        "<head>" +
                        "<link href=\"css/bootstrap.css\" rel=\"stylesheet\">" +
                        "<link href=\"css/summernote.css\" rel=\"stylesheet\">" +
                        "</head>" +
                        "<body>" + materialModel.Content + "</body>" +
                        "</html>";
        mWebView.loadDataWithBaseURL("file:///android_asset/Internal/", site, "text/html", "UTF-8", null);

    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

          setContentView(R.layout.fragment_material);


        materialModel = (MaterialModel) getIntent().getSerializableExtra(Constants.BUNDLE_ARG_MATERIAL);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        materialImage = findViewById(R.id.materialImage);

        Glide.with(this).load(materialModel.Image).into(materialImage);
        collapsingToolbarLayout.setTitle(materialModel.Name);

        LoadWebView();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
