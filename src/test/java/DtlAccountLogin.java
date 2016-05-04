
import java.net.URL;
import java.util.List;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DtlAccountLogin {
	static String accout_initial_value="未登录", 
		accout_before_login,accout_input_value,
	    accout_input_pwd, accout_afeter_login;
	static AndroidDriver<AndroidElement> driver;
			
public void judgeLoginBefore() throws Exception{
		    	//进入我的界面
		    	WebElement d = driver.findElement(By.
		    			id("com.updrv.lifecalendar:id/lin_menu_main_personal_account"));
		    	d.click();
		    	//判断是否为登录状态	
		    	Thread.sleep(10);
		        accout_before_login=driver.findElement(By.
				id("com.updrv.lifecalendar:id/tv_personal_account_user_name")).getText();//适用于索尼手机
				//用户未登录直接进入注册页面登录
		if(accout_before_login.equals(accout_initial_value)){
			   System.out.println("用户未登录进入登录页面");
		 }   
	   else{
			System.out.println("用户已登录退出登录再登录");
			WebElement e = driver.findElement(By.
					id("com.updrv.lifecalendar:id/account_linear"));
			e.click();
			WebElement e1 = driver.findElement(By.
					id("com.updrv.lifecalendar:id/account_linear"));
			e1.click();
			
			List<AndroidElement> textFieldsList = driver.
					findElementsByClassName("android.widget.RelativeLayout");
			textFieldsList.get(4).click();
			WebElement e2 = driver.findElement(By.
					id("com.updrv.lifecalendar:id/dialog_text2"));
			e2.click();
		}
		    }

public void judgeLoginAfter(){ 
accout_afeter_login=driver.findElement(By.
		id("com.updrv.lifecalendar:id/tv_personal_account_user_name")).getText();
if(accout_afeter_login.equals(accout_input_value))
{
   System.out.println("登录成功用户名为："+accout_afeter_login);
}
else if(accout_afeter_login.equals(accout_initial_value)){
	//System.out.print(current_time.format(new Date())+" ");
	System.out.println("用户登录失败");
	 
}	  
    }

			
@Before
public void setUp() throws Exception{
			 DesiredCapabilities caps = new DesiredCapabilities();
//			 File classpathRoot = new File(System.getProperty("user.dir"));
//		     File appDir = new File(classpathRoot, "apps");
//		     File app = new File(appDir, "LifeCalendar.apk");
//			caps.setCapability(MobileCapabilityType.APP,app);//APP路径
			caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4");//安卓系统版本
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");//测试平台
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, "honami");//测试设备名称
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,"Appium");
			caps.setCapability(MobileCapabilityType.APP_PACKAGE,"com.updrv.lifecalendar");
			caps.setCapability("appActivity", ".activity.MainActivity");
			caps.setCapability("noReset", "ture");
			driver = new AndroidDriver<AndroidElement> (new URL("http://127.0.0.1:4723/wd/hub"), caps);
		}
@Test
public void dtllogin() throws Exception{
			accout_input_value="apple00";accout_input_pwd="123456";
			judgeLoginBefore();
			Thread.sleep(3000);
			WebElement
			wbbutton0=driver.findElement(By.id("com.updrv.lifecalendar:id/account_linear"));
			wbbutton0.click();
			WebElement user_accounts = driver.findElement(By.id("com.updrv.lifecalendar:id/user_accounts"));
			user_accounts.sendKeys(accout_input_value);
		    WebElement user_password = driver.findElement(By.id("com.updrv.lifecalendar:id/user_password"));
		    user_password.sendKeys(accout_input_pwd);
		    WebElement btn_login = driver.findElement(By.id("com.updrv.lifecalendar:id/btn_login"));
		    btn_login.click();
		    Thread.sleep(2000);
		    judgeLoginAfter();
		   
		}

	@After
	public void teardown() {
		driver.quit();
	}

}
