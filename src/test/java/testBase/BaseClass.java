 package testBase;

import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
//import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException {
		
		//loading config.propertices file
		p=new  Properties();
		FileReader file=new  FileReader("./src//test//resources//config.properties");
		p.load(file);
		
		logger=(Logger) LogManager.getLogger(this.getClass());
		
		if(p.getProperty("excution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("window")) {
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("No matching os");
				return;
			}
			
			//bowser
			switch(br.toLowerCase()) {
			case "chrome":capabilities.setBrowserName("chrome");break;
			case "edge":capabilities.setBrowserName("micrsoftEdge");break;
			default:System.out.println("no matching browser");return;
			}
		}
		
		if(p.getProperty("excution_env").equalsIgnoreCase("local")) {
		switch(br.toLowerCase()) {
		
		case "chrome":driver=new ChromeDriver();break;
		case "edge": driver=new EdgeDriver();break;
		case "firefox":driver=new FirefoxDriver();break;
		default :System.out.println("invalide browser name");return;
		}
		
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//driver.get("https://demo.opencart.com/en-gb?route=account/register");
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.get(p.getProperty("appURL"));  //reading url from property file
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown() {
		driver.quit();
	}
	

	public String randomString() {
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomNumber() {
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
		
	}
	
	public String randomAlphaNumberic() {
		String generatedString=RandomStringUtils.randomAlphabetic(3);
		String generatedNumber=RandomStringUtils.randomNumeric(3);
		return (generatedString+generatedNumber);
	}

	public String captureScreen(String name) throws Exception {
		
		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date(0));
		
		TakesScreenshot takeScreenshot=(TakesScreenshot) driver;
		File sourefile=takeScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshors\\"+ name + "_" + timeStamp + ".png";
		File targetfile=new File(targetFilePath);
		sourefile.renameTo(targetfile);
		return targetFilePath;
	}


}