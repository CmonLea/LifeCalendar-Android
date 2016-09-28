package com.lifecalendar;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;



public class TestUpdate {
	AndroidDriver<WebElement> driver;
	@Before
	public void setUp(){
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName","Android Device");
		capabilities.setCapability("platformVersion", "4.4");
		//capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("unicodeKeyboard","ture");
		capabilities.setCapability("resetKeyboard","ture");
		capabilities.setCapability("timeout","120");
		//capabilities.setCapability("noReset","false");
		//capabilities.setCapability("fullReset","ture");
		
		//声明测试程序包名及activity
		capabilities.setCapability("appPackage", "com.updrv.lifecalendar");
		capabilities.setCapability("appActivity", ".activity.MainActivity");
		//初始化驱动远程连接地址
		try {
			driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
		
			e.printStackTrace();
		}
	}

	
	@Test
	public void testLuanchUpdate() throws InterruptedException {
		boolean update = false;
		try {
			update = driver.findElementById(
					"com.updrv.lifecalendar:id/dialog_bj").isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (update) {
			System.out.println("升级可用，执行升级");
			driver.findElementById("com.updrv.lifecalendar:id/dialog_text2")
					.click();
			driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);;
			//创建一个pullfile目录用来存放从手机获取的文件
			File pullfile = new File("pullfileforlder");
			//判断目录是否存在不存在则创建一个
			if (!pullfile.exists()) {
				pullfile.mkdir();
			}
			try {
				//使用adb命令获取手机升级包的文件夹，并存放到pullfile目录下
				Runtime.getRuntime().exec(
						" adb pull //sdcard//LifeCalendar" + " " + pullfile);
			   // 列出所有文件
				File[] files = pullfile.listFiles();
				System.out.println("\n目录" + pullfile.getName() + "下的所有文件");

				for (File file : files) {
					System.out.print("  " + file.getName());
				}

				// 列出所有.apk文件
				File[] apkFiles = pullfile
						.listFiles(new FileNameSelector("apk"));
				System.out.println("\n目录" + pullfile.getName() + "下的.apk文件");
				File apkfile = null;
				//筛选后缀为.apk的文件
				for (File file : apkFiles) {
					System.out.print("  " + file.getName());
					apkfile = file;
				}
				//获取升级apk文件的相对路径
				String appPath=apkfile.getAbsolutePath();
				System.out.println("升级包的路径为"+appPath);
				driver.closeApp();
				driver.installApp(appPath);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.startActivity("com.updrv.lifecalendar", ".activity.syssetting.SystemSettingActivity ");
				String afterupdate=driver
				.findElementsByXPath(
						"//android.widget.RelativeLayout[@index=12]/android.widget.RelativeLayout/android.widget.TextView")
				.get(2).getText();
				Assert.assertEquals("当前已最新",
						afterupdate);

			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}

		} else {
			System.out.println("升级不可用，直接启动应用");
			driver.closeApp();
			driver.launchApp();
		}

	}
@After
public void teardown(){
	driver.quit();
}
//过滤相关格式的类
class FileNameSelector implements FilenameFilter
{
 String extension = ".";
 public FileNameSelector(String fileExtensionNoDot)
 {
  extension += fileExtensionNoDot;
 }
 @Override
 public boolean accept(File dir, String name)
 {
  return name.endsWith(extension);
 }
}
}
