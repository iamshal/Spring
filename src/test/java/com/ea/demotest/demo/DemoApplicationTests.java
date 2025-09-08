package com.ea.demotest.demo;

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

	@BeforeEach
	void setUp() {
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
		System.out.println("🔍 Starting employee creation test...");
		
		System.out.println("📝 Step 1: Clicking login...");
		homePage.clickLogin();
		page.waitForTimeout(2000);
		
		System.out.println("🔐 Step 2: Performing login...");
		loginPage.performLogin("admin", "password");
		page.waitForTimeout(2000);
		
		System.out.println("👥 Step 3: Navigating to employee list...");
		homePage.clickEmployeeList();
		page.waitForTimeout(2000);
		
		System.out.println("➕ Step 4: Clicking create new employee...");
		employeeListPage.clickCreateNew();
		page.waitForTimeout(2000);
		
		System.out.println("📋 Step 5: Creating employee...");
		createEmployeePage.createEmployee("AutoUser1", "11111", "autotestuser1@gmail.com", "20000", "Middle");
		
		System.out.println("✅ Test completed! Browser will stay open for 5 seconds...");
		page.waitForTimeout(5000);
	}

}
