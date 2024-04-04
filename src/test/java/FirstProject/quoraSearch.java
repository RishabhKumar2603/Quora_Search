package FirstProject;

import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

public class quoraSearch {

	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		
		
		// user input for the type of Browser to automate
		System.out.println("Enter the number for the browser you want to open");
		System.out.println("1 for Chrome");
		System.out.println("2 for Edge");
		int a = sc.nextInt();
		System.out.println("Wait, browser is starting");
		
		
		WebDriver driver;
		if(a==1) {
			 driver = new ChromeDriver();
		}
		else {
			 driver = new EdgeDriver();
		}
		// using implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		// Launching the url of the Website
		driver.get("https://www.quora.com/profile/Quora");
		
		// maximizing the window
		driver.manage().window().maximize();
		
		
		//verifying the title whether "Quora" is present or not
		String str= driver.getTitle();
		if(str.equals("Quora"))
		{
			System.out.println("Title is present on the Webpage");
		}else {
			System.out.println("Title is not present on the Webpage");
		}
		//initializing javascript executor variable
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		// printing Credential and highlights
		System.out.println(driver.findElement(By.xpath("//div[@class='q-box qu-mb--medium']")).getText());
		
		//Scrolling
		WebElement frame1 = driver.findElement(By.xpath("//div[@class='q-box']/div[contains(text(),'Knows about')]"));
		new Actions(driver)
				.scrollToElement(frame1)
				.perform();
		Thread.sleep(2000);
		
		// clicking on view more in space section and scrolling
		WebElement viewMore1=driver.findElement(By.xpath("//div[@class='q-box qu-color--gray_dark qu-pr--medium qu-tapHighlight--none qu-display--flex qu-alignItems--center']//div[@class='q-text qu-ellipsis qu-whiteSpace--nowrap'][contains(text(),'View more')]"));
		try {
			while(viewMore1.isDisplayed()) {
				viewMore1.click();
				Thread.sleep(1000);
				js.executeScript("scrollBy(0,500)");
				Thread.sleep(1000);
			}
		}
		catch(Exception e) {
			System.out.println("Element not found");
		}
		//printing in Space section 
        System.out.println(driver.findElement(By.xpath("//div[contains(@class,'q-box qu-pt--small qu-pb--medium')]")).getText());
        
        //scrolling to Know more section 
        WebElement frame2 = driver.findElement(By.xpath("//div[contains(@class,'q-box qu-color--gray_dark qu-tapHighlight--none qu-display--flex qu-alignItems--center')]//div[contains(@class,'q-text qu-ellipsis qu-whiteSpace--nowrap')][normalize-space()='View more']"));
        new Actions(driver)
                .scrollToElement(frame2)
                .perform();
        
     // clicking on view more and scrolling down in Know More
        WebElement viewMore2 = driver.findElement(By.xpath("//div[contains(@class,'q-box qu-color--gray_dark qu-tapHighlight--none qu-display--flex qu-alignItems--center')]//div[contains(@class,'q-text qu-ellipsis qu-whiteSpace--nowrap')][normalize-space()='View more']"));
        try {
        	while(viewMore2.isDisplayed()){
        	viewMore2.click();
        	Thread.sleep(2000);
        	js.executeScript("scrollBy(0,100)");
       	Thread.sleep(2000);
        	}
        }
        catch(Exception e) {}
        
    // printing in Space section 
        System.out.println(driver.findElement(By.xpath("//div[contains(@class,'q-box PageContentsLayout___StyledBox-d2uxks-0')]//div[contains(@class,'q-box qu-mt--small')]")).getText());

    // sign in
        driver.findElement(By.xpath("//div[contains(text(),'Sign')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[contains(text(),'email')]")).click();
        Thread.sleep(1000);
        
        //checking if "NEXT" button is enabled or not
        boolean nextButton = driver.findElement(By.xpath("//button[@tabindex = '13']")).isEnabled();
        if(nextButton) {
        	System.out.println("The \"Next\" button is enabled");
        }else {
            	System.out.println("The \"Next\" button is disabled"); 
        }
        Thread.sleep(1000);
       // entering wrong email
        System.out.println();                                      
        driver.findElement(By.id("email")).sendKeys("abc@abc");
        Thread.sleep(2000);

     //   printing the messege on the console after entering wrong email
        String str2 = driver.findElement(By.xpath("//div[contains(@id,'form-field')]/child:: div")).getText();
        Thread.sleep(1000);
        System.out.println(str2);
        
     //  closing the driver
        driver.quit();

	}
}
		
