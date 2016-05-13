import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestSwitchCity {
	AndroidDriver<WebElement> driver;
	SwipeElementDirection direction;
	int index;

	@Before
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Coolpad8670");
		capabilities.setCapability("platformVersion", "4.4.2");
		// capabilities.setCapability("noReset","false");
		capabilities.setCapability("appPackage", "com.updrv.lifecalendar");
		capabilities.setCapability("appActivity", ".activity.MainActivity");
		capabilities.setCapability("unicodeKeyboard", "ture");
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
	}

	@Test
	public void switchcity() throws Exception {

		driver.findElementById(
				"com.updrv.lifecalendar:id/iv_weather_container_ll").click();
		driver.findElementById("com.updrv.lifecalendar:id/iv_citylist").click();

		for (int i = 1; i < 3; i++) {
			System.out.println("----------------------------当前为第" + i
					+ "次循环----------------------------");
			List<WebElement> list_city = driver
					.findElementsByXPath("//android.widget.ListView/android.widget.FrameLayout");
			// System.out.println("list_city=" + list_city.size());

			System.out.println("进入时已存在的城市如下：");
			// 获取添加前已存在的城市名称并存入list列表
			List<WebElement> find_city = driver
					.findElementsByXPath("//android.widget.ListView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.TextView");
			// 输出已存在的城市名称
			Iterator<WebElement> iterator = find_city.iterator();
			ArrayList<String> arraylist = new ArrayList<String>();
			while (iterator.hasNext()) {
				String exist_citys = iterator.next().getText();
				// System.out.println("exist_citys=" + exist_citys);
				arraylist.add(exist_citys);
			}
			System.out.println("arraylist=" + arraylist);
			// 获取城市
			System.out.println(list_city.size());
			if (list_city.size() == 5) {
				System.out.println("最多只能添加5个城市");

			} else if (list_city.size() < 5) {

				// 点击添加按钮
				driver.findElementById("com.updrv.lifecalendar:id/lay_save")
						.click();

				List<WebElement> Hot_City_Slot = driver
						.findElementsByXPath("//android.widget.GridView/android.widget.LinearLayout");
				index = (int) (Math.random() * 30 + 1);
				// 获取正在添加城市的名称
				System.out.println("index=" + index + ",Hot_City_Slot size = "
						+ Hot_City_Slot.size());
				String Being_added_city = Hot_City_Slot
						.get(index)
						.findElement(
								By.id("com.updrv.lifecalendar:id/cityName"))
						.getText();

				// 判断正在添加的城市和已存在的是否重复
				System.out.println("Being_added_city===" + Being_added_city);

				if (compareSameCity(arraylist, Being_added_city)) {
					System.out.println(Being_added_city + "已添加");
					// System.out.println("添加重复城市前的城市列表为" + strs1);
					// driver.quit();
				} else {
					driver.findElementsByXPath(
							"//android.widget.GridView/android.widget.LinearLayout")
							.get(index).click();
					System.out.println("添加的城市为：" + Being_added_city);
				}
			}
		}

		int startx, starty, endx, endy, duration;
		int startx1, starty1, endx1, endy1, duration1;
		startx = driver.manage().window().getSize().getWidth() * 5 / 6;
		starty = driver.manage().window().getSize().getHeight() * 1 / 4;
		endx = driver.manage().window().getSize().getWidth() * 1 / 6;
		endy = starty = driver.manage().window().getSize().getHeight() * 1 / 4;
		duration = 250;

		startx1 = driver.manage().window().getSize().getWidth() * 1 / 6;
		starty1 = driver.manage().window().getSize().getHeight() * 1 / 4;
		endx1 = driver.manage().window().getSize().getWidth() * 5 / 6;
		endy1 = starty = driver.manage().window().getSize().getHeight() * 1 / 4;

		duration1 = 500;

		for (int i = 1; i <= 3; i++) {

			driver.startActivity("com.updrv.lifecalendar",
					".activity.weather.WeatherNewActivity ");
			Thread.sleep(1500);
			snapshot(driver, "2016-01-13-weather.jpg");
			driver.swipe(startx, starty, endx, endy, duration);
			driver.swipe(startx, starty, endx, endy, duration);
			driver.swipe(startx1, starty1, endx1, endy1, duration1);
			driver.swipe(startx1, starty1, endx1, endy1, duration1);

			// driver.getScreenshotAs(OutputType.FILE);

			Thread.sleep(1500);
			driver.findElementById("com.updrv.lifecalendar:id/aqi_relative")
					.click();
			snapshot(driver, "2016-01-13-api.jpg");
			driver.swipe(startx, starty, endx, endy, duration);
			driver.swipe(startx, starty, endx, endy, duration);
			driver.swipe(startx1, starty1, endx1, endy1, duration1);
			driver.swipe(startx1, starty1, endx1, endy1, duration1);

		}
		
	}

	@After
	public void teardown() {
		driver.quit();
	}

	/**
	 * This Method create for take screenshot
	 *
	 * @author
	 * @param driver
	 * @param filename
	 */

	public static void snapshot(TakesScreenshot drivername, String filename) {
		// this method will take screen shot ,require two parameters ,one is
		// driver name, another is file name

		String currentPath = System.getProperty("user.dir"); // get current work
																// folder
		File scrFile = drivername.getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy
		// somewhere
		try {
			System.out.println("截图保存的路径为:" + currentPath + "/" + filename);
			FileUtils
					.copyFile(scrFile, new File(currentPath + "\\" + filename));
		} catch (IOException e) {
			System.out.println("无法保存截图");
			e.printStackTrace();
		} finally {
			System.out.println("截图完毕, 文件位于 " + currentPath + " 文件夹");
		}
	}

	private boolean compareSameCity(ArrayList<String> list, String city) {
		for (int j = 0; j < list.size(); j++) {
			if (list.get(j).contains(city)) {
				return true;
			}
		}
		return false;
	}

}
