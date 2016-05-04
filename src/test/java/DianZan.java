import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;


public class DianZan {
AndroidDriver<WebElement> driver;

@Before
public void setUp() throws Exception{
	DesiredCapabilities capabilities=new DesiredCapabilities();
	capabilities.setCapability("deviceName", "Coolpad8670");
	capabilities.setCapability("platformVersion", "4.4.2");
	capabilities.setCapability("noReset","ture");
	capabilities.setCapability("appPackage", "com.updrv.lifecalendar");
	capabilities.setCapability("appActivity", ".activity.MainActivity");
	driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
}

	@Test
	public void dianzan() {
		int connectionstate = driver.getNetworkConnection().value;

		if (connectionstate == 1) {
			System.out.println("当前网络状态为飞行模式");
		} else if (connectionstate == 2) {
			System.out.println("当前网络状态为WiFi模式");
		} else if (connectionstate == 4) {
			System.out.println("当前网络状态为移动数据模式");
		} else {
			System.out.println("请检查网络连接");
		}
		driver.findElementById(
				"com.updrv.lifecalendar:id/tv_menu_main_day_life").click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		String s1 = driver.findElementById(
				"com.updrv.lifecalendar:id/usr_tianzan_text").getText();
		int myint = Integer.parseInt(s1);
		driver.findElementById("com.updrv.lifecalendar:id/user_tianzan_imge")
				.click();
		Assert.assertEquals(Integer.parseInt(driver.findElementById(
				"com.updrv.lifecalendar:id/usr_tianzan_text").getText()),
				myint + 1);

		// 取消点赞
		String s2 = driver.findElementById(
				"com.updrv.lifecalendar:id/usr_tianzan_text").getText();
		int myint2 = Integer.parseInt(s2);
		driver.findElementById("com.updrv.lifecalendar:id/user_tianzan_imge")
				.click();
		Assert.assertEquals(Integer.parseInt(driver.findElementById(
				"com.updrv.lifecalendar:id/usr_tianzan_text").getText()),
				myint2 - 1);
		
	}
@After
public void teardown(){
	driver.quit();
}
}
