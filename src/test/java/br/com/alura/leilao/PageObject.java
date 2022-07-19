package br.com.alura.leilao;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class PageObject {
    protected WebDriver browser;

    public PageObject(WebDriver browser) {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        if (browser == null) {
            this.browser = new ChromeDriver();
        } else {
            this.browser = browser;
        }
        this.browser.manage()
                .timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS)
                .pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public void fechar() {
        this.browser.quit();
    }

}
