package miniproject;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.github.dockerjava.transport.DockerHttpClient.Request.Method;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Mainjava {
   public static WebDriver driver; 
	public static void main(String[] args) throws InterruptedException, IOException {
		MethodFlight flight=new MethodFlight();
		System.out.println("1.chrome\n2.edge");
		Scanner sc=new Scanner(System.in);
		int option=sc.nextInt();
		PrintStream ps=new PrintStream(new File("C:\\Users\\2318420\\eclipse-workspace\\Selenium\\src\\test\\java\\miniproject\\Result.txt"));
		System.setOut(ps);
		
		  
		   switch(option)
		   {
		   case 1:
		   {
			   
			    driver=Driversetup.chromedriver();
				break;
		   }
		   case 2:
		   {
			   driver=Driversetup.edgedriver();
				break;		   
	      }
	   default:
		System.out.println("No Browser found");
        System.exit(0);
      }
		  
			 try
			   {
				    flight.driverselection(driver);
				    String launchWebsite= flight.launchWebsite();
				    flight.excel();
					flight.OriginPlace();
					flight.DestinationPlace();
					flight.date();
					flight.ClassSelection();
					String searchButton  = flight.SearchButton();
					String verify = flight.verifyCityDate();
					List<String> display = flight.DisplayName();
					String ss = flight.CaptureScreenshot();
					flight.closeWebsite();
				   ps.print(launchWebsite);
				   System.out.println();
				   ps.print(searchButton);
				   System.out.println();
				   ps.print(verify);
				   System.out.println();
				   for(String s : display) {
					   ps.print(s);
					   System.out.println();
				   }
				   ps.print(ss);			   
			   }
			 catch(Exception e) 
			   {
				 System.out.println(e);
			   }
	   }
	  
	}
