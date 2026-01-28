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
import testBase.BaseClass;
import utilities.ExcelUtility;

public class MainTestPage extends BaseClass {
	HomePage home;
	LoginPage login;
	ExcelUtility excel;
	FooterPage footer;
	
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
		login.setPassword("Moulikpatel2704");
		boolean isToastDisplayed=login.isDisplayed();
		Assert.assertEquals(isToastDisplayed, true);		
	}
	@Test(priority=3)
	public void validateSearchResults() throws IOException
	{
		home=new HomePage(driver);
		excel=new ExcelUtility();
		home.sendDataSearchBox();
		home.openDateBox();
		home.selectWeekendEle();
		home.clickSearchBtn();
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=excel.createSheet(workbook);
		for(int i=1;i<=35;i++)
		{
			String title=driver.findElement(By.xpath("//li[@data-testid='accommodation-list-element']["+i+"]//a/span")).getText();
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
	@Test
	public void validateSocialMediaLinks() throws InterruptedException
	{
		List<WebElement> social=footer.getsocailLinks();
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
		Assert.assertTrue(true);
	}	
}
