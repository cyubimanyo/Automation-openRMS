# Author : Andhiny Fatikha
# Creation Date : 29 Maret 2025

@RegisterPatientsMethod
Feature: Register Patients Features

  @01_RegisterNewPatient
  Scenario: [POSITIVE] Register for a new Patient
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
		And User back to Homepage
		And User click Find Patient Record
		And User search Patient Name after register New Patient
		And User verify registered New Patient name exist at record
		Then User logout from Website openMRS