#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Login
  Test Login functionality

  Background: 
    Given User is on login page

  Scenario Outline: Login to application with valid user name and password
    When The user logins to app with username as "<username>" and password as "<password>"
    Then The user should login successfully and is brought to the product page

    Examples: 
      | username      | password     |
      | standard_user | secret_sauce |

  Scenario Outline: Login to application with valid user name and invalid password
    When The user logins to app with username as "<username>" and password as "<password>"
    Then The user should to get a error message "Epic sadface: Username and password do not match any user in this service"

    Examples: 
      | username      | password |
      | standard_user | invalid  |
