import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResult {
    WebDriver driver;

    public final By PAGEROOT = By.xpath("//*");



    public SearchResult(WebDriver driver){
        this.driver = driver;
    }

    public int searchWord(String word){
        int number = 0;
        List<WebElement> words = driver.findElements(PAGEROOT);
        for (WebElement elements : words) {
            try {
                if (elements.getText().contains(word)) {
                    number += 1;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(word + "szó ennyiszer szerepel az oldalon: " + number );
        return number;
    }

}
