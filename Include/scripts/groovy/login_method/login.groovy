package login_method
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.configuration.RunConfiguration
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
		WebUI.openBrowser('')
		WebUI.navigateToUrl(GlobalVariable.url)
		WebUI.maximizeWindow()
		WebUI.delay(2)
	
		TestObject element_username = new TestObject('dynamicUsernameField')
		element_username.addProperty('xpath', ConditionType.EQUALS, "//input[@id='username']")
	
		boolean isPageLoaded = WebUI.verifyElementVisible(element_username, FailureHandling.OPTIONAL)
	
		if (isPageLoaded) {
			WebUI.comment("The website is successfully loaded!")
			WebUI.takeScreenshot()
		} else {
			WebUI.comment("The website did not load successfully.")
			WebUI.takeScreenshot()
		}
	}

	@When("User login (.*), (.*)")
	def loginWebsite(String username, String password) {
		TestObject element_username = new TestObject('dynamicUsernameField')
		element_username.addProperty('xpath', ConditionType.EQUALS, "//input[@id='username']")
		
		TestObject element_password = new TestObject('dynamicPasswordField')
		element_password.addProperty('xpath', ConditionType.EQUALS, "//input[@id='password']")
	
		WebUI.click(element_username)
		WebUI.setText(element_username, username)
	
		WebUI.click(element_password)
		WebUI.setText(element_password, password)

		String filledUsername = WebUI.getAttribute(element_username, 'value')
		WebUI.comment("Filled Username = ${filledUsername}")
		String filledPassword = WebUI.getAttribute(element_password, 'value')
		WebUI.comment("Filled Password = ${filledPassword}")
	
		if (filledUsername == username && filledPassword == password) {
			WebUI.comment("Username and Password successfully filled!")
		} else {
			WebUI.comment("Failed to fill Username or Password correctly.")
		}
	
		WebUI.delay(1)
		WebUI.takeScreenshot()
	}

	@And("User select Location")
	def selectLocation() {
		TestObject element_location = new TestObject('dynamicLocation')
		element_location.addProperty('xpath', ConditionType.EQUALS, "//li[@id='${Location}']")
	
		WebUI.click(element_location)
		WebUI.delay(1)
	
		boolean isSelected = WebUI.verifyElementAttributeValue(element_location, 'class', 'selected', 5, FailureHandling.OPTIONAL)
	
		if (isSelected) {
			WebUI.comment("Location '${Location}' selected successfully.")
		} else {
			WebUI.comment("Failed to select location '${Location}'.")
		}
	
		WebUI.takeScreenshot()
	}

	@And("User click Login")
	def clickLogin() {
		TestObject element_login_button = new TestObject('dynamicLoginButton')
		element_login_button.addProperty('xpath', ConditionType.EQUALS, "//input[@id='loginButton']")
	
		WebUI.click(element_login_button)
		WebUI.delay(2)
	
		TestObject dashboard_element = new TestObject('dashboardIdentifier')
		dashboard_element.addProperty('xpath', ConditionType.EQUALS, "//div[@id='navbarSupportedContent']")
	
		boolean isLoginSuccessful = WebUI.verifyElementVisible(dashboard_element, FailureHandling.OPTIONAL)
	
		if (isLoginSuccessful) {
			WebUI.comment("Login Success!")
		} else {
			WebUI.comment("Login Failed — Dashboard not found.")
		}
	
		WebUI.takeScreenshot()
	}

	@And("User verify text at Homepage")
	def verifyTextHomepage() {
		String expectedText = "Logged in as Super User (admin) at ${Location}."
	
		TestObject verify_homepage = new TestObject('dynamicHomepageText')
		verify_homepage.addProperty('xpath', ConditionType.EQUALS, "//h4[normalize-space()='${expectedText}']")
	
		boolean isTextCorrect = WebUI.verifyElementText(verify_homepage, expectedText, FailureHandling.OPTIONAL)
	
		if (isTextCorrect) {
			WebUI.comment("Text is correctly displayed on the Homepage = '${expectedText}'")
		} else {
			WebUI.comment("Expected text not found or incorrect: '${expectedText}'")
		}
	
		WebUI.delay(1)
		WebUI.takeScreenshot()
	}

	@Then("User verify text invalid Username or Password")
	def verifyInvalidUsernameOrPassword() {
		TestObject verify_error_message = new TestObject('invalidLoginMessage')
		verify_error_message.addProperty('xpath', ConditionType.EQUALS, "//div[@id='error-message']")
	
		String expectedError = "Invalid username/password. Please try again."
		
		boolean isErrorDisplayed = WebUI.verifyElementText(verify_error_message, expectedError, FailureHandling.OPTIONAL)
	
		if (isErrorDisplayed) {
			WebUI.comment("Error message correctly displayed: '${expectedError}'")
		} else {
			WebUI.comment("Error message not found or text did not match.")
		}
	
		WebUI.delay(1)
		WebUI.takeScreenshot()
		WebUI.closeBrowser()
	}

	@Then("User logout from Website openMRS")
	def logoutWebsite() {
		TestObject element_logout = new TestObject('logoutLink')
		element_logout.addProperty('xpath', ConditionType.EQUALS, "//a[normalize-space()='Logout']")
	
		TestObject element_username = new TestObject('dynamicUsernameField')
		element_username.addProperty('xpath', ConditionType.EQUALS, "//input[@id='username']")
	
		WebUI.click(element_logout)
		WebUI.delay(2)

		boolean isLoggedOut = WebUI.verifyElementVisible(element_username, FailureHandling.OPTIONAL)
	
		if (isLoggedOut) {
			WebUI.comment("Successfully logged out and returned to login page.")
		} else {
			WebUI.comment("Logout failed — username input not visible.")
		}
	
		WebUI.takeScreenshot()
		WebUI.closeBrowser()
	}
}