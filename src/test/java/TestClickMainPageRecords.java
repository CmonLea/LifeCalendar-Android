import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;


public class TestClickMainPageRecords {
	AndroidDriver<WebElement> driver;

	@Before
	public void setUp() throws Exception{
		DesiredCapabilities capabilities=new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Coolpad8670");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("noReset","false");
		capabilities.setCapability("appPackage", "com.updrv.lifecalendar");
		capabilities.setCapability("appActivity", ".activity.MainActivity");
		capabilities.setCapability("unicodeKeyboard","ture");
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}

	@Test
	public void xuanzejishi() throws Exception {
	
		driver.findElementById("com.updrv.lifecalendar:id/lin_menu_main_recordthing").click();
        driver.findElementById("com.updrv.lifecalendar:id/rl_record_add").click();
		driver.findElementById("com.updrv.lifecalendar:id/et_recordthing_title").sendKeys("记事日测试");;
        driver.findElementById("com.updrv.lifecalendar:id/txt_title_finish").click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElementById("com.updrv.lifecalendar:id/lin_menu_main_calendar").click();
        int startx, starty, endx, endy, duration;
		startx = driver.manage().window().getSize().getWidth() * 1/2;
		endx = driver.manage().window().getSize().getWidth() * 1/2;
		starty = driver.manage().window().getSize().getHeight() * 3/5;
		endy = driver.manage().window().getSize().getHeight() * 2/5;
        duration=1500;
		driver.swipe(startx, starty, endx, endy, duration);

	    List <WebElement> note_list=driver.
	    		findElementsByXPath("//android.widget.ListView[@resource-id='com.updrv.lifecalendar:id/calendar_fragment_ll']/android.widget.LinearLayout");
		//System.out.println("note_list="+note_list.size());
		int index=(int)(Math.random()*note_list.size());
		//System.out.println("index="+index);
		note_list.get(index).click();
		Assert.assertEquals("记事详情", driver.findElementById("com.updrv.lifecalendar:id/txt_detail_title_name").getText());
	
	}
	@After
	public void teardown(){
		driver.quit();
	}
}
