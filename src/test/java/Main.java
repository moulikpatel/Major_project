import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		//setup
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.trivago.in/en-IN?themeId=280&sem_keyword=trivago&sem_creativeid=72636953390707&sem_matchtype=be&sem_network=o&sem_device=c&sem_campaignid=191340818&sem_adgroupid=3930406455&sem_targetid=40966707859&cipc=br&cip=9119110005&msclkid=d6aa8dc7775913d66f6ae4b6d1ce6747");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		//WebDriverWait mywait = new WebDriverWait(driver,Duration.ofSeconds(10));
		//file operations
		
//		FileInputStream file =new FileInputStream("./testData/Login_Test_Data.xlsx");
//		XSSFWorkbook workbook=new XSSFWorkbook(file);
//		XSSFSheet sheet=workbook.getSheet("Login");
//		int rows=sheet.getLastRowNum();
//		int cols=sheet.getRow(0).getLastCellNum();
		//validating login
		WebElement signinMenu=driver.findElement(By.xpath("//button[@data-testid='header-login']"));
		signinMenu.click();
		WebElement signin=driver.findElement(By.xpath("//button[@data-testid='login-modal-email-button']"));
		signin.click();
		WebElement email=driver.findElement(By.id("email"));
		email.sendKeys("moulikpatel2704@gmail.com");
		WebElement emailCont=driver.findElement(By.xpath("//form/button"));
		emailCont.click();
		WebElement password=driver.findElement(By.xpath("//input[@data-testid='password-strength-input']"));
		password.sendKeys("Moulikpatel2704");
		WebElement passwordCont=driver.findElement(By.xpath("//button[@data-testid='login-submit']"));
		passwordCont.click();
		WebElement toast =driver.findElement(By.xpath("//div[@data-testid='login-toast']"));		
		System.out.print(toast.isDisplayed());
		//validate search functionality
		WebElement searchBox=driver.findElement(By.id("input-auto-complete"));
		searchBox.sendKeys("Pondicherry");
		WebElement dateBox=driver.findElement(By.xpath("//button[@data-testid='search-form-calendar']"));
		dateBox.click();
		WebElement weekendEle=driver.findElement(By.xpath("//label[@data-testid='thisWeekend-index-label']"));
		weekendEle.click();
		WebElement searchBtn=driver.findElement(By.xpath("//button[@data-testid='search-button-with-loader']"));
		searchBtn.click();
		//Thread.sleep(3000);
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("Search");
		for(int i=1;i<=35;i++) {
//			sheet.createRow(i-1);
			String title=driver.findElement(By.xpath("//li[@data-testid='accommodation-list-element']["+i+"]//a/span")).getText();
			//Thread.sleep(2000);
			String price=driver.findElement(By.xpath("//li[@data-testid='accommodation-list-element']["+i+"]//div[@class='_96iWr8']/div[2]/div/div//div[@class='a_9yeK ElOk08']//div[@data-cos='recommendedPrice']")).getText();
			////li[@data-testid='accommodation-list-element']["+i+"]//div[@class='_96iWr8']//div[@class='_2tl76L']/div//div[@data-testid='recommended-price']
			XSSFRow row=sheet.createRow(i-1);
			XSSFCell cell1=row.createCell(0);
			XSSFCell cell2=row.createCell(1);
			cell1.setCellValue(title);
			cell2.setCellValue(price);
		}
		LocalTime now=LocalTime.now();
		//LocalTime.now() → Captures the time-of-day only (no date, no timezone).
		DateTimeFormatter fmt=DateTimeFormatter.ofPattern("hh_mm_ss");
		//DateTimeFormatter.ofPattern("HH:mm:ss") → Prepares a custom format (24-hour).
		String time=now.format(fmt);
		//now.format(fmt) → Converts the LocalTime into a formatted string for printing.
//		HH → 24‑hour (00–23)
//		hh → 12‑hour (01–12)
//		mm → minutes
//		ss → seconds
//		a → AM/PM
		FileOutputStream file=new FileOutputStream("./testResults/Search_"+time+"_Results.xlsx");
		workbook.write(file);
		workbook.close();
		file.close();
		List<WebElement> social=driver.findElements(By.xpath("//ul[@class='DoQ1yb']/li"));
		for(int i=0;i<social.size();i++) {
			driver.findElements(By.xpath("//ul[@class='DoQ1yb']/li")).get(i).click();
			Set<String> ids=driver.getWindowHandles();
			List<String> idss=new ArrayList<>(ids);
			driver.switchTo().window(idss.get(1));
			Thread.sleep(1000);
			System.out.println(driver.getTitle());
			driver.close();
			driver.switchTo().window(idss.get(0));
}
		driver.quit();
		
		
}

}
