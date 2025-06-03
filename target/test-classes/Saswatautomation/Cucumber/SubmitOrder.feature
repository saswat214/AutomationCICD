
@tag
Feature: Purchase the order from ecommerce website
  I want to use this template for my feature file
  
  Background: 
  Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <Username> and password <Password>
    When I add product <ProductName> to cart
    And Checkout <ProductName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation page

    Examples: 
      | Username                   | Password       | ProductName  |
      | supremesaswat214@gmailc.om | Anbesivam@123# | ZARA COAT 3  |
      
