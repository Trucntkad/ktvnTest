package ktvn.utils;

import java.util.Date;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public interface IExtentTestReporter {
	void log(Status logStatus, String stepName, String details);

	void log(Status logStatus, String details);

	void log(Status logStatus, String stepName, Throwable t);

	void log(Status logStatus, Throwable t);

	void setDescription(String description);

	String getDescription();

	void setStartedTime(Date startedTime);

	Date getStartedTime();

	void setEndedTime(Date endedTime);

	Date getEndedTime();

	String addScreenCapture(String imgPath);

	String addBase64ScreenShot(String base64);

	String addScreencast(String screencastPath);

	ExtentTest assignCategory(String... categories);

	ExtentTest assignAuthor(String... authors);

	ExtentTest appendChild(ExtentTest node);

	Status getRunStatus();

	void startTest(String testName);

	void startTest(String testName, String testDescription);

	void endTest();
}
