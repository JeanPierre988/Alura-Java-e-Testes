package br.com.caelum.leilao.dominio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TesteDoAvaliador {
    @Test
    public static void main(String[] args) {
        Usuario joao = new Usuario("João");
        Usuario jose = new Usuario("Jose");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Carro Novo");

        leilao.propoe(new Lance(joao, 250.0));
        leilao.propoe(new Lance(jose, 300.0));
        leilao.propoe(new Lance(maria, 400.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        double maiorEsperado = 410;
        double menorEsperado = 250;

        Assertions.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
        Assertions.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
        Assertions.assertEquals
    }
}
