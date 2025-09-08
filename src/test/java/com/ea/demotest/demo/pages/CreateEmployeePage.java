package com.ea.demotest.demo.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.springframework.stereotype.Component;

@Component
public class CreateEmployeePage {

    private Page page;
    // Define all the web elements and methods for interacting with the CreateEmployeePage
    private Locator txtName, txtDurationWorked, txtEmail, txtSalary, ddlGrade, btnCreate;

    public CreateEmployeePage(Page page) {
        this.page = page;
        txtName = page.locator("#Name");
        txtDurationWorked = page.locator("#DurationWorked");
        txtEmail = page.locator("#Email");
        txtSalary = page.locator("#Salary");
        ddlGrade = page.locator("#Grade");
        btnCreate = page.locator(".btn");
    }

    //Create Employee method
    public void createEmployee(String name, String durationWorked, String email, String salary, String grade) {
        txtName.fill(name);
        txtDurationWorked.fill(durationWorked);
        txtSalary.fill(salary);
        txtEmail.fill(email);
        ddlGrade.selectOption(grade);
        btnCreate.click();
    }
}
