package miniproject;

//import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driversetup {
	static WebDriver driver;
	
	public static WebDriver chromedriver()
	{
		
		WebDriverManager.chromedriver().setup();
	    driver=new ChromeDriver();
		System.out.println("Chrome Browser");
		return driver;
	}

	public static WebDriver edgedriver()
	{
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		System.out.println("Edge Browser");
    	return driver;
	}

	
}