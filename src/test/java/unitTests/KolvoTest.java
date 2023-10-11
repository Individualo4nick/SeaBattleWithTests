package unitTests;

import org.example.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KolvoTest {
    private Game game;
    private int P1, P2, P3, P4;
    @BeforeEach
    public void setUp(){
        game = new Game();
    }
    private void fillNumber(int P4, int P3, int P2, int P1){
        this.P4 = P4;
        this.P3 = P3;
        this.P2 = P2;
        this.P1 = P1;
    }
    private void fillPole(int p4, int p3, int p2, int p1){
        int index = 0;
        for(int i = 0; i < p4; i++) {
            for(int j = 0; j < 4; j++) {
                game.poleComp[index][j] = 18;
            }
            index += 2;
        }
        //Трехпарусные корабли
        index = 2;
        for(int i = 0; i < p3; i++ ) {
            for(int j =0 ; j < 3; j++) {
                game.poleComp[index][j] = 17;
            }
            index += 2;
        }
        //Двухпарусные корабли
        index = 0;
        for(int i =0; i < p2; i++) {
            for(int j = 5; j <= 6; j++) {
                game.poleComp[index][j] = 16;
            }
            index += 2;
        }

        // Однопарусные корабли
        index = 0;
        for(int i = 0; i < p1; i++) {
            game.poleComp[7][index] = 15;
            index += 2;
        }
    }
    @Test
    public void checkKolvo(){
        fillNumber(1, 2, 3, 4);
        fillPole(P4, P3, P2, P1);
        game.kolvoUbitPk(game.poleComp);
        Assertions.assertEquals(game.P1, P1);
        Assertions.assertEquals(game.P2, P2);
        Assertions.assertEquals(game.P3, P3);
        Assertions.assertEquals(game.P4, P4);
    }
    @Test
    public void checkKolvoWithZeros(){
        fillNumber(0, 0, 0, 0);
        fillPole(P4, P3, P2, P1);
        game.kolvoUbitPk(game.poleComp);
        Assertions.assertEquals(game.P1, P1);
        Assertions.assertEquals(game.P2, P2);
        Assertions.assertEquals(game.P3, P3);
        Assertions.assertEquals(game.P4, P4);
    }
}
