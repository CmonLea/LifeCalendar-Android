import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;


public class TestBackTodayM {
	AndroidDriver<WebElement> driver;

	@Before
	public void setUp() throws Exception{
		DesiredCapabilities capabilities=new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Coolpad8670");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("noReset","ture");
		capabilities.setCapability("appPackage", "com.updrv.lifecalendar");
		capabilities.setCapability("appActivity", ".activity.MainActivity");
		//capabilities.setCapability("unicodeKeyboard","ture");
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}

	@Test
	public void backtotoday() throws Exception {
		for(int i=1;i<=5;i++){
		int x = (int)(driver.manage().window().getSize().getWidth()
				*  Math.random() * 5 / 6+1/6);
		int y = (int)(driver.manage().window().getSize().getHeight()
				*(Math.random() * 0.27 + 0.28));
		
		String date_before = driver.findElementById(
				"com.updrv.lifecalendar:id/txt_main_menu_solar_date_time")
				.getText();
		driver.tap(1, x, y, 300);

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElementById("com.updrv.lifecalendar:id/iv_main_menu_today")
				.click();
		String date_after = driver.findElementById(
				"com.updrv.lifecalendar:id/txt_main_menu_solar_date_time")
				.getText();
		Assert.assertEquals(date_after, date_before);
		}
	
	}
	@After
	public void teardown(){
		driver.quit();
	}
}
