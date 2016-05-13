import io.appium.java_client.android.AndroidDriver;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestCommentDayLife {
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
	public void pinglun() throws Exception {
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
		driver.findElementByName("日子").click();
		Thread.sleep(3000);
		int startx = driver.manage().window().getSize().getWidth() * 1 / 2;
		int endx = driver.manage().window().getSize().getWidth() * 1 / 2;
		int starty = driver.manage().window().getSize().getHeight() * 3 / 4;
		int endy = driver.manage().window().getSize().getHeight() * 1 / 4;
		int duration = 300;
		System.out.println("startx=" + startx);
		System.out.println("endx=" + endx);
		System.out.println("starty=" + starty);
		System.out.println("endy=" + endy);
		driver.swipe(startx, starty, endx, endy, duration);

		WebElement pinglun = driver
				.findElementById("com.updrv.lifecalendar:id/day_input_message");
		pinglun.click();
		Thread.sleep(1000);
		// driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		WebElement pinglun1 = driver
				.findElementById("com.updrv.lifecalendar:id/common_msg_input_et");
		pinglun1.click();

		pinglun1.sendKeys("apple00");
		driver.findElementByName("发送").click();
		
	}

	@After
	public void teardown() {
		driver.quit();
	}
}
