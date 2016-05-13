import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestClickShare {
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

		WebElement fenxiang = driver
				.findElementById("com.updrv.lifecalendar:id/day_share_details");
		fenxiang.click();
		Thread.sleep(1000);

		List<WebElement> qudao = driver
				.findElementsByXPath("//android.widget.GridView/android.widget.LinearLayout");
		int index = (int) (Math.random() * 4);
		qudao.get(index).click();
		if (driver.isAppInstalled("com.tencent.mobileqq") == true && index == 0) {
			System.out.println("分享日子到QQ");
		} else if (driver.isAppInstalled("com.tencent.mobileqq") == false
				&& index == 0) {
			System.out.println("QQ未安装");

		} else if (driver.isAppInstalled("com.tencent.mobileqq") == true
				&& index == 1) {
			System.out.println("分享日子到QQ空间");
		} else if (driver.isAppInstalled("com.tencent.mobileqq") == false
				&& index == 1) {
			System.out.println("QQ未安装");

		} else if (driver.isAppInstalled("com.tencent.mm") == false
				&& index == 2) {
			System.out.println("微信未安装");

		} else if (driver.isAppInstalled("com.tencent.mm") == true
				&& index == 2) {
			System.out.println("分享日子到微信好友");
		} else if (driver.isAppInstalled("com.tencent.mm") == true
				&& index == 3) {
			System.out.println("分享日子到微信朋友圈");
		} else if (driver.isAppInstalled("com.tencent.mm") == false
				&& index == 3) {
			System.out.println("微信未安装");

		}
		
	}
	@After
	public void teardown(){
		driver.quit();
	}
}
