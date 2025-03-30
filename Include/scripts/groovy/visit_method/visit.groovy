package visit_method
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
import org.openqa.selenium.Keys

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



class visit {
	public static String GivenName = ''
	public static String MiddleName = ''
	public static String FamilyName = ''
	public static String AppointmentType = ''
	public static String Provider = ''
	public static String MinTimeFrameValue = ''
	public static String MaxTimeFrameValue = ''
	
	String selectedTimeFrameUnits = ''
	
	def generateRandomTimeFrameUnits() {
		def time_frame_units = [
			"Day(s)",
			"Week(s)",
			"Month(s)",
			"Year(s)"
		]
		return time_frame_units[new Random().nextInt(time_frame_units.size())]
	}
	
	@And("User click Find Patient Record")
	def clickFindPatientRecord() {
		TestObject element_find_patient_record = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//a[@id='coreapps-activeVisitsHomepageLink-coreapps-activeVisitsHomepageLink-extension']")

		WebUI.click(element_find_patient_record)
		WebUI.delay(1)
		WebUI.takeScreenshot()
	}
	
	@And("User verify Appointments Request")
	def verifyAppointmentsRequest() {
	    String timeFrame = "${MinTimeFrameValue} ${selectedTimeFrameUnits} - ${MaxTimeFrameValue} ${selectedTimeFrameUnits}"
	    
	    TestObject text_service_type = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//td[normalize-space()='${AppointmentType}']")
	    TestObject text_provider = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//td[normalize-space()='${Provider}']")
	    TestObject text_time_frame = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//td[normalize-space()='${timeFrame}']")
		
		WebUI.scrollToElement(text_service_type, 3)
		
	    boolean isServiceTypeCorrect = WebUI.verifyElementText(text_service_type, AppointmentType, FailureHandling.OPTIONAL)
	    boolean isProviderCorrect = WebUI.verifyElementText(text_provider, Provider, FailureHandling.OPTIONAL)
	    boolean isTimeFrameCorrect = WebUI.verifyElementText(text_time_frame, timeFrame, FailureHandling.OPTIONAL)
	
	    if (isServiceTypeCorrect && isProviderCorrect && isTimeFrameCorrect) {
	        KeywordUtil.logInfo("✅ All appointment details are correct:")
	        KeywordUtil.logInfo("Service Type: ${AppointmentType}")
	        KeywordUtil.logInfo("Provider: ${Provider}")
	        KeywordUtil.logInfo("Time Frame: ${timeFrame}")
	    } else {
	        KeywordUtil.logInfo("❌ Appointment verification failed!")
	        
	        if (!isServiceTypeCorrect) {
	            KeywordUtil.logInfo("❌ Service Type mismatch! Expected: '${AppointmentType}', but not found.")
	        }
	        
	        if (!isProviderCorrect) {
	            KeywordUtil.logInfo("❌ Provider mismatch! Expected: '${Provider}', but not found.")
	        }
	
	        if (!isTimeFrameCorrect) {
	            KeywordUtil.logInfo("❌ Time Frame mismatch! Expected: '${timeFrame}', but not found.")
	        }
	
	        WebUI.takeScreenshot()
	        KeywordUtil.markFailed("❌ One or more appointment details are incorrect.")
	    }
	
	    WebUI.delay(1)
	    WebUI.takeScreenshot()
	}
	
	@And("User click Appointments tab")
	def clickAppointmentsTab() {
		TestObject element_appointments_tab = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//a[@id='ui-id-2']")
		
		WebUI.click(element_appointments_tab)
		WebUI.delay(1)
		WebUI.takeScreenshot()
	}
	
	@And("User click Save at Request Appointment field")
	def clickSaveAtRAField() {
		TestObject element_save_button = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//input[@id='save-button']")
		
		WebUI.click(element_save_button)
		WebUI.delay(1)
		WebUI.takeScreenshot()
	}
	
