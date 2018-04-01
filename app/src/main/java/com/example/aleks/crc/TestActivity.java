package com.example.aleks.crc;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity {

    Button next;
    TextView message, div, numQuestion;
    EditText answer;
    String question = "Вопрос ";
    int count = 0;
    int score = 0;
    questions q;
    android.support.v7.app.ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        next = findViewById(R.id.next);
        message = findViewById(R.id.message);
        div = findViewById(R.id.div);
        answer = findViewById(R.id.answer);
        numQuestion = findViewById(R.id.numQuestion);

        q = new questions();

        getIntro();
        getQuestion();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count == (q.getCountQuestions() - 1)){
                    finish();
                    Intent intent = new Intent(TestActivity.this, DateActivity.class);
                    intent.putExtra("SCORE", String.valueOf(score));
                    intent.putExtra("COUTOFQUESTIONS", String.valueOf(q.getCountQuestions()));
                    startActivity(intent);
                    return;
                }
                if(q.getAnswers(count).equals(answer.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Верно", Toast.LENGTH_LONG).show();
                    score++;
                }else{
                    Toast.makeText(getApplicationContext(), "Неверно", Toast.LENGTH_LONG).show();
                }
                count++;
                answer.getText().clear();
                getQuestion();
            }
        });

    }

    private void getIntro(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TestActivity.this);
        alertDialogBuilder
                .setMessage("Тест состоит из "+ q.getCountQuestions() +" вопросов.\n\nВычислите CRC для сообщения и представьте его в виде шестнадцатеричной цифры. \n\nВ поле ответа нужно ввести шестнадцатиричное число.")
                .setCancelable(false)
                .setPositiveButton("Начать тест", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @SuppressLint("DefaultLocale")
    private void getQuestion(){
        numQuestion.setText(String.format("%s %d", question, count + 1));
        message.setText(q.getMessage(count));
        div.setText(q.getDivider(count));
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
