package com.example.aleks.crc;

import java.util.Random;

/**
 * Created by aleks on 11.03.2018.
 */

public class questions {

    private String divider[];
    private String answers[];
    private String message[];
    private static final int COUNT_QUESTIONS = 5;


    public String getMessage(int i) {
        return message[i];
    }

    public String getDivider(int i) {
        return divider[i];
    }

    public String getAnswers(int i) {
        return answers[i];
    }

    public int getCountQuestions(){ return COUNT_QUESTIONS; }


    public questions(){
        Random random = new Random();
        CRC crc = new CRC();

        message = new String[COUNT_QUESTIONS];
        divider = new String[COUNT_QUESTIONS];
        answers = new String[COUNT_QUESTIONS];

        for (int i = 0; i < COUNT_QUESTIONS; i++){
            int data_bits = 1000 + random.nextInt(2000 - 1000);
            int divisor_bits = 20 + random.nextInt(30 - 20);
            message[i] = Binary.binar(data_bits);
            divider[i] = Binary.binar(divisor_bits);
            answers[i] = crc.getCRC(message[i], divider[i]);
        }

    }


}
