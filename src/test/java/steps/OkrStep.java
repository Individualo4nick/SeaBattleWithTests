package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

public class OkrStep {
    private int pole[][];
    int x, y;
    boolean checkCoord;
    String errorMessage;
    @Given("Поле с кораблями и выстрелами")
    public void givePole() {
        this.pole = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j%2 == 0)
                    this.pole[i][j] = -1;
                if (j%2 != 0)
                    this.pole[i][j] = 6;
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
            this.checkCoord = true;
        } else this.checkCoord = false;
    }
    @And("Значение в клетке равно {int} или {int}")
    public void checkSecondPredicateTrue(int firstValue, int secondValue) {
        if(pole[x][y] == firstValue || pole[x][y] == secondValue)
            this.checkCoord = true;
        else this.checkCoord = false;
    }
    @Then("Уменьшается значение в клетке на 1. Ожидаем {int} или {int}")
    public void decrementValue(int firstAnswer, int secondAnswer) {
        if (checkCoord) {
            this.pole[x][y]--;
        }
        Assertions.assertTrue(pole[x][y] == firstAnswer || pole[x][y] == secondAnswer);
    }
    @When("Координаты не находятся внутри поля")
    public void checkFirstPredicateFalse() {
        if (((x >= 0) && (x <= 9)) && ((y >= 0) && (y <= 9))) {
            this.checkCoord = true;
        } else this.checkCoord = false;
    }
    @Then("Выводится сообщение {string}")
    public void noDecrementValue(String predicateErrorMessage) {
        if (!checkCoord) {
            errorMessage = "Ошибка координат";
        }
        Assertions.assertEquals(errorMessage, predicateErrorMessage);
    }
}