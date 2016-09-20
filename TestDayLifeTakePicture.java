import static org.junit.Assert.*;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;


public class TestDayLifeTakePicture {
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
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}

	@Test
	public void testDayLifeTakePicture(){
		// 点击主界面“日子”模块
		try {
			driver.findElementById(
					"com.updrv.lifecalendar:id/tv_menu_main_day_life").click();
			// 点击右上角相机图标
			driver.findElementById("com.updrv.lifecalendar:id/iv_camera").click();

			Assert.assertEquals(
					driver.findElementById(
							"com.updrv.lifecalendar:id/day_from_album_tv")
							.getText(), "从相册中选择");
			Assert.assertEquals(
					driver.findElementById(
							"com.updrv.lifecalendar:id/day_take_picture_tv")
							.getText(), "拍照");
			Assert.assertEquals(
					driver.findElementById(
							"com.updrv.lifecalendar:id/day_cancel_tv").getText(),
					"取消");
			// System.out.println("从拍照入口发布日子");
			driver.findElementById("com.updrv.lifecalendar:id/day_take_picture_tv")
					.click();
			takeScreenShot("必过用例-日子拍照");
			String activity = driver.currentActivity();

			assertTrue(activity.contains("camera"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@After
	public void teardown() {
		driver.quit();
	}
	
	public void takeScreenShot(String filename) {
		  // 新建一个文件夹来存储截图
		 String destDir = "daylifescreenshots";
		  // 截图
		  File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		  // 用时间格式来给文件命名
		  DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		  // 在项目文件夹下创建一个 "daylifescreenshots" 提供给 destDir.
		  new File(destDir).mkdirs();
		  // 将文件的名称设置为当前的系统时间
		  String destFile = dateFormat.format(new Date()) +filename+ ".png";

		  try {
		   // 将文件拷贝到指定的文件目录
		   FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		 }
}