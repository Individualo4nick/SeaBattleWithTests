package org.example;

import javax.swing.*;

public class Game {
    public int polePlayer[][];
    public int poleComp[][];
    public int C1, C2, C3, C4;
    public int P1, P2, P3, P4;
    public final int pause=600;
    public boolean myHod;
    public boolean compHod;
    public static int endGame=3;
    Thread thread=new Thread();
    public Game() {
        polePlayer = new int[10][10];
        poleComp = new int[10][10];
    }
    public void start() {
        //если вдруг компьютер еще не закончил ход, то ждем
        //обнуляем массив
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                polePlayer[i][j] = 0;
                poleComp[i][j] = 0;
            }
        }
        myHod =true; //мой ход
        compHod=false;
        endGame=0;// игра идет
        kolvoUbitPk(poleComp);
        kolvoUbitPlayer(polePlayer);
        if (!Screen.rasstanovka) {
            setPalubaPlay();
        }
        setPalubaComp();
    }
    public void hodPlayer(int pole[][], int i, int j) {
        pole[i][j] += 7;
        proverkaNaPopadanie(pole, i, j);
        endGame();
        thread =new Thread(new Runnable() {
            @Override
            public void run() {
                //если промах
                if (poleComp[i][j] < 8) {
                    myHod = false;
                    compHod = true; //передаем ход компьютеру
                    // Ходит компьютер - пока попадает в цель
                    while (compHod) {
                        try {
                            Thread.sleep(pause);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        compHod = compHodit(polePlayer);
                        //воспроизводим звук при попадании компьютера
                    }
                    myHod = true;//передаем ход игроку после промаха компьютера
                }
            }
        });
        thread.start();
    }
    public void endGame(){
        if (endGame==0){
            int sumEnd=330; //когда все корабли убиты
            int sumPlay=0; // Сумма убитых палуб игрока
            int sumComp=0; // Сумма убитых палуб компьютера
            kolvoUbitPk(poleComp);
            kolvoUbitPlayer(polePlayer);
            if (endGame==0) {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        // Суммируем подбитые палубы
                        if (polePlayer[i][j] >= 15) sumPlay += polePlayer[i][j];
                        if (poleComp[i][j] >= 15) sumComp += poleComp[i][j];
                    }
                }
                if (sumPlay == sumEnd) {
                    endGame = 2;
                    //выводим диалоговое окно игроку
                    JOptionPane.showMessageDialog(null,
                            "Вы проиграли! Попробуйте еще раз",
                            "Вы проиграли", JOptionPane.INFORMATION_MESSAGE);

                } else if (sumComp == sumEnd) {
                    endGame = 1;
                    //выводим диалоговое окно игроку
                    JOptionPane.showMessageDialog(null,
                            "Поздравляю! Вы выиграли!",
                            "Вы выиграли", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
    private void proverkaNaPopadanie(int mas[][], int i, int j){
        if (mas[i][j]==8) { //Если однопалубный
            mas[i][j] += 7; //прибавляем к убитому +7
            surroundADeadShip(mas,i,j);//Уменьшаем окружение убитого на 1
        }
        else if (mas[i][j]==9){
            proverkaNaUbiistvo(mas,i,j,2);
        }
        else if (mas[i][j]==10){
            proverkaNaUbiistvo(mas,i,j,3);
        }
        else if (mas[i][j]==11){
            proverkaNaUbiistvo(mas,i,j,4);
        }
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
    public void kolvoUbitPk(int[][]mas){
        P4=0;P3=0;P2=0;P1=0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (mas[i][j]==18) P4++;
                if (mas[i][j]==17) P3++;
                if (mas[i][j]==16) P2++;
                if (mas[i][j]==15) P1++;
            }
        }
        P4/=4;P3/=3;P2/=2;
    }
}
