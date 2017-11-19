package com.softserve.fineui.junit;

import com.softserve.fineui.Utils;
import com.softserve.fineui.helpers.AbstractTest;
import org.junit.Test;
import org.openqa.selenium.By;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by Dmytro Firsov on 12/3/2016.
 */
public class UtilsTest extends AbstractTest {

    @Test
    public void fileExists() throws Exception {
        String path = TEMP_DIR_NAME + File.separator + "test.txt";
        File file = new File(path);
        assertEquals(file.exists(), Utils.fileExists(path));
        file.createNewFile();
        assertEquals(file.exists(), Utils.fileExists(path));
    }

    @Test
    public void createDir() throws Exception {
        String dir = this.TEMP_DIR_NAME + File.separator + "createDir";
        Boolean result = Utils.createDir(dir);
        File testDir = new File(dir);
        testDir.mkdir();
        assertEquals(testDir.exists(), result);
    }

    @Test
    public void canAccessSomePage() {
        try{
            execute.driversGet("https://www.gitbook.com/book/rangle-io/ngcourse2/details");
            //execute.focusOnElementByCssSelector("#gb");
            execute.driversWaitSeconds(5);
            s.makeExpectedScreenshotsForAllBrowsers();

            execute.driversGet("https://www.gitbook.com/book/rangle-io/ngcourse2/details");
            //execute.focusOnElementByCssSelector("#gb");
            execute.driversWaitSeconds(5);
            s.makeActualScreenshotsForAllBrowsers();

            qa.assertAllDiffs(s.makeDiffScreenshotsForAllBrowsers());

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void canAccessWebPage() {
        try{
            for(int i = 0; i< drivers.size(); i++){
                //drivers.get(i).manage().window().maximize();
                drivers.get(i).get("https://facebook.com");
                screenshots.get(i).makeExpectedScreenshot();
                drivers.get(i).get("https://amazon.com");
                screenshots.get(i).makeActualScreenshot();
                screenshots.get(i).makeDiff();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void loginToFacebook(){
        String login = "dmitriy.firsov@gmail.com";
        String password = "";
        chrome_driver.manage().window().maximize();
        chrome_driver.get("https://facebook.com");
        s4ch.makeExpectedScreenshot();
        chrome_driver.findElement(By.id("email")).sendKeys(login);
        chrome_driver.findElement(By.id("pass")).sendKeys(password);
        chrome_driver.findElement(By.id("loginbutton")).click();
        s4ch.makeActualScreenshot();
        qa.assertAllDiffs(s.makeDiffScreenshotsForAllBrowsers());
    }
}