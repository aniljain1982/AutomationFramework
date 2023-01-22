package com.automation.ui.cura;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AppointmentConfirmationPage extends BasePage {

	public AppointmentConfirmationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[text()='Go to Homepage']")
	WebElement btnHomePage;

	@FindBy(id = "facility")
	WebElement facility;

	@FindBy(id = "hospital_readmission")
	WebElement hospitalReadmission;

	@FindBy(id = "program")
	WebElement program;

	@FindBy(id = "visit_date")
	WebElement visitDate;

	@FindBy(id = "comment")
	WebElement comment;

	public AppointmentConfirmationPage waitForAppointmentConfirmationPageLoad() throws Exception {
		webElementHelper.waitTillElementIsVisible(btnHomePage);
		return initializeAppointmentConfirmationPage();
	}

	public String getFacility() {
		return facility.getText();
	}

	public String getHospitalReadmission() {
		return hospitalReadmission.getText();
	}

	public String getProgram() {
		return program.getText();
	}

	public String getVisitDate() {
		return visitDate.getText();
	}

	public String getComment() {
		return comment.getText();
	}

	public HomePage goToHomePage() throws Exception {
		webElementHelper.waitTillElementIsClickable(btnHomePage).click(btnHomePage);
		return initializeHomePage();
	}
}
