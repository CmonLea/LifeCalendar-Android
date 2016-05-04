import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;


public class WeatherOverview {

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
	public void weathersurvey() throws Exception {

		driver.findElementById(
				"com.updrv.lifecalendar:id/iv_weather_container_ll").click();
		List<WebElement> weather_list = driver
				.findElementsByXPath("//android.widget.LinearLayout[@resource-id='com.updrv.lifecalendar:id/day_temp']/android.widget.LinearLayout");
		Assert.assertEquals(weather_list.size(), 6);
	
	}
	@After
	public void teardown(){
		driver.quit();
	}
}
