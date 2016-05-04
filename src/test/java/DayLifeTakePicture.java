import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;


public class DayLifeTakePicture {
	 AndroidDriver<WebElement> driver;
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
		public void daylifetakePicture() throws InterruptedException{
		
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
		driver.findElementById("com.updrv.lifecalendar:id/iv_camera").click();
		
		Assert.assertEquals(driver.findElementById("com.updrv.lifecalendar:id/day_from_album_tv").getText(),"从相册中选择" );
		Assert.assertEquals(driver.findElementById("com.updrv.lifecalendar:id/day_take_picture_tv").getText(),"拍照" );
		Assert.assertEquals(driver.findElementById("com.updrv.lifecalendar:id/day_cancel_tv").getText(),"取消");
		int i=(int)(Math.random()*2);
		switch(i){
		case 0:
			System.out.println("从相册入口发布日子");
			driver.findElementByName("从相册中选择").click();
			List<WebElement> xiangce=driver.findElementsByXPath("//android.widget.GridView/android.widget.LinearLayout");
			xiangce.get(0).click();
			
			List<WebElement> zhaopian=driver.findElementsByXPath("//android.widget.GridView/android.widget.RelativeLayout");
			zhaopian.get(0).click();
			driver.findElementById("com.updrv.lifecalendar:id/btn_sure").click();
			driver.findElementById("com.updrv.lifecalendar:id/media_release_desc").sendKeys("VIPABC");
			driver.findElementById("com.updrv.lifecalendar:id/tv_daylife_post_send").click();
			Thread.sleep(2000);
			String content=driver.findElementById("com.updrv.lifecalendar:id/user_content").getText();
			if(content.equals("VIPABC")){
				System.out.println("日子发布成功");
			}else{
				System.out.println("日子发布失败，请重试");
			}
			driver.findElementByName("我的").click();
			driver.findElementById("com.updrv.lifecalendar:id/lin_personal_account_day_lifte").click();
			driver.findElementById("com.updrv.lifecalendar:id/user_more").click();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			driver.findElementByName("确定").click();
		break;
		
		case 1:
			System.out.println("从拍照入口发布日子");
		driver.findElementByName("拍照").click();
		driver.findElementByAccessibilityId("快门按钮").click();
		driver.findElementByAccessibilityId("确定").click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElementById("com.updrv.lifecalendar:id/media_release_desc").sendKeys("VIPABC");
		driver.findElementById("com.updrv.lifecalendar:id/tv_daylife_post_send").click();
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		String content1=driver.findElementById("com.updrv.lifecalendar:id/user_content").getText();
		if(content1.equals("VIPABC")){
			System.out.println("日子发布成功");
		}else{
			System.out.println("日子发布失败，请重试");
		}
		driver.findElementByName("我的").click();
		driver.findElementById("com.updrv.lifecalendar:id/lin_personal_account_day_lifte").click();
		driver.findElementById("com.updrv.lifecalendar:id/user_more").click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElementByName("确定").click();
	break;
		
		}
	
		}
	
	@After
	public void teardown() {
		driver.quit();
	}
}
