@tag
Feature: Error Validation
  I want to use this template for my feature file

  Background: 
  Given I landed on Ecommerce page

  @ErrorValidation
  Scenario Outline: Login page error validation
    Given Logged in with username <Username> and password <Password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | Username                   | Password       |
      | supremesaswat214@gmailc.om | Anbesivam@12 |
