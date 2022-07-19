package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CadastroLeiloesPage extends PageObject {
    private static final String URL_CADASTRO_LEILOES = "http://localhost:8080/leiloes/new";

    public CadastroLeiloesPage(WebDriver browser) {
        super(browser);
    }

    public LeiloesPage cadastrarLeilao(String nome, String valorInicial, String dataAbertura) {
        browser.findElement(By.id("nome")).sendKeys(nome);
        browser.findElement(By.id("valorInicial")).sendKeys(valorInicial);
        browser.findElement(By.id("dataAbertura")).sendKeys(dataAbertura);
        browser.findElement(By.id("button-submit")).submit();
        return new LeiloesPage(browser);
    }

    public boolean isPaginaAtual() {
        return browser.getCurrentUrl().equals(URL_CADASTRO_LEILOES);
    }

    public boolean hasMensagensDeValidacaoVisiveis() {
        String pageSource = browser.getPageSource();
        return pageSource.contains("minimo 3 caracteres")
                && pageSource.contains("não deve estar em branco")
                && pageSource.contains("deve ser um valor maior de 0.1")
                && pageSource.contains("deve ser uma data no formato dd/MM/yyyy");
    }
}
