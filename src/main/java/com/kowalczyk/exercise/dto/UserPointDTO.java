package com.kowalczyk.exercise.dto;

/**
 * Created by JK on 2016-10-01.
 */
public class UserPointDTO {

    private int x;
    private int y;

    public UserPointDTO(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
