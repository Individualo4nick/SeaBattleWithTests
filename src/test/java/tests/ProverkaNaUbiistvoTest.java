package tests;

import org.example.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProverkaNaUbiistvoTest {

    @Test
    public void test4PalubUbil() {
        //Arrange
        Game game =new Game();
        game.polePlayer[0][1] = 11;
        game.polePlayer[0][2] = 11;
        game.polePlayer[0][3] = 11;
        game.polePlayer[0][4] = 11;
        //Action
        game.proverkaNaUbiistvo(game.polePlayer, 0,2, 4);
        //Assert
        Assertions.assertEquals(game.polePlayer[0][1], 18);
        Assertions.assertEquals(game.polePlayer[0][2], 18);
        Assertions.assertEquals(game.polePlayer[0][3], 18);
        Assertions.assertEquals(game.polePlayer[0][4], 18);
    }
    @Test
    public void test3PalubUbil() {
        //Arrange
        Game game =new Game();

        game.polePlayer[0][1] = 10;
        game.polePlayer[0][2] = 10;
        game.polePlayer[0][3] = 10;
        //Action
        game.proverkaNaUbiistvo(game.polePlayer, 0,2, 3);
        //Assert
        Assertions.assertEquals(game.polePlayer[0][1], 17);
        Assertions.assertEquals(game.polePlayer[0][2], 17);
        Assertions.assertEquals(game.polePlayer[0][3], 17);
    }
    @Test
    public void test2PalubUbil() {
        //Arrange
        Game game =new Game();
        game.polePlayer[0][1] = 9;
        game.polePlayer[0][2] = 9;

        //Action
        game.proverkaNaUbiistvo(game.polePlayer, 0,2, 2);
        //Assert
        Assertions.assertEquals(game.polePlayer[0][1], 16);
        Assertions.assertEquals(game.polePlayer[0][2], 16);
    }
    @Test
    public void test2PalubNeUbil() {
        //Arrange
        Game game =new Game();
        game.polePlayer[0][1] = 9;
        game.polePlayer[0][2] = 1;

        //Action
        game.proverkaNaUbiistvo(game.polePlayer, 0,1, 2);
        //Assert
        Assertions.assertEquals(game.polePlayer[0][1], 9);
        Assertions.assertEquals(game.polePlayer[0][2], 1);
    }

}
