package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Game;
import org.junit.Assert;

public class KolvoUbitStep {
    private Game game;
    private int C1;
    private int C2;
    private int C3;
    private int C4;
    private void fillTestGameField(int[][] field, int c4, int c3, int c2, int c1) {
        //Подбитые четырехпалубные корабли
        int index = 0;
        for(int i =0 ; i < C4; i++) {
            for(int j = 0; j < 4; j++) {
                field[index][j] = 18;
            }
            index += 2;
        }
        //Трехпарусные корабли
        index = 2;
        for(int i = 0; i < C3; i++ ) {
            for(int j =0 ; j < 3; j++) {
                field[index][j] = 17;
            }
            index += 2;
        }
        //Двухпарусные корабли
        index = 0;
        for(int i =0; i < C2; i++) {
            for(int j = 5; j <= 6; j++) {
                field[index][j] = 16;
            }
            index += 2;
        }

        // Однопарусные корабли
        index = 0;
        for(int i = 0; i < C1; i++) {
            field[7][index] = 15;
            index += 2;
        }
    }
    @Given("Дано игровое поле, с подбитыми кораблями")
    public void setNewGame(){
        game = new Game();
    }

    @Given("Количество подбитых четырехпарусных {int}, трехпарусных {int}, двухпарусных {int}, однопарусных {int}")
    public void setExpectedCount(int arg0, int arg1, int arg2, int arg3) {
        C4 = arg0;
        C3 = arg1;
        C2 = arg2;
        C1 = arg3;
        int[][] field = game.polePlayer;
        fillTestGameField(field, C4, C3, C2, C1);

    }
    @When("Количество указано корректно")
    public void checkTestValues() {
        Assert.assertTrue(C1 <= 4);
        Assert.assertTrue(C2 <= 3);
        Assert.assertTrue(C3 <= 2);
        Assert.assertTrue(C4 <= 1);

    }

    @Then("Получаем корректное значение подбитых кораблей каждого типа")
    public void getResult() {
        game.kolvoUbitPlayer(game.polePlayer);
        Assert.assertEquals(game.C1, C1);
        Assert.assertEquals(game.C2, C2);
        Assert.assertEquals(game.C3, C3);
        Assert.assertEquals(game.C4, C4);
    }


}
