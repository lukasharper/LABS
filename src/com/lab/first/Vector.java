package com.lab.first;

public class Vector {
    private int[] data;
    private int size;

    Vector(int size) {
        this.size = size;
        data = new int[size];
    }

    public int getSize() {
        return size;
    }

    public void setData(int i, int data) {
        this.data[i] = data;
    }

    public int getData(int i) {
        return data[i];
    }
}
