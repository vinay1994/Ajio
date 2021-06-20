package main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	 public static WebDriver   driver = null;
	  public static WebDriver starDriver() {   
	            WebDriverManager.chromedriver().setup();
	            ChromeOptions options = new ChromeOptions();
	            driver = new ChromeDriver(options);
	        driver.manage().window().maximize();
	        return driver;
	    }
	 
	 
	 
	  
}
