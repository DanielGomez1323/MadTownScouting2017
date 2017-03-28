package com.example.pollo.madtownscouting2017;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UploadPicture extends AppCompatActivity {

    Button picUploadButton;
    Button homeButton;
    ImageView picPreview;
    SQLiteDatabase myDB = null;
    Cursor c;
    String id;
    String selectedFilePath;
    Bitmap bp;
    ExifInterface ei;
    ProgressDialog dialog;
    TextView message;
    String teamNumber;
    private String SERVER_URL = "http://www.gorohi.com/1323/picupload.php";
    String m = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_picture);
        picUploadButton = (Button) findViewById(R.id.picUploadButton);
        homeButton = (Button) findViewById(R.id.homeButton);
        picPreview = (ImageView) findViewById(R.id.picUploadPreview);
        message = (TextView) findViewById(R.id.responseText);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StartMenu.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        String[] columns = new String[]{
                "_id",
                "teamNumber",
                "pic1"
        };
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        myDB = openOrCreateDatabase("FRC", MODE_PRIVATE, null);
        c = myDB.query("TeamPictures", columns, "_id = ?", new String[]{id}, null, null, null);
        c.moveToFirst();
        try {
            if(c.getCount() > 0) {
                selectedFilePath = c.getString(c.getColumnIndex("pic1"));
                teamNumber = c.getString(c.getColumnIndexOrThrow("teamNumber"));
                bp = BitmapFactory.decodeFile(selectedFilePath);
                ei = new ExifInterface(selectedFilePath);
            }
        } catch (Exception e) {
            Log.d("ERROR", e.toString());
        }
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,6);
        switch(orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                bp = rotateImage(bp, 90);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                bp = rotateImage(bp, 180);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                bp = rotateImage(bp, 270);
                break;
            case ExifInterface.ORIENTATION_NORMAL:
            default:
                break;
        }

        picPreview.setImageBitmap(bp);

        picUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on upload button Click
                if (selectedFilePath != null) {
                    dialog = ProgressDialog.show(UploadPicture.this, "", "Uploading File...", true);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //creating new thread to handle Http Operations
                            uploadFile(selectedFilePath);
                        }
                    }).start();
                } else {
                    Toast.makeText(UploadPicture.this, "Please choose a File First", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix,
                true);
    }
    public int uploadFile(final String selectedFilePath){

        int serverResponseCode = 0;

        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";


        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File selectedFile = new File(selectedFilePath);


        String[] parts = selectedFilePath.split("/");
        final String fileName = parts[parts.length-1];

        if(!selectedFile.isFile()){
            dialog.dismiss();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    message.setText("Source file doesn't exist: " + selectedFilePath);
                }
            });
            return 0;
        }else{
            try{
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                URL url = new URL(SERVER_URL);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setUseCaches(false);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("ENCTYPE", "multipart/form-data");
                connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                connection.setRequestProperty("fileToUpload", teamNumber + "." + fileName);

                dataOutputStream = new DataOutputStream(connection.getOutputStream());

                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"fileToUpload\";filename=\"" + teamNumber + "." + fileName + "\"" + lineEnd);

                dataOutputStream.writeBytes(lineEnd);

                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];

                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                while(bytesRead > 0){
                    dataOutputStream.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesRead, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }

                dataOutputStream.writeBytes(lineEnd);
                dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                serverResponseCode = connection.getResponseCode();
                final String serverResponseMessage = connection.getResponseMessage();

                if(serverResponseCode == 200){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            message.setText(serverResponseMessage);
                        }
                    });
                }

                fileInputStream.close();
                dataOutputStream.flush();
                dataOutputStream.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        message.setText("File not found!");
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Toast.makeText(UploadPicture.this, "URL error!", Toast.LENGTH_SHORT).show();
            } catch (java.io.IOException e){
                e.printStackTrace();
                Toast.makeText(UploadPicture.this, "Cannot Read/Write File!", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
            return serverResponseCode;
        }
    }

}
