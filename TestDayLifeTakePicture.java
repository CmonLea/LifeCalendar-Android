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
			//���������豸��Ϣ
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("deviceName","Coolpad8670");
			capabilities.setCapability("platformVersion", "4.4");
			//capabilities.setCapability("app", app.getAbsolutePath());
			//capabilities.setCapability("unicodeKeyboard","ture");
			capabilities.setCapability("resetKeyboard","ture");
			capabilities.setCapability("noReset","ture");
			
			//�������Գ��������activity
			capabilities.setCapability("appPackage", "com.updrv.lifecalendar");
			capabilities.setCapability("appActivity", ".activity.MainActivity");
			//��ʼ������Զ�����ӵ�ַ
			driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}

	@Test
	public void testDayLifeTakePicture(){
		// ��������桰���ӡ�ģ��
		try {
			driver.findElementById(
					"com.updrv.lifecalendar:id/tv_menu_main_day_life").click();
			// ������Ͻ����ͼ��
			driver.findElementById("com.updrv.lifecalendar:id/iv_camera").click();

			Assert.assertEquals(
					driver.findElementById(
							"com.updrv.lifecalendar:id/day_from_album_tv")
							.getText(), "�������ѡ��");
			Assert.assertEquals(
					driver.findElementById(
							"com.updrv.lifecalendar:id/day_take_picture_tv")
							.getText(), "����");
			Assert.assertEquals(
					driver.findElementById(
							"com.updrv.lifecalendar:id/day_cancel_tv").getText(),
					"ȡ��");
			// System.out.println("��������ڷ�������");
			driver.findElementById("com.updrv.lifecalendar:id/day_take_picture_tv")
					.click();
			takeScreenShot("�ع�����-��������");
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
		  // �½�һ���ļ������洢��ͼ
		 String destDir = "daylifescreenshots";
		  // ��ͼ
		  File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		  // ��ʱ���ʽ�����ļ�����
		  DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		  // ����Ŀ�ļ����´���һ�� "daylifescreenshots" �ṩ�� destDir.
		  new File(destDir).mkdirs();
		  // ���ļ�����������Ϊ��ǰ��ϵͳʱ��
		  String destFile = dateFormat.format(new Date()) +filename+ ".png";

		  try {
		   // ���ļ�������ָ�����ļ�Ŀ¼
		   FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		 }
}