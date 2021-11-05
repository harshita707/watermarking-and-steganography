package com.application.imagesteganographyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.application.imagesteganographyapp.Decryption.DecryptActivity;
import com.application.imagesteganographyapp.Encryption.EncryptActivity;
import com.application.imagesteganographyapp.Encryption.EncryptImageActivity;

public class MainActivity extends AppCompatActivity {
    View encrypt, decrypt, encryptImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        encrypt = findViewById(R.id.encode_text);
        decrypt = findViewById(R.id.decode);
        encryptImg = findViewById(R.id.encode_image);

        encrypt.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EncryptActivity.class);
            startActivity(intent);
        });

        encryptImg.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EncryptImageActivity.class);
            startActivity(intent);
        });

        decrypt.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DecryptActivity.class);
            startActivity(intent);
        });
    }

}
