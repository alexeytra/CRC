package com.example.aleks.crc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class EndActivity extends AppCompatActivity {

    Button sent;
    TextView result, textSurname, textGroup;
    View rootView;
    Bitmap bit;
    Uri uri;
    File file;
    String score, count, surname, group;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);


        sent = findViewById(R.id.btnSend);
        result = findViewById(R.id.result);
        textGroup = findViewById(R.id.setGroup);
        textSurname = findViewById(R.id.setSurname);


        Intent intent = getIntent();
        score = intent.getStringExtra("SCORE");
        count = intent.getStringExtra("COUTOFQUESTIONS");
        surname = intent.getStringExtra("SURNAME");
        group = intent.getStringExtra("GROUP");

        textSurname.setText(surname);
        textGroup.setText("Группа: " + group);
        result.setText(score + " из " + count);

        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAttach();
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "altaevaa@mail.ru" });
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Тест CRC");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Выполнил " + surname + ". Группа " + group + " \n Результат теста " + score + " из " + count);
                emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
                emailIntent.setType("image/jpeg");
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            }
        });
    }

    public void getAttach(){
        rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        bit = takeScreenshot();
        saveBitmap(bit);
        uri = Uri.fromFile(file);
    }

    public Bitmap takeScreenshot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap) {
        //file = new File(Environment.getExternalStorageDirectory() + "/CRC/screenshot.png");
        file = new File(getExternalFilesDir(null) + "screenshot.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setMessage("Выйти? (все данные будут удалены)")
                    .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                            return;
                        }
                    })
                    .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            return;
                        }
                    })
                    .show();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
