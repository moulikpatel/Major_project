package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WishListPage extends BasePage {
	public WishListPage(WebDriver driver) {
		super(driver);
	}
	//locators
	By menu=By.xpath("//button[@data-testid=\"header-profile-menu-desktop\"]");
	By favorites=By.xpath("//a[@data-testid=\"profile-menu-favorites\"]");
	
	By favoritesList=By.xpath("//ul[@class='_30Zjlp']/li/a");
	
	
	
	//Action Method
	public void moveToMenu() {
		Actions ac=new Actions(driver);
		WebElement ele=driver.findElement(menu);
		ac.moveToElement(ele).perform();
	}
	public void moveTOFavorites() {
		Actions ac=new Actions(driver);
		WebElement ele=driver.findElement(favorites);
		ac.moveToElement(ele).perform();
		ele.click();
	}
	public void clickOnFavoritesList() {
		driver.findElement(favoritesList).click();
	}
	

}
