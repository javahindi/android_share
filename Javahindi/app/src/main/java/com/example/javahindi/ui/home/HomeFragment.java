package com.example.javahindi.ui.home;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.javahindi.databinding.FragmentHomeBinding;

import java.util.BitSet;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final WebView homeWebView = binding.idWebViewHome;
        final ProgressBar loadingPB = binding.idPBLoading;
        homeWebView.loadUrl("https://www.javahindi.com");
        homeWebView.getSettings().setJavaScriptEnabled(true);
        homeWebView.setWebViewClient(new WebViewClient());
        homeWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                super.onPageStarted(view,url,favicon);
                loadingPB.setVisibility(view.VISIBLE);
            }

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
                loadingPB.setVisibility(view.GONE);
            }
        });
        homeWebView.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event){
                if(event.getAction()==KeyEvent.ACTION_DOWN){
                    switch (keyCode){
                        case KeyEvent.KEYCODE_BACK:
                            if(homeWebView.canGoBack()){
                                homeWebView.goBack();
                            }
                    }
                }
                return  false;
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}