Feature: Rabota by test

  @regression
  Scenario: Search for vacancies on the site https://rabota.by/
    Given Open home page
    When I'm confirm with search region
    And I'm enter search data "QA Automation"
    And I'm click search button
    Then I'm go to search page and check search criteria "QA Automation"
    And I'm check search result count more 50