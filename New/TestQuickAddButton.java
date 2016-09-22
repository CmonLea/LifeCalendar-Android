

import static org.junit.Assert.assertTrue;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.Connection;

import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestQuickAddButton {
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

	@Ignore("��ʾ")
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
		System.out.println("��ǰ������Ϊ"+contextNames);
//WEBVIEW_com.updrv.lifecalendar
		driver.context((String) contextNames.toArray()[1]); // set context
							System.out.println("contextNames.toArray()[1]="+contextNames.toArray()[1].toString());								// to WEBVIEW_1
//driver.context("WEBVIEW_com.updrv.lifecalendar");
		driver.findElementById("myEditor").click();
		driver.findElementByClassName("edui-body-container").sendKeys(
				"tell me why");//
		driver.context("NATIVE_APP");// 
		driver.findElementById("com.updrv.lifecalendar:id/common_top_next")
				.click();
		
	}

	@Test
	public void testDayLifeAddButtonDaily() {
		driver.findElementById(
				"com.updrv.lifecalendar:id/lin_menu_main_arcmenu").click();
		driver.findElementById("com.updrv.lifecalendar:id/tv_day_life_event")
				.click();
		List<WebElement> dailylist = driver
				.findElementsByClassName("android.widget.TextView");
		Assert.assertEquals("�������ѡ��", dailylist.get(0).getText());
		//
		Assert.assertEquals("����", dailylist.get(1).getText());
		Assert.assertEquals("ȡ��", dailylist.get(2).getText());
		int index = (int) (Math.random() * 3);
		System.out.println("index="+index);
		switch (index) {
		case 0:
			System.out.println("����ᷢ������");
			dailylist.get(0).click();
			Assert.assertEquals(".activity.ablum.PhotoAlbumActivity",
					driver.currentActivity());
			List<WebElement> photolist = driver
					.findElementsByXPath("//android.widget.GridView/android.widget.LinearLayout");
			if (photolist.size() != 0) {
				photolist.get(0).click();
				List<WebElement> selectlist = driver
						.findElementsByXPath("//android.widget.GridView/android.widget.RelativeLayout");
				if (selectlist.size() != 0) {
					selectlist.get(0).click();
					driver.findElementById("com.updrv.lifecalendar:id/btn_sure")
							.click();
					driver.findElementById(
							"com.updrv.lifecalendar:id/media_release_desc")
							.sendKeys("Hello My Dream");
					driver.findElementById(
							"com.updrv.lifecalendar:id/tv_daylife_post_send")
							.click();
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					Assert.assertTrue("���ӷ��ͳɹ�", driver.findElementById("com.updrv.lifecalendar:id/user_content").getText().equals("Hello My Dream"));
					driver.findElementById("com.updrv.lifecalendar:id/lin_menu_main_personal_account").click();
					driver.findElementById("com.updrv.lifecalendar:id/common_itemnormal_container").click();
					Assert.assertTrue("�ҵ�����������ʾ����������", driver.findElementById("com.updrv.lifecalendar:id/user_content").getText().equals("Hello My Dream"));
					driver.findElementById("com.updrv.lifecalendar:id/user_more").click();
					driver.findElementById("com.updrv.lifecalendar:id/dialog_text2").click();
				}
			} else {
				System.out.println("�ֻ�������Ƭ");
			}
			break;

		case 1:
			System.out.println("���շ�������");
			dailylist.get(1).click();
			String activity = driver.currentActivity();

			assertTrue(activity.contains("camera"));
			break;

		case 2:
			System.out.println("ȡ����������");
			dailylist.get(2).click();
			Assert.assertEquals(".activity.MainActivity",
					driver.currentActivity());
			break;

		}
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
			 //driver.getConnection()
			// Connection connect= ;
			 if(Connection.WIFI!=null||Connection.DATA!=null){
				 //Connection.NONE.(false);
				// driver.getConnection().NONE;
				 driver.closeApp();
				 driver.launchApp();
				
			 }else{
				 System.out.println("���������ã�ֱ������Ӧ��");
				 driver.launchApp();
			 }
		 }
	}
}