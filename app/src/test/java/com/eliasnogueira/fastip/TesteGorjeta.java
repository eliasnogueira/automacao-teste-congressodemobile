package com.eliasnogueira.fastip;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import static junit.framework.Assert.assertEquals;

public class TesteGorjeta {


    @Test
    public void teste() throws MalformedURLException {

        // informar a app
        File arquivo = new File("build/outputs/apk/app-debug.apk");

        // informar as capacidades
        DesiredCapabilities capacidades = new DesiredCapabilities();
        capacidades.setCapability(MobileCapabilityType.PLATFORM, MobilePlatform.ANDROID);
        capacidades.setCapability(MobileCapabilityType.DEVICE_NAME, "Android emulator");
        capacidades.setCapability(MobileCapabilityType.APP, arquivo.getAbsolutePath());

        // abrir a 'conexao' com o dispositivo
        AndroidDriver<MobileElement> driver =
                new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capacidades);

        // interagir com os componentes
        driver.findElement(By.id("com.eliasnogueira.fastip:id/billAmtEditText")).sendKeys("100");
        driver.findElement(By.id("com.eliasnogueira.fastip:id/calcTipButton")).click();

        // validar os resultados esperados
        assertEquals("$15,00", driver.findElement(By.id("com.eliasnogueira.fastip:id/tipAmtTextView")).getText());
        assertEquals("$115,00", driver.findElement(By.id("com.eliasnogueira.fastip:id/totalAmtTextView")).getText());

        driver.closeApp();

    }

}
