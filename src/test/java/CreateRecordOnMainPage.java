import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;


public class CreateRecordOnMainPage {
	AndroidDriver<WebElement> driver;

	@Before
	public void setUp() throws Exception{
		DesiredCapabilities capabilities=new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Coolpad8670");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("noReset","ture");
		capabilities.setCapability("appPackage", "com.updrv.lifecalendar");
		capabilities.setCapability("appActivity", ".activity.MainActivity");
		capabilities.setCapability("unicodeKeyboard","ture");
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}
	@Test
	public void changanxinzengjishi() throws Exception{
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
		
		int x=driver.findElementById("com.updrv.lifecalendar:id/horizontal_view_group").getSize().getHeight()*1/2;
	int y=driver.findElementById("com.updrv.lifecalendar:id/horizontal_view_group").getSize().getWidth()*1/2;
    driver.tap(1, x, y, 1500);
    driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
	driver.findElementById("com.updrv.lifecalendar:id/et_recordthing_title").sendKeys("长按新建记事");
    driver.findElementByName("完成").click();
    driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
    driver.scrollTo("长按新建记事");
    String jishi=driver.findElementById("com.updrv.lifecalendar:id/tt_title_tv").getText();
    if(jishi.equals("长按新建记事")){
    	System.out.println("记事新建成功");
    }else{
    	System.out.println("新建记事失败");
    }
	
	}

	@After
	public void teardown() {
		driver.quit();
	}
}
