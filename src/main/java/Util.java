import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Util {
     WebDriver driver;

    public Util(WebDriver driver) {
        this.driver = driver;

    }

    public String[] readCredential() {
        String[] result = new String[2];
        try {
            File myUser = new File("User.txt");
            Scanner scanner = new Scanner(myUser);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] temp = data.split(" = ");
                if (temp[0].equals("username")) {
                    result[0] = temp[1];
                } else {
                    result[1] = temp[1];
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /*public void saveToFile(){

        String text = driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/div[123]/p[1]")).getText();
        try {
            FileWriter fileWriter=new FileWriter("SaveToFile.txt", true);
            fileWriter.append(text);
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }*/

    public String writeToFile(String title, String text) {
        try {
            FileWriter textFile = new FileWriter("searchResults.txt",true);
            textFile.append(title + " : \n" + text);
            textFile.close();
            return null;
        } catch (IOException var4) {
            return var4.getMessage();
        }
   }


}




