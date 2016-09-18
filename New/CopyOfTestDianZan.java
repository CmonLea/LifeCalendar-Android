import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;


@SuppressWarnings("deprecation")
public class CopyOfTestDianZan {
AndroidDriver<WebElement> driver;

int starty ;
int startx ;
int endx ;
int endy ;
int duration ;

@Before
public void setUp() throws Exception{
	DesiredCapabilities capabilities=new DesiredCapabilities();
	capabilities.setCapability("deviceName", "Coolpad8670");
	capabilities.setCapability("platformVersion", "4.4.2");
	capabilities.setCapability("noReset","ture");
	capabilities.setCapability("appPackage", "com.updrv.lifecalendar");
	capabilities.setCapability("appActivity", ".activity.MainActivity");
	driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);  
}

	@Test
	public void checkDaylifePicture() {
		driver.findElementById(
				"com.updrv.lifecalendar:id/lin_menu_main_day_life").click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//��ȡ���ӵ������߶�
		int DayBar = driver
				.findElementById("com.updrv.lifecalendar:id/title_layout")
				.getSize().getHeight();
		System.out.println("DayBar="+DayBar);
		// ����߶�
		// �����ж�ҳ���Ƿ���ڻ����

		Boolean isactivityelementpresent = driver.findElementByXPath(
				"//android.support.v4.view.ViewPager/android.widget.ImageView")
				.isDisplayed();
		//���ڻҳ�����������ҳ��+�û���Ϣ+�������ݡ����߶�����ȫ��ʾ����ͼƬ
		if (isactivityelementpresent) {
			int ActivityBar = driver
					.findElementByXPath(
							"//android.support.v4.view.ViewPager/android.widget.ImageView")
					.getSize().getHeight();
			System.out.println("ActivityBar="+ActivityBar);
			int Day_Main_Rel = driver
					.findElementById("com.updrv.lifecalendar:id/day_main_rel")
					.getSize().getHeight();
			System.out.println("Day_Main_Rel="+Day_Main_Rel);
			int User_Content=driver
					.findElementById("com.updrv.lifecalendar:id/user_content")
					.getSize().getHeight();
			System.out.println("User_Content="+User_Content);
			starty = DayBar + ActivityBar+Day_Main_Rel+User_Content-75;
			
			System.out.println("�����ʱstarty="+starty);
			startx = DayBar * (1 / 2);
			endx = DayBar * (1 / 2);
			endy = DayBar;
			duration = 500;

			driver.swipe(startx, starty, endx, endy, duration);
			
			List<WebElement> imagelist = driver
					.findElementsByXPath("//android.widget.RelativeLayout[@index='3']/android.widget.RelativeLayout/android.widget.ImageView");

			int index = (int) (Math.random() * imagelist.size());
			System.out.println("���ӵ�ͼƬΪ:"+imagelist.size());
			System.out.println("������ӵ�ͼƬΪ:"+index);

			imagelist.get(index).click();
			
			Assert.assertEquals("�жϲ鿴������ת��ҳ���Ƿ�����", ".activity.daylife.DayPictureBrowseActivity", driver.currentActivity());

			//���������û���Ϣ+�������ݸ��߶�����ȫ��ʾ����ͼƬ
		} else {
			int Day_Main_Rel = driver
					.findElementById("com.updrv.lifecalendar:id/day_main_rel")
					.getSize().getHeight();
			
			int User_Content=driver
					.findElementById("com.updrv.lifecalendar:id/user_content")
					.getSize().getHeight();

			starty = DayBar + Day_Main_Rel+User_Content+75;
			System.out.println("starty="+starty);
			startx = DayBar * (1 / 2);
			endx = DayBar * (1 / 2);
			endy = DayBar;
			duration = 500;

			driver.swipe(startx, starty, endx, endy, duration);
			
			List<WebElement> imagelist = driver
					.findElementsByXPath("//android.widget.RelativeLayout[@index='3']/android.widget.RelativeLayout/android.widget.ImageView");

			int index = (int) (Math.random() * imagelist.size());
			System.out.println("���ӵ�ͼƬΪ:"+imagelist.size());
			System.out.println("������ӵ�ͼƬΪ:"+index);

			imagelist.get(index).click();
			Assert.assertEquals("�жϲ鿴������ת��ҳ���Ƿ�����", ".activity.daylife.DayPictureBrowseActivity", driver.currentActivity());


		}

		// 2���û���Ϣ�߶�

		// 3�������������ݸ߶�

		// 4������ͼƬ�߶�1�š�2��...6��

		// ��ȫ��ʾ���ޣ������߶ȵ���1+2+3

		// ��ȫ��ʾ���ۿ������߶ȵ���1+2+3+4
	}
@After
public void teardown(){
	driver.quit();
}
}
