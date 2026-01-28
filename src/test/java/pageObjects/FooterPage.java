package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FooterPage  extends BasePage{

	public FooterPage(WebDriver driver) {
		super(driver);
	}
	
	By socialLinks=By.xpath("//ul[@class='DoQ1yb']/li");
	
	public List<WebElement> getsocailLinks(){
		return driver.findElements(socialLinks);
	}
	
	
	
	

}
