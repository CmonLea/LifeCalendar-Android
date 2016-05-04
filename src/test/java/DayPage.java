import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.net.URL;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;


public class DayPage {
	 AndroidDriver<AndroidElement> driver;
		@Before
		public void setUp() throws Exception{
//			File classpathRoot = new File(System.getProperty("user.dir"));
//			File appDir = new File(classpathRoot, "apps");
//			File app = new File(appDir, "LifeCalendar.apk");
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
		public void DaypageElementDispaly(){
		
		int connectionstate=driver.getNetworkConnection().value;
		
		if(connectionstate==1){
			System.out.println("当前网络状态为飞行模式");
		}else if(connectionstate==2){
			System.out.println("当前网络状态为WiFi模式");
		}else if(connectionstate==4){
			System.out.println("当前网络状态为移动数据模式");
		}else{
			System.out.println("请检查网络连接");
		}
		driver.findElementById("com.updrv.lifecalendar:id/tv_menu_main_day_life").click();
		Assert.assertEquals(driver.findElementById("com.updrv.lifecalendar:id/day_title_new").getText(),"最新" );
		Assert.assertEquals(driver.findElementById("com.updrv.lifecalendar:id/day_title_host").getText(),"热门" );
		Assert.assertEquals(driver.findElementById("com.updrv.lifecalendar:id/day_title_special").getText(),"专题");
		
		}
	
		@After
		public void teardown(){
			driver.quit();
		}
}
