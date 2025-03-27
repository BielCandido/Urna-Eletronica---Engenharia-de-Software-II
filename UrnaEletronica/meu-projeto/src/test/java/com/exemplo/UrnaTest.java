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

    @Test
    void testEleitor(){
        
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
        
        urna.votar(5, scanner);
        urna.exibirResultado();
    }

    @Test
    void testVotoNulo() {
        String input = "S\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        
        urna.votar(99, scanner); // Número inválido
        urna.exibirResultado();
    }

    @Test
    void testVotoBranco() {
        String input = "S\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        
        urna.votar(0, scanner);
        urna.exibirResultado();
    }
}
