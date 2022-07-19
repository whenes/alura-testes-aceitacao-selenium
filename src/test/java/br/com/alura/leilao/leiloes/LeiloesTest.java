package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class LeiloesTest {
    private LeiloesPage paginaDeLeiloes;
    private CadastroLeiloesPage paginaDeCadastro;

    @BeforeEach
    public void beforeEach() {
        LoginPage paginaDeLogin = new LoginPage();
        paginaDeLogin.preecheFormularioDeLogin("fulano", "pass");
        this.paginaDeLeiloes = paginaDeLogin.submeteFormulario();
        this.paginaDeCadastro = this.paginaDeLeiloes.carregarFormulario();
    }

    @AfterEach
    public void afterEach() {
        this.paginaDeLeiloes.fechar();
    }

    @Test
    public void deveriaCadastrarLeilao() {
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilão do dia " + hoje;
        String valor = "500.00";

        this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);
        assertTrue(paginaDeLeiloes.isLeilaoCadastrado(nome, valor, hoje));
    }

    @Test
    public void deveriaValidarCadastroDeLeilao() {
        this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao("", "", "");
        assertFalse(paginaDeCadastro.isPaginaAtual());
        assertTrue(paginaDeLeiloes.isPaginaAtual());
        assertTrue(paginaDeCadastro.hasMensagensDeValidacaoVisiveis());
    }
}
