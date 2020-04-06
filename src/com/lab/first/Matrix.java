package com.lab.first;



public class Matrix {

    private int[][] data;
    private int size;

    Matrix(int size) {
        this.size = size;
        data = new int[size][size];
    }

    public int getSize() {
        return size;
    }

    public void setData(int i, int j, int data) {
        this.data[i][j] = data;
    }

    public int getData(int i, int j) {
        return data[i][j];
    }

    public void showMatrix() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                System.out.print(data[i][j] + "\t");
            System.out.println();
        }
    }

}
