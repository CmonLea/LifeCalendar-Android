import java.net.URL;
import java.util.List;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;


public class TestFullHalfScreenSwitch {
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
	public void fullhalfscreenswitch(){
		for(int i=0;i<3;i++){
		driver.findElementById("com.updrv.lifecalendar:id/lin_menu_main_personal_account").click();
		driver.findElementById("com.updrv.lifecalendar:id/lin_personal_account_personalise").click();
List <AndroidElement> checklist=driver.findElementsByXPath("//android.widget.LinearLayout[@resource-id='com.updrv.lifecalendar:id/ll_style_select']/android.widget.LinearLayout");

checklist.get((int)Math.random()*2).click();	

driver.findElementById("com.updrv.lifecalendar:id/user_center_back_lin").click();
driver.findElementById("com.updrv.lifecalendar:id/lin_menu_main_calendar").click();
double condition1=driver.findElementById("com.updrv.lifecalendar:id/horizontal_view_group").getSize().getHeight();
double condition2=driver.manage().window().getSize().getHeight();

double condition=  condition1/condition2;
System.out.println(condition);
if(condition<0.5){
System.out.println("设置为半屏日历");
}
else if(condition>0.5){
System.out.println("设置为全屏日历");
}
		}
	
	}

	@After
	public void teardown() {
		driver.quit();
	}
}
