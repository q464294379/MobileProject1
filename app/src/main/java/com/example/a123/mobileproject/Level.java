package com.example.a123.mobileproject;

/**
 * Created by 123 on Jul/05/17.
 */

public class Level {

    private int maxTime=0;
    private int h_cards_count=0;
    private int v_cards_count=0;
    private int lv=0;

    public static final int MAX_H_CARDS_COUNT=6;
    public static final int MAX_V_CARDS_COUNT=10;

    public Level(int lv) {
        this.lv=lv;
        if(lv>1){
            this.h_cards_count=(3+lv-1);
            this.v_cards_count=(4+lv)-1;
        }
        else{
            this.h_cards_count=(3*lv);
            this.v_cards_count=(4*lv);
        }

        this.maxTime=lv*15;
    }

    public int getLv() {
        return lv;
    }

    public int getH_cards_count() {
        return h_cards_count;
    }


    public int getV_cards_count() {
        return v_cards_count;
    }

    public int getMaxTime() {
        return maxTime;
    }



}
