package testBase;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
	
	public WebDriver driver;
	@BeforeClass
	public void driverSetup() {
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://www.trivago.in/en-IN?themeId=280&sem_keyword=trivago&sem_creativeid=72636953390707&sem_matchtype=be&sem_network=o&sem_device=c&sem_campaignid=191340818&sem_adgroupid=3930406455&sem_targetid=40966707859&cipc=br&cip=9119110005&msclkid=d6aa8dc7775913d66f6ae4b6d1ce6747");
	}
	@AfterClass
	public void tearDown() {
	    if (driver != null) {
	        driver.quit();
	    }
	}

}