	@And("User fill Time Frame")
	def fillTimeFrame() {
		selectedTimeFrameUnits = generateRandomTimeFrameUnits()
		
		TestObject element_min_timeframe_value = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//input[@id='min-time-frame-value']")
		TestObject element_min_timeframe_units = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//select[@id='min-time-frame-units']")
		TestObject element_max_timeframe_value = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//input[@id='max-time-frame-value']")
		TestObject element_max_timeframe_units = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//select[@id='max-time-frame-units']")
		
		WebUI.waitForElementVisible(element_min_timeframe_units, 5)
		WebUI.waitForElementClickable(element_min_timeframe_units, 5)
		WebUI.waitForElementVisible(element_max_timeframe_units, 5)
		WebUI.waitForElementClickable(element_max_timeframe_units, 5)
		
		// Fill Min Time Frame Value
		WebUI.click(element_min_timeframe_value)
		WebUI.setText(element_min_timeframe_value, MinTimeFrameValue)
		KeywordUtil.logInfo("Min Time Frame Value = ${MinTimeFrameValue}")
		
		// Fill Min Time Frame Units
		WebUI.selectOptionByLabel(element_min_timeframe_units, selectedTimeFrameUnits, false)
		KeywordUtil.logInfo("Selected Min Time Frame Units = ${selectedTimeFrameUnits}")
		
		// Fill Max Time Frame Value
		WebUI.click(element_max_timeframe_value)
		WebUI.setText(element_max_timeframe_value, MaxTimeFrameValue)
		KeywordUtil.logInfo("Max Time Frame Value = ${MaxTimeFrameValue}")
		
		// Fill Max Time Frame Units
		WebUI.selectOptionByLabel(element_max_timeframe_units, selectedTimeFrameUnits, false)
		KeywordUtil.logInfo("Selected Max Time Frame Units = ${selectedTimeFrameUnits}")
		
		WebUI.delay(1)
		WebUI.takeScreenshot()
	}
	
	@And("User fill Notes")
	def fillNotes() {
		String value_notes = "AutoTest for Patient = ${GivenName} ${MiddleName} ${FamilyName}"
		
		TestObject element_notes = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//textarea[@id='notes']")
		
		WebUI.click(element_notes)
		WebUI.setText(element_notes, value_notes)
		KeywordUtil.logInfo("Notes = ${value_notes}")
		WebUI.delay(1)
		WebUI.takeScreenshot()
	}
	
	@And("User fill Provider")
	def fillProvider() {
		TestObject element_provider = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//input[@id='provider']")
		
		WebUI.click(element_provider)
		WebUI.setText(element_provider, Provider)
		KeywordUtil.logInfo("Provider = ${Provider}")
		WebUI.sendKeys(element_provider, Keys.chord(Keys.ENTER))
		WebUI.delay(1)
		WebUI.takeScreenshot()
	}
	
	@And("User fill Appointment Type")
	def fillAppointmentType() {
		TestObject element_appointment_type = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//input[@id='appointment-type']")
		
		WebUI.click(element_appointment_type)
		WebUI.setText(element_appointment_type, AppointmentType)
		KeywordUtil.logInfo("Appointment Type = ${AppointmentType}")
		WebUI.sendKeys(element_appointment_type, Keys.chord(Keys.ENTER))
		WebUI.delay(1)
		WebUI.takeScreenshot()
	}
	
	@And("User click Actions > Request Appointment")
	def clickRequestAppointment() {
		TestObject element_action_dropdown = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//span[@class='d-none d-sm-none d-md-inline d-lg-inline']")
		TestObject element_request_appointment = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//a[normalize-space()='Request Appointment']")
		
		WebUI.takeScreenshot()
		WebUI.click(element_action_dropdown)
		WebUI.delay(1)
		WebUI.takeScreenshot()
		WebUI.click(element_request_appointment)
		WebUI.delay(1)
		WebUI.takeScreenshot()
	}
	
	@And("User click Start Visit")
	def clickStartVisit() {
		TestObject element_start_visit = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//div[contains(text(),'Start Visit')]")
		TestObject element_confirm_button = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//button[@id='start-visit-with-visittype-confirm']")
		
		WebUI.click(element_start_visit)
		WebUI.delay(1)
		WebUI.takeScreenshot()
		WebUI.click(element_confirm_button)
		WebUI.delay(3)
		WebUI.takeScreenshot()
	}
	
	@And("User search existed Patient Name")
	def searchPatientName2() {
		
		TestObject element_search_patient = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//input[@id='patient-search']")

		WebUI.click(element_search_patient)
		WebUI.setText(element_search_patient, GivenName)
		KeywordUtil.logInfo("Patient Name = ${GivenName}")
		WebUI.delay(1)
		WebUI.takeScreenshot()
	}
	
	@And("User click the Patient Name")
	def clickPatientName() {
		TestObject element_patient_name = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//td[normalize-space()='${GivenName} ${MiddleName} ${FamilyName}']")
		
		WebUI.takeScreenshot()
		WebUI.click(element_patient_name)
		WebUI.delay(3)
		WebUI.takeScreenshot()
	}
}