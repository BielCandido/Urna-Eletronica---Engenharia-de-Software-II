package com.exemplo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Urna urna = Urna.getInstance();

        while (true) {
            System.out.println("\n========================================");
            System.out.println("       SISTEMA DE URNA ELETRÔNICA    ");
            System.out.println("========================================");
            System.out.println("1. Votar");
            System.out.println("2. Lista de candidatos");
            System.out.println("3. Resultado atual da votação");
            System.out.println("4. Sair");
            System.out.println("========================================\n");
            System.out.print("Escolha uma opção: ");
            
            String entrada = scanner.nextLine();
            int opcao;
            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.println("\n----------------------------------------");
                    System.out.print("Digite seu ID de eleitor: ");
                    String idEleitor = scanner.nextLine().trim();
                    
                    if (urna.eleitorJaVotou(idEleitor)) {
                        System.out.println("Você já votou! Não é permitido votar novamente.");
                        break;
                    }

                    System.out.print("Digite o número do candidato (ou 0 para voto em branco): ");
                    String entradaCandidato = scanner.nextLine();
                    System.out.println("----------------------------------------\n");
                    int numeroCandidato;

                    try {
                        numeroCandidato = Integer.parseInt(entradaCandidato);
                    } catch (NumberFormatException e) {
                        System.out.println("Número de candidato inválido!");
                        continue;
                    }

                    urna.votar(idEleitor, numeroCandidato, scanner);
                    break;
                case 2:
                    urna.exibirCandidatos();
                    break;
                case 3:
                    urna.exibirResultado();
                    break;
                case 4:
                    System.out.println("Finalizando o sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção de Menu inexistente! Tente novamente.");
            }
        }
    }
}