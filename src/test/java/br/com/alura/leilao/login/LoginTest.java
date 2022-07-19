package br.com.alura.leilao.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    private LoginPage paginaDeLogin;

    @BeforeEach
    public void beforeEach() {
        this.paginaDeLogin = new LoginPage();
    }

    @AfterEach
    public void afterEach() {
        this.paginaDeLogin.fechar();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        paginaDeLogin.preecheFormularioDeLogin("fulano", "pass");
        paginaDeLogin.submeteFormulario();

        assertFalse(paginaDeLogin.isPaginaDeLogin());
        assertEquals("fulano", paginaDeLogin.getNomeUsuarioLogado());
    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos() {
        paginaDeLogin.preecheFormularioDeLogin("invalido", "asdf32234");
        paginaDeLogin.submeteFormulario();

        assertTrue(paginaDeLogin.isPaginaDeLoginComDadosInvalidos());
        assertNull(paginaDeLogin.getNomeUsuarioLogado());
        assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemQueEstejaLogado() {
        paginaDeLogin.navegaParaPaginaDeLances();

        assertTrue(paginaDeLogin.isPaginaDeLogin());
        assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));
    }
}
