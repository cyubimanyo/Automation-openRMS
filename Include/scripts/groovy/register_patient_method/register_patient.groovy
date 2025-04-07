package register_patient_method
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
import org.openqa.selenium.JavascriptExecutor

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.common.WebUiCommonHelper
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



class register_patient {

	public static String GivenName = ''
	public static String MiddleName = ''
	public static String FamilyName = ''
	public static String Day = ''
	public static String Year = ''
	public static String Address1 = ''
	public static String Address2 = ''
	public static String CityVillage = ''
	public static String StateProvince = ''
	public static String Country = ''
	public static String PostalCode = ''
	public static String RelativesName = ''

	def generateRandomYear() {
		def minYear = 1900
		def maxYear = 2025
		return (minYear + new Random().nextInt(maxYear - minYear + 1)).toString()
	}

	def generateRandomMonth() {
		def months = [
			"January",
			"February",
			"March",
			"April",
			"May",
			"June",
			"July",
			"August",
			"September",
			"October",
			"November",
			"December"
		]
		return months[new Random().nextInt(months.size())]
	}

	def generateRandomRelativesType() {
		def relatives_type = [
			"Doctor",
			"Sibling",
			"Parent",
			"Aunt/Uncle",
			"Supervisor",
			"Patient",
			"Child",
			"Niece/Nephew",
			"Supervisee"
		]
		return relatives_type[new Random().nextInt(relatives_type.size())]
	}

	@And("User click Register a Patient menu")
	def clickRegisterAPatientMenu() {
		TestObject element_rap_menu = new TestObject().addProperty(
				'xpath',
				ConditionType.EQUALS,
				"//a[@id='referenceapplication-registrationapp-registerPatient-homepageLink-referenceapplication-registrationapp-registerPatient-homepageLink-extension']"
				)

		WebUI.click(element_rap_menu)
		WebUI.delay(1)
		WebUI.takeScreenshot()
	}

	@And("User search Patient Name after register New Patient")
	def searchPatientName1() {
		String full_name = "${GivenName} ${MiddleName} ${FamilyName}"
		KeywordUtil.logInfo("Full Name = ${full_name}")

		TestObject element_search_patient = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//input[@id='patient-search']")

		WebUI.click(element_search_patient)
		WebUI.setText(element_search_patient, full_name)
		KeywordUtil.logInfo("Patient Name = ${full_name}")
		WebUI.delay(1)
		WebUI.takeScreenshot()
	}

	@And("User verify registered New Patient name exist at record")
	def verifyRegisteredNewPatientName() {
		String full_name = "${GivenName} ${MiddleName} ${FamilyName}"
		KeywordUtil.logInfo("Full Name = ${full_name}")

		TestObject element_patient_name = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//td[contains(normalize-space(), '${full_name}')]")

		WebUI.takeScreenshot()

		if (WebUI.verifyElementPresent(element_patient_name, 5, FailureHandling.OPTIONAL)) {
			String actualText = WebUI.getText(element_patient_name).trim()
			KeywordUtil.logInfo("🔍 Found text: ${actualText}")

			if (actualText.contains(full_name)) {
				KeywordUtil.logInfo("✅ Patient record found: ${full_name}")
			} else {
				KeywordUtil.logInfo("❌ Patient record text mismatch! Expected: ${full_name}, Found: ${actualText}")
				WebUI.takeScreenshot()
				KeywordUtil.markFailed("Patient record verification failed due to text mismatch.")
			}
		} else {
			KeywordUtil.logInfo("❌ Patient record NOT found: ${full_name}")
			WebUI.takeScreenshot()
			KeywordUtil.markFailed("Patient record verification failed because the element is not present.")
		}

		WebUI.delay(3)
	}

	@And("User back to Homepage")
	def backToHomepage() {
		WebUI.navigateToUrl("https://o2.openmrs.org/openmrs/index.htm")
		WebUI.delay(3)
		WebUI.takeScreenshot()
	}

	@And("User click Next button")
	def clickNextButton() {
		TestObject element_next_button = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//button[@id='next-button']")

		WebUI.click(element_next_button)
		WebUI.delay(1)
		WebUI.takeScreenshot()
	}

	@And("User click Confirm")
	def clickConfirm() {
		TestObject element_confirm_button = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//input[@id='submit']")

		WebUI.delay(1)
		WebUI.takeScreenshot()
		WebUI.click(element_confirm_button)
		WebUI.delay(1)
		WebUI.takeScreenshot()
	}

	@And("User select Gender")
	def selectGender() {
		def genders = ["M", "F"]
		def selectRandomGender = genders[new Random().nextInt(genders.size())]

		TestObject option_gender = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//option[@value='${selectRandomGender}']")

		WebUI.click(option_gender)
		KeywordUtil.logInfo("Selected Gender = ${selectRandomGender}")
		WebUI.delay(1)
		WebUI.takeScreenshot()
	}

