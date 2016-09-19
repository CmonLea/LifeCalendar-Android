import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.runners.MethodSorters;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;


@SuppressWarnings("deprecation")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDailyLife {
	
AndroidDriver<WebElement> driver;

int starty ;
int startx ;
int endx ;
int endy ;
int duration ;
public static int Frame=0;//Ӧ��ҳ��+֪ͨ���ĸ߶�
static int Content=0;//Ӧ������Ҳҳ�߶�
static int ActivityBar=0;//�ҳ�߶�(�������)
static int DayBar = 0;//���ӵ������߶�
static int Notifi = 0;//֪ͨ���߶�
static int Day_Main_Rel=0;//�û���Ϣ���߶�
static int User_Content=0;//�û��������ݸ߶�
@Before
public void setUp() throws Exception{
	DesiredCapabilities capabilities=new DesiredCapabilities();
	capabilities.setCapability("deviceName", "Coolpad8670");
	capabilities.setCapability("platformVersion", "4.4.2");
	capabilities.setCapability("noReset","ture");
	capabilities.setCapability("unicodeKeyboard", "ture");
	capabilities.setCapability("resetKeyboard", "ture");
	capabilities.setCapability("appPackage", "com.updrv.lifecalendar");
	capabilities.setCapability("appActivity", ".activity.MainActivity");
	driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);  
}

	@Test
	public void testCheckDaylifePicture() throws NoSuchElementException {
		// ���ڻҳ�����������ҳ��+�û���Ϣ+�������ݡ����߶�����ȫ��ʾ����ͼƬ
		if (isactivityelementpresent()) {
			System.out.println("��ǰ�Ϊ��ʾ״̬");
			//��ȡ������ʽ
			getDayLifeElementHeight();
			List<WebElement> imagelist = driver
					.findElementsByXPath("//android.widget.RelativeLayout[@index='3']/android.widget.RelativeLayout/android.widget.ImageView");

			int index = (int) (Math.random() * imagelist.size());
			System.out.println("���ӵ�ͼƬΪ:" + imagelist.size());
			System.out.println("������ӵ�ͼƬΪ:" + index);

			imagelist.get(index).click();

			Assert.assertEquals("�жϲ鿴������ת��ҳ���Ƿ�����",
					".activity.daylife.DayPictureBrowseActivity",
					driver.currentActivity());

			// ���������û���Ϣ+�������ݸ��߶�����ȫ��ʾ����ͼƬ
		} else {
			System.out.println("��ǰû����ʾ�");
			getDayLifeElementHeight();
			List<WebElement> imagelist = driver
					.findElementsByXPath("//android.widget.RelativeLayout[@index='3']/android.widget.RelativeLayout/android.widget.ImageView");

			int index = (int) (Math.random() * imagelist.size());
			System.out.println("���ӵ�ͼƬΪ:" + imagelist.size());
			System.out.println("������ӵ�ͼƬΪ:" + index);
			imagelist.get(index).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			takeScreenShot("�鿴����ͼƬ");
			Assert.assertEquals("�жϲ鿴������ת��ҳ���Ƿ�����",
					".activity.daylife.DayPictureBrowseActivity",
					driver.currentActivity());

		}
	}
	@Test//���Ե��޹���
	public void testLike() throws NoSuchElementException{
		//�������
		try {
			driver.findElementById(
					"com.updrv.lifecalendar:id/lin_menu_main_day_life").click();
			getDayLifeElementHeight();//�ж�����ҳ�Ƿ���ʾ�
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			//��ȡ����ǰ�ĵ�����
			takeScreenShot("����ǰ������");
			String like = driver.findElementById(
					"com.updrv.lifecalendar:id/usr_tianzan_text").getText();
			//����ȡ�����ַ�������ת��Ϊ������ֵ
			int confirmlike = Integer.parseInt(like);
			driver.findElementById("com.updrv.lifecalendar:id/user_tianzan_imge")
					.click();
			takeScreenShot("���޺������");
			//�жϵ��޺�������Ƿ��1
			Assert.assertEquals(confirmlike + 1,Integer.parseInt(driver.findElementById(
							"com.updrv.lifecalendar:id/usr_tianzan_text").getText()));

			// ȡ�����ޣ��鿴ȡ�������Ƿ��1
			String dislike = driver.findElementById(
					"com.updrv.lifecalendar:id/usr_tianzan_text").getText();
			int cancellike = Integer.parseInt(dislike);
			driver.findElementById("com.updrv.lifecalendar:id/user_tianzan_imge")
					.click();
			takeScreenShot("ȡ�����޺������");
			Assert.assertEquals(Integer.parseInt(driver.findElementById(
					"com.updrv.lifecalendar:id/usr_tianzan_text").getText()),
					cancellike - 1);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testShare() {
		boolean isQQInstalled = driver.isAppInstalled("com.tencent.mobileqq");
		boolean isWechatInstalled = driver.isAppInstalled("com.tencent.mm");

		driver.findElementById(
				"com.updrv.lifecalendar:id/tv_menu_main_day_life").click();
		getDayLifeElementHeight();// �ж�����ҳ�Ƿ���ʾ�
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		WebElement fenxiang = driver
				.findElementById("com.updrv.lifecalendar:id/day_share_details");
		fenxiang.click();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		List<WebElement> qudao = driver
				.findElementsByXPath("//android.widget.GridView/android.widget.LinearLayout");
		int index = (int) (Math.random() * 4);
		// qudao.get(index).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		switch (index) {
		case 0:
			if (isQQInstalled) {
				qudao.get(index).click();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				takeScreenShot("�������ӵ�QQ����");
				Assert.assertEquals(".activity.ForwardRecentActivity",
						driver.currentActivity());

			} else {
				qudao.get(index).click();
				takeScreenShot("QQδ��װ");
				Assert.assertEquals(false, isQQInstalled);
			}
			break;

		case 1:
			if (isQQInstalled) {
				qudao.get(index).click();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				takeScreenShot("�������ӵ�QQ�ռ�");
				Assert.assertEquals(
						".cooperation.qzone.share.QZoneShareActivity",
						driver.currentActivity());

			} else {
				qudao.get(index).click();
				takeScreenShot("QQδ��װ");
				Assert.assertEquals(false, isQQInstalled);
			}
			break;
		case 2:
			if (isWechatInstalled) {
				qudao.get(index).click();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				takeScreenShot("�������ӵ�΢�ź���");
				Assert.assertEquals("ui.transmit.SelectConversationUI",
						driver.currentActivity());

			} else {
				qudao.get(index).click();
				takeScreenShot("΢��δ��װ");
				Assert.assertEquals(false, isWechatInstalled);
			}
			break;
		case 3:
			if (isWechatInstalled) {
				qudao.get(index).click();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				takeScreenShot("�������ӵ�΢��");
				Assert.assertEquals(".plugin.sns.ui.SnsUploadUI",
						driver.currentActivity());

			} else {
				qudao.get(index).click();
				takeScreenShot("΢��δ��װ");
				Assert.assertEquals(false, isWechatInstalled);
			}
			break;
		}
	}

	@Test
	public void testReview() throws NoSuchElementException {
		try {
			getDayLifeElementHeight();
			driver.findElementById("com.updrv.lifecalendar:id/day_input_message")
					.click();
			driver
					.findElementById("com.updrv.lifecalendar:id/common_msg_input_et")
.sendKeys("are you ok?");;
			driver.findElementById(
					"com.updrv.lifecalendar:id/common_msg_input_commit").click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
@After
public void teardown(){
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
//�˷����ж�����ҳ���Ƿ���ڻҳ��
public boolean isactivityelementpresent(){
	driver.findElementById(
			"com.updrv.lifecalendar:id/lin_menu_main_day_life").click();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	boolean isactivityelementpresent = false;
	try {
		isactivityelementpresent = driver
				.findElementByXPath(
						"//android.support.v4.view.ViewPager/android.widget.ImageView")
				.isDisplayed();
//		System.out.println("isactivityelementpresent="
//				+ isactivityelementpresent);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("�����δ��ʾ");
		Assert.assertFalse("�����δ��ʾ", isactivityelementpresent = false);
	}
	return isactivityelementpresent;
}
    //�˷���������ȡ���ӽ������Ԫ�صĸ߶ȣ��Է��㻬��������ͼƬλ��
	public void getDayLifeElementHeight() {
		driver.findElementById(
				"com.updrv.lifecalendar:id/lin_menu_main_day_life").click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// ��ȡ��������߶�
		Frame = driver.findElementsByClassName("android.widget.FrameLayout")
				.get(0).getSize().getHeight();
		// ��ȡӦ�ý���ĸ߶�
		Content = driver.findElementById("android:id/content").getSize()
				.getHeight();
		// ��ȡ֪ͨ���ĸ߶�
		Notifi = Frame - Content;

		// ��ȡ���ӵ������߶�
		DayBar = driver
				.findElementById("com.updrv.lifecalendar:id/title_layout")
				.getSize().getHeight();
		Day_Main_Rel = driver
				.findElementById("com.updrv.lifecalendar:id/day_main_rel")
				.getSize().getHeight();
		System.out.println("Day_Main_Rel=" + Day_Main_Rel);
		// �������ݸ߶�
		User_Content = driver
				.findElementById("com.updrv.lifecalendar:id/user_content")
				.getSize().getHeight();
		System.out.println("User_Content=" + User_Content);

		// �ҳ�����ʱ���ҳ�滬��ȥֱ����ȡ������ͼƬ
		if (isactivityelementpresent()) {
			ActivityBar = driver
					.findElementByXPath(
							"//android.support.v4.view.ViewPager/android.widget.ImageView")
					.getSize().getHeight();

			starty =Notifi+DayBar+ActivityBar+Day_Main_Rel + User_Content;

			System.out.println("�����ʱstarty=" + starty);
			startx = DayBar * (1 / 2);
			endx = DayBar * (1 / 2);
		
			endy = Notifi+DayBar;	
			System.out.println("�������endy="+endy);
			
			duration = 2000;

			driver.swipe(startx, starty, endx, endy, duration);
         //����ֱ����ȡ������ͼƬ
		} else {

			starty = Notifi+DayBar + Day_Main_Rel + User_Content;
			System.out.println("�������ʱstarty=" + starty);
			startx = DayBar * (1 / 2);
			endx = DayBar * (1 / 2);
			System.out.println("���������endy="+endy);
			endy = Notifi+DayBar;
			duration = 2000;

			driver.swipe(startx, starty, endx, endy, duration);

		}

	}

}