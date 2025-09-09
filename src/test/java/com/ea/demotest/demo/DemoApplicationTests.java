package com.ea.demotest.demo;

import com.ea.demotest.demo.libraries.CsvDataReader;
import com.ea.demotest.demo.pages.CreateEmployeePage;
import com.ea.demotest.demo.pages.EmployeeListPage;
import com.ea.demotest.demo.pages.HomePage;
import com.ea.demotest.demo.pages.LoginPage;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
@SpringBootTest
class DemoApplicationTests {

	private String url = "http://eaapp.somee.com";

	@Autowired
	private Page page;

	@Autowired
	private HomePage homePage;

	@Autowired
    private LoginPage loginPage;

	@Autowired
    private CreateEmployeePage createEmployeePage;

	@Autowired
	private EmployeeListPage employeeListPage;

	@Autowired
	private CsvDataReader csvDataReader;

	private List<Map<String, String>> testData;

	@BeforeEach
	void setUp() {
		// Load test data from CSV
		try {
			testData = csvDataReader.readTestData("src/test/resources/test-data.csv");
			System.out.println("📊 Loaded " + testData.size() + " test data rows from CSV");
		} catch (Exception e) {
			System.out.println("⚠️ Could not load CSV data: " + e.getMessage());
			testData = List.of();
		}
        // Navigate to the application under test
        System.out.println("🚀 Navigating to URL: " + url);
        page.navigate(url);
        page.waitForLoadState();
        System.out.println("✅ Page loaded successfully!");
        
        // Wait a bit so you can see the browser
        page.waitForTimeout(3000);
    }

	@Test
	void createEmployeeTest() {
		System.out.println("🔍 Starting data-driven employee creation test...");
		
		if (testData.isEmpty()) {
			System.out.println("⚠️ No test data available, running with default values");
			runSingleTest("admin", "password", "admin@test.com", "50000", "IT", "8");
		} else {
			for (Map<String, String> data : testData) {
				System.out.println("📝 Processing test case: " + data.get("TestCase"));
				runSingleTest(
					data.get("Username"),
					data.get("Password"), 
					data.get("Email"),
					data.get("Salary"),
					data.get("Department"),
					data.get("DurationWorked")
				);
				System.out.println("✅ Test case " + data.get("TestCase") + " completed!");
			}
		}
		
		System.out.println("✅ All tests completed! Browser will stay open for 5 seconds...");
		page.waitForTimeout(5000);
	}
	
	private void runSingleTest(String username, String password, String email, String salary, String department, String durationWorked) {
		System.out.println("📝 Step 1: Clicking login...");
		homePage.clickLogin();
		page.waitForTimeout(2000);
		
		System.out.println("🔐 Step 2: Performing login...");
		loginPage.performLogin(username, password);
		page.waitForTimeout(2000);
		
		System.out.println("👥 Step 3: Navigating to employee list...");
		homePage.clickEmployeeList();
		page.waitForTimeout(2000);
		
		System.out.println("➕ Step 4: Clicking create new employee...");
		employeeListPage.clickCreateNew();
		page.waitForTimeout(2000);
		
		System.out.println("📋 Step 5: Creating employee...");
		createEmployeePage.createEmployee(username, durationWorked, email, salary, department);
		page.waitForTimeout(2000);
	}

}
