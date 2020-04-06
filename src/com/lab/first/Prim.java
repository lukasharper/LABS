package com.lab.first;

import com.lab.Transformer;

import java.io.FileReader;
import java.io.IOException;

public class Prim {

    public static void main(String[] args) {
        Matrix dop = new Matrix(7);
        Matrix mat = new Matrix(7);
        Vector road = new Vector(7);
        road.setData(0, 1);
        char ii, jj;
        int max, kordi = 0, kordj = 0, weight = 0, dot = 0;


        for (int i = 1; i < mat.getSize(); i++) {
            for (int j = 0; j < mat.getSize(); j++) {
                mat.setData(i, j, 0);
                dop.setData(i, j, 0);
            }
        }
        try (FileReader reader = new FileReader("C:\\Users\\andri\\Desktop\\DMCAD\\src\\com\\lab\\first\\notes3.txt")) {
            int c;
            int num = 0;
            c = reader.read();


            while ((c = reader.read()) != '\n') ;
            while ((c = reader.read()) != -1) {


                if ((char) c == '\n') {

                    mat.setData(kordi, kordj, num);
                    mat.setData(kordj, kordi, num);
                    num = 0;
                    continue;
                }
                if (c >= 65) {

                    kordi = Transformer.upperLetterToNum((char) c);

                    while ((c = reader.read()) < 65) ;
                    kordj = -1;
                    kordj = Transformer.upperLetterToNum((char) c);

                } else if (Character.getNumericValue(c) >= 0 && Character.getNumericValue(c) <= 9) {

                    num *= 10;
                    num += Character.getNumericValue(c);
                }

            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }


        for (int j = 0; j < mat.getSize(); j++)
            dop.setData(0, j, mat.getData(0, j));

        for (int k = 1; k < mat.getSize(); k++) {
            max = 0;
            for (int i = 0; i < mat.getSize(); i++)
                for (int j = 0; j < mat.getSize(); j++)
                    if (max < dop.getData(i, j)) {
                        max = dop.getData(i, j);
                        kordi = i;
                        kordj = j;
                        dot = j;
                    }

            weight += max;

            System.out.println(Transformer.numToUpperLetter(kordi) + "-" + Transformer.numToUpperLetter(kordj) + ": " + max);
            road.setData(dot, 1);
            for (int i = 0; i < mat.getSize(); i++)
                if (road.getData(i) == 1) {
                    mat.setData(i, dot, 0);
                    mat.setData(dot, i, 0);
                }
            for (int i = 0; i < mat.getSize(); i++)
                if (road.getData(i) == 1)
                    for (int j = 0; j < mat.getSize(); j++)
                        dop.setData(i, j, mat.getData(i, j));
        }
        System.out.println("weight: " + weight);
    }

}
