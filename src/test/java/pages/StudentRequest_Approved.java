package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StudentRequest_Approved {
	
	@FindBy(xpath="//table//tr/td[2]/div/p[text()='Approved']")
	public List<WebElement> requestApproved;
	
	@FindBy(xpath="//div[@id='root']/div//table/tbody/tr/td[5]")
	public List<WebElement> studentID;
	
	@FindBy(xpath="//div[@id='root']/div//table/tbody/tr/td[6]")
	public List<WebElement> studentFirstName;
	
	@FindBy(xpath="//div[@id='root']/div//table/tbody/tr/td[7]")
	public List<WebElement> studentLastName;
	
	public void listRequestApproved (WebDriver driver) {
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
	}
	
	public void getRequestApprovedList() {
		List<String> requestApprovedList = new ArrayList<>();
		List<String> studentIDList = new ArrayList<>();
		System.out.println("The total of request with Approved status is " + requestApproved.size());
		for (int i=0; i<requestApproved.size(); i++)
		{
		String sApproved = requestApproved.get(i).getText();
		requestApprovedList.add(sApproved);
		String sStudentID = studentID.get(i).getText();
		studentIDList.add(sStudentID);
		System.out.println("Status" + sApproved +" Student ID " + sStudentID);
		}
	}
}
