package com.appium.test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.StartsActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
public class AccountRegister {
	//声明驱动变量
	public AppiumDriver<AndroidElement> driver;
	String substr0="@";
	String substr1=GetRadom.getCharAccount((int) (3+Math.random()*3));
	String substr2=".gmail";
	String user_name1="未登录";
	String user_name2="";
	String pass_word="";
	String confirm_password=pass_word;
	String email="";
	//定义用户名的正则表达式用来判断输入的用户名是否符合规则，用户名的规则为：6-16位字母或者或者数字组成
	String usernamerex="[0-9a-zA-Z]{6,16}";
	//定义密码及确认密码的正则表达式
	String passwordrex="[0-9a-zA-Z]{6,16}";
	//定义邮箱格式的正则表达式
	String emailrex="\\w+@\\w+(\\.\\w+)*\\.\\w+";
	//定义输入为空格的正则表达式
	String blankrex="\\p{Blank}+";

	//在junit setUp()方法中初始化测试信息
	@Before
public void setUp() throws Exception {

	//声明测试程序路径
	File classpathRoot = new File(System.getProperty("user.dir"));
	File appDir = new File(classpathRoot, "../../../apps");
	File app = new File(appDir, "LifeCalendar.apk");
	//声明测试设备信息
	DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setCapability("deviceName","honami");
	capabilities.setCapability("platformVersion", "4.4");
	capabilities.setCapability("app", app.getAbsolutePath());
	//声明测试程序包名及activity
	capabilities.setCapability("appPackage", "com.updrv.lifecalendar");
	capabilities.setCapability("appActivity", ".activity.MainActivity");
	//初始化驱动远程连接地址
	driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}
	/*===================================定义一个获取随机用户名的方法==============================*/
	
static class GetRadom{
		
public static String getCharAccount(int length) {
		String account="";
		Random random = new Random();
			
		  for (int i = 0; i <=length; i++) {
		   // 输出字母还是数字
		   String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; 
		   // 字符串
		   if ("char".equalsIgnoreCase(charOrNum)) {
		    // 取得大写字母还是小写字母
		    int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; 
		    account += (char) (choice + random.nextInt(26));
		   
		   } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
		    account += String.valueOf(random.nextInt(10));
		 
		   }
		  
		  }
		  return account;
	}
		 }
	/*======================定义一个获取随机用户名的方法==========================*/
	
	//自定义一个用户名的类来判断用户名的注册信息
class UserNameData{
		//自定义一个文件读取方法
public void readFile(String fileName,String string) {
		ArrayList<String> list = new ArrayList<>();
		try {
			//创建Filereader类对象来读取文件
			FileReader fr = new FileReader(new File("username.txt")); 
			//创建BufferReader类来读取Reader类对象fr
			BufferedReader bufr = new BufferedReader(fr);
			String s = null; 
			//分行读取文件里的字符串
			while ((s = bufr.readLine()) != null) {
				String str = s;
				//将字符串存储在list集合里
				list.add(str);
			}
			//将传入的字符串形参与list集合里的字符串比较
			boolean Isequal = compare(list, string);
			if (Isequal) {
				System.out.println( "用户名已被注册");
				//driver.close();
			}else {
				System.out.println("正在验证邮箱是否被注册...... ");
				Thread.sleep(1000);
				new EmailData().readFile("emaildata.txt", email);
				
		bufr.close(); 
		fr.close(); 
			}

		}
		catch (Exception e1) { 
			e1.printStackTrace();
		}
	}
		//自定义的比较方法
	public boolean compare(ArrayList<String> list2, String string) {
		boolean flag = false;
		//遍历list集合
		for (int i = 0; i < list2.size(); i++) {
			if (list2.get(i).equals(string)) {
				return true;
			}
		}
		return flag;
	}
	}
	//自定义一个判断邮箱是否注册的类，同用户名判断
