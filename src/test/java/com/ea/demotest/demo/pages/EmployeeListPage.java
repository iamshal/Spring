package com.ea.demotest.demo.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.springframework.stereotype.Component;

@Component
public class EmployeeListPage {

    private Locator btnCreateNew;

    public EmployeeListPage(Page page) {
        btnCreateNew = page.locator("text=Create New");
    }

    public void clickCreateNew(){
        btnCreateNew.click();
    }
}
