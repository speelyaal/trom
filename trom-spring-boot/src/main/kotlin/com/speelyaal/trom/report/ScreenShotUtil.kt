package com.speelyaal.trom.report

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver

class ScreenShotUtil {
    private val LOG: Logger = LogManager.getLogger(ScreenShotUtil::class.java)

    lateinit var filePath: String;
    fun getScreenShot(driver: WebDriver): String{
        var screenshot: TakesScreenshot  = driver as TakesScreenshot
        /*var src: File = screenshot.getScreenshotAs(OutputType.FILE)

        val path = filePath + "/reports/images/" + System.currentTimeMillis() + ".png"
        var destination: File = File(path)

        try {
            FileCopyUtils.copy(src,destination);
            LOG.info("File copy done")
        }catch (exception: Exception){
            LOG.error("Error copying screenshot file")
        }
        return path*/

        return "data:image/png;base64, " + screenshot.getScreenshotAs(OutputType.BASE64)


    }



}