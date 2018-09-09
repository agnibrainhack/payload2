package com.example.root.showuremo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.showuremo.pojo.ImageUpload;
import com.example.root.showuremo.pojo.SendData;
import com.example.root.showuremo.rest.ApiClient;
import com.example.root.showuremo.rest.ApiInterface;

import java.io.ByteArrayOutputStream;
import java.util.Objects;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hp on 28-01-2018.
 */

public class AfterSplash extends AppCompatActivity{
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    Button bt1, bt2;
    ImageView mImageView;
    Bitmap imageBitmap;
    TextView txt;
    private ProgressDialog progressDialog;
    String[] good = {"Umm... It seems you are very happy","Are you trying to say that you're not happy with your success",
    "Hmm... did you score full marks today?","Delighted to see you delighted","Yeah, you are the man of the moment",
    "What fancies you to think I don't know that you are happy"};
     //0-5
    String[] bad = {"Umm... Dont feel bad for today","Yeah I know it was not a nice day",
                    "Sometimes just ignore your hard luck","Don't get frustrated just share yourself with someone",
                    "Hmm... Don't allow sad feelings to overtake you","It's a matter of time before you kickbutt ,just wait"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takepic);
        mImageView = findViewById(R.id.imageView);
        txt = findViewById(R.id.textrocks);
        bt1 = findViewById(R.id.button);
        bt2 = findViewById(R.id.button2);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkforresult();
            }
        });
    }


    private void dispatchTakePictureIntent() {
     Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
       }
     }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
        Bundle extras = data.getExtras();
        imageBitmap = (Bitmap) extras.get("data");
        mImageView.setImageBitmap(imageBitmap);
     }
    }

    private void checkforresult() {
        if(imageBitmap == null){
            Toast.makeText(AfterSplash.this,"Please Click An Image",Toast.LENGTH_LONG).show();
        }
        else{
            progressDialog=new ProgressDialog(this);
            progressDialog.setMessage("Please Wait...."+'\n'+"I am figuring out your feelings");
            progressDialog.setCancelable(false);
            progressDialog.show();
            ByteArrayOutputStream stream =  new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.WEBP, 100, stream);
            byte[] bitmapData = stream.toByteArray();
            String image = Base64.encodeToString(bitmapData, Base64.DEFAULT);
            SendData snd = new SendData();
            snd.setImage(image);
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<ImageUpload> call = apiService.savePost(snd);
            call.enqueue(new Callback<ImageUpload>() {
                @Override
                public void onResponse(Call<ImageUpload> call, Response<ImageUpload> response) {
                    progressDialog.dismiss();
                    try {
                        if (response.body().getImageResult() != null) {

                            if (Objects.equals(response.body().getImageResult(), "SMILING")) {
                                Random rand = new Random();
                                int r = rand.nextInt(6);
                                txt.setText(good[r]);
                            } else if (Objects.equals(response.body().getImageResult(), "NOT SMILING")) {
                                Random rand = new Random();
                                int r = rand.nextInt(6);
                                txt.setText(bad[r]);
                            }
                            else
                                txt.setText("Sorry I couldn't Detect the face");
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        txt.setText("Sorry I couldn't Detect the face");
                    }


                }

                @Override
                public void onFailure(Call<ImageUpload> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(AfterSplash.this,"Sorry Network Problem Hommies!", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}
