import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestCreateNotes {

	AndroidDriver<WebElement> driver;

	@Before
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Coolpad8670");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("noReset", "ture");
		capabilities.setCapability("appPackage", "com.updrv.lifecalendar");
		capabilities.setCapability("appActivity",
				".activity.recordthing.RecordThingDetailsNewActivity");
		capabilities.setCapability("unicodeKeyboard", "ture");
		capabilities.setCapability("resetKeyboard", "ture");
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
	}

	@Test
	public void createNotes() {
		for (int i = 1; i <= 5; i++) {
			driver.findElementByClassName("android.widget.EditText").sendKeys(
					"记事测试" + i);// 输入记事标题

			Set<String> contextNames = driver.getContextHandles();

			driver.context((String) contextNames.toArray()[1]); // set context
																// to WEBVIEW_1

			driver.findElementById("myEditor").click();
			driver.findElementByClassName("edui-body-container").sendKeys(
					"记事内容" + i);// 输入记事内容
			driver.context("NATIVE_APP");// 切换到Native_app
			driver.findElementById("com.updrv.lifecalendar:id/common_top_next")
					.click();
			driver.startActivity("com.updrv.lifecalendar",
					".activity.recordthing.RecordThingDetailsNewActivity");
		}

	}

	@After
	public void teardown() {
		driver.quit();
	}

}
