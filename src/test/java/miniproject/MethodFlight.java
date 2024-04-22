package miniproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MethodFlight {
		boolean result=false;
		static Properties p =new Properties();
	//Multi-browser automation
		public static WebDriver driver;
		public void driverselection(WebDriver driver) {
			this.driver=driver;
		}
		  //excel data
		  public void excel()
		  {
			  Dataproperties.dataExtract();
		  }
		//Launch https://www.air.irctc.co.in/ in a web browser, and verify the appropriate site is opened or not. 
		public String launchWebsite() throws InterruptedException
		{
			String launchWebsite;
			
			try {
				FileInputStream fis = new FileInputStream("C:\\Users\\2318420\\eclipse-workspace\\Selenium\\src\\test\\java\\miniproject\\website.properties");
				p.load(fis);
				fis.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}

		    driver.get(p.getProperty("baseUrl"));
			 String actual_title=driver.getTitle();
			    String expected_title="Air Ticket Booking | Book Flight Tickets | Cheap Air Fare - IRCTC Air";
			  
              
			    
			    	if(expected_title.equals(actual_title))
			    			{
			    		      launchWebsite= "Site opened";
			    			}
			    	else
			    	{
			    		launchWebsite= "Site didn't open";
			    	}
			    	
			    	//3)Maximize the window
				    driver.manage().window().maximize();
				    return launchWebsite;
		}
		//Enter "Hyd" in “From” city
		public void OriginPlace() throws InterruptedException
        {
			  WebElement enterfrom= driver.findElement(By.id("stationFrom"));
			  enterfrom.sendKeys(Dataproperties.fromlocation);
			  Thread.sleep(1000);
			  List<WebElement> list1=driver.findElements(By.xpath("//*[contains(text(),'Hyderabad')]"));
			  for(WebElement lis:list1)
			  {
				  String text=lis.getText();
				  if(text.contains("HYD"))
				  {
					  lis.click();
				  }
			  }

		}
		//Enter "Pun" in “To” city
		public void DestinationPlace() throws InterruptedException
		{
			WebElement Enterto= driver.findElement(By.id("stationTo"));
			  Enterto.sendKeys(Dataproperties.tolocation);
			  Thread.sleep(1000);
			  List<WebElement> list2=driver.findElements(By.xpath("//*[contains(text(),'Pune')]"));
			  for(WebElement li:list2)
			  {
				  String text=li.getText();
				  if(text.contains("PNQ"))
				  {
					  li.click();
				  }
			  }
		}
	
		//Select today’s date 
		public void date() 
		{
			WebElement date=driver.findElement(By.id("originDate"));
			date.click();
			WebElement today=date.findElement(By.xpath("//span[@class='act active-red']"));
			today.click();
		}
		
		//Select "Business" class
		public void ClassSelection()
		{
			
			WebElement dropdownEle=driver.findElement(By.id("noOfpaxEtc"));
			dropdownEle.click();
			WebElement dr=driver.findElement(By.id("travelClass"));
			Select drop=new Select(dr);
			drop.selectByVisibleText("Business");
		}
		//Click on "Search" button. 
		public String SearchButton() throws InterruptedException 
		{
			String searchButton;
			WebElement search=driver.findElement(By.xpath("//*[@type='submit']"));
			search.click();
			//driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			Thread.sleep(6000);
			searchButton = "Moved to next page";
			return searchButton;
			
		}
		//Validating city ,date
		public String verifyCityDate() throws InterruptedException
		{
			String 	verifyCity="";
			List<WebElement> city1=driver.findElements(By.xpath("//*[@class='right-searchbarbtm-in']/div[2]/span"));
			List<WebElement> city2=driver.findElements(By.xpath("//*[@class='right-searchbarbtm-in']/div[3]/span"));
		    Thread.sleep(4000);
			WebElement dep_date=driver.findElement(By.xpath("//input[@id='originDate']"));
			Thread.sleep(4000);
			dep_date.click();
			WebElement today1=dep_date.findElement(By.xpath("//span[@class='act active active-red']"));
			today1.click();
		
			int noOfResults = city1.size();
		
			for(int i = 0; i < noOfResults; i++)
			{
				String c1=city1.get(i).getText();
				String c2=city2.get(i).getText();
				String departure_date=today1.getText();
				System.out.println(departure_date);
			
				if(c1.contains("Hyderabad") && c2.contains("Pune") && LocalDate.now().toString().split("-")[2].contains(departure_date))
				{
					result = true;
					verifyCity= "Task verified";
					
				}
				else
				{
					result=false;
					verifyCity= "Task failed";
					break;
				}
			}
			return verifyCity;
			}
			//Display the name and Number of available Flights on the console
		
		 public List<String> DisplayName()
		{
			List<String> display = new ArrayList<String>();
			List<WebElement> flight_name=driver.findElements(By.xpath("//*[@class='right-searchbarbtm-in']/div[1]//b"));
			List<WebElement>no_of_flights=driver.findElements(By.xpath("//*[@class='right-searchbarbtm-in']/div[1]//span"));
			int num=flight_name.size();
			
			if(num>0)
			{
			for(int j=0 ;j<num ;j++)
				{
					String fname=no_of_flights.get(j).getText();
					String flight_num=flight_name.get(j).getText();
					display.add(flight_num+"*****"+fname);
	
				}
			}
			else
			{
				System.out.println("No Flight available");
			}
			//System.out.println(display);
			return display;
		}
		//Take Screenshot
		public String CaptureScreenshot() throws InterruptedException, IOException
		{
		
			TakesScreenshot ts = (TakesScreenshot)driver;
			File sourceFile = ts.getScreenshotAs(OutputType.FILE);
			File destinationFile = new File("C:\\Users\\2318420\\eclipse-workspace\\Selenium\\screenshots\\flight.jpg");
			FileUtils.copyFile(sourceFile, destinationFile);
			return "Screenshot captured";
	
		}
		//Closing the driver
		public void closeWebsite() throws InterruptedException
		{
			driver.quit();
		}
		}



