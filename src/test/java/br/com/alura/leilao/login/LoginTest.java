package br.com.alura.leilao.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    private static final String URL_LOGIN = "http://localhost:8080/login";
    private WebDriver browser;

    @BeforeAll
    public static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
    }

    @BeforeEach
    public void beforeEach() {
        this.browser = new ChromeDriver();
        browser.navigate().to(URL_LOGIN);

    }

    @AfterEach
    public void afterEach() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        this.browser.quit();

    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));
        assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos() {
        browser.findElement(By.id("username")).sendKeys("invalido");
        browser.findElement(By.id("password")).sendKeys("123asdfb");
        browser.findElement(By.id("login-form")).submit();

        assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login?error"));
        assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
        assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")));
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemQueEstejaLogado() {
        this.browser.navigate().to("http://localhost:8080/leiloes/2");
        assertTrue(browser.getCurrentUrl().equals(URL_LOGIN));
        assertFalse(browser.getPageSource().contains("Dados do Leilão"));
    }
}
