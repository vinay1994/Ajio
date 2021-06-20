package test;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import main.Base;

public class PageObjetcs extends Base{
	
	
	@FindBy(xpath="//input[@name='searchVal']")
	WebElement search_Txt;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement submit_Btn;
	@FindBy(xpath="//div[@class='name']")
	List<WebElement> name;
	@FindBy(xpath="//span[@class='price  ']")
	List<WebElement> discountPrice;
	@FindBy(xpath="//span[@class='orginal-price']")
	List<WebElement> originalPrice;
	
 public PageObjetcs(WebDriver driver) {
	 PageFactory.initElements(driver, this);
 }
 
 //***************************************************************
//Constants
//int size=name.size();
  int size=6;
//***************************************************************
 
public void sendText(String s) {
	search_Txt.clear();
	search_Txt.sendKeys(s);
	submit_Btn.click();	
}


public String getNameAndPrice() throws InterruptedException {
String productName=null;
	for(int i=0; i<size; i++) {
		scrollToElement(name.get(i));
		productName=name.get(i).getText();
	System.out.println("Product Name:="+productName+", Original Price:= "+originalPrice.get(i).getText()+", discounted price:="+discountPrice.get(i).getText());
	}
	return productName;
	}
 
 
 public String getMaxOriginalPriceWithProductName() throws ParseException, InterruptedException {
	 int index=0,temp=0;
	 String pPrice=null;
		Map<String,Integer>map=new HashMap<String, Integer>();
		for(int i=0; i<size; i++) {
			scrollToElement(name.get(i));
			index=originalPrice.get(i).getText().indexOf(" ");
			 pPrice=originalPrice.get(i).getText().substring(index+1);
			
			if(pPrice.contains(","))
				temp=formateString(pPrice);
			else
				temp=Integer.parseInt(pPrice);
		      map.put(name.get(i).getText(), temp);
		
		}
		 String productName=getMaxPrice_productName(map);
		 System.out.println("Product Name of highest price :-"+productName+", Highest original Price:="+map.get(productName));
		 return productName;
		}
 
 
 //***************************************************************
 //Generic Methods
 
 
public String getMaxPrice_productName(Map<String, Integer> hmap) {
	double temp=0;
	String pName = null;
	Set<String> it=hmap.keySet();
	for(String s:it) {
		System.out.println(s+":="+hmap.get(s));
		if(temp<hmap.get(s)) {
			temp=hmap.get(s);
			pName=s;
		}	
	}
	
	return pName;
}

public int formateString(String fs) throws ParseException {
	DecimalFormat  format = new DecimalFormat  ("#,###");
	Number aNumber = format.parse(fs);
	return aNumber.intValue();
}

public void scrollToElement(WebElement element) throws InterruptedException {
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);	
}
}
