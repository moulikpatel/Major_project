package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	//locators
	
	By email=By.id("email");
	By emailBtn=By.xpath("//form/button");
	By password=By.xpath("//input[@data-testid='password-strength-input']");
	By passwordBtn=By.xpath("//button[@data-testid='login-submit']");
	By toast=By.xpath("//div[@data-testid='login-toast']");
	
	
	//Action methods 
	public void setEmail(String mail) {
		driver.findElement(email).sendKeys(mail);
		driver.findElement(emailBtn).click();
	}
	public void setPassword(String pass) {
		driver.findElement(password).sendKeys(pass);
		driver.findElement(passwordBtn).click();
	}
	public boolean isDisplayed() {
		return driver.findElement(toast).isDisplayed();
	}
	

}
