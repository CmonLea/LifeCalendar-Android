import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;


public class QuickAnniversary {
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
	public void quickanniversary() {
		String str = "去吧！皮卡丘！";
		driver.findElementById("com.updrv.lifecalendar:id/iv_menu_main_middle")
				.click();
		driver.findElementById("com.updrv.lifecalendar:id/tv_aniversary_event_ll")
				.click();
		driver.findElementById("com.updrv.lifecalendar:id/et_aniversary_content")
				.sendKeys(str);
		driver.findElementById("com.updrv.lifecalendar:id/tv_aniversary_title_finish")
				.click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		String str1 = driver.findElementById(
				"com.updrv.lifecalendar:id/tv_aniversary_item_title")
				.getText();
//		System.out.println(str1);
		if (str1.contains(str)) {
			System.out.println("在纪念日列表显示结果为：true");
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			driver.findElementById(
					"com.updrv.lifecalendar:id/lin_menu_main_calendar").click();

			if (driver.findElementById("com.updrv.lifecalendar:id/aniversary_item_title")
					.getText().equals(str)) {

				System.out.println("在日历下方显示结果：true");
				System.out.println("用例执行结果为：true");
			} else if(str1.contains(str)== false) {
			
			System.out.println("在纪念日列表显示结果为：false");
		} 
		}
	
	}
	
	@After
	public void teardown() {
		driver.quit();
	}
}