class EmailData{
public void readFile(String fileName,String string) {
			ArrayList<String> list = new ArrayList<>();
			try {
				File file=new File("emaildata.txt");
				FileReader fr = new FileReader(file); 
				BufferedReader bufr = new BufferedReader(fr);
				String s = null; 
				while ((s = bufr.readLine()) != null) {
					String str = s;
					list.add(str);
				}
				boolean Isequal = compare(list, string);
				if (Isequal) {
					System.out.println( "邮箱已被注册！");
					//driver.close();
				}else {
					System.out.println("正在验证是否注册成功...... ");
					Thread.sleep(3000);
					String getUser0=driver.findElement(By.id("com.updrv.lifecalendar:id/tv_personal_account_user_name")).getText();
					if(getUser0.equals(user_name2)){
			        System.out.println("注册成功!"+"用户名为："+user_name2+"密码为："+pass_word+"邮箱为："+email);
			     
			    	}
			    	else{
			    		if(getUser0.equals(user_name2)==false)
			    		System.out.println("注册失败");
			    	}
				}
			bufr.close(); 
			fr.close(); 
			} 
				catch (Exception e1) { 
				e1.printStackTrace();
			}
		}	
public boolean compare(ArrayList<String> list2, String string){
			boolean flag = false;
			for (int i = 0; i < list2.size(); i++) {
				if (list2.get(i).equals(string)) {
					return true;
				}
			}
			return flag;
		}
		}
	@Test
	//测试主体
