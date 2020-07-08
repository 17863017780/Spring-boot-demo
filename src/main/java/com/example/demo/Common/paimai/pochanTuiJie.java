package com.example.demo.Common.paimai;

import com.example.demo.Common.uiSelenium.BasePageImpl;
import com.example.demo.Common.uiSelenium.LocationUtil;
import com.google.errorprone.annotations.Var;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author ccjh1
 * @creat 2020/1/13
 */
public class pochanTuiJie {
    String url ="http://box.shop.jd.com/bankruptcy/announcement/list";
    String xpath_copy="/html/body/div[2]/div/div[3]/div/table/tbody/tr[2]/td[8]/div[3]";
    String xpath_newWindow="";
    String xpath_xiaHua="/html/body/div[2]/div/div[3]/div[1]/div[4]/div[1]";
    String xpath_xiaHua2="/html/body/div[2]/div/div[3]/div[1]/div[5]/div[1]";
    String xpath_tijiao="/html/body/div[2]/div/div[3]/div[1]/div[5]/div[2]/div/div[3]/span[1]";

    /**
     * 正常操作
     * @param loc
     */
    public static void fuzhiTuijie(pochanTuiJie loc,int num){
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        // chromedriver服务地址
        WebDriver driver=new ChromeDriver();
        BasePageImpl a=new BasePageImpl(driver,"https://www.jd.com");
        a.maxBrowser();
        a.click("xpath=//*[@id=\"ttbar-login\"]/a[1]");
        a.click("xpath=//*[@id=\"content\"]/div[2]/div[1]/div/div[3]/a");
        a.sendKeys("xpath=//*[@id=\"loginname\"]","testsifa0");
        a.sendKeys("xpath=//*[@id=\"nloginpwd\"]","360BUY");
        a.click("xpath=//*[@id=\"loginsubmit\"]");
        a.toUrl(loc.url);
        a.driverWait(300);
        for (int i=0;i< num;i++) {
            a.click("xpath=" + loc.xpath_copy);   //新的窗口
            a.driverWait(300);
            a.switchToNewWindow();
            a.driverWait(300);
            a.moveToElement("xpath=" + loc.xpath_xiaHua);
            a.moveToElement("xpath=" + loc.xpath_xiaHua2);
            a.click("xpath=" + loc.xpath_tijiao);
        }
    }
    public static void main(String[] args){
        pochanTuiJie lovc =new pochanTuiJie();
        fuzhiTuijie(lovc,50);
    }
}
