package com.automation.listener;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestNGListener implements ITestListener, IReporter, ISuiteListener {
	SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public String OUTPUT_FOLDER = "TEST-OUTPUT" + File.separator;
	public String FILE_NAME = "TestReport";
	public ExtentReports extent;

	@Override
	public void onStart(ISuite suite) {
		Date date = new Date();
		System.out.println("Suite " + suite.getName() + " started  at " + dateFormatter.format(date));

	}

	@Override
	public void onFinish(ISuite suite) {
		Date date = new Date();
		System.out.println("Suite " + suite.getName() + " started  at " + dateFormatter.format(date));
	}

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		init();

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();
			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestNodes(context.getFailedTests(), Status.FAIL);
				buildTestNodes(context.getSkippedTests(), Status.SKIP);
				buildTestNodes(context.getPassedTests(), Status.PASS);
			}
		}

		for (String s : Reporter.getOutput()) {
			extent.setTestRunnerOutput(s);
		}

		extent.flush();

		new File(OUTPUT_FOLDER + FILE_NAME + ".html")
				.renameTo(new File(OUTPUT_FOLDER + FILE_NAME + System.currentTimeMillis() + ".html"));

	}

	private void init() {

		File directory = new File(OUTPUT_FOLDER);
		if (!directory.exists()) {
			directory.mkdir();
		}

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER + FILE_NAME + ".html");

		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.setAppendExisting(true);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setReportUsesManualConfiguration(true);

	}

	private void buildTestNodes(IResultMap tests, Status status) {
		ExtentTest test;
		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.createTest(result.getName().trim()).assignCategory("qTest: " + result.getTestName());

				if (result.getMethod().getDescription() != null)
					test.getModel().setDescription(result.getMethod().getDescription());

				test.getModel().setStartTime(getTime(result.getStartMillis()));
				test.getModel().setEndTime(getTime(result.getEndMillis()));

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				if (result.getStatus() == ITestResult.SKIP) {
					test.log(status, result.getThrowable());
				} else if (result.getStatus() == ITestResult.FAILURE) {
					test.log(status, result.getThrowable());
				} else if (result.getStatus() == ITestResult.SUCCESS) {
					test.log(status, "Test " + status.toString().toLowerCase() + "ed");
				}

				test.getModel().setStartTime(getTime(result.getStartMillis()));
				test.getModel().setEndTime(getTime(result.getEndMillis()));

			}
		}

	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(result.getTestName());
		System.out.println(result.getStartMillis());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			String testName = result.getTestClass().getName() + "." + result.getMethod().getMethodName();
			String throwableStr = "";
			String throwableMessage = "";
			if (result.getThrowable() != null) {
				throwableStr = result.getThrowable().toString();
				throwableMessage = result.getThrowable().getMessage();
			}

			if (throwableStr.contains("Assert")) {
				Reporter.log("<span id =\"" + testName + "\">Assertion Error: <br>"
						+ throwableStr.substring(throwableStr.indexOf(":") + 1, throwableStr.length())
						+ "</br></span>");
			} else if (throwableMessage.contains("Exception:") && throwableMessage.contains("Detailed Trace:")) {
				String error = throwableMessage.substring(0, throwableMessage.indexOf("Detailed Trace:"));

				Reporter.log("<span id =\"" + testName + "\">" + error + "</span><br/>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println();

	}

}
