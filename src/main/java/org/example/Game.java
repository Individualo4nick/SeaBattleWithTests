package org.example;

public class Game {
    public int polePlayer[][];
    public int poleComp[][];
    public int C1, C2, C3, C4;
    public int P1, P2, P3, P4;
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
    public void kolvoUbitPlayer(int[][]mas) {
        C4 = 0;C3 = 0;C2 = 0;C1 = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (mas[i][j] == 18) C4 = (C4 + 1);
                if (mas[i][j] == 17) C3 = (C3 + 1);
                if (mas[i][j] == 16) C2 = (C2 + 1);
                if (mas[i][j] == 15) C1 = (C1 + 1);
            }
        }
        C4/=4;C3/=3;C2/=2;
    }

}
