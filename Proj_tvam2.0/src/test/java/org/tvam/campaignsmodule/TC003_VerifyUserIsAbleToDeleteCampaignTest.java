package org.tvam.campaignsmodule;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import org.tvam.bussinessutility.BaseClass;
import org.tvam.objectrepository.CampaignPage;
import org.tvam.objectrepository.CreateCampaignPage;
import org.tvam.objectrepository.DashboardPage;

@Listeners (org.tvam.genericutility.ListenerUtility.class)

public class TC003_VerifyUserIsAbleToDeleteCampaignTest extends BaseClass {

	@Test (groups = "regression")
	public void tc003_VerifyUserIsAbleToDeleteCampaignTest() throws IOException, InterruptedException {
		
		// read data excel file and store it
		String RANDOMDATA = jUtil.getRandomData();
		String CAMPAIGNNAME = fUtil.readDataFromExcelFile("testdata", 1, 1)+RANDOMDATA;
		String TARGETSIZE = fUtil.readDataFromExcelFile("testdata", 1, 3);

		// create object for DashBoard class
		DashboardPage dashboardPage = new DashboardPage(driver);
		dashboardPage.getCampaignsModule().click();

		// create object for CampaignPage class
		CampaignPage campaignpage = new CampaignPage(driver);
		campaignpage.getCreateCampaignButton().click();

		// create object for CreateCampaignPage class
		CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTextField().sendKeys(CAMPAIGNNAME);
		createCampaignPage.getTargetSizeTextField().sendKeys(TARGETSIZE);
		createCampaignPage.getCreateCampaignButton().submit();
		
		Thread.sleep(500);
		
		//WebDriverUtility - generic utility
		wUtil.toMoveToElement(driver, dashboardPage.getToastMessageText());
		
		String ExpectedResult = "Campaign "+CAMPAIGNNAME+" Successfully Added";
		String ActualResult = dashboardPage.getToastMessageText().getText();
		
		//Assertion
		Assert.assertEquals(ActualResult, ExpectedResult); 
		
		Reporter.log("Execution Completed",true);
		
	}
}








