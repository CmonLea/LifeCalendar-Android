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
public static int Frame=0;//应用页面+通知栏的高度
static int Content=0;//应用内容也页高度
static int ActivityBar=0;//活动页高度(如果存在)
static int DayBar = 0;//日子导航栏高度
static int Notifi = 0;//通知栏高度
static int Day_Main_Rel=0;//用户信息栏高度
static int User_Content=0;//用户日子内容高度
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
		// 存在活动页面则上拉“活动页面+用户信息+日子内容”个高度以完全显示日子图片
		if (isactivityelementpresent()) {
			System.out.println("当前活动为显示状态");
			//获取滑动方式
			getDayLifeElementHeight();
			List<WebElement> imagelist = driver
					.findElementsByXPath("//android.widget.RelativeLayout[@index='3']/android.widget.RelativeLayout/android.widget.ImageView");

			int index = (int) (Math.random() * imagelist.size());
			System.out.println("日子的图片为:" + imagelist.size());
			System.out.println("点击日子的图片为:" + index);

			imagelist.get(index).click();

			Assert.assertEquals("判断查看日子跳转的页面是否正常",
					".activity.daylife.DayPictureBrowseActivity",
					driver.currentActivity());

			// 否则上拉用户信息+日子内容个高度以完全显示日子图片
		} else {
			System.out.println("当前没有显示活动");
			getDayLifeElementHeight();
			List<WebElement> imagelist = driver
					.findElementsByXPath("//android.widget.RelativeLayout[@index='3']/android.widget.RelativeLayout/android.widget.ImageView");

			int index = (int) (Math.random() * imagelist.size());
			System.out.println("日子的图片为:" + imagelist.size());
			System.out.println("点击日子的图片为:" + index);
			imagelist.get(index).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			takeScreenShot("查看日子图片");
			Assert.assertEquals("判断查看日子跳转的页面是否正常",
					".activity.daylife.DayPictureBrowseActivity",
					driver.currentActivity());

		}
	}
	@Test//测试点赞功能
	public void testLike() throws NoSuchElementException{
		//点击日子
		try {
			driver.findElementById(
					"com.updrv.lifecalendar:id/lin_menu_main_day_life").click();
			getDayLifeElementHeight();//判断日子页是否显示活动
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			//获取点赞前的点赞数
			takeScreenShot("点赞前点赞数");
			String like = driver.findElementById(
					"com.updrv.lifecalendar:id/usr_tianzan_text").getText();
			//将获取到的字符型数字转化为整形数值
			int confirmlike = Integer.parseInt(like);
			driver.findElementById("com.updrv.lifecalendar:id/user_tianzan_imge")
					.click();
			takeScreenShot("点赞后点赞数");
			//判断点赞后点赞数是否加1
			Assert.assertEquals(confirmlike + 1,Integer.parseInt(driver.findElementById(
							"com.updrv.lifecalendar:id/usr_tianzan_text").getText()));

			// 取消点赞，查看取消点赞是否减1
			String dislike = driver.findElementById(
					"com.updrv.lifecalendar:id/usr_tianzan_text").getText();
			int cancellike = Integer.parseInt(dislike);
			driver.findElementById("com.updrv.lifecalendar:id/user_tianzan_imge")
					.click();
			takeScreenShot("取消点赞后点赞数");
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
		getDayLifeElementHeight();// 判断日子页是否显示活动
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
				takeScreenShot("分享日子到QQ好友");
				Assert.assertEquals(".activity.ForwardRecentActivity",
						driver.currentActivity());

			} else {
				qudao.get(index).click();
				takeScreenShot("QQ未安装");
				Assert.assertEquals(false, isQQInstalled);
			}
			break;

		case 1:
			if (isQQInstalled) {
				qudao.get(index).click();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				takeScreenShot("分享日子到QQ空间");
				Assert.assertEquals(
						".cooperation.qzone.share.QZoneShareActivity",
						driver.currentActivity());

			} else {
				qudao.get(index).click();
				takeScreenShot("QQ未安装");
				Assert.assertEquals(false, isQQInstalled);
			}
			break;
		case 2:
			if (isWechatInstalled) {
				qudao.get(index).click();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				takeScreenShot("分享日子到微信好友");
				Assert.assertEquals("ui.transmit.SelectConversationUI",
						driver.currentActivity());

			} else {
				qudao.get(index).click();
				takeScreenShot("微信未安装");
				Assert.assertEquals(false, isWechatInstalled);
			}
			break;
		case 3:
			if (isWechatInstalled) {
				qudao.get(index).click();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				takeScreenShot("分享日子到微信");
				Assert.assertEquals(".plugin.sns.ui.SnsUploadUI",
						driver.currentActivity());

			} else {
				qudao.get(index).click();
				takeScreenShot("微信未安装");
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
//此方法判断日子页面是否存在活动页面
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
		System.out.println("活动内容未显示");
		Assert.assertFalse("活动内容未显示", isactivityelementpresent = false);
	}
	return isactivityelementpresent;
}
    //此方法用来获取日子界面各个元素的高度，以方便滑动到日子图片位置
	public void getDayLifeElementHeight() {
		driver.findElementById(
				"com.updrv.lifecalendar:id/lin_menu_main_day_life").click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// 获取日历界面高度
		Frame = driver.findElementsByClassName("android.widget.FrameLayout")
				.get(0).getSize().getHeight();
		// 获取应用界面的高度
		Content = driver.findElementById("android:id/content").getSize()
				.getHeight();
		// 获取通知栏的高度
		Notifi = Frame - Content;

		// 获取日子导航栏高度
		DayBar = driver
				.findElementById("com.updrv.lifecalendar:id/title_layout")
				.getSize().getHeight();
		Day_Main_Rel = driver
				.findElementById("com.updrv.lifecalendar:id/day_main_rel")
				.getSize().getHeight();
		System.out.println("Day_Main_Rel=" + Day_Main_Rel);
		// 日子内容高度
		User_Content = driver
				.findElementById("com.updrv.lifecalendar:id/user_content")
				.getSize().getHeight();
		System.out.println("User_Content=" + User_Content);

		// 活动页面存在时将活动页面滑上去直至获取到日子图片
		if (isactivityelementpresent()) {
			ActivityBar = driver
					.findElementByXPath(
							"//android.support.v4.view.ViewPager/android.widget.ImageView")
					.getSize().getHeight();

			starty =Notifi+DayBar+ActivityBar+Day_Main_Rel + User_Content;

			System.out.println("活动存在时starty=" + starty);
			startx = DayBar * (1 / 2);
			endx = DayBar * (1 / 2);
		
			endy = Notifi+DayBar;	
			System.out.println("活动存在是endy="+endy);
			
			duration = 2000;

			driver.swipe(startx, starty, endx, endy, duration);
         //滑动直至获取到日子图片
		} else {

			starty = Notifi+DayBar + Day_Main_Rel + User_Content;
			System.out.println("活动不存在时starty=" + starty);
			startx = DayBar * (1 / 2);
			endx = DayBar * (1 / 2);
			System.out.println("活动不存在是endy="+endy);
			endy = Notifi+DayBar;
			duration = 2000;

			driver.swipe(startx, starty, endx, endy, duration);

		}

	}

}