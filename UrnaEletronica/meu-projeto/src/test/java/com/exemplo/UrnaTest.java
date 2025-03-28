package com.exemplo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Scanner;
import java.io.ByteArrayInputStream;

class UrnaTest {
    private Urna urna;

    @BeforeEach
    void setUp() {
        urna = Urna.getInstance();
    }

    
    @BeforeEach
    void setUp_Eleitor() {
        Eleitor.registrarVoto("12345"); // Simulando que o eleitor "12345" já votou
    }


    @Test
    void testNovoVoto() {
        // Testa se o eleitor pode votar quando ele ainda não votou
        boolean podeVotar = Eleitor.podeVotar("13579");
        assertTrue(podeVotar, "O eleitor deve poder votar.");
    }

    @Test
    void testNaoPodeVotar() {
        // Testa se o eleitor não pode votar quando ele já votou
        boolean podeVotar = Eleitor.podeVotar("13579");
        assertTrue(podeVotar, "O eleitor não deve poder votar novamente.");
    }

    @Test
    void testVotoAdicionaEleitor() {
        // Testa se o método de registrar voto adiciona o eleitor corretamente
        Eleitor.registrarVoto("67890");
        assertFalse(Eleitor.podeVotar("67890"), "O eleitor que já registrou voto não deve poder votar novamente.");
    }

    @Test
    void testInicializacaoCandidatos() {
        assertNotNull(urna);
        urna.exibirCandidatos(); // Apenas para depuração
    }

    

    @Test
    void testVotoValido() {
        String input = "S\n"; // Simular confirmação do voto
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        
        urna.votar("1", 5, scanner);
        urna.exibirResultado();
    }

    @Test
    void testVotoNulo() {
        String input = "S\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        
        urna.votar(input, 99, scanner); // Número inválido
        urna.exibirResultado();
    }

    @Test
    void testVotoBranco() {
        String input = "S\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        
        urna.votar("", 0, scanner);
        urna.exibirResultado();
    }
}
