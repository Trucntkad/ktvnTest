package test;

import org.testng.annotations.Test;

import commonPage.basePage;
import pages.KtvnLogin;
import ktvn.utils.Logger;

public class KtvnLoginTest extends basePage{

	KtvnLogin objLogin;
	
	@Test(priority = 0)
	public void testKtvnLogin() {

		try {
			// Create Login Page object
			objLogin = new KtvnLogin();
			objLogin.ktvnTestLogin(driver);
			// login to application
			objLogin.loginToKtvnTest(sUsername, sPassword);
			Thread.sleep(1000);

			// Verify login 
			String titleParentPortal = objLogin.getParentPortalTitle();
			//Assert.assertTrue(titleParentPortal.contains("Parent Portal") );
			Logger.assertExtentContains(titleParentPortal,"Parent Portal");
			System.out.print("Login successfully");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(" Failed->" + e);

		} finally {
			closeBrowser();
		}
	}
}
