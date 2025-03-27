package com.exemplo;

import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Classe que define Urna, utilizando Singleton
class Urna {
    private static Urna instance;
    private Map<Integer, Candidato> candidatos;
    private Map<Integer, Integer> votos;
    private int votosBrancos;
    private int votosNulos;

    private Urna() {
        candidatos = new HashMap<>();
        votos = new HashMap<>();
        votosBrancos = 0;
        votosNulos = 0;
        inicializarCandidatos();
    }

    public static synchronized Urna getInstance() {
        if (instance == null) {
            instance = new Urna();
        }
        return instance;
    }

    // Povoamento de candidatos
    private void inicializarCandidatos() {
        candidatos.put(5, new Candidato("Cleber Coelho", 5));
        candidatos.put(10, new Candidato("Maria Dolores", 10));
        candidatos.put(15, new Candidato("José Diniz", 15));
        candidatos.put(20, new Candidato("Felipe Vaz", 20));

        // Certifica que os votos comecem no valor 0 
        for (int numero : candidatos.keySet()) {
            votos.put(numero, 0);
        }
    }

    // Registra voto com sistema de confirmação do eleitor
    public void votar(int numeroCandidato, Scanner scanner) {
        if (numeroCandidato == 0) {
            System.out.println("Você votou em BRANCO.");
            System.out.print("Confirmar voto? (S/N): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("S")) {
                votosBrancos++;
                System.out.println("Voto em branco confirmado.");
            } else {
                System.out.println("Voto cancelado. Escolha novamente.");
            }
        } else if (candidatos.containsKey(numeroCandidato)) {
            Candidato candidato = candidatos.get(numeroCandidato);
            System.out.println("Você votou em: " + candidato.getNome() + " (" + candidato.getNumero() + ")");
            System.out.print("Confirmar voto? (S/N): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("S")) {
                votos.put(numeroCandidato, votos.getOrDefault(numeroCandidato, 0) + 1);
                System.out.println("Voto confirmado para: " + candidato.getNome());
            } else {
                System.out.println("Voto cancelado. Escolha novamente.");
            }
        } else {
            System.out.println("Número inválido! Seu voto será considerado NULO.");
            System.out.print("Confirmar voto? (S/N): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("S")) {
                votosNulos++;
                System.out.println("Voto nulo confirmado.");
            } else {
                System.out.println("Voto cancelado. Escolha novamente.");
            }
        }
    }

    public void exibirResultado() {
        System.out.println("\n========================================");
        System.out.println("          RESULTADO DA ELEIÇÃO          ");
        System.out.println("========================================\n");
    
        int totalVotosValidos = 0;
    
        // Contabilizando votos válidos (dos candidatos)
        for (int votosCandidato : votos.values()) {
            totalVotosValidos += votosCandidato;
        }
    
        // Exibir se não houve votos registrados
        if (totalVotosValidos == 0 && votosNulos == 0 && votosBrancos == 0) {
            System.out.println("Nenhum voto registrado.");
            return;
        }
    
        int maiorVoto = 0;
        List<Candidato> vencedores = new ArrayList<>();
    
        // Exibir os candidatos com seus votos e percentual
        for (Map.Entry<Integer, Candidato> entry : candidatos.entrySet()) {
            int numero = entry.getKey();
            int quantidadeVotos = votos.getOrDefault(numero, 0);
            double percentual = (quantidadeVotos * 100.0) / totalVotosValidos; // Considerar apenas votos válidos
    
            Candidato candidato = entry.getValue();
            System.out.printf("%s (%d): %d votos (%.2f%%)%n", candidato.getNome(), candidato.getNumero(), quantidadeVotos, percentual);
    
            if (quantidadeVotos > maiorVoto) {
                maiorVoto = quantidadeVotos;
                vencedores.clear();
                vencedores.add(candidato);
            } else if (quantidadeVotos == maiorVoto) {
                vencedores.add(candidato);
            }
        }
    
        // Exibir votos nulos e brancos
        System.out.println("\nVotos nulos: " + votosNulos);
        System.out.println("Votos brancos: " + votosBrancos);
    
        // Exibir o vencedor ou empate
        if (vencedores.size() > 1) {
            System.out.print("\nHouve um empate entre: ");
            for (int i = 0; i < vencedores.size(); i++) {
                System.out.print(vencedores.get(i).getNome());
                if (i < vencedores.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(" com " + maiorVoto + " votos cada.");
        } else {
            System.out.println("\n => Vencedor: " + vencedores.get(0).getNome() + " com " + maiorVoto + " votos!");
        }
    }    

    public void exibirCandidatos() {
        System.out.println("\n========================================");
        System.out.println("          LISTA DE CANDIDATOS           ");
        System.out.println("========================================");
        for (Candidato candidato : candidatos.values()) {
            System.out.println(candidato.getNome() + " - Número: " + candidato.getNumero());
        }
    }
}
