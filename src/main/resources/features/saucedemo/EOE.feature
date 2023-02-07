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
Feature: End to End Ui Flow
  Test EOE functionality

  Background: 
    Given User is on login page
    When The user logins to app with username as "standard_user" and password as "secret_sauce"
    Then The user should login successfully and is brought to the product page

  Scenario Outline: Add one product and checkout
    Given The user adds product "<productName>" to cart
    Then user navigate to cart page
    And user verify that product name as "<productName>", price as "<price>" and quantity as "<quantity>"
    And user navigate to checkout information page
    And user enter the first name as "first_name", last name as "last_name" and postal code as "123456"
    And user navigate to checkout overview page
    And user verify that product name is "Sauce Labs Backpack", price is "$29.99", quantity is "1", card is "SauceCard #31337" , shipping is "FREE PONY EXPRESS DELIVERY!", item total is "Item total: $29.99", tax is "Tax: $2.40" and total is "Total: $32.39"
    And user navigates to checkout complete page

    Examples: 
      | productName         | price  | quantity |
      | Sauce Labs Backpack | $29.99 |        1 |
