package ktvn.utils;

import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentTestReporter implements IExtentTestReporter{
	public static ThreadLocal<ExtentTest> testReporters = new ThreadLocal<>();
	private ExtentReports reporter;

	public ExtentTestReporter(ExtentReports reporter) {
		this.reporter = reporter;
	}

	public void startTest(String testName) {
		testReporters.set(reporter.createTest(testName));
	}

	public void startTest(String testName, String testDescription) {
		testReporters.set(reporter.createTest(testName, testDescription));
	}

	public void endTest() {
		reporter.flush();
	}

	@Override
	public void log(Status logStatus, String details) {
		getTestReporter().log(logStatus, details);
	}

	@Override
	public void log(Status logStatus, Throwable t) {
		getTestReporter().log(logStatus, t);
	}

	@Override
	public ExtentTest assignCategory(String... categories) {
		return getTestReporter().assignCategory(categories);
	}

	@Override
	public ExtentTest assignAuthor(String... authors) {
		return getTestReporter().assignAuthor(authors);
	}

	@Override
	public Status getRunStatus() {
		return getTestReporter().getStatus();
	}

	private ExtentTest getTestReporter() {
		return testReporters.get();
	}

	@Override
	public void log(Status logStatus, String stepName, String details) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void log(Status logStatus, String stepName, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDescription(String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStartedTime(Date startedTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getStartedTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEndedTime(Date endedTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getEndedTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addScreenCapture(String imgPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addBase64ScreenShot(String base64) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addScreencast(String screencastPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExtentTest appendChild(ExtentTest node) {
		// TODO Auto-generated method stub
		return null;
	}
}
