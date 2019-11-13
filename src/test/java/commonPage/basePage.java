package commonPage;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.nio.file.Paths;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ktvn.utils.ExtentReporter;

public class basePage {
	
	public WebDriver driver;
	protected String browsers = "chrome";
	
	public String sUsername = "admin@test.com";
	public String sPassword = "test123";

	public static int NumberOfFunctionallFailures = 0;
	public static String CurrentClassName;

	String sReportFile = "";
	static String sEnviromentName = "Karros KTVN TEST";
	static String buildVersion = "v0.1";

	static InetAddress inetAddress = null;
	static String testsuiteName = "";
	protected String sTestCaseName = this.getClass().getName();
	protected String testClass = sTestCaseName;
	protected String sTestSuiteFileName = "";

	public static final String APP_URL = "http://localhost:4444/wd/hub";

	public static final String OUTPUT_FOLDER1 = System.getProperty("user.dir") + "/test-output/";

	@Parameters({ "browsers", "testsuite" })
	@BeforeSuite
	public void beforeSuite(@Optional("chrome") String browsers, @Optional("REG") String testsuite) {
		sReportFile = Paths.get(OUTPUT_FOLDER1 + "extent/" + testsuite + "_" + getHostName() + ".html").toString();
		ExtentReporter.createReporter(sReportFile, browsers, APP_URL);
		testsuiteName = testsuite;
	}

	@Parameters("browsers")
	@BeforeMethod
	public synchronized void methodSetup(Method caller, @Optional("chrome") String browsers)
			throws MalformedURLException {
		ExtentReporter.getTestReporter().startTest(getTestName(caller), "123");
		this.browsers = browsers;
		if (this.browsers.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./WebDrivers/chromedriver.exe");
			 ChromeOptions options = new ChromeOptions();
			 options.addArguments("disable-infobars");
			 driver = new ChromeDriver(options);
		} else if (this.browsers.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "\\ktvnTest\\WebDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} 
		driver.manage().window().maximize();
		driver.get("http://ktvn-test.s3-website.us-east-1.amazonaws.com/signin");
	}

	private String getTestName(Method caller) {
		Test testAnnotation = caller.getAnnotation(Test.class);
		if (testAnnotation != null) {
			if (!testAnnotation.testName().isEmpty()) {
				return testAnnotation.testName();
			}
		}
		return sTestCaseName;
	}

	@AfterClass
	public synchronized void afterClass() {
		ExtentReporter.currentHTMLReporter().config().setJS("$(document).ready(function() {"
				+ "var list = $('#test-view-charts').find('.block.text-small');\n" + "var left_list_0 = list[0];\n"
				+ "var left_list_1 = list[1];\n" + "var list_span_0 = $(left_list_0).find('span');\n"
				+ "var span_value_1 = parseInt($(list_span_0[0]).text());\n"
				+ "var list_span_1 = $(left_list_1).find('span');\n"
				+ "var span_value_2 = parseInt($(list_span_1[0]).text());\n"
				+ "var span_value_3 = parseInt($(list_span_1[1]).text());\n"
				+ "var total = span_value_1 + span_value_2 +span_value_3;\n"
				+ "$($('.block.text-small')[0]).prepend('<span>'+total+' Total test(s) in " + sEnviromentName + ", "
				+ testsuiteName + "</span></br>');\n" + "$('.test-desc').prepend('<b><div>" + buildVersion
				+ "</div></b>');\n});");
		ExtentReporter.currentExtent().attachReporter(ExtentReporter.currentHTMLReporter());
	}

	@Parameters({ "browsers", "testsuite" })
	@AfterSuite
	protected void afterSuite(@Optional("chrome") String browsers, @Optional("REG") String testsuite) throws Exception {
		ExtentReporter.closeReport();
	}

	/*
	 * Host name and IP
	 */
	public static String getHostName() {
		try {
			inetAddress = InetAddress.getLocalHost();
			return inetAddress.getHostName();
		} catch (UnknownHostException e) {
			return "LocalHost";
		}
	}

	public static String getIPAddress() {
		try {
			inetAddress = InetAddress.getLocalHost();
			return inetAddress.getHostAddress();
		} catch (UnknownHostException e) {
			return "Local Host";
		}
	}

	public void closeBrowser() {
		try {
			driver.close();
			driver.quit();
		} catch (Exception e) {
			System.out.println("Error occurred while closing browser." + e.getStackTrace());
		}
	}
}
