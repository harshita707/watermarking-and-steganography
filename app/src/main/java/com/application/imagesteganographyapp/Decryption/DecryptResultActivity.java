package com.application.imagesteganographyapp.Decryption;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.application.imagesteganographyapp.Algorithms.AESencryption;
import com.application.imagesteganographyapp.R;
import com.application.imagesteganographyapp.Utils.Constants;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DecryptResultActivity extends AppCompatActivity {

  @BindView(R.id.tvSecretMessage)
  TextView tvSecretMessage;

  @BindView(R.id.ivSecretImage)
  ImageView ivSecretImage;


  private String secretImagePath;
  private String secretMessage;
  private String password;
  private String decryptText = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_decrypt_result);

    ActionBar actionBar = getSupportActionBar();
    actionBar.setTitle("Decrypted Result Image");

    ButterKnife.bind(this);

    //initToolbar();

    Intent intent = getIntent();

    if (intent != null) {
      password = intent.getStringExtra("secretKey");
      Bundle bundle = intent.getExtras();
      secretMessage = bundle.getString(Constants.EXTRA_SECRET_TEXT_RESULT);
      secretImagePath = bundle.getString(Constants.EXTRA_SECRET_IMAGE_RESULT);
    }

    if (secretMessage != null) {
      try {
         decryptText = AESencryption.decryptText(secretMessage, password);
      } catch (Exception e) {
        Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
        e.printStackTrace();
        decryptText = "[Wrong password entered, please try again]";
      }
      if(decryptText !=null ) {
        Toast.makeText(this, R.string.decrypt_success, Toast.LENGTH_SHORT).show();
      }
      tvSecretMessage.setText(decryptText);
    } else if (secretImagePath != null) {
      ivSecretImage.setVisibility(View.VISIBLE);
      setSecretImage(secretImagePath);
    }
  }

/*  public void initToolbar() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setTitle("Decryption");
    }
  }*/

  public void setSecretImage(String path) {
    Picasso.with(this)
      .load(new File(path))
      .fit()
      .placeholder(R.drawable.ic_file_upload)
      .into(ivSecretImage);
  }
}
