import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;


public class TestQuickButton{
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
	public void Shortcuts() throws Exception {
		driver.findElementById("com.updrv.lifecalendar:id/iv_menu_main_middle")
				.click();
		List<WebElement> text = driver
				.findElementsByClassName("android.widget.TextView");
		String str = null;
		ArrayList<String> name = new ArrayList<String>();
		for (int i = 0; i < text.size(); i++) {
			str = text.get(i).getText();
			name.add(str);
		}
		System.out.println(name);

		while (name.contains("记事") && name.contains("纪念日")
				&& name.contains("闹钟") && name.contains("日子")) {

			System.out.println("true");

			break;
		}
	
	}

	@After
	public void teardown() {
		driver.quit();
	}
}
