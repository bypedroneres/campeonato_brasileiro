import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CampeonatoBrasileiroAnalyzer {
    public static void main(String[] args) {
        // Caminho para os arquivos CSV
        String golsCSVFile = "campeonato-brasileiro-gols.csv";
        String cartoesCSVFile = "campeonato-brasileiro-cartoes.csv";
        String estatisticasCSVFile = "campeonato-brasileiro-estatisticas-full.csv";
        String jogosCSVFile = "campeonato-brasileiro-full.csv";

        try {
            // Lê e processa o arquivo de gols
            Map<String, Integer> vitoriasPorTime2008 = new HashMap<>();
            BufferedReader golsReader = new BufferedReader(new FileReader(golsCSVFile));
            String line;
            boolean headerSkipped = false; // Pular o cabeçalho
            while ((line = golsReader.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }
                String[] parts = line.split(",");
                String ano = parts[1].trim();
                String clube = parts[2].trim();
                if ("2008".equals(ano)) {
                    vitoriasPorTime2008.put(clube, vitoriasPorTime2008.getOrDefault(clube, 0) + 1);
                }
                // Outras operações com os dados de gols
            }
            
            golsReader.close();

            // Implemente as saídas para as outras informações desejadas

            // Exemplo para o jogador que mais fez gols
            Map<String, Integer> golsPorJogador = new HashMap<>();
            BufferedReader golsReader2 = new BufferedReader(new FileReader(golsCSVFile)); // Use a different variable name
            headerSkipped = false;
            while ((line = golsReader2.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }
                String[] parts = line.split(",");
                String jogador = parts[3].trim();
                golsPorJogador.put(jogador, golsPorJogador.getOrDefault(jogador, 0) + 1);
            }
            golsReader2.close();

            Optional<Map.Entry<String, Integer>> jogadorMaisGols = golsPorJogador.entrySet().stream()
                    .max(Map.Entry.comparingByValue());

            String resultadoJogadorMaisGols = jogadorMaisGols.map(entry -> "Jogador que mais fez gols: " + entry.getKey())
                    .orElse("Nenhum jogador encontrado.");

            System.out.println(resultadoJogadorMaisGols);

            // Continue implementando as saídas para as outras informações

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
