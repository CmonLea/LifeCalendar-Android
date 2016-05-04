

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.net.URL;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CreateMemorialDay {
	public AppiumDriver<AndroidElement> driver;
	@Before
	public void setUp() throws Exception{
//		File classpathRoot = new File(System.getProperty("user.dir"));
//		File appDir = new File(classpathRoot, "apps");
//		File app = new File(appDir, "LifeCalendar.apk");
		//声明测试设备信息
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName","honami");
		capabilities.setCapability("platformVersion", "4.4");
		//capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("unicodeKeyboard","ture");
		capabilities.setCapability("resetKeyboard","ture");
		capabilities.setCapability("noReset","ture");
		//capabilities.setCapability("autoLaunch","false");
		
		//声明测试程序包名及activity
		capabilities.setCapability("appPackage", "com.updrv.lifecalendar");
		capabilities.setCapability("appActivity", ".activity.MainActivity");
		//初始化驱动远程连接地址
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}
	@After()
	public void teardown(){
		driver.quit();
	}

	@Test
	public void set() throws Exception {
		for (int i = 0; i <= 4; i++) {
			System.out.println("*========================当前为第" + i
					+ "次循环========================*");
			WebElement jilu = driver.findElementByName("记录");
			jilu.click();

			WebElement jinianri = driver
					.findElementById("com.updrv.lifecalendar:id/tv_record_holiday");
			jinianri.click();

			int rukou = (int) (Math.random() * 2);
			// int rukou=0;
			// System.out.println("rukou="+rukou);
			// switch(rukou){
			if (driver
					.findElementById(
							"com.updrv.lifecalendar:id/tv_record_holiday")
					.getText().contains("纪念日(0)") == true) {
				// boolean
				// tubiao=driver.findElementById("com.updrv.lifecalendar:id/lin_no_aniversary").isDisplayed();
				System.out.println("纪念日面板进入新建纪念日");
				WebElement mianban = driver
						.findElementById("com.updrv.lifecalendar:id/lin_no_aniversary");
				mianban.click();
			} else if (rukou == 0) {
				System.out.println("右上角进入新建纪念日");
				WebElement youshang = driver
						.findElementById("com.updrv.lifecalendar:id/rl_record_add");
				youshang.click();
			} else if (rukou == 1) {

				System.out.println("快捷菜单进入新建纪念日");
				WebElement quickmenu = driver
						.findElementById("com.updrv.lifecalendar:id/iv_menu_main_middle");
				quickmenu.click();
				WebElement jinianr = driver
						.findElementById("com.updrv.lifecalendar:id/tv_aniversary_event_ll");
				jinianr.click();
			}

			// System.out.println(tubiao);

			// }
			// 选择纪念日图标

			WebElement icon = driver
					.findElementById("com.updrv.lifecalendar:id/iv_aniversary_icon");
			icon.click();
			List<AndroidElement> icon_select = driver
					.findElementsByXPath("//android.widget.GridView/android.widget.LinearLayout");
			int index = (int) (Math.random() * 30);

			WebElement select = icon_select.get(index);
			select.click();

			WebElement text = driver
					.findElementById("com.updrv.lifecalendar:id/et_aniversary_content");
			text.sendKeys("纪念日");

			// new GetDate().getdate();

			WebElement aniversary_type = driver
					.findElementById("com.updrv.lifecalendar:id/rl_aniversary_type");
			aniversary_type.click();
			// 选择纪念日类型
			List<AndroidElement> aniversary_type_dialog = driver
					.findElementsByXPath("//android.widget.ListView/android.widget.LinearLayout");
			int type_aniversary = (int) (Math.random() * 3);

			aniversary_type_dialog.get(type_aniversary).click();
			// 选择纪念日单位
			WebElement aniversary_unit = driver
					.findElementById("com.updrv.lifecalendar:id/cb_aniversary_unit");
			String button1_befo = aniversary_unit.getAttribute("checked");

			if (button1_befo.equals("true")) {
				System.out.println("设置前默认纪念单位为:周年");
			} else if (button1_befo.equals("false")) {
				System.out.println("设置前默认纪念单位为:天");
			}

			int j = (int) (Math.random() * 2);
			if (j == 0) {
				aniversary_unit.click();
				String button1_afte = driver.findElementById(
						"com.updrv.lifecalendar:id/cb_aniversary_unit")
						.getAttribute("checked");
				if (button1_afte.equals("true")) {
					System.out.println("设置后纪念单位为:周年");
				} else if (button1_afte.equals("false")) {
					System.out.println("设置后纪念单位为:天");
				}
			} else if (j == 1) {
				String button1_afte1 = driver.findElementById(
						"com.updrv.lifecalendar:id/cb_aniversary_unit")
						.getAttribute("checked");
				if (button1_afte1.equals("true")) {
					System.out.println("设置后纪念单位为:周年");
				} else if (button1_afte1.equals("false")) {
					System.out.println("设置后纪念单位为:天");
				}
			}

			WebElement remind = driver
					.findElementByXPath("//android.widget.LinearLayout[5]/android.widget.RelativeLayout/android.widget.CheckBox");

			String button2_befo = remind.getAttribute("checked");
			if (button2_befo.equals("true")) {
				System.out.println("设置前默认纪念日提醒状态：提醒");
			} else if (button2_befo.equals("false")) {
				System.out.println("设置前默认纪念日提醒状态：不提醒");
			}
			int k = (int) (Math.random() * 2);
			switch (k) {
			case 0:

				remind.click();
				String button2_afte = driver
						.findElementByXPath(
								"//android.widget.LinearLayout[5]/android.widget.RelativeLayout/android.widget.CheckBox")
						.getAttribute("checked");
				if (button2_afte.equals("true")) {
					System.out.println("设置后纪念日提醒状态：纪念日提醒");

					WebElement aniversary_remind = driver
							.findElementById("com.updrv.lifecalendar:id/rl_aniversary_remind");

					aniversary_remind.click();

					List<AndroidElement> remind_list = driver
							.findElementsById("com.updrv.lifecalendar:id/dialogItems");
					remind_list.get((int) Math.random() * 7).click();

					WebElement confirm = driver
							.findElementById("com.updrv.lifecalendar:id/negativeButton");
					confirm.click();
				} else if (button2_afte.equals("false")) {
					System.out.println("设置后纪念日提醒状态：纪念日不提醒");
				}
				break;
			case 1:
				String button2_afte2 = driver
						.findElementByXPath(
								"//android.widget.LinearLayout[5]/android.widget.RelativeLayout/android.widget.CheckBox")
						.getAttribute("checked");

				if (button2_afte2.equals("true")) {
					System.out.println("设置后纪念日提醒状态：纪念日提醒");

					WebElement aniversary_remind = driver
							.findElementById("com.updrv.lifecalendar:id/rl_aniversary_remind");

					aniversary_remind.click();

					List<AndroidElement> remind_list = driver
							.findElementsById("com.updrv.lifecalendar:id/dialogItems");
					remind_list.get((int) Math.random() * 7).click();

					WebElement confirm = driver
							.findElementById("com.updrv.lifecalendar:id/negativeButton");
					confirm.click();
				} else if (button2_afte2.equals("false")) {
					System.out.println("设置后纪念日提醒状态：纪念日不提醒");
				}
				break;
			}

			WebElement wancheng = driver
					.findElementById("com.updrv.lifecalendar:id/tv_aniversary_title_finish");
			wancheng.click();
			System.out.println("*========================第" + i
					+ "次循环结束========================*");
		}

	}
	class GetDate{
	public void getdate() throws Exception{
		int startx1,endx1,startx2,endx2,startx3,endx3;
		int starty1,starty2,starty3,endy1,endy2,endy3;
		int duration1=(int)(500*Math.random()+600);
		int duration2=(int)(500*Math.random()+1500);
		int duration3=(int)(800*Math.random()+500);
		startx1=endx1=driver.manage().window().getSize().getWidth()*1/6;
		startx2=endx2= driver.manage().window().getSize().getWidth()*1/2;
		startx3=endx3=driver.manage().window().getSize().getWidth()*5/6;
		WebElement jinianriqi=driver.findElementById("com.updrv.lifecalendar:id/every_type");
		jinianriqi.click();
		int datawindows_size=driver.findElementById("com.updrv.lifecalendar:id/year").getSize().getHeight();
		System.out.println("datawindows_size="+datawindows_size);
		
		if(datawindows_size==292){
		System.out.println("当前窗口为正常大小");
		
		//正常窗口
		starty1=(int)(driver.manage().window().getSize().getHeight()*(0.3*Math.random()+0.62));
		starty2=(int)(driver.manage().window().getSize().getHeight()*(0.3*Math.random()+0.62));
		starty3=(int)(driver.manage().window().getSize().getHeight()*(0.3*Math.random()+0.62));
		endy1=(int)(driver.manage().window().getSize().getHeight()*(0.3*Math.random()+0.62));
		endy2=(int)(driver.manage().window().getSize().getHeight()*(0.3*Math.random()+0.62));
		endy3=(int)(driver.manage().window().getSize().getHeight()*(0.3*Math.random()+0.62));
		//选择年份   
		driver.swipe( startx1, starty1, endx1, endy1,duration1);
		//选择月份
		driver.swipe(startx2, starty2, endx2, endy2, duration2);
		//选择日期
		driver.swipe(startx3, starty3, endx3, endy3, duration3);
		
		
		}
		else if(datawindows_size==160){
			
		System.out.println("窗口为缩小的大小");
	    //缩小后的窗口
		
		starty1=(int)(driver.manage().window().getSize().getHeight()*(0.16*Math.random()+0.76));
		starty2=(int)(driver.manage().window().getSize().getHeight()*(0.16*Math.random()+0.76));
		starty3=(int)(driver.manage().window().getSize().getHeight()*(0.16*Math.random()+0.76));
		endy1=(int)(driver.manage().window().getSize().getHeight()*(0.16*Math.random()+0.76));
		endy2=(int)(driver.manage().window().getSize().getHeight()*(0.16*Math.random()+0.76));
		endy3=(int)(driver.manage().window().getSize().getHeight()*(0.16*Math.random()+0.76));
		//选择年份   
		driver.swipe( startx1, starty1, endx1, endy1,duration1);
		//选择月份
		driver.swipe(startx2, starty2, endx2, endy2, duration2);
		//选择日期
		driver.swipe(startx3, starty3, endx3, endy3, duration3);
		
		
		}
WebElement queding=driver.findElementById("com.updrv.lifecalendar:id/btn_datetime_sure");
queding.click();
	
	}
	}
	}
