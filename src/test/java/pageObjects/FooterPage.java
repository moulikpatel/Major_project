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
	By emailInput=By.xpath("//input[@id='newsletter-form-footer']");
	By subscribeBtn=By.xpath("//button[@data-testid='newsletter-subscribe-button']");
	
	public List<WebElement> getsocailLinks(){
		return driver.findElements(socialLinks);
	}
	public void setEmail(String val) {
		driver.findElement(emailInput).sendKeys(val);
	}
	public void clickSubscribeBtn() {
		driver.findElement(subscribeBtn).click();
	}
	
	
	
	

}