public void TestRegister() throws Exception{
		//判断是否为登录状态
for(int i=1;i<=5;i++){	  
			
			substr1=GetRadom.getCharAccount((int) (3+Math.random()*3));
			user_name2=GetRadom.getCharAccount((int)(5+(Math.random())*12));
			 pass_word=GetRadom.getCharAccount((int)((5+Math.random()*12)));
			 confirm_password=pass_word;
			 email=GetRadom.getCharAccount(6)+substr0+substr1+substr2;
			 
			 
			
	System.out.println("*==============================当前为第"+i+"次循环============================*");
			
		WebElement d = driver.findElement(By.
				id("com.updrv.lifecalendar:id/lin_menu_main_personal_account"));
		d.click();//在青橙上没有这项,适用于索尼手机
		String getUser=driver.findElement(By.
				id("com.updrv.lifecalendar:id/tv_personal_account_user_name")).
				getText();//适用于索尼手机
		//用户未登录直接进入注册页面登录
		if(getUser.equals(user_name1)){
			System.out.println("用户未登录进入注册页面");
		WebElement e = driver.findElement(By.
					id("com.updrv.lifecalendar:id/account_linear"));
	    	e.click();
	    	WebElement e4 = driver.findElement(By.
	    			id("com.updrv.lifecalendar:id/tv_register"));
	    	e4.click();
	    	Thread.sleep(500);
	    	//初始化登录状态
	    	String user_name0=driver.findElement(By.
	    			id("com.updrv.lifecalendar:id/user_name")).
	    			getText();
	    	String pass_word0=driver.findElement(By.
	    			id("com.updrv.lifecalendar:id/user_pwd")).
	    			getText();
	    	String confirm_password0=driver.findElement(By.
	    			id("com.updrv.lifecalendar:id/user_pwd_ok")).
	    			getText();
	    	
	    	String email0=driver.findElement(By.
	    			id("com.updrv.lifecalendar:id/email")).
	    			getText();
	    	
	    	List<AndroidElement> textFieldsList1 = driver.
	    			findElementsByClassName("android.widget.EditText");	
	    	textFieldsList1.get(0).sendKeys(user_name2);
	    	textFieldsList1.get(1).sendKeys(pass_word);
	    	textFieldsList1.get(2).sendKeys(confirm_password);
	    	textFieldsList1.get(3).sendKeys(email);

	    	//执行注册
	    	WebElement e5 = driver.findElement(By.
	    			id("com.updrv.lifecalendar:id/btn_register"));
	    	e5.click();   
	    	
	    	if(user_name2.equals(user_name0)||user_name2.matches(blankrex)||user_name2.equals("")){
	    		//提示用户名为空
	    		
	    	System.out.println("用户名不能为空！请重新输入");
	    	driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    	((StartsActivity) driver).startActivity("com.updrv.lifecalendar", ".activity.MainActivity");
	    	}
	    	else {
	    		System.out.println("用户名不为空，正在检测密码是否为空...");
	    		//检测密码是否为空
	    		if(pass_word.equals(pass_word0)||pass_word.matches(blankrex)||pass_word.equals("")){
	    			//提示密码为空
	    			System.out.println("密码为空,请重新输入！");
	    			driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    			((StartsActivity) driver).startActivity("com.updrv.lifecalendar", ".activity.MainActivity");
	    		}
	    		else {
	    				//检测确认密码是否为空
	    				
	    				System.out.println("密码不为空，正在检测确认密码是否为空...");
	    				
	    				if(confirm_password.equals(confirm_password0)||confirm_password.matches(blankrex)||confirm_password.equals("")){
	    					//提示确认密码为空
	    					System.out.println("确认密码为空，请重新输入");
	    					driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    					((StartsActivity) driver).startActivity("com.updrv.lifecalendar", ".activity.MainActivity");
	    				}
	    				//确认密码不为空
	    					else {
	    						//检测邮箱是否为空
	    						System.out.println("确认密码不为空，正在检测邮箱是否为空...");
	    						if(email.equals(email0)||email.matches(blankrex)||email.equals("")){
	    							//提示邮箱为空
	    							System.out.println("邮箱为空，请重新输入");
	    							driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    							((StartsActivity) driver).startActivity("com.updrv.lifecalendar", ".activity.MainActivity");
	    						}
	    						//邮箱不为空
	    						else{
	    							System.out.println("邮箱不为空，检测用户名、密码、确认密码、邮箱规则...");
	    							System.out.println("/*===========================用户名、密码、确认密码、邮箱规则判断=============================*/")	;					
	    							
	    							//判断用户名、密码、邮箱规则、以及是否被注册
	    							if(user_name2.matches(usernamerex)){
	    								System.out.println("用户名合法，判断密码是否合法");
	    								if(pass_word.matches(passwordrex)){
	    									System.out.println("密码合法，判断确认密码是否一致");
	    									if(confirm_password.matches(pass_word)){
	    									
	    									System.out.println("确认密码一致，判断邮箱是否合法");
	    									if(email.matches(emailrex)){
	    										System.out.println("邮箱合法，正在检测用户名及邮箱是否被注册......");
	    										new UserNameData().readFile("username.txt", user_name2);
	    										}
	    									else{
	    										System.out.println("邮箱不合法，请重新输入");
	    										driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    										((StartsActivity) driver).startActivity("com.updrv.lifecalendar", ".activity.MainActivity");
	    						
	    									}
	    									
	    									}
	    									else{
	    										System.out.println("确认密码与密码不一致，请重新输入");
	    										driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    										((StartsActivity) driver).startActivity("com.updrv.lifecalendar", ".activity.MainActivity");
	    										
	    									}
	    								}
	    								else{
	    									System.out.println("密码为6-16位英文字母数字组成请重新输入");
	    									driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    									((StartsActivity) driver).startActivity("com.updrv.lifecalendar", ".activity.MainActivity");
	    									
	    								}
	    								
	    							}
	    							else{
	    								System.out.println("用户名为6-16位英文字母数字组成请重新输入");
	    								driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    								((StartsActivity) driver).startActivity("com.updrv.lifecalendar", ".activity.MainActivity");
	    							}
	    						}
	    					
	    					}
	    				}
	    			}
	    	Thread.sleep(500);	
	  System.out.println("*======================测试结束====================================*");
	    	Thread.sleep(500);
		}
		//用户已登录退出登录再进行注册
		else{
			System.out.println("用户已登录退出登录再进入注册页面");
			WebElement e = driver.findElement(By.
					id("com.updrv.lifecalendar:id/account_linear"));
	    	e.click();
	    	WebElement e1 = driver.findElement(By.
	    			id("com.updrv.lifecalendar:id/account_linear"));
	    	e1.click();
	    	List<AndroidElement> textFieldsList = driver.findElementsByClassName("android.widget.RelativeLayout");
	    	textFieldsList.get(4).click();
	    	WebElement e2 = driver.findElement(By.id("com.updrv.lifecalendar:id/dialog_text2"));
	    	e2.click();
	    	WebElement e3 = driver.findElement(By.id("com.updrv.lifecalendar:id/account_linear"));
	    	e3.click();
	    	WebElement e4 = driver.findElement(By.id("com.updrv.lifecalendar:id/tv_register"));
	    	e4.click();
	    	
	    	
	    	//初始化登录状态
	    	String user_name0=driver.findElement(By.
	    			id("com.updrv.lifecalendar:id/user_name")).
	    			getText();
	    	String pass_word0=driver.findElement(By.
	    			id("com.updrv.lifecalendar:id/user_pwd")).
	    			getText();
	    	String confirm_password0=driver.findElement(By.
	    			id("com.updrv.lifecalendar:id/user_pwd_ok")).
	    			getText();
	    	
	    	String email0=driver.findElement(By.
	    			id("com.updrv.lifecalendar:id/email")).
	    			getText();
	    	//
	    	
	    	
	    	List<AndroidElement> textFieldsList1 = driver.findElementsByClassName("android.widget.EditText");
	    	
	    	textFieldsList1.get(0).sendKeys(user_name2);
	    	textFieldsList1.get(1).sendKeys(pass_word);
	    	textFieldsList1.get(2).sendKeys(confirm_password);
	    	textFieldsList1.get(3).sendKeys(email);
	    	WebElement e5 = driver.findElement(By.id("com.updrv.lifecalendar:id/btn_register"));
	    	e5.click();
	    	

		if(user_name2.equals(user_name0)||user_name2.matches(blankrex)||user_name2.equals("")){    		
	    	System.out.println("用户名不能为空！请重新输入");
	    	((StartsActivity) driver).startActivity("com.updrv.lifecalendar", ".activity.MainActivity");
	    	}
	    	else {
	    		System.out.println("用户名不为空，正在检测密码是否为空...");
	    		//检测密码是否为空
	    		if(pass_word.equals(pass_word0)||pass_word.matches(blankrex)||pass_word.equals("")){
	    			//提示密码为空
	    			System.out.println("密码为空,请重新输入！");
	    			((StartsActivity) driver).startActivity("com.updrv.lifecalendar", ".activity.MainActivity");
	    		}    		
	    		else {
	    				//检测确认密码是否为空
	    				
	    				System.out.println("密码不为空，正在检测确认密码是否为空...");
	    				
	    				if(confirm_password.equals(confirm_password0)||confirm_password.matches(blankrex)||confirm_password.equals("")){
	    					//提示确认密码为空
	    					System.out.println("确认密码为空，请重新输入");
	    					((StartsActivity) driver).startActivity("com.updrv.lifecalendar", ".activity.MainActivity");
	    				}
	    				//确认密码不为空
	    					else {
	    						//检测邮箱是否为空
	    						System.out.println("确认密码不为空，正在检测邮箱是否为空...");
	    						if(email.equals(email0)||email.matches(blankrex)||email.equals("")){
	    							//提示邮箱为空
	    							System.out.println("邮箱为空，请重新输入");
	    							((StartsActivity) driver).startActivity("com.updrv.lifecalendar", ".activity.MainActivity");
	    						}
	    						//邮箱不为空
	    						else{
	    							System.out.println("邮箱不为空，检测用户名、密码、确认密码、邮箱规则...");
	    		/*===========================用户名、密码、确认密码、邮箱规则判断=============================*/					
	    							
	    							//判断用户名、密码、邮箱规则、以及是否被注册
	    							if(user_name2.matches(usernamerex)){
	    								System.out.println("用户名合法，判断密码是否合法");
	    								if(pass_word.matches(passwordrex)){
	    									System.out.println("密码合法，判断确认密码是否一致");
	    									if(confirm_password.matches(pass_word)){
	    									System.out.println("确认密码一致，判断邮箱是否合法");
	    									if(email.matches(emailrex)){
	    										System.out.println("邮箱合法，正在检测用户名及邮箱是否被注册......");
	    										new UserNameData().readFile("username.txt", user_name2);
	    										}
	    									else{
	    										System.out.println("邮箱不合法，请重新输入");
	    										//driver.closeApp();
	    									}
	    									
	    									}
	    									else{
	    										System.out.println("确认密码与密码不一致，请重新输入");
	    										//driver.closeApp();
	    									}
	    								}
	    								else{
	    									System.out.println("密码为6-16位英文字母数字组成请重新输入");
	    									//driver.closeApp();
	    								}
	    								
	    							}
	    							else{
	    								System.out.println("用户名为6-16位英文字母数字组成请重新输入");
	    								//driver.closeApp();
	    							}
	    						}
	    					
	    					}
	    				}
	    			}
	    System.out.println("*======================测试结束====================================*");
		}
	}
}
	//结束测试
	@After
	public void tearDown() throws Exception {
	    
		driver.closeApp();
	}
	

	
}
