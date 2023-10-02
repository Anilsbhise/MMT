package makeMyTrip_web;

import java.io.File;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Logo_Validation {
	WebDriver driver;
	String browser = "firefox";

	@BeforeTest
	public void beforeTest() {
		//enter browser name
		if(browser.equals("chrome")) {
			// Create a new chrome webdriver instance
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\91968\\Desktop\\anill_project\\driver\\chromedriver_win32\\chromedriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browser.equals("firefox")) {
			// Create a new Firefox webdriver instance
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\91968\\Desktop\\anill_project\\driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
	}

	@Test(enabled = false)
	public void logoValidation() {
		// Open the Makemytrip website
		driver.get("https://www.makemytrip.com/");

		// Wait for the page to load
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.xpath("//span[@class='commonModal__close']")).click();

		// Locate the logo element
		boolean logo = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[1]/div[1]/a/picture/img")).isDisplayed();

		// Verify that the logo is present
		Assert.assertTrue(logo);



		// Close the browser
		driver.quit();
	}

	@Test (enabled =true)
	public void findflights() {
		// Open the MakeMyTrip website
		driver.get("https://www.makemytrip.com/");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.xpath("//span[@class='commonModal__close']")).click();


		// Enter the departure city
		String FromLocation = "Mumbai";
		WebElement fromCity = driver.findElement(By.id("fromCity"));
		fromCity.click();
		fromCity.sendKeys(FromLocation);

		List<WebElement>  citi= driver.findElements(By.xpath("//p[@class='font14 appendBottom5 blackText']"));
		for(int i=0; i<citi.size(); i++) {
			System.out.println("print city name "+citi.get(i).getText());
			if(citi.get(i).getText().contains(FromLocation)) {
				citi.get(i).click();
				break;
			}
		}

		// Enter the arrival city
		String ToLocation = "Delhi";
		WebElement toCity = driver.findElement(By.id("toCity"));
		toCity.click();
		toCity.sendKeys(ToLocation);
		
		List<WebElement>  Tociti= driver.findElements(By.xpath("//p[@class='font14 appendBottom5 blackText']"));
		for(int i=0; i<Tociti.size(); i++) {
			System.out.println("print city name "+Tociti.get(i).getText());
			if(Tociti.get(i).getText().contains(ToLocation)) {
				Tociti.get(i).click();
				break;
			}
		}

		// Select the departure date
		//        driver.findElement(By.id("departureDate")).click();
		driver.findElement(By.xpath("//div[@aria-label='Sun Nov 05 2023']")).click();

		// Click the search button
		driver.findElement(By.xpath("//a[@class='primaryBtn font24 latoBold widgetSearchBtn ']")).click();

		// Wait for the search results to load
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
		
		driver.findElement(By.xpath("//button[@class='button buttonSecondry buttonBig fontSize12 relative']")).click();


		// Find the first 5 flight result
		WebElement flightResult = driver.findElement(By.xpath("//p[@class='font24 blackFont whiteText appendBottom20 journey-title makeFlex spaceBetween bottom']"));
		System.out.println(flightResult.getText());
		
		List<WebElement> Airline_Name = driver.findElements(By.xpath("//p[@class='boldFont blackText airlineName']"));
		List<WebElement> Departure_time = driver.findElements(By.xpath("//div[@class='flexOne timeInfoLeft']"));
		List<WebElement> Arrival_time = driver.findElements(By.xpath("//div[@class='flexOne timeInfoRight']"));
		List<WebElement> Price = driver.findElements(By.xpath("//div[@class='blackText fontSize18 blackFont white-space-no-wrap clusterViewPrice']"));
				
				
				// Print the flight details to the console
		for(int j=0; j<Airline_Name.size();j++) {
			System.out.print(j+1 +"  ");
			System.out.print(Airline_Name.get(j).getText().trim()+ "--");
			System.out.print("("+Departure_time.get((j+j)/2).getText().trim()+ ")-TO-");
			System.out.print("("+Arrival_time.get(j).getText().trim()+ ")-");
			System.out.print(Price.get(j).getText().trim());
			System.out.println("------");
			if(j==4) {
				break;
			}
			
		}
		// Close the browser
		driver.close();
	}

}
