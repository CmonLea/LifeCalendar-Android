import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class QuickDayLife {
	AndroidDriver<WebElement> driver;

	@Before
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Coolpad8670");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("noReset", "ture");
		capabilities.setCapability("appPackage", "com.updrv.lifecalendar");
		capabilities.setCapability("appActivity", ".activity.MainActivity");
		capabilities.setCapability("unicodeKeyboard", "ture");
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
	}

	@Test
	public void quickdayLife() {

		driver.findElementById("com.updrv.lifecalendar:id/iv_menu_main_middle")
				.click();
		driver.findElementById("com.updrv.lifecalendar:id/tv_day_life_event_ll")
				.click();
		int index = (int) (Math.random() * 3);
		driver.findElementsByClassName("android.widget.TextView").get(index)
				.click();
		switch (index) {
		case 0:
			System.out.println("从相册发布日子");
			driver.findElementsByXPath(
					"//android.widget.GridView/android.widget.LinearLayout")
					.get(0).click();
			driver.findElementsByXPath(
					"//android.widget.GridView/android.widget.RelativeLayout")
					.get(0).click();
			driver.findElementById("com.updrv.lifecalendar:id/btn_sure")
					.click();
			driver.findElementById(
					"com.updrv.lifecalendar:id/media_release_desc").sendKeys(
					"applause");
			int connectionstate = driver.getNetworkConnection().value;
			if (connectionstate == 1) {
				System.out.println("当前网络状态为飞行模式");
			} else if (connectionstate == 2 || connectionstate == 4) {

				driver.findElementById(
						"com.updrv.lifecalendar:id/ll_daylife_post_send")
						.click();
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				Assert.assertTrue(driver.findElementByName("applause")
						.isDisplayed());
			} else {
				System.out.println("请检查网络连接");
			}
			break;
		case 1:
			System.out.println("拍照发布日子");
			driver.findElementById(
					"com.android.camera:id/camera_shutter_middle_button")
					.click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			driver.findElementById("com.android.camera:id/btn_done").click();
			driver.findElementById(
					"com.updrv.lifecalendar:id/media_release_desc").sendKeys(
					"applause");
			int connectionstate1 = driver.getNetworkConnection().value;
			if (connectionstate1 == 1) {
				System.out.println("当前网络状态为飞行模式");
			} else if (connectionstate1 == 2 || connectionstate1 == 4) {

				driver.findElementById(
						"com.updrv.lifecalendar:id/ll_daylife_post_send")
						.click();
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				Assert.assertTrue(driver.findElementByName("applause")
						.isDisplayed());
			} else {
				System.out.println("请检查网络连接");
			}
			break;
		case 2:
			break;
		}

	}

	@After
	public void teardown() {
		driver.quit();
	}
}
