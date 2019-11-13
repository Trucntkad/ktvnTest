package ktvn.utils;

import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class Logger {
	/**
	 * Logging and report
	 */

	static boolean functionalFail = false;
	public static String currentLoggedStep = "";

	public static void logInfo(String details) {
		log(Status.INFO, details);
	}

	public static void logPass(String details) {
		log(Status.PASS, details);
	}

	public static void logFail(String details) {
		log(Status.FAIL, details + logScreenshot());
	}

	public static void logFunctionalFail(String details) {
		log(Status.FAIL, details+logScreenshot());
	}

	public static void logSkip(String details) {
		log(Status.SKIP, details);
	}

	public static void log(String details) {
		log(Status.PASS, details);
	}

	public static void logExceptionFail(String details) {
		log(Status.FAIL, details+logScreenshot());
		Assert.fail(details);
	}

	public static void log(Status status, String details) {
		ExtentTestReporter.testReporters.get().log(status, details);
	}

	public static void logInfo(Boolean condition, String message, String bugId) throws Exception {
		if (condition == false) {
			System.out.println(currentLoggedStep);
			ExtentTestReporter.testReporters.get().log(Status.INFO,
					String.format(
							"%s (%s; request by Joe following email \"Selenium - Failed Functionality TCs, Nov-9\")",
							message, bugId));
		}
	}
	
	public static String logScreenshot() {
		return "";
	}
	
/*
	public static String logScreenshot() {
		String screenShotPath ="";
		try {
			String path = WebDrivers.captureScreenshot(UUID.randomUUID().toString(), "screenshots");
			path = GDriveUltils.uploadScreenshotToGD(path);
			String gdrivePath = "https://drive.google.com/uc?id=%s";
			// ExtentTestReporter.testReporters.get().addScreenCaptureFromPath(String.format(gdrivePath,
			// path));
			screenShotPath = "<br/><a href=\"" + String.format(gdrivePath, path) + "\" target=\"_blank\"><img src=\""
					+ String.format(gdrivePath, path) + "\" style=\"width: 160px;height: 90px;\"/></a>";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return screenShotPath;
	}
*/
	/**
	 * Assertion section
	 */

	public static void assertExtentEquals(Object actual, Object expected) throws Exception {
		if (!(actual.toString().equals(expected.toString())))
			logFail("Actual is : \"" + actual.toString() + "\" - Expected is \"" + expected.toString() + "\"");
	}

	public static void assertExtentEquals(Object s1, Object s2, String message) throws Exception {
		if (!(s1.toString().equals(s2.toString())))
			logFail("Actual is : \"" + s1.toString().trim() + "\" - Expected is \"" + s2.toString().trim()+"\" " + message);
	}

	public static void assertExtentContains(String actual, String expected) throws Exception {
		if (!actual.toString().contains(expected.toString()) && !expected.toString().contains(actual.toString()))
			logFail("\""+actual.toString().trim() + "\" is NOT contains \"" + expected.toString().trim()+"\"");
	}

	public static void assertExtentContains(String actual, String expected, String message) throws Exception {
		if (!actual.toString().contains(expected.toString()) && !expected.toString().contains(actual.toString()))
			logFail("\"" + actual.toString().trim() + "\" is NOT contains \"" + expected.toString().trim()+"\" " + message);
	}

	public static void assertExtentNotEquals(Object actual, Object expected, String message) throws Exception {
		if (actual.toString().equals(expected.toString()))
			logFail("\""+ actual.toString().trim() + "\" still equal \"" + expected.toString().trim()+"\" " + message);
	}

	public static void assertExtentNotEquals(Object actual, Object expected) throws Exception {
		if (actual.toString().equals(expected.toString()))
			logFail("\"" + actual.toString().trim() + "\" still equal \"" + expected.toString().trim() + "\"");
	}

	public static void assertExtentTrue(Boolean b) throws Exception {
		if (b == false)
			logFail("Result is NOT TRUE ");
	}

	/*
	 * @author Bruce.ThuyDo
	 *
	 */
	public static void assertExtentTrue(Boolean b, String sMessage) throws Exception {
		if (b == false)
			logFail(sMessage);
	}

	public static void assertExtentFalse(Boolean b) throws Exception {
		if (b == true)
			logFail("Result is NOT FALSE ");
	}

	/*
	 * @author Bruce.ThuyDo
	 *
	 */
	public static void assertExtentFalse(Boolean b, String sMessage) throws Exception {
		if (b == true)
			logFail(sMessage);
	}

	public static void extentEqual(Object s1, Object s2) throws Exception {
		functionalFail = false;
		if (!(s1.equals(s2))) {
			logFail(s1.toString() + " isn't equal " + s2.toString());
			functionalFail = true;
		}
	}
}
