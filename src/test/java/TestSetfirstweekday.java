import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestSetfirstweekday {

	AppiumDriver<WebElement> driver;

	@Before
	public void setUp() throws Exception {
		// File classpathRoot = new File(System.getProperty("user.dir"));
		// File appDir = new File(classpathRoot, "apps");
		// File app = new File(appDir, "LifeCalendar.apk");
		// 声明测试设备信息
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "honami");
		capabilities.setCapability("platformVersion", "4.4");
		// capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("unicodeKeyboard", "ture");
		capabilities.setCapability("resetKeyboard", "ture");
		capabilities.setCapability("noReset", "ture");

		// 声明测试程序包名及activity
		capabilities.setCapability("appPackage", "com.updrv.lifecalendar");
		capabilities.setCapability("appActivity", ".activity.MainActivity");
		// 初始化驱动远程连接地址
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
	}

	@After
	public void teardown() {
		driver.quit();
	}

	@Test
	public void setfristweekday() {
		// for(int i=0;i<=2;i++){

		List<WebElement> week_day_before = driver
				.findElementsByXPath("//android.widget.LinearLayout[@resource-id='com.updrv.lifecalendar:id/week_linearlayout']/android.widget.TextView");
		System.out.println("设置前的周首日为：" + week_day_before.get(0).getText());
		WebElement wode = driver.findElementByName("我的");
		wode.click();

		WebElement setting = driver
				.findElementById("com.updrv.lifecalendar:id/lin_personal_account_setting");
		setting.click();

		WebElement sundaybox = driver
				.findElementById("com.updrv.lifecalendar:id/sundaybox");

		sundaybox.click();

		WebElement back = driver
				.findElementById("com.updrv.lifecalendar:id/lay_back");
		back.click();

		WebElement main_page = driver
				.findElementById("com.updrv.lifecalendar:id/lin_menu_main_calendar");
		main_page.click();

		// 检测日历主界面周首日
		List<WebElement> week_day = driver
				.findElementsByXPath("//android.widget.LinearLayout[@resource-id='com.updrv.lifecalendar:id/week_linearlayout']/android.widget.TextView");

		if (week_day.get(0).getText().contains("周日")) {
			System.out.println("设置后的周首日为周日");
		} else if (week_day.get(0).getText().contains("周一")) {
			System.out.println("设置后的周首日为周一");
		}
		
	}
}
