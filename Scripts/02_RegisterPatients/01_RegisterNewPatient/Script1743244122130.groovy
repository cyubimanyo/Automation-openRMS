import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable
import register_patient_method.register_patient

import org.openqa.selenium.Keys as Keys

register_patient.GivenName = GivenName
register_patient.MiddleName = MiddleName
register_patient.FamilyName = FamilyName
register_patient.Day = Day
register_patient.Year = Year
register_patient.Address1 = Address1
register_patient.Address2 = Address2
register_patient.CityVillage = CityVillage
register_patient.StateProvince = StateProvince
register_patient.Country = Country
register_patient.PostalCode = PostalCode
register_patient.RelativesName = RelativesName

CucumberKW.runFeatureFileWithTags("Include/features/02_RegisterPatients/Register Patients.feature", "@01_RegisterNewPatient")