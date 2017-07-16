package com.example.a123.mobileproject;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by 123 on Jul/05/17.
 */

public class game {
    private Level lv;
    private int colum =0;
    private int row =0;
    private int [][]g = new int[row][colum];

    public game(Level lv){
        this.lv=lv;
        this.colum= lv.getV_cards_count();
        this.row =lv.getH_cards_count();
        this.g = new int[row][colum];
        position(row, colum);
    }

    public void position(int r, int c){
        List<Integer> randoms = new ArrayList<Integer>();
        int num= (r*c)/2;
        for(int i = 0; i < num; i++ ) {
                randoms.add(i);
        }
        List<Integer> clonedList = new ArrayList<Integer>();
        clonedList.addAll(randoms);
        clonedList.addAll(randoms);
        Collections.shuffle(clonedList);
        int x=0;
        for(int i = 0; i < row; i++) {
            for(int j=0; j<colum; j++) {
                g[i][j] = clonedList.get(x++);
                Log.d("c", String.valueOf(g[i][j]));
            }
        }
    }

    public boolean compareCard(int i, int j){
        if(i==j){
            return true;
        }
        else{
            return false;
        }

    }


    public int getData(int r, int c){
        return g[r][c];
    }

    public int getColum() {
        return colum;
    }

    public int getRow() {
        return row;
    }

    public int[][] getG() {
        return g;
    }



}
