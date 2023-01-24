package com.automation.ui.cura;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.helper.DateHelper;

public class AppointmentPage extends BasePage {

	public AppointmentPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "combo_facility")
	WebElement comboFacility;

	@FindBy(id = "chk_hospotal_readmission")
	WebElement chkHosReadmission;

	@FindBy(xpath = "//input[@type='radio']")
	List<WebElement> radioHealthCareProgram;

	@FindBy(id = "txt_visit_date")
	WebElement txtVisitDate;

	@FindBy(css = ".datepicker-days")
	WebElement datePickerDays;

	@FindBy(id = "txt_comment")
	WebElement txtComment;

	@FindBy(id = "btn-book-appointment")
	WebElement btnBookAppointment;

	String date;
	public AppointmentPage waitForAppointmentPageLoad() throws Exception {
		webElementHelper.waitTillElementIsVisible(comboFacility);
		return initializeAppointmentPage();
	}

	public AppointmentConfirmationPage makeAnAppointment(String healthCenter, String readmission,
			String healthCareProgram, int appointmentAfterDays, String comment) throws Exception {
		webElementHelper.waitTillElementIsVisible(comboFacility).selectComboByText(comboFacility, healthCenter);

		if (readmission.equalsIgnoreCase("yes")) {
			if (!webElementHelper.checkSelected(chkHosReadmission)) {
				chkHosReadmission.click();
			}
		}
		WebElement radio = webElementHelper.getElementByAttributeValue(radioHealthCareProgram, healthCareProgram)
				.getWebElement();
		radio.click();

		this.date = DateHelper.returnFutureDate("dd/MM/yyyy", appointmentAfterDays);
		webElementHelper.click(txtVisitDate).waitTillElementIsVisible(datePickerDays).sendKeysTo(txtVisitDate, date);

		// List<WebElement> columns=datePickerDays.findElements(By.tagName("td"));
		// WebElement ele=webElementHelper.getElementWithText(columns,
		// DateHelper.getCurrentDayPlus(15)).getWebElement();
		// ele.click();

		webElementHelper.clickAndSendkeys(txtComment, comment);
		btnBookAppointment.click();
		return initializeAppointmentConfirmationPage();
	}
	
	public String getDate() {
		return date;
	}

}
