package stepDefinitions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



public class testgg {


    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/java/driver/drivers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-blink-features=AutomationControlled");
        //options.addArguments("--headless=new");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://duckduckgo.com");
        driver.findElement(By.name("q")).sendKeys("casa");
        driver.findElement(By.xpath("//*[@id=\"searchbox_homepage\"]/div/div[1]/div/button[2]")).click();


        try { Thread.sleep(3000); } catch (Exception e) {}
        driver.quit();
    }
}
