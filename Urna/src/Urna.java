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

    private Urna() {
        candidatos = new HashMap<>();
        votos = new HashMap<>();
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
    if (candidatos.containsKey(numeroCandidato)) {
        Candidato candidato = candidatos.get(numeroCandidato);
        System.out.println("Você votou em: " + candidato.getNome() + " (" + candidato.getNumero() + ")");
        System.out.print("Confirmar voto? (S/N): ");
        
        String confirmacao = scanner.nextLine().trim().toUpperCase();
        if (confirmacao.equals("S")) {
            votos.put(numeroCandidato, votos.getOrDefault(numeroCandidato, 0) + 1);
            System.out.println("Voto confirmado para: " + candidato.getNome());
        } else {
            System.out.println("Voto cancelado. Escolha novamente.");
        }
    } else {
        System.out.println("Candidato não encontrado!");
    }
}

    //  Resultado da votação
    public void exibirResultado() {
        System.out.println("\nResultado da Eleição:");
        int maiorVoto = 0;
        List<Candidato> vencedores = new ArrayList<>();

        for (Map.Entry<Integer, Candidato> entry : candidatos.entrySet()) {
            int numero = entry.getKey();
            Candidato candidato = entry.getValue();
            int quantidadeVotos = votos.getOrDefault(numero, 0);

            System.out.println(candidato.getNome() + " (" + candidato.getNumero() + "): " + quantidadeVotos + " votos");

            if (quantidadeVotos > maiorVoto) {
                maiorVoto = quantidadeVotos;
                vencedores.clear();
                vencedores.add(candidato);
            } else if (quantidadeVotos == maiorVoto) {
                vencedores.add(candidato);
            }
        }

        // Exibir o vencedor ou o empate
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
            System.out.println("\nVencedor: " + vencedores.get(0).getNome() + " com " + maiorVoto + " votos!");
        }
    }

    public void exibirCandidatos() {
        System.out.println("\nLista de Candidatos:");
        for (Candidato candidato : candidatos.values()) {
            System.out.println(candidato.getNome() + " - Número: " + candidato.getNumero());
        }
    }
}