package Saswatautomation.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import Saswatautomation.PageObjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException {
		Properties p=new Properties();
		FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\Saswatautomation\\Resources\\GlobalData.properties");
		p.load(fs);
		String browserName=System.getProperty("browser") != null ? System.getProperty("browser"):p.getProperty("browser");
		//String browserName=p.getProperty("browser");
		if(browserName.contains("chrome")) {
			ChromeOptions options=new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));//full screen
		}
		else if(browserName.contains("firefox")) {
			//fireforx driver
		}
		else if(browserName.contains("edge")) {
			WebDriverManager.edgedriver().setup(); 
	        driver = new EdgeDriver();  
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		driver.manage().window().maximize();
		return driver;
	}
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		File f=new File(filepath);
		//reads json to string
		String jsonContent=FileUtils.readFileToString(f, StandardCharsets.UTF_8);
		//reads string to HashMap jackson Databind
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data = 
	    mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>(){});
		mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
		//{map,map1}
	}
	
	 public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		 TakesScreenshot ts=(TakesScreenshot)driver;
		 File source=ts.getScreenshotAs(OutputType.FILE);
		 File dest=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		 FileUtils.copyFile(source, dest);
		 return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	 }
	 
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver=initializeDriver();
		landingPage=new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
    }
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
}
