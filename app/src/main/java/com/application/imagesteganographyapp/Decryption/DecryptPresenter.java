package com.application.imagesteganographyapp.Decryption;


interface DecryptPresenter {

  void setStegiImgPath(String path);

  void selectImage(String path);


  void decryptMessage();
}
