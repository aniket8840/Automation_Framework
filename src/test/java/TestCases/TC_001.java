package TestCases;

import org.testng.annotations.Test;
import PageObjects.AccountRegistrationPage;
import PageObjects.HomePage;
import TestBase.BaseClass;

public class TC_001 extends BaseClass {

	@Test(groups = { "Regression", "Master" })
	public void verifyAccountRegistration() throws InterruptedException {
		HomePage hp = new HomePage(driver);
		hp.clickOnMyAccount();
		hp.clickOnRegister();

		AccountRegistrationPage rp = new AccountRegistrationPage(driver);
		rp.setFirstName(randomString().toUpperCase());
		rp.setLastName(randomString().toUpperCase());
		rp.setEmail(randomAlphaNumeric() + "@gmail.com");
		rp.setTelephone(randomNumber());

		String password = randomAlphaNumeric();
		rp.setPassword(password);
		rp.setConfirmPassword(password);
		rp.setPrivacyPolicy();
		rp.clickContinueBtn();
		System.out.println(rp.getConfirmationMsg());

	}
}
