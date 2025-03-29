# Author : Andhiny Fatikha
# Creation Date : 29 Maret 2025

@LoginMethod
Feature: Login Features

  @01_Login_ValidData
  Scenario Outline: Login with valid Username and Password
    Given User open Website openMRS
		When User login <username>, <password>
		And User select Location
		And User click Login
		And User verify text at Homepage
		Then User logout from Website openMRS

    Examples: 
      | username | password | 
      | admin    | Admin123 | 