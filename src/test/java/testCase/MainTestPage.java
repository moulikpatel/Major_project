package testCase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.FooterPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.SearchPage;
import pageObjects.WishListPage;
import testBase.BaseClass;
import utilities.ExcelUtility;
import utilities.ScreenShot;


public class MainTestPage extends BaseClass {
	HomePage home;
	LoginPage login;
	ExcelUtility excel;
	FooterPage footer;
	ScreenShot ss;
	SearchPage search;
	WishListPage wishlist;
	@Test(priority=1)
	public void validateTitle() {
		
		String Title=driver.getTitle();
		String actualTitile="trivago.in - Compare hotel prices worldwide";
		boolean isValid=Title.equals(actualTitile);
		System.out.println(Title);
		Assert.assertEquals(isValid, true);
	}
	@Test(priority=2)
	public void validateLoginFunctionality() {
		home=new HomePage(driver);
		login=new LoginPage(driver);
		home.clickSiginMenu();
		home.clickSigin();
		login.setEmail("moulikpatel2704@gmail.com");
		login.setPassword("Moulikpatel2704");	ss=new ScreenShot();
		ss.takeScreenShot(driver,"AfterLogin");
		boolean isToastDisplayed=login.isDisplayed();
		Assert.assertEquals(isToastDisplayed, true);		
	}
	@Test(priority=3)
	public void validateSearchResults() throws IOException, InterruptedException
	{
		home=new HomePage(driver);
		excel=new ExcelUtility();
		search=new SearchPage(driver);
		home.sendDataSearchBox();
		home.openDateBox();
		home.selectWeekendEle();
		
		home.clickSearchBtn();
		//search.clickCheckboxes();
		//search.clickFilterBtn();
		
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=excel.createSheet(workbook);
		for(int i=1;i<=35;i++)
		{
			String title=driver.findElement(By.xpath("//li[@data-testid='accommodation-list-element']["+i+"]//a/span")).getText();
			if(i<=5){
				driver.findElement(By.xpath("//li[@data-testid='accommodation-list-element']["+i+"]/div/article/div[1]/div/div/div/div/div[2]//button")).click();
				search.insertData(title);
			if(i==1) {
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[@data-testid='item-card-favorite-form-submit-button']")).click();
			}	
			}
			String price=driver.findElement(By.xpath("//li[@data-testid='accommodation-list-element']["+i+"]//div[@class='_96iWr8']/div[2]/div/div//div[@class='a_9yeK ElOk08']//div[@data-cos='recommendedPrice']")).getText();
			XSSFRow row=excel.createRow(sheet,i-1);
			XSSFCell cell1=excel.createCell(row, 0);
			XSSFCell cell2=excel.createCell(row, 1);
			cell1.setCellValue(title);
			cell2.setCellValue(price);
		}
		LocalTime now=LocalTime.now();
		DateTimeFormatter fmt=DateTimeFormatter.ofPattern("hh_mm_ss");
		String time=now.format(fmt);
		FileOutputStream file=new FileOutputStream("./testResults/Search_"+time+"_Results.xlsx");
		workbook.write(file);
		workbook.close();
		file.close();
		Assert.assertTrue(true);	
	}
	@Test(priority=4)
	public void validateSocialMediaLinks() throws InterruptedException
	{
		Thread.sleep(1000);
		List<WebElement> social=driver.findElements(By.xpath("//ul[@class='DoQ1yb']/li"));
		System.out.println(social.size());
		for(int i=0;i<social.size();i++)
		{
			driver.findElements(By.xpath("//ul[@class='DoQ1yb']/li")).get(i).click();
			Set<String> ids=driver.getWindowHandles();
			List<String> idss=new ArrayList<>(ids);
			driver.switchTo().window(idss.get(1));
			Thread.sleep(1000);
			System.out.println(driver.getTitle());
			driver.close();
			driver.switchTo().window(idss.get(0));
		}
	}
	@Test(priority=5)
	public void validateSubscribe() throws InterruptedException
	{
		footer=new FooterPage(driver);
		footer.setEmail("dummy@gmail.com");
		footer.clickSubscribeBtn();
		Thread.sleep(2000);
		ss=new ScreenShot();
		ss.takeScreenShot(driver,"SubscribeDefect");
		Assert.assertTrue(false);
	}
	@Test(priority=6)
	public void wishList() throws InterruptedException {
		boolean wish=true;
		search=new SearchPage(driver);
		wishlist=new WishListPage(driver);
		wishlist.moveToMenu();
		wishlist.moveTOFavorites();
		Thread.sleep(2000);
		wishlist.clickOnFavoritesList();
		int size=SearchPage.li.size();
		for(int i=1;i<size;i++) {
			String str=driver.findElement(By.xpath("//div[@data-testid='favorites-page-items-list']/ul/li/article/ul/li["+i+"]/div/div[2]/div[1]/div[1]//span")).getText();
			if(search.li.get(size-(i-1)-1).equals(str)) {
				wish=true;
				}
			else {
				wish=false;
				break;
			}
		}
		Assert.assertTrue(wish);
	}
	
}
