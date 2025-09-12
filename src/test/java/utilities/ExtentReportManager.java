package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporte;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repname;
	
	public void onStart(ITestContext testContext) {
		/*
		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentdatetimestamp=df.format(dt);
		*/
		
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date(0));
		
		repname="Test-Report-"+ timeStamp +".html";
		sparkReporte = new ExtentSparkReporter(".\\reports\\" + repname);
		
		sparkReporte.config().setDocumentTitle("Opencart Automation report");
		sparkReporte.config().setReportName("opencart functional testing");
		sparkReporte.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporte);
		extent.setSystemInfo("Application","opencart");
		extent.setSystemInfo("Module","Admin");
		extent.setSystemInfo("Sub Module","Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment","QA");
		
		String os=testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System",os);
		
		String browser=testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser",browser);
		
		List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups",includedGroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS,result.getName()+"got successfully excuted");
		
	}

	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL,result.getName()+"got Failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
		
		try {
			String imgpath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,result.getName()+"got skiped");
		test.log(Status.INFO,result.getThrowable().getMessage());
		
	}
	
	public void onFinish(ITestContext testContext) {
		
		extent.flush();
		
		String pathOfExtentReport =System.getProperty("user.dir")+"\\reports\\"+repname;
		File extentReport=new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
//			try {
//				Url
//				
				
				
			//}
	}
}
