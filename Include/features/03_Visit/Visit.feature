# Author : Andhiny Fatikha
# Creation Date : 29 Maret 2025

@VisitMethod
Feature: Visit Features

  @01_MakeAppointment
  Scenario: [POSITIVE] Make Appointment for the first time
    And User click Find Patient Record
		And User search Patient Name
		And User click the Patient Name
		And User click Start Visit
		And User click Actions > Request Appointment         
		And User fill Appointment Type
		And User fill Provider
		And User fill Time Frame
		And User fill Notes
		And User click Save at Request Appointment field
		And User click Appointments tab
#		And User verify Appointments Request
		Then User logout from Website openMRS