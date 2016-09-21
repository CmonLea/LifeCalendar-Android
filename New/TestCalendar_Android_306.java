package com.lifecalendar;

import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.mobile.NetworkConnection;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestCalendar_Android_306 {
	// ��������Android�ع�������Calendar_Android-306:�ع�����-���+
	AndroidDriver<WebElement> driver;

	@Before
	public void setUp() throws Exception {
		// File classpathRoot = new File(System.getProperty("user.dir"));
		// File appDir = new File(classpathRoot, "apps");
		// File app = new File(appDir, "LifeCalendar.apk");
		// ���������豸��Ϣ
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Coolpad8670");
		capabilities.setCapability("platformVersion", "4.4");
		// capabilities.setCapability("app", app.getAbsolutePath());
		// capabilities.setCapability("unicodeKeyboard","ture");
		capabilities.setCapability("resetKeyboard", "ture");
		capabilities.setCapability("noReset", "ture");

		// �������Գ��������activity
		capabilities.setCapability("appPackage", "com.updrv.lifecalendar");
		capabilities.setCapability("appActivity", ".activity.MainActivity");
		// ��ʼ������Զ�����ӵ�ַ
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	@Ignore("��ʾ")
	@Test
	public void testDayLifeAddButton() {
		try {
			driver.findElementById("com.updrv.lifecalendar:id/lin_menu_main_arcmenu")
			.click();
//			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			Assert.assertTrue(driver.findElementById("com.updrv.lifecalendar:id/tv_record_event").getText().toString().equals("����"));
			Assert.assertTrue(driver.findElementById("com.updrv.lifecalendar:id/tv_day_life_event").getText().toString().equals("����"));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	// @Ignore("��ʾ")
	@Test
	public void testDayLifeAddButtonNotes() {
		isupdatepresent();
		driver.findElementById(
				"com.updrv.lifecalendar:id/lin_menu_main_arcmenu").click();
		driver.findElementById("com.updrv.lifecalendar:id/tv_record_event_ll")
				.click();
		String activity = driver.currentActivity();
		Assert.assertTrue(activity
				.equals(".activity.recordthing.RecordThingDetailsNewActivity"));
		driver.findElementById("com.updrv.lifecalendar:id/et_recordthing_title")
				.sendKeys("appium test");

		Set<String> contextNames = driver.getContextHandles();

		driver.context((String) contextNames.toArray()[1]); // set context
															// to WEBVIEW_1

		driver.findElementById("myEditor").click();
		driver.findElementByClassName("edui-body-container").sendKeys(
				"tell me why");//
		driver.context("NATIVE_APP");// 
		driver.findElementById("com.updrv.lifecalendar:id/common_top_next")
				.click();
		
	}

	@Test
	public void testDayLifeAddButtonDaily() {

	}

	@After
	public void teardown() {
		driver.quit();
	}
	public void isupdatepresent(){
		boolean update=false;
		 try {
			update=driver.findElementById("com.updrv.lifecalendar:id/dialog_bj").isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 if(update){
			 System.out.println("�������ã����Լ��¹ص�����");
			 NetworkConnectionSetting connect= driver.getNetworkConnection();
			 if(connect.wifiEnabled()||connect.dataEnabled()){
				 connect.setWifi(false);
				 driver.closeApp();
				 driver.launchApp();
				
			 }else{
				 driver.launchApp();
			 }
		 }
	}
}
