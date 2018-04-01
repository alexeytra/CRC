package com.example.aleks.crc;

import java.math.BigInteger;

public class CRC {

    public String getCRC(String data_bits, String divisor_bits){
        int[] data = new int[data_bits.length()];
        int[] divisor = new int[divisor_bits.length()];

        for(int i = 0; i < data_bits.length(); i++)
            data[i] = Character.getNumericValue(data_bits.charAt(i));

        for(int i = 0; i < divisor_bits.length(); i++)
            divisor[i] = Character.getNumericValue(divisor_bits.charAt(i));

        int tot_length = data_bits.length() + divisor_bits.length() - 1;

        int[] div = new int[tot_length];
        int[] rem = new int[tot_length];
        int[] crc = new int[tot_length];


        //Генерация crc
        for(int i = 0; i < data.length; i++)
            div[i]= data[i];


        for(int j = 0; j < div.length; j++){
            rem[j] = div[j];
        }

        rem = divide(div, divisor, rem);

        StringBuilder c = new StringBuilder();

        for(int i = 0; i < rem.length; i++)
            c.append(rem[i]);

        BigInteger a = new BigInteger(c.toString(), 2);
        String CRC = a.toString(16);
        return CRC.toUpperCase();


        /*
        for(int i = 0; i < div.length; i++)
        {
            crc[i]=(div[i] ^ rem[i]);
        }

        StringBuilder CRC = new StringBuilder();

        for(int i = 0; i < crc.length; i++)
            CRC.append(crc[i]);

        return CRC.toString();*/
    }

    //Деление
    private static int[] divide(int div[], int divisor[], int rem[])
    {
        int cur = 0;
        while(true)
        {
            for(int i=0; i < divisor.length; i++)
                rem[cur+i] = (rem[cur+i] ^ divisor[i]);
            while(rem[cur] == 0 && cur != rem.length - 1)
                cur++;
            if((rem.length-cur) < divisor.length)
                break;
        }
        return rem;
    }
}
