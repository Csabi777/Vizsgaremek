
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class WikiTest {
    WebDriver driver;


    @BeforeAll
    public static void Init() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    public void setDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        // options.addArguments("--headless");
        options.addArguments("incognito");
        options.addArguments("--disable-gpu", "--ignore-certificate-errors", "--disable-extensions", "--disable-dev-shm-usage");
        options.addArguments("window-size=1200,730");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        // driver.manage().window().maximize();

    }


    @Test
    @DisplayName("1 Bejelentkezés")
    public void testLogin() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        String username = "KissBela123";
        String password = "TNY=-pG.8T*gNGv";
        mainPage.sendUsername(username);
        mainPage.sendPassword(password);
        mainPage.clickBlueLoginButton();
        Thread.sleep(200);

        Assertions.assertEquals("Log out", mainPage.getLogoutText());

    }


    @Test
    @DisplayName("2 Bejelentkezés - adatok külső fileból")
    public void testLoginFromFile() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        Util util = new Util(driver);
        mainPage.clickLoginButton();
        String username = util.readCredential()[0];
        String password = util.readCredential()[1];
        mainPage.sendUsername(username);
        mainPage.sendPassword(password);
        mainPage.clickBlueLoginButton();
        Thread.sleep(200);

        Assertions.assertEquals("Log out", mainPage.getLogoutText());
    }


    @Test
    @DisplayName("3 Kijelentkezés")
    public void testLogout() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        LogOutPage logOutPage = new LogOutPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        mainPage.clickLoginButton();
        String username = "KissBela123";
        String password = "TNY=-pG.8T*gNGv";
        mainPage.sendUsername(username);
        mainPage.sendPassword(password);
        mainPage.clickBlueLoginButton();
        Thread.sleep(200);
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.LOGOUTBUTTON));
        mainPage.clickLogout();

        Assertions.assertEquals("https://en.wikipedia.org/w/index.php?title=Special:UserLogout&returnto=Main+Page", LogOutPage.URL);

    }


    @Test
    @DisplayName("4 Adatvédelmi nyilatkozat müvelet - szöveg mentése külső fileba")
    public void testPrivacyPolicy() throws InterruptedException, IOException {
        MainPage mainPage = new MainPage(driver);
        PrivacyAndPolicyPage privacyAndPolicyPage = new PrivacyAndPolicyPage(driver);
        Util util = new Util(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1000);
        mainPage.clickPrivacyAndPolicy();
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(privacyAndPolicyPage.YOURRIGHTSTEXT));
        Thread.sleep(1000);

        FileWriter myWriter = new FileWriter("savetofile.txt", true);
        myWriter.write(driver.findElement(privacyAndPolicyPage.YOURRIGHTSTEXT).getText());
        myWriter.close();

        //*util.writeToFile(privacyAndPolicyPage.getPrivacyRights(), privacyAndPolicyPage.getPrivacyText());

    }


    @Test
    @DisplayName("5 Adatok listázása - kiratás")
    public void testArtsMenuListing() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        TheArtsPage theArtsPage = new TheArtsPage(driver);
        mainPage.clickTheArtsLink();
        Thread.sleep(1000);
        List<String> actual = theArtsPage.menuLister();
        List<String> expected = new ArrayList<>();
        expected.add("Activities");
        expected.add("Culture");
        expected.add("Geography");
        expected.add("Health");
        expected.add("History");
        expected.add("Mathematics");
        expected.add("Nature");
        expected.add("People");
        expected.add("Philosophy");
        expected.add("Religion");
        expected.add("Society");
        expected.add("Technology");
        expected.add("Random portal");

        Assertions.assertEquals(expected, actual);

    }


    @Test
    @DisplayName("6 Több oldalas bejárás - URL-ek mentése listába")
    public void testPageWalkthrough() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        PrivacyAndPolicyPage privacyAndPolicyPage = new PrivacyAndPolicyPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(2000);
        mainPage.clickPrivacyAndPolicy();
        Thread.sleep(1000);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        privacyAndPolicyPage.clickOldPolicy();
        Thread.sleep(2000);
        List<String> infos = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            privacyAndPolicyPage.clickPrevPolicy();
            Thread.sleep(2000);
            String currentinfo = privacyAndPolicyPage.getRevisionInfo();
            infos.add(currentinfo + "\n");
            Thread.sleep(1000);
        }
        System.out.println(infos);

        Assertions.assertNotNull(infos);

    }


    @Test
    @DisplayName("7 Új adat bevitel - Belépés után a user oldalon egy új aláírás beállítása")
    public void testNewData() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        PreferencesPage preferencesPage = new PreferencesPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        mainPage.clickLoginButton();
        String username = "KissBela123";
        String password = "TNY=-pG.8T*gNGv";
        mainPage.sendUsername(username);
        mainPage.sendPassword(password);
        mainPage.clickBlueLoginButton();
        Thread.sleep(1000);
        mainPage.clickPreferences();
        preferencesPage.clickUserProfile();
        Thread.sleep(1000);
        preferencesPage.setSignature("Ez egy teszt aláírás 321321");
        Thread.sleep(1000);
        preferencesPage.clickSaveButton();
        Thread.sleep(2000);

        String actual = driver.findElement(By.xpath("//*[@id=\"mw-htmlform-signature\"]/div[1]/div/div/label/a[1]")).getText();
        String expected = "Ez egy teszt aláírás 321321";

        Assertions.assertEquals(expected, actual);

    }


    @Test
    @DisplayName("8 Meglévő adat modosítása - Betütípus cseréje ")
    public void testChangeFonts() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        LanguageSettingPage languageSettingPage = new LanguageSettingPage(driver);
        Thread.sleep(1000);
        mainPage.clickSettings();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedConditions.visibilityOfElementLocated(languageSettingPage.FONTSBUTTON)));
        languageSettingPage.clickFontButton();
        Thread.sleep(1000);
        languageSettingPage.checkCheckbox();
        Thread.sleep(1000);
        String font = "ComicNeue";
        languageSettingPage.setFonts(font);

        String actual = driver.findElement(By.xpath("//*[@id=\"mp-welcome\"]/a")).getCssValue("font-family").split(",")[0];

        Assertions.assertEquals(actual, font);

    }

    @Test
    @DisplayName("9 Adat törlése  - Belépés után a user oldalon az egyéni aláírás törlése")
    public void testDeleteData() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        PreferencesPage preferencesPage = new PreferencesPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        mainPage.clickLoginButton();
        String username = "KissBela123";
        String password = "TNY=-pG.8T*gNGv";
        mainPage.sendUsername(username);
        mainPage.sendPassword(password);
        mainPage.clickBlueLoginButton();
        Thread.sleep(1000);
        mainPage.clickPreferences();
        preferencesPage.clickUserProfile();
        Thread.sleep(1000);
        preferencesPage.deleteSignatureKeys();
        /*preferencesPage.setSignature("");*/
        Thread.sleep(2000);
        preferencesPage.clickSaveButton();


        String actual = driver.findElement(preferencesPage.SIGNATUREINPUT).getText();
        String expected = "";

        Assertions.assertEquals(expected, actual);

    }




    @AfterEach
    public void tearDown() {

        driver.quit();
    }

}
