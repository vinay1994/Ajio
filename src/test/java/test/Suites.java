package test;
import java.text.ParseException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import main.Base;

public class Suites extends Base {
	WebDriver driver;
	PageObjetcs page;
	@BeforeMethod
	public void bef() {
		driver=Base.starDriver();		
		page=new PageObjetcs(driver);
		driver.get("https://www.ajio.com/");
				
	}

	
	@Test
	public void getNameAndOriginalAnddiscountedPrice() throws InterruptedException {
		page.sendText("jeans");
		page.getNameAndPrice();
	//	Assert.assertEquals("Mid-Wash Skinny Jeans", page.getNameAndPrice());
	}
	
	@Test
	public void getMaxValueOfProductWithName() throws ParseException, InterruptedException {
		page.sendText("jeans");
		page.getMaxOriginalPriceWithProductName();
	//	Assert.assertEquals("Lightly Washed Straight Fit Jeans", page.getMaxOriginalPriceWithProductName());
	}
	
	
	@AfterMethod
	public void aft(){
		driver.quit();
	}
	
}
