import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;


public class BackTodayN {
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
		String date_before = driver.findElementById(
				"com.updrv.lifecalendar:id/txt_main_menu_solar_date_time")
				.getText();
		int i = (int) (Math.random() * 2);
		if (i == 0) {
			System.out.println("向右滑动");
			int startx = driver
					.findElementById(
							"com.updrv.lifecalendar:id/horizontal_view_group")
					.getSize().getWidth() * 2 / 3;
			int endx = driver
					.findElementById(
							"com.updrv.lifecalendar:id/horizontal_view_group")
					.getSize().getWidth() * 5 / 6;
			int starty = driver
					.findElementById(
							"com.updrv.lifecalendar:id/horizontal_view_group")
					.getSize().getHeight() * 1 / 2;
			int endy = driver
					.findElementById(
							"com.updrv.lifecalendar:id/horizontal_view_group")
					.getSize().getHeight() * 1 / 2;
			int duration = 500;

			driver.swipe(startx, starty, endx, endy, duration);
			Thread.sleep(2000);

		} else if (i == 1) {
			System.out.println("向左滑动");
			int startx = driver
					.findElementById(
							"com.updrv.lifecalendar:id/horizontal_view_group")
					.getSize().getWidth() * 5 / 6;
			int endx = driver
					.findElementById(
							"com.updrv.lifecalendar:id/horizontal_view_group")
					.getSize().getWidth() * 2 / 3;
			int starty = driver
					.findElementById(
							"com.updrv.lifecalendar:id/horizontal_view_group")
					.getSize().getHeight() * 1 / 2;
			int endy = driver
					.findElementById(
							"com.updrv.lifecalendar:id/horizontal_view_group")
					.getSize().getHeight() * 1 / 2;
			int duration = 500;

			driver.swipe(startx, starty, endx, endy, duration);
			Thread.sleep(2000);
		}

		driver.findElementById("com.updrv.lifecalendar:id/iv_main_menu_today")
				.click();
		String date_after = driver.findElementById(
				"com.updrv.lifecalendar:id/txt_main_menu_solar_date_time")
				.getText();
		Assert.assertEquals(date_after, date_before);
		
	}
	@After
	public void teardown(){
		driver.quit();
	}
}
