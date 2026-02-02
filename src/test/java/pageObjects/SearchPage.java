package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage {
	static public List<String> li=new ArrayList<>();
	public SearchPage(WebDriver driver)
	{
		super(driver);
	}
	
	//locators
	
	By filterBtn=By.xpath("//ul[@id='refinement-row-filters']/li[1]");
	By wifi=By.xpath("//div[@data-testid=\"refinement-row-pills\"]/div/ul/li[1]");
	By parkingBtn=By.xpath("//div[@data-testid=\"refinement-row-pills\"]/div/ul/li[3]");
		
	
	//Actions Method
	public void clickFilterBtn() {
		driver.findElement(filterBtn).click();
	}
	public void insertData(String title) {
		li.add(title);
	}
	public void clickCheckboxes() {
		driver.findElement(wifi).click();
		driver.findElement(parkingBtn).click();
	}
	public String getListitem(int i) {
		return li.get(i);
	}
	

}
