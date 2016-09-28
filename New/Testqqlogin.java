

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Testqqlogin {
	AndroidDriver<AndroidElement> driver;
	@Before
	public void setUp() throws Exception{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "huawei");
		capabilities.setCapability("platformVersion", "4.4");
		// 声明测试程序包名及activity
		capabilities.setCapability("appPackage", "com.updrv.lifecalendar");
		capabilities.setCapability("appActivity", ".activity.MainActivity");
		driver = new AndroidDriver<AndroidElement>(new URL(
				"http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	@Test
	public void qqlogin() throws Exception{
	
	  getQQ();
	 		
	}
	@After
	public void teardown(){
		driver.quit();
	}

	public void getQQ() throws Exception {
		boolean isqqinstalled = driver.isAppInstalled("com.tencent.mobileqq");
		// driver.startActivity("com.tencent.mobileqq",
		// "com.tencent.qqconnect.wtlogin.Login");
		if (isqqinstalled) {
			driver.removeApp("com.tencent.mobileqq");
		}
		driver.findElementById(
				"com.updrv.lifecalendar:id/lin_menu_main_personal_account")
				.click();
		driver.findElement(By.id("com.updrv.lifecalendar:id/account_linear"))
				.click();
		driver.findElement(By.id("com.updrv.lifecalendar:id/qq_login")).click();

		Set<String> contexts = driver.getContextHandles();// 定义一个string类型的set集合来储存APP类型
		// 遍历这个集合
		for (String context : contexts) {
			System.out.println(context); // it will print NATIVE_APP \n
											// WEBVIEW_com.example.testapp
		}
		driver.context("WEBVIEW_com.updrv.lifecalendar");
		// driver.context("NATIVE_APP");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElementByClassName("input_id").sendKeys("1415506275");

		driver.findElementByClassName("input_pwd")
				.sendKeys("1285322614@qq.com");
		driver.findElementById("go").click();
		Thread.sleep(5000);

	}
	

}
