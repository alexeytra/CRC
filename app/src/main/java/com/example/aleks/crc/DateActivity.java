package com.example.aleks.crc;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DateActivity extends AppCompatActivity {

    android.support.v7.app.ActionBar actionBar;
    EditText surname, group;
    Button finishTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        surname = findViewById(R.id.enterSurname);
        group = findViewById(R.id.enterGroup);
        finishTest = findViewById(R.id.finishTest);


        finishTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intGet = getIntent();

                Intent intent = new Intent(DateActivity.this, EndActivity.class);
                intent.putExtra("SCORE", intGet.getStringExtra("SCORE"));
                intent.putExtra("COUTOFQUESTIONS", intGet.getStringExtra("COUTOFQUESTIONS"));
                intent.putExtra("SURNAME", surname.getText().toString());
                intent.putExtra("GROUP", group.getText().toString());
                startActivity(intent);
                finish();
            }
        });
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
