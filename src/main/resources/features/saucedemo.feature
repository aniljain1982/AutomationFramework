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

  Scenario Outline: Login to application with valid user name and password
    Given User is on login page
    When The user logins to app with username as "<username>" and password as "<password>"
    Then The user should login successfully and is brought to the inventory page

    Examples: 
      | username      | password     |
      | standard_user | secret_sauce |
