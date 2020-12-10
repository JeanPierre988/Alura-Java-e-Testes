package br.com.caelum.leilao.dominio.AnoBissexto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnoBissexto {

    @Test
    public void anoBissexto() {
        assertEquals(true, isAnoBissexto(2012));
        assertEquals(true, isAnoBissexto(2016));
    }

    @Test
    public void anoNaoBissexto() {
        assertEquals(false, isAnoBissexto(2015));
        assertEquals(false, isAnoBissexto(2011));
    }

    public boolean isAnoBissexto(int ano) {
        if (((ano % 4) == 0) && ((ano % 100) != 0)) return true;
        else if ((ano % 400) == 0) return true;
        else return false;
    }
}
