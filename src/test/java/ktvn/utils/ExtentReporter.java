package ktvn.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	private static ExtentReports extent = null;
	private static IExtentTestReporter testReporter = null;
	private static ExtentHtmlReporter htmlReporter = null;

	public static IExtentTestReporter currentReporter() {
		return testReporter;
	}
	
	public static ExtentHtmlReporter currentHTMLReporter() {
		return htmlReporter;
	}
	
	public static ExtentReports currentExtent() {
		return extent;
	}

	public static void createReporter(String filePath, String browser, String url) {
		extent = createInstance(filePath);
		extent.setSystemInfo("Browser", browser);
		extent.setSystemInfo("Env", url);
	}

	public static void closeReport() {
		extent.flush();
	}

	// this code can be further simplified
	public static IExtentTestReporter getTestReporter() {
		testReporter = new ExtentTestReporter(extent);
		return testReporter;
	}

	public static ExtentReports createInstance(String fileName) {
		htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.setAppendExisting(false);
		htmlReporter.config().setReportName("Jpay Automation Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config()
				.setCSS(".end-time:before {content: 'End Time: ';} "
						+ "\n .start-time:before {content: 'Start Time: '; display: inline-block;}"
						+ "\n .grey.lighten-1:before {content: 'Time taken: '; display: inline-block;}"
						+ "\n .end-time, .category-status > .fail {background: #5a50ef; display: inline-block;}");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		return extent;
	}
}
