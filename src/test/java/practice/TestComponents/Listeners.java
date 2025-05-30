package practice.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import practice.Resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();//Thread safe
	public void onTestStart(ITestResult result) {
		test=extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);	//unique thread id(ErrorValidationTest->test)
		}
	public void onTestSucess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
	}
	public void onTestFailure(ITestResult result) {
		//screenshot, Attach to Report
		extentTest.get().fail(result.getThrowable());
		try {
		driver=(WebDriver) result.getTestClass().getRealClass().getField("driver")
				.get(result.getInstance());
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		String filePath=null;
		try {
			filePath=getScreenshot(result.getMethod().getMethodName(),driver);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
	}
	public void onTestSkipped(ITestResult result) {
		
	}
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
