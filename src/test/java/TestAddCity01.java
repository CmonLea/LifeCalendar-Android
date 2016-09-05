import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;


public class TestAddCity01 {
	AppiumDriver<WebElement> driver;
	List<String> arraylist = null;//中间变量主要用于做比较
   static int CITY_POSTION=(int)(Math.random()*32);//热门城市的坐标
	@Before
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Coolpad8670");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("appPackage", "com.updrv.lifecalendar");
		capabilities.setCapability("appActivity",
				".activity.weather.WeatherNewActivity");
		capabilities.setCapability("unicodeKeyboard", "ture");
		capabilities.setCapability("resetKeyboard", "ture");
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
	}

	@Test
	// 添加城市
	public void addCity() {
		try {

			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		snapshot(driver, "天气详情界面界面截图.png");
		driver.findElementById("com.updrv.lifecalendar:id/weather_new_title_iv")
				.click();// 点击天气详情界面的添加按钮
		
		// 截图方法
		snapshot(driver, "添加前城市管理界面截图.png");
		// 首次进入城市管理界面 已存在的城市
//for(int i=0;i<5;i++){
		if(driver.findElementsByXPath("//android.widget.GridView/android.widget.RelativeLayout").size()>=9){
	System.out.println("当前页面城市数量大于9个，上拉页面以获取完整城市列表");
		driver.swipe(250, 640, 250, 320, 800);
		new AddCities().addCities();
//		String selectedcity = driver
//				// 定义一个选中的城市
//				.findElementsByXPath(
//						"//android.widget.GridView/android.widget.LinearLayout/android.widget.TextView")
//				.get(5).getText();
//		System.out.println("选择的城市为:" + selectedcity);
//		List<WebElement> lt = driver
//				.findElementsByXPath("//android.widget.GridView[@index=3]/android.widget.LinearLayout");
//		compareCity( arraylist,selectedcity);
		}else{
			System.out.println("城市数量小于9个在这里执行...");
		new AddCities().addCities();
		}
//}	
}

	@After
	public void teardown() {
		driver.quit();
	}
	public static void snapshot(TakesScreenshot drivername, String filename) {
		// 这个方法用于创建截图 ,包含两个形参 ,一个是driver的名称，一个是文件的名称
		String currentPath = System.getProperty("user.dir"); //获取当前工作空间目录
		File scrFile = drivername.getScreenshotAs(OutputType.FILE);
		// 获取到截图后，可以拷贝截图到任意位置
		try {
			System.out.println("截图保存的路径为:" + currentPath + "\\" + filename);
			FileUtils
					.copyFile(scrFile, new File(currentPath + "\\" + filename));
		} catch (IOException e) {
			System.out.println("无法保存截图");
			e.printStackTrace();
		} finally {
			System.out.println("截图完毕, 文件位于 " + currentPath + "文件夹");
		}
	}
	public boolean compareCity(List<String> list,String str){
		boolean flag=false;
		for (int i = 0; i <list.size(); i++) {
			if (arraylist.get(i).contains(str)) {
				System.out.println(str + "已添加");
				flag= true;
				break;
			} else if (str.contains("定位")) {
				System.out.println("点击了定位");
//			} else {
//				System.out.println("城市未被添加执行添加操作");
//
//				//System.out.println("CITY_POSTION的值为：" + CITY_POSTION);
//
//				//lt.get(CITY_POSTION).click();
//				
//
//			}
			snapshot(driver, "添加城市后城市管理界面界面截图.png");
		}
	return flag;
	}
		return flag;
	}
	class AddCities{
		
		public void addCities() {
			List<WebElement> excistcitylist = driver
					.findElementsByXPath("//android.widget.LinearLayout[2]/android.widget.TextView[1]");

			// 一般的for循环遍历城市管理界面存在的城市
			List<String> al = new ArrayList<String>();
			for (int i = 0; i < excistcitylist.size(); i++) {
				System.out.println("进入城市管理界面已存在的城市为:"
						+ excistcitylist.get(i).getText());

				al.add(excistcitylist.get(i).getText());

			}
			arraylist = al;
			//System.out.println("arraylist"+arraylist);
//
//			List<WebElement> cityList = driver
//					.findElementsByXPath("//android.widget.GridView/android.widget.RelativeLayout");

			if (arraylist.size() == 12) {
				System.out.println("界面添加的城市已达上限！");

			} else {

				// 点击添加按钮
				driver.findElementById("com.updrv.lifecalendar:id/addcity")
						.click();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
		
					e.printStackTrace();
					System.out.println("页面可能还未跳转到热门城市界面");
				}
				//while(cityList.size()<12){
				String selectedcity = driver
						// 定义一个选中的城市
						.findElementsByXPath(
								"//android.widget.GridView/android.widget.LinearLayout/android.widget.TextView")
						.get(CITY_POSTION).getText();
//				System.out
//						.println("选择城市文本的长度："
//								+ driver.findElementsByXPath(
//										"//android.widget.GridView/android.widget.LinearLayout/android.widget.TextView")
//										.size());
//				System.out.println("CITY_POSTION的值为：" + CITY_POSTION);
				System.out.println("选择的城市为:" + selectedcity);
				List<WebElement> lt = driver
						.findElementsByXPath("//android.widget.GridView[@index=3]/android.widget.LinearLayout");
				//System.out.println("点击的城市长度是" + lt.size());
				
			 boolean flag=false;
				for (int i = 0; i <arraylist.size(); i++) {
					System.out.println("arraylist的长度为："+arraylist.size());
					if (arraylist.get(i).contains("东京")) {
						System.out.println(selectedcity + "已添加");
						flag=true;
						//System.out.println("flag="+flag);
						break;
					} else if (selectedcity.contains("定位")) {
						System.out.println("点击了定位");
						try {
							Thread.sleep(500);
							System.out.println("请稍等...正在定位城市");
						} catch (InterruptedException e) {
						
							e.printStackTrace();
						}
						
					} 
					
				}
				if(flag==false){
					System.out.println("城市未被添加执行添加操作，添加的城市为："+selectedcity);

				//System.out.println("CITY_POSTION的值为：" + CITY_POSTION);

					lt.get(CITY_POSTION).click();
				
				}
//				System.out.println("城市未被添加执行添加操作，添加的城市为："+selectedcity);
//
//				//System.out.println("CITY_POSTION的值为：" + CITY_POSTION);
//
//				lt.get(5).click();
				//break;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				snapshot(driver, "添加城市后城市管理界面界面截图.png");
				//}
			}
		}
	
	}
	
}