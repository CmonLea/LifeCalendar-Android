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
		//获取日子导航栏高度
		int DayBar = driver
				.findElementById("com.updrv.lifecalendar:id/title_layout")
				.getSize().getHeight();
		System.out.println("DayBar="+DayBar);
		// 活动栏高度
		// 首先判断页面是否存在活动内容

		Boolean isactivityelementpresent = driver.findElementByXPath(
				"//android.support.v4.view.ViewPager/android.widget.ImageView")
				.isDisplayed();
		//存在活动页面则上拉“活动页面+用户信息+日子内容”个高度以完全显示日子图片
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
			
			System.out.println("活动存在时starty="+starty);
			startx = DayBar * (1 / 2);
			endx = DayBar * (1 / 2);
			endy = DayBar;
			duration = 500;

			driver.swipe(startx, starty, endx, endy, duration);
			
			List<WebElement> imagelist = driver
					.findElementsByXPath("//android.widget.RelativeLayout[@index='3']/android.widget.RelativeLayout/android.widget.ImageView");

			int index = (int) (Math.random() * imagelist.size());
			System.out.println("日子的图片为:"+imagelist.size());
			System.out.println("点击日子的图片为:"+index);

			imagelist.get(index).click();
			
			Assert.assertEquals("判断查看日子跳转的页面是否正常", ".activity.daylife.DayPictureBrowseActivity", driver.currentActivity());

			//否则上拉用户信息+日子内容个高度以完全显示日子图片
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
			System.out.println("日子的图片为:"+imagelist.size());
			System.out.println("点击日子的图片为:"+index);

			imagelist.get(index).click();
			Assert.assertEquals("判断查看日子跳转的页面是否正常", ".activity.daylife.DayPictureBrowseActivity", driver.currentActivity());


		}

		// 2、用户信息高度

		// 3、日子文字内容高度

		// 4、日子图片高度1张、2张...6张

		// 完全显示点赞，上拉高度等于1+2+3

		// 完全显示评论框，上拉高度等于1+2+3+4
	}
@After
public void teardown(){
	driver.quit();
}
}
