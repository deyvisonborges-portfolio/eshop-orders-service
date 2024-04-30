Feature: Order Management

  Scenario: *** Create a New Order ***

    Given Order status is "CREATED"
    And Order items are empty
    And Customer ID is provided
    And Payment IDs are empty
    And Subtotal is "0.00 BRL"
    And Shipping fee is "0.00 BRL"
    And Discount is "0.00 BRL"
    And Total is "0.00 BRL"

    When Order is created
    Then Order ID is generated
    And Order is active
    And Created at and Updated at timestamps are set

  Scenario: *** Create an Order from Existing Data ***

    Given Order status is provided
    And Order items are provided
    And Customer ID is provided
    And Payment IDs are provided
    And Subtotal is provided
    And Shipping fee is provided
    And Discount is provided
    And Total is provided

    When Order is created with existing data
    Then Order ID is set
    And Order is active
    And Created at and Updated at timestamps are set accordingly

  Scenario: *** Clone an Existing Order ***

    Given an existing Order
    When Order is cloned
    Then a new Order with identical data is created

  Scenario: *** Update Order Status ***

    Given an existing Order
    And a new Order status is provided
    When Order status is updated
    Then Order status is changed accordingly

  Scenario: *** Update Order Items ***

    Given an existing Order
    And new Order items are provided
    When Order items are updated
    Then Order items are replaced with the new ones

  Scenario: *** Update Customer ID ***

    Given an existing Order
    And a new Customer ID is provided
    When Customer ID is updated
    Then Order's Customer ID is changed accordingly

  Scenario: *** Update Payment IDs ***

    Given an existing Order
    And new Payment IDs are provided
    When Payment IDs are updated
    Then Order's Payment IDs are replaced with the new ones

  Scenario: *** Update Subtotal ***

    Given an existing Order
    And a new Subtotal is provided
    When Subtotal is updated
    Then Order's Subtotal is changed accordingly

  Scenario: *** Update Shipping Fee ***

    Given an existing Order
    And a new Shipping fee is provided
    When Shipping fee is updated
    Then Order's Shipping fee is changed accordingly

  Scenario: *** Update Discount ***

    Given an existing Order
    And a new Discount is provided
    When Discount is updated
    Then Order's Discount is changed accordingly

  Scenario: *** Update Total ***

    Given an existing Order
    And a new Total is provided
    When Total is updated
    Then Order's Total is changed accordingly

  Scenario: *** Set Order Inactive ***

    Given an existing Order
    When Order is set to inactive
    Then Order becomes inactive
