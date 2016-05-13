import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;


public class TestTongzhilan {
   AndroidDriver<AndroidElement> driver;
	@Before
	
	public void setUp() throws Exception{
//		File classpathRoot = new File(System.getProperty("user.dir"));
//		File appDir = new File(classpathRoot, "apps");
//		File app = new File(appDir, "LifeCalendar.apk");
		//声明测试设备信息
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName","Coolpad8670");
		capabilities.setCapability("platformVersion", "4.4");
		//capabilities.setCapability("app", app.getAbsolutePath());
		//capabilities.setCapability("unicodeKeyboard","ture");
		capabilities.setCapability("resetKeyboard","ture");
		capabilities.setCapability("noReset","ture");
		
		//声明测试程序包名及activity
		capabilities.setCapability("appPackage", "com.updrv.lifecalendar");
		capabilities.setCapability("appActivity", ".activity.MainActivity");
		//初始化驱动远程连接地址
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}
	
	@Test
	public void tongzhilan() throws Exception{
		driver.startActivity("com.updrv.lifecalendar", ".activity.syssetting.SystemSettingActivity ");
		AndroidElement notifacation=driver.
				findElementById("com.updrv.lifecalendar:id/notifactionSet");
		notifacation.click();
		String notifacation_weather=driver.
				findElementById("com.updrv.lifecalendar:id/weather_box").getAttribute("checked");
		String notifacation_date=driver.
				findElementById("com.updrv.lifecalendar:id/week_box").getAttribute("checked");

		if(notifacation_weather.equals("true")
				&&notifacation_date.equals("true"))
		{
			
			System.out.println("通知栏天气和日历为开启状态");
			driver.openNotifications();
			Thread.sleep(2000);
			Assert.assertTrue(driver.findElementById("com.updrv.lifecalendar:id/week_name_1").isDisplayed());
			Assert.assertTrue(driver.findElementById("com.updrv.lifecalendar:id/weather_icon").isDisplayed());

		}
	else if(notifacation_weather.equals("true")
				&&notifacation_date.equals("false"))
		{
			
			System.out.println("通知栏天气开启状态，日期未显示在通知栏");
			driver.openNotifications();
			Thread.sleep(2000);
			Assert.assertTrue(driver.findElementById("com.updrv.lifecalendar:id/weather_icon").isDisplayed());
		}
		else if(notifacation_weather.equals("false")
				&&notifacation_date.equals("true"))
		{
			System.out.println("通知栏未显示天气，显示了日期");
			driver.openNotifications();
			Thread.sleep(2000);
			Assert.assertTrue(driver.findElementById("com.updrv.lifecalendar:id/week_name_1").isDisplayed());
			
		}else if(notifacation_weather.equals("false")
				&&notifacation_date.equals("false"))
		{
			System.out.println("通知栏没有显示天气和日期");
		}
		
	}
	@After
	public void teardown(){
		driver.quit();
	}
}
