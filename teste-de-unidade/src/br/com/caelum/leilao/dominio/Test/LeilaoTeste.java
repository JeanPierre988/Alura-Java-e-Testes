package br.com.caelum.leilao.dominio.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LeilaoTeste {
    @Test
    public void deveReceberUmLance() {
        Leilao leilao = new Leilao("Carro Zero");
        assertEquals(0, leilao.getLances().size());

        leilao.propoe(new Lance(new Usuario("Jobs"), 2000));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void deveReceberVariosLances() {
        Leilao leilao = new Leilao("Moto 0");
        leilao.propoe(new Lance(new Usuario("Gates"), 500));
        leilao.propoe(new Lance(new Usuario("Musk"), 700));

        assertEquals(2, leilao.getLances().size());
        assertEquals(500, leilao.getLances().get(0).getValor(), 0.00001);
        assertEquals(700, leilao.getLances().get(1).getValor(), 0.00001);
    }

    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
        Leilao leilao = new Leilao("Bike");
        Usuario steve = new Usuario("Jobs");

        leilao.propoe(new Lance(steve, 2000.0));
        leilao.propoe(new Lance(steve, 3000.0));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
        Leilao leilao = new Leilao("Foguete");
        Usuario steve = new Usuario("Jobs");
        Usuario bill = new Usuario("Gates");

        leilao.propoe(new Lance(steve, 2000.0));
        leilao.propoe(new Lance(bill, 3000.0));

        leilao.propoe(new Lance(steve, 1000.0));
        leilao.propoe(new Lance(bill, 5000.0));

        leilao.propoe(new Lance(steve, 1600.0));
        leilao.propoe(new Lance(bill, 3200.0));

        leilao.propoe(new Lance(steve, 7200.0));
        leilao.propoe(new Lance(bill, 6700.0));

        leilao.propoe(new Lance(steve, 9700.0));
        leilao.propoe(new Lance(bill, 10000.0));

        leilao.propoe(new Lance(steve, 12000.0));

        assertEquals(10, leilao.getLances().size());
        assertEquals(10000.0, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
    }
}
