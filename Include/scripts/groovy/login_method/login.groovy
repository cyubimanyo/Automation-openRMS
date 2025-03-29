package login_method
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When



class login {
	
	public static String Location = ''

	@Given("User open Website openMRS")
	def openWebsite() {
		WebUI.openBrowser(GlobalVariable.url)
		WebUI.delay(2)
		WebUI.takeScreenshot()
	}

	@When("User login (.*), (.*)")
	def loginWebsite(String username, String password) {
		TestObject element_username = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//input[@id='username']")
		TestObject element_password = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//input[@id='password']")
		
		WebUI.click(element_username)
		WebUI.setText(element_username, username)
		WebUI.click(element_password)
		WebUI.setText(element_password, password)
		WebUI.delay(1)
		WebUI.takeScreenshot()
	}

	@And("User select Location")
	def selectLocation() {
		TestObject element_location = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//li[@id='${Location}']")
		
		WebUI.click(element_location)
		WebUI.delay(1)
		WebUI.takeScreenshot()
	}
	
	@And("User click Login")
	def clickLogin() {
		TestObject element_login_button = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//input[@id='loginButton']")
		
		WebUI.click(element_login_button)
		WebUI.delay(1)
		WebUI.takeScreenshot()
	}
	
	@And("User verify text at Homepage")
	def verifyTextHomepage() {
		TestObject verify_homepage = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//h4[normalize-space()='Logged in as Super User (admin) at ${Location}.']")
		
		WebUI.verifyElementText(verify_homepage, "Logged in as Super User (admin) at ${Location}.")
		WebUI.delay(1)
		WebUI.takeScreenshot()
	}
	
	@Then("User logout from Website openMRS")
	def logoutWebsite() {
		TestObject element_logout = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//a[normalize-space()='Logout']")
		
		WebUI.click(element_logout)
		WebUI.delay(1)
		WebUI.takeScreenshot()
	}
}