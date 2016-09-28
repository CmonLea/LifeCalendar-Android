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
		
		//�������Գ��������activity
		capabilities.setCapability("appPackage", "com.updrv.lifecalendar");
		capabilities.setCapability("appActivity", ".activity.MainActivity");
		//��ʼ������Զ�����ӵ�ַ
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
			System.out.println("�������ã�ִ������");
			driver.findElementById("com.updrv.lifecalendar:id/dialog_text2")
					.click();
			driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);;
			//����һ��pullfileĿ¼������Ŵ��ֻ���ȡ���ļ�
			File pullfile = new File("pullfileforlder");
			//�ж�Ŀ¼�Ƿ���ڲ������򴴽�һ��
			if (!pullfile.exists()) {
				pullfile.mkdir();
			}
			try {
				//ʹ��adb�����ȡ�ֻ����������ļ��У�����ŵ�pullfileĿ¼��
				Runtime.getRuntime().exec(
						" adb pull //sdcard//LifeCalendar" + " " + pullfile);
			   // �г������ļ�
				File[] files = pullfile.listFiles();
				System.out.println("\nĿ¼" + pullfile.getName() + "�µ������ļ�");

				for (File file : files) {
					System.out.print("  " + file.getName());
				}

				// �г�����.apk�ļ�
				File[] apkFiles = pullfile
						.listFiles(new FileNameSelector("apk"));
				System.out.println("\nĿ¼" + pullfile.getName() + "�µ�.apk�ļ�");
				File apkfile = null;
				//ɸѡ��׺Ϊ.apk���ļ�
				for (File file : apkFiles) {
					System.out.print("  " + file.getName());
					apkfile = file;
				}
				//��ȡ����apk�ļ������·��
				String appPath=apkfile.getAbsolutePath();
				System.out.println("��������·��Ϊ"+appPath);
				driver.closeApp();
				driver.installApp(appPath);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.startActivity("com.updrv.lifecalendar", ".activity.syssetting.SystemSettingActivity ");
				String afterupdate=driver
				.findElementsByXPath(
						"//android.widget.RelativeLayout[@index=12]/android.widget.RelativeLayout/android.widget.TextView")
				.get(2).getText();
				Assert.assertEquals("��ǰ������",
						afterupdate);

			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}

		} else {
			System.out.println("���������ã�ֱ������Ӧ��");
			driver.closeApp();
			driver.launchApp();
		}

	}
@After
public void teardown(){
	driver.quit();
}
//������ظ�ʽ����
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
