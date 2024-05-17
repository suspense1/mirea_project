package ru.mirea.ishutin.mireaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ru.mirea.ishutin.mireaproject.databinding.ActivityAuthBinding;

public class AuthActivity extends AppCompatActivity {

    private ActivityAuthBinding binding;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();
        binding.signUpButton.setOnClickListener(view -> SignUp());
        binding.signInButton.setOnClickListener(view -> SignIn());
        firebaseAuth.signOut();
//        if (firebaseAuth.getCurrentUser() != null){
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
    }

    private void SignIn() {
        Intent intent = new Intent(this, MainActivity.class);
        String email = binding.emailET.getText().toString();
        String password = binding.passwordET.getText().toString();
        if (!email.isEmpty() && !password.isEmpty()){
            firebaseAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "Вход успешный", Toast.LENGTH_SHORT);
                                finish();
                            }else {
                                Toast.makeText(getApplicationContext(), "Ошибка входа", Toast.LENGTH_SHORT);
                            }
                        }
                    });
        }
    }

    private void SignUp() {
        String email = binding.emailET.getText().toString();
        String password = binding.passwordET.getText().toString();
        Intent intent = new Intent(this, MainActivity.class);
        if (!email.isEmpty() && !password.isEmpty()){
            firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "Регистрация успешна", Toast.LENGTH_SHORT);
                                finish();
                            }else {
                                Toast.makeText(getApplicationContext(), "Ошибка регистрации", Toast.LENGTH_SHORT);
                            }
                        }
                    });

        }
    }
}