package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
	
	//Constructor
	public HomePage(WebDriver driver) {
		super(driver);
	}
	//Locators
	By signinMenu=By.xpath("//button[@data-testid='header-login']");
	By signin=By.xpath("//button[@data-testid='login-modal-email-button']");
	By searchBox=By.id("input-auto-complete");
	By dateBox=By.xpath("//button[@data-testid='search-form-calendar']");
	By weekendEle=By.xpath("//label[@data-testid='thisWeekend-index-label']");
	By searchBtn=By.xpath("//button[@data-testid='search-button-with-loader']");
	
	
	//Actions Method
	public void clickSiginMenu() {
		driver.findElement(signinMenu).click();
	}
	public void clickSigin() {
		driver.findElement(signin).click();
	}
	public void sendDataSearchBox() {
		driver.findElement(searchBox).sendKeys("Pondicherry");
	}
	public void openDateBox() {
		driver.findElement(dateBox).click();
	}
	public void selectWeekendEle() {
		driver.findElement(weekendEle).click();
	}
	public void clickSearchBtn() {
		driver.findElement(searchBtn).click();
	}
	
	
	
}
