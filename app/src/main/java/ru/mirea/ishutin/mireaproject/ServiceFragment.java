package ru.mirea.ishutin.mireaproject;

import static android.Manifest.permission.FOREGROUND_SERVICE;
import static android.Manifest.permission.POST_NOTIFICATIONS;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;

import ru.mirea.ishutin.mireaproject.databinding.FragmentServiceBinding;


public class ServiceFragment extends Fragment {

    private FragmentServiceBinding binding;
    private int PermissionCode = 200;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentServiceBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("MSG", "Created");

        binding.webView.setWebViewClient(new WebViewClient());
        binding.webView.loadData("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/MHxJXBLJW98?si=U-LtQ8uqOsIclkiH\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>", "text/html", "utf-8");
        binding.webView.getSettings();
        binding.webView.getSettings().setJavaScriptEnabled(true);

        Intent service = new Intent(getContext(), UserService.class);
        ContextCompat.startForegroundService(getContext(), service);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}