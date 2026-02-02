package utilities;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
 
public class ScreenShot {
    // Path should include the file extension in the logic below
    static String filePath = "./screenshots";
//    static WebDriver driver;
 
    public static void takeScreenShot(WebDriver driver, String fileName) {
        try {
            // Convert web driver object to TakeScreenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            // Capture the screenshot as a file
            File src = ts.getScreenshotAs(OutputType.FILE);
            // Define destination with .png extension
            File dest = new File(filePath + "\\" + fileName + ".png");
            // Move file to destination
            src.renameTo(dest);
            System.out.println("Screenshot taken: " + fileName);
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }
}