package ru.mirea.ishutin.mireaproject.ui.webview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.ishutin.mireaproject.databinding.FragmentWebViewBinding;

public class WebViewFragment extends Fragment {

    private FragmentWebViewBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WebViewViewModel webViewViewModel =
                new ViewModelProvider(this).get(WebViewViewModel.class);

        binding = FragmentWebViewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.webView.setWebViewClient(new WebViewClient());
        binding.webView.loadUrl("https://www.google.ru/");
        binding.webView.getSettings().setJavaScriptEnabled(true);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}