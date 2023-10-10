package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.example.Game;
import org.junit.jupiter.api.Assertions;

public class OkrStep {
    private Game game;
    private int x, y;
    private int copyPole[][] = new int[10][10];
    @Given("Поле с кораблями и выстрелами")
    public void givePole() {
        game = new Game();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j%2 == 0)
                    game.polePlayer[i][j] = -1;
                if (j%2 != 0)
                    game.polePlayer[i][j] = 6;
            }
        }
    }
    @Given("Координаты x и y - {int} {int}")
    public void giveCoord(int x, int y){
        this.x = x;
        this.y = y;
    }
    @When("Координаты находятся внутри поля")
    public void checkFirstPredicate() {
        if (((x >= 0) && (x <= 9)) && ((y >= 0) && (y <= 9))) {
            Assertions.assertTrue(true);
        } else Assertions.fail();
    }
    @And("Значение в клетке равно {int} или {int}")
    public void checkSecondPredicateTrue(int firstValue, int secondValue) {
        if(game.polePlayer[x][y] == firstValue || game.polePlayer[x][y] == secondValue)
            Assertions.assertTrue(true);
        else Assertions.fail();
    }
    @Then("Уменьшается значение в клетке на 1. Ожидаем {int} или {int}")
    public void decrementValue(int firstAnswer, int secondAnswer) {
        game.setOkrKilled(game.polePlayer, x, y);
        Assertions.assertTrue(game.polePlayer[x][y] == firstAnswer || game.polePlayer[x][y] == secondAnswer);
    }
    @When("Координаты не находятся внутри поля")
    public void checkFirstPredicateFalse() {
        if (((x >= 0) && (x <= 9)) && ((y >= 0) && (y <= 9))) {
            Assertions.fail();
        } else Assertions.assertTrue(true);
    }
    @Then("Поле не меняется")
    public void noDecrementValue() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                copyPole[i][j] = game.polePlayer[i][j];
            }
        }
        game.setOkrKilled(game.polePlayer, x, y);
        Assertions.assertArrayEquals(copyPole, game.polePlayer);
    }
}