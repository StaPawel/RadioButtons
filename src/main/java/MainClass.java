import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MainClass {
    public static void main (String [] args){
        //implementacja sciezki do chromedrivera
        String driverPath = "C:\\chromedriver.exe";
        //implementacja adresu strony
        String urlPath = "https://antycaptcha.amberteam.pl/exercises/exercise4?seed=38ccca6d-b8cd-4730-b26a-779d2843ac3a";
        //implementacja drivera
        WebDriver driver;
        //inicjalizacja drivera
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.get(urlPath);
        //tworzenie obiektu wait typu WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver,2);
        //oczekiwana odpowiedz po poprawnie zakonczonym tescie
        String expectedAnswer = "OK. Good answer";

        //inicjalizacja elementow
        WebElement radio0 = driver.findElement(By.xpath("//input[@value='v10']"));
        WebElement radio1 = driver.findElement(By.xpath("//input[@value='v71']"));
        WebElement radio2 = driver.findElement(By.xpath("//input[@value='v82']"));
        WebElement radio3 = driver.findElement(By.xpath("//input[@value='v83']"));
        WebElement checkButton = driver.findElement(By.id("solution"));
        WebElement answer = driver.findElement(By.id("trail"));

        //zacekaj na radiobutton z grupy 0 i kliknij w niego
        wait.until(ExpectedConditions.visibilityOf(radio0));
        radio0.click();
        //zacekaj na radiobutton z grupy 1 i kliknij w niego
        wait.until(ExpectedConditions.visibilityOf(radio1));
        radio1.click();
        //zacekaj na radiobutton z grupy 2 i kliknij w niego
        wait.until(ExpectedConditions.visibilityOf(radio2));
        radio2.click();
        //zacekaj na radiobutton z grupy 3 i kliknij w niego
        wait.until(ExpectedConditions.visibilityOf(radio3));
        radio3.click();

        //zaczekaj na przycisk CHECK SOLUTION i kliknij w niego
        wait.until(ExpectedConditions.elementToBeClickable(checkButton));
        checkButton.click();

        String answerString = answer.getText();

        //jesli odpowiedz nie zawiera frazy OK. to pobierz odpowiedz jeszcze raz
        while (!answerString.contains("OK.")){
            answerString = answer.getText();
        }
        //pobierz docelowa odpowiedz
        answerString = answer.getText();

        //porownaj czy oczekiwana odpowiedz i aktualna odpowiedz sa takie same
        Assert.assertEquals(answerString,expectedAnswer);

    }
}
