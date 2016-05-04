import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;


public class QuickAlarmClock {
	AndroidDriver<WebElement> driver;

	@Before
	public void setUp() throws Exception{
		DesiredCapabilities capabilities=new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Coolpad8670");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("noReset","ture");
		capabilities.setCapability("appPackage", "com.updrv.lifecalendar");
		capabilities.setCapability("appActivity", ".activity.MainActivity");
		capabilities.setCapability("unicodeKeyboard","ture");
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}

	@Test
	public void quickalarmclock() {
		
		driver.findElementById("com.updrv.lifecalendar:id/iv_menu_main_middle")
				.click();
		driver.findElementById("com.updrv.lifecalendar:id/tv_alarm_clock_ll")
				.click();
		driver.findElementById("com.updrv.lifecalendar:id/repeatLayout")
				.click();
		driver.findElementsByClassName("android.widget.RelativeLayout").get(1).click();
		driver.findElementById("com.updrv.lifecalendar:id/labelLayout").click();
		driver.findElementById("com.updrv.lifecalendar:id/dialog_edit").sendKeys("睡觉时间");
		driver.findElementById("com.updrv.lifecalendar:id/dialog_text2").click();
		driver.findElementById("com.updrv.lifecalendar:id/lay_add_recordthing").click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		if(driver.findElementById("com.updrv.lifecalendar:id/clock_item_name").getText().contains("睡觉时间")){
			 System.out.println("新建闹钟用例执行成功");
		 }else{
			 System.out.println("新建闹钟用例执行失败");
		 }
	}
	
	@After
	public void teardown(){
		driver.quit();
	}
}