	@And("User fill Relatives")
	def fillRelatives() {
		String relativesType = generateRandomRelativesType()

		TestObject element_relatives_type = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//select[@id='relationship_type']")
		TestObject element_person_name = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//input[@placeholder='Person Name']")

		WebUI.selectOptionByLabel(element_relatives_type, relativesType, true)
		KeywordUtil.logInfo("Selected Relatives Type = ${relativesType}")

		WebUI.click(element_person_name)
		WebUI.setText(element_person_name, RelativesName)
		KeywordUtil.logInfo("Relatives Name = ${RelativesName}")

		WebUI.delay(1)
		WebUI.takeScreenshot()
	}

	@And("User fill Contact Info")
	def fillContactInfo() {
		TestObject element_address1 = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//input[@id='address1']")
		TestObject element_address2 = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//input[@id='address2']")
		TestObject element_city_village = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//input[@id='cityVillage']")
		TestObject element_state_province = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//input[@id='stateProvince']")
		TestObject element_country = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//input[@id='country']")
		TestObject element_postal_code = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//input[@id='postalCode']")

		// Fill Address 1
		WebUI.click(element_address1)
		WebUI.setText(element_address1, Address1)
		KeywordUtil.logInfo("Address 1 = ${Address1}")

		// Fill Address 2
		WebUI.click(element_address2)
		WebUI.setText(element_address2, Address2)
		KeywordUtil.logInfo("Address 2 = ${Address2}")

		// Fill City Village
		WebUI.click(element_city_village)
		WebUI.setText(element_city_village, CityVillage)
		KeywordUtil.logInfo("City / Village = ${CityVillage}")

		// Fill State Province
		WebUI.click(element_state_province)
		WebUI.setText(element_state_province, StateProvince)
		KeywordUtil.logInfo("State / Province = ${StateProvince}")

		// Fill Country
		WebUI.click(element_country)
		WebUI.setText(element_country, Country)
		KeywordUtil.logInfo("Country = ${Country}")

		// Fill Postal Code
		WebUI.click(element_postal_code)
		WebUI.setText(element_postal_code, PostalCode)
		KeywordUtil.logInfo("Postal Code = ${PostalCode}")

		WebUI.delay(1)
		WebUI.takeScreenshot()
	}

	@And("User fill Birthdate")
	def fillBirthdate() {
		String estimatedYears = generateRandomYear()
		String estimatedMonths = generateRandomMonth()

		TestObject element_day = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//input[@id='birthdateDay-field']")
		TestObject element_month = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//select[@id='birthdateMonth-field']")
		TestObject element_year = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//input[@id='birthdateYear-field']")
		TestObject element_estimated_years = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//input[@id='birthdateYears-field']")
		TestObject element_estimated_months = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//input[@id='birthdateMonths-field']")

		// Fill Day
		WebUI.click(element_day)
		WebUI.setText(element_day, Day)
		KeywordUtil.logInfo("Day = ${Day}")

		// Check if month dropdown is selectable
		boolean isMonthSelectable = WebUI.verifyElementNotHasAttribute(element_month, 'disabled', 5, FailureHandling.OPTIONAL) &&
				WebUI.getNumberOfTotalOption(element_month) > 1

		if (isMonthSelectable) {
			// Fill Month
			WebUI.selectOptionByLabel(element_month, estimatedMonths, true)
			KeywordUtil.logInfo("Selected Month = ${estimatedMonths}")
		} else {
			KeywordUtil.logInfo("Month selection is not available")
		}

		WebUI.delay(1)
		WebUI.takeScreenshot()

		// Fill Year
		WebUI.click(element_year)
		WebUI.setText(element_year, Year)
		KeywordUtil.logInfo("Year = ${Year}")

		if (Day == null || Year == null || !isMonthSelectable) {
			// Fill Estimated Years
			WebUI.click(element_estimated_years)
			WebUI.setText(element_estimated_years, estimatedYears)
			KeywordUtil.logInfo("Estimated Years = ${estimatedYears}")

			// Fill Estimated Months
			WebUI.click(element_estimated_months)
			WebUI.setText(element_estimated_months, estimatedMonths)
			KeywordUtil.logInfo("Estimated Months = ${estimatedMonths}")

			WebUI.delay(1)
			WebUI.takeScreenshot()
		} else {
			KeywordUtil.logInfo("Day & Year is not null!")
		}
	}

	@And("User fill Name field")
	def fillNameField() {

		List<WebElement> nameFields = WebUiCommonHelper.findWebElements(
				new TestObject().addProperty('xpath', ConditionType.EQUALS, "//input[starts-with(@id, 'fr') and contains(@id, '-field')]"),
				10
				)

		if (nameFields.size() >= 3) {
			// Fill Given Name
			nameFields[0].click()
			nameFields[0].sendKeys(GivenName)
			KeywordUtil.logInfo("Given Name = ${GivenName}")

			// Fill Middle Name
			nameFields[1].click()
			nameFields[1].sendKeys(MiddleName)
			KeywordUtil.logInfo("Middle Name = ${MiddleName}")

			// Fill Family Name
			nameFields[2].click()
			nameFields[2].sendKeys(FamilyName)
			KeywordUtil.logInfo("Family Name = ${FamilyName}")

			WebUI.delay(1)
			WebUI.takeScreenshot()
		} else {
			KeywordUtil.markFailed("Not enough Name fields found! Expected 3, but found ${nameFields.size()}")
		}
	}

}