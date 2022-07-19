package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LeiloesPage extends PageObject {
    private static final String URL_LEILOES = "http://localhost:8080/leiloes";
    private static final String URL_CADASTRO_LEILOES = "http://localhost:8080/leiloes/new";

    public LeiloesPage(WebDriver browser) {
        super(browser);
    }

    public CadastroLeiloesPage carregarFormulario() {
        this.browser.navigate().to(URL_CADASTRO_LEILOES);
        return new CadastroLeiloesPage(browser);
    }

    public boolean isLeilaoCadastrado(String nome, String valor, String data) {
        WebElement linhaDaTabela = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
        WebElement colunaNome = linhaDaTabela.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement colunaDataAbertura = linhaDaTabela.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement colunaValorInicial = linhaDaTabela.findElement(By.cssSelector("td:nth-child(3)"));
        return colunaNome.getText().equalsIgnoreCase(nome)
                && colunaValorInicial.getText().equalsIgnoreCase(valor)
                && colunaDataAbertura.getText().equalsIgnoreCase(data);
    }

    public boolean isPaginaAtual() {
        return browser.getCurrentUrl().equals(URL_LEILOES);
    }
}
