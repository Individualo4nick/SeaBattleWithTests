package org.example;

public class Game {
    public int polePlayer[][];
    public int poleComp[][];
    public Game() {
        polePlayer = new int[10][10];
        poleComp = new int[10][10];
    }
    public void setOkrKilled(int mas[][], int i, int j){
        if (indOutOfBounds(i, j)){
            if (mas[i][j]==-1 || mas[i][j]==6){
                mas[i][j]--;
            }
        }
    }
    private boolean indOutOfBounds(int i, int j) {
        if (((i >= 0) && (i <= 9)) && ((j >= 0) && (j <= 9))) {
            return true;
        } else return false;
    }
}
