package ru.mirea.ishutin.mireaproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class ProfileFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private EditText sName;
    private EditText name;
    private EditText age;

    private SharedPreferences sharedPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(view1 -> saveInfo());

        name = view.findViewById(R.id.nameET);
        sName = view.findViewById(R.id.sNameET);
        age = view.findViewById(R.id.ageET);

        sharedPref	=
                getContext().getSharedPreferences("mirea_project_settings",	Context.MODE_PRIVATE);
        name.setText(sharedPref.getString("KEY_NAME", "Ваше имя"));
        sName.setText(sharedPref.getString("KEY_SNAME", "Ваша фамилия"));
        age.setText(sharedPref.getString("KEY_AGE", "Ваш возраст"));

    }

    private void saveInfo() {

        SharedPreferences.Editor editor	= sharedPref.edit();

        editor.putString("KEY_NAME", name.getText().toString());
        editor.putString("KEY_SNAME", sName.getText().toString());
        editor.putString("KEY_AGE", age.getText().toString());

        editor.apply();
    }
}