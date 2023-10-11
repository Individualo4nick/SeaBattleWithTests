package org.example;

public class Game {
    public int polePlayer[][];
    public int poleComp[][];
    public int C1, C2, C3, C4;
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

    public void proverkaNaUbiistvo(int pole[][], int i, int j, int kolpalub) {
        //Количество раненых палуб
        int ranen=0;
        //Выполняем подсчет раненых палуб
        for (int a=i-(kolpalub-1);a<=i+(kolpalub-1  );a++) {
            for (int b = j - (kolpalub - 1); b <= j + (kolpalub - 1); b++) {
                // Если это палуба раненого корабля
                if (indOutOfBounds(a, b) && (pole[a][b] == kolpalub + 7))
                    ranen++;
            }
        }
        // Если количество раненых палуб совпадает с количеством палуб
        // корабля, то он убит - прибавляем число 7
        if (ranen==kolpalub) {
            for (int x=i-(kolpalub-1);x<=i+(kolpalub-1);x++) {
                for (int y=j-(kolpalub-1);y<=j+(kolpalub-1);y++) {
                    // Если это палуба раненого корабля
                    if (indOutOfBounds(x, y)&&(pole[x][y]==kolpalub+7)) {
                        // помечаем палубой убитого корабля
                        pole[x][y]+=7;
                        // уменьшаем на 1 окружение убитого корабля
                        surroundADeadShip(pole, x, y);
                    }
                }
            }
        }


    }
    public void surroundADeadShip(int[][] mas, int i, int j) {
        setOkrKilled(mas, i - 1, j - 1); // сверху слева
        setOkrKilled(mas, i - 1, j); // сверху
        setOkrKilled(mas, i - 1, j + 1); // сверху справа
        setOkrKilled(mas, i, j + 1); // справа
        setOkrKilled(mas, i + 1, j + 1); // снизу справа
        setOkrKilled(mas, i + 1, j); // снизу
        setOkrKilled(mas, i + 1, j - 1); // снизу слева
        setOkrKilled(mas, i, j - 1); // слева
    }
}
