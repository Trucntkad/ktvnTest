package test;

import org.testng.annotations.Test;

import commonPage.basePage;
import pages.KtvnLogin;
import pages.StudentRequest_Approved;

public class StudentRequest_ApprovedTest extends basePage{
	KtvnLogin objLogin;
	StudentRequest_Approved objStudentRequest;
	
	@Test(priority = 0)
	public void testTotalRequestApproved() {

		try {
			// Create Login Page object
			objLogin = new KtvnLogin();
			objLogin.ktvnTestLogin(driver);
			// login to application
			objLogin.loginToKtvnTest(sUsername, sPassword);
			Thread.sleep(4000);
			objStudentRequest = new StudentRequest_Approved();
			objStudentRequest.listRequestApproved(driver);
			//Get total request with status is Approved
			objStudentRequest.getRequestApprovedList();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(" Failed->" + e);
		} finally {
			closeBrowser();
		}
	}
}
