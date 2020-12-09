package br.com.caelum.leilao.dominio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TesteDoAvaliador {
    @Test
    public void deveEntenderLancesEmOrdemCrescente() {
        //1 Cenário
        Usuario joao = new Usuario("João");
        Usuario jose = new Usuario("Jose");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Carro Novo");

        leilao.propoe(new Lance(joao, 250.0));
        leilao.propoe(new Lance(jose, 300.0));
        leilao.propoe(new Lance(maria, 400.0));

        //2 Ação
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        //3 Validação
        assertEquals(400, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(250, leiloeiro.getMenorLance(), 0.00001);

    }

    @Test
    public void deveEntenderLancesEmOrdemDecrescente() {
        //1 Cenário
        Usuario joao = new Usuario("João");
        Usuario jose = new Usuario("Jose");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Carro Novo");

        leilao.propoe(new Lance(joao, 4000.0));
        leilao.propoe(new Lance(jose, 3000.0));
        leilao.propoe(new Lance(maria, 2000.0));

        //2 Ação
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        //3 Validação
        assertEquals(2000, leiloeiro.getMenorLance(), 0.00001);
        assertEquals(4000, leiloeiro.getMaiorLance(), 0.00001);


    }

    @Test
    public void deveEntenderLanceUnico() {
        //1 Cenário
        Usuario joao = new Usuario("João");

        Leilao leilao = new Leilao("Carro Novo");

        leilao.propoe(new Lance(joao, 1000.0));

        //2 Ação
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        //3 Validação
        assertEquals(1000, leiloeiro.getMenorLance(), 0.00001);
        assertEquals(1000, leiloeiro.getMaiorLance(), 0.00001);


    }

    @Test
    public void deveEntenderLancesSemOrdem() {
        //1 Cenário
        Usuario joao = new Usuario("João");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");
        Usuario carla = new Usuario("Carla");
        Usuario roberta = new Usuario("Roberta");

        Leilao leilao = new Leilao("Carro Novo");

        leilao.propoe(new Lance(joao, 4000.0));
        leilao.propoe(new Lance(jose, 2000.0));
        leilao.propoe(new Lance(maria, 6000.0));
        leilao.propoe(new Lance(carla, 3000.0));
        leilao.propoe(new Lance(roberta, 5000.0));

        //2 Ação
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        //3 Validação
        assertEquals(2000, leiloeiro.getMenorLance(), 0.00001);
        assertEquals(6000, leiloeiro.getMaiorLance(), 0.00001);
    }

    @Test
    public void deveEncontrarOsTresMaiores() {
        //1 Cenário
        Usuario joao = new Usuario("João");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");
        Usuario carla = new Usuario("Carla");
        Usuario roberta = new Usuario("Roberta");

        Leilao leilao = new Leilao("Carro Novo");

        leilao.propoe(new Lance(joao, 4000.0));
        leilao.propoe(new Lance(jose, 2000.0));
        leilao.propoe(new Lance(maria, 6000.0));
        leilao.propoe(new Lance(carla, 3000.0));
        leilao.propoe(new Lance(roberta, 5000.0));

        //2 Ação
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        //3 Validação
        assertEquals(3, maiores.size());
        assertEquals(6000.0, maiores.get(0).getValor(), 0.00001);
        assertEquals(5000.0, maiores.get(1).getValor(), 0.00001);
        assertEquals(4000.0, maiores.get(2).getValor(), 0.00001);
    }

    @Test
    public void deveSelecionarLancesDe5000ParaCima() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao, 10000),
                new Lance(joao, 800)));

        assertEquals(1, resultado.size());
        assertEquals(10000, resultado.get(0).getValor(), 0.00001);
    }

    @Test
    public void deveEncontrarOsDoisMaiores() {
        //1 Cenário
        Usuario joao = new Usuario("João");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");
        Usuario carla = new Usuario("Carla");
        Usuario roberta = new Usuario("Roberta");

        Leilao leilao = new Leilao("Carro Novo");

        leilao.propoe(new Lance(joao, 4000.0));
        leilao.propoe(new Lance(jose, 2000.0));
//        leilao.propoe(new Lance(maria, 6000.0));
//        leilao.propoe(new Lance(carla, 3000.0));
//        leilao.propoe(new Lance(roberta, 5000.0));

        //2 Ação
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        //3 Validação
        assertEquals(2, maiores.size());
        assertEquals(4000.0, maiores.get(0).getValor(), 0.00001);
        assertEquals(2000.0, maiores.get(1).getValor(), 0.00001);
//        assertEquals(4000.0, maiores.get(2).getValor(), 0.00001);
    }

    @Test
    public void deveEncontrarListaVazia() {
        //1 Cenário
        Leilao leilao = new Leilao("Carro Novo");

        //2 Ação
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        //3 Validação
        assertEquals(0, maiores.size());
    }
}

