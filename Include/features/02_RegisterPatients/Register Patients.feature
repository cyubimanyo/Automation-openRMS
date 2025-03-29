# Author : Andhiny Fatikha
# Creation Date : 29 Maret 2025

@RegisterPatientsMethod
Feature: Register Patients Features

  @01_RegisterNewPatient
  Scenario: [POSITIVE] Register for a new Patient
		# Register New Patient
		And User click Register a Patient menu
		And User fill Name field
		And User click Next button
		And User select Gender
		And User click Next button
		And User fill Birthdate
		And User click Next button
		And User fill Contact Info
		And User click Next button
		And User click Next button
		And User fill Relatives
		And User click Next button
		And User click Confirm
		Then User logout from Website openMRS