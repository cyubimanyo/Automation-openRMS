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
  
  @02_Login_InvalidUsername
  Scenario Outline: Login with invalid Username and valid Password
  	Given User open Website openMRS
		When User login <username>, <password>
		And User select Location
		And User click Login
		Then User verify text invalid Username or Password

    Examples: 
      | username | password | 
      | apaya    | Admin123 |
  
  @03_Login_InvalidPassword
  Scenario Outline: Login with valid Username and invalid Password
  	Given User open Website openMRS
		When User login <username>, <password>
		And User select Location
		And User click Login
		Then User verify text invalid Username or Password

    Examples: 
      | username | password | 
      | admin    | apayahue |