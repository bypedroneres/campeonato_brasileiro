import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class CardAnalyzer {
    public static void main(String[] args) {
        String cartoesCSVFile = "campeonato-brasileiro-cartoes.csv";
        List<Cartoes> cartoes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(cartoesCSVFile))) {
            String linha;

            // Pule o cabeçalho, se houver
            br.readLine();

            // Itere sobre as linhas do arquivo
            while ((linha = br.readLine()) != null) {
                String[] cartaoString = linha.split(",");

                if (cartaoString.length >= 5) {
                    // Acesse as colunas específicas
                    String id = cartaoString[0].trim().replace("\"", "");
                    String cartao = (cartaoString[3].trim().replace("\"", ""));
                    String atleta = (cartaoString[4].trim().replace("\"", ""));

                    // Crie um objeto Partida com as informações
                    Cartoes novoCartao = new Cartoes();
                    novoCartao.setId(id);
                    novoCartao.setCartao(cartao);
                    novoCartao.setAtleta(atleta);

                    // Adicione o objeto à lista
                    cartoes.add(novoCartao);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Cartoes> listaCartoesAmarelo = cartoes.stream().filter(cartao -> Objects.equals(cartao.getCartao(), "Amarelo")).toList();
        // Agrupar jogadores e contar repeticoes
        Map<String, Long> numeroDeCartoesAmarelosPorJogador = listaCartoesAmarelo.stream()
                .collect(Collectors.groupingBy(Cartoes::getAtleta, Collectors.counting()));
        // Encontrar o jogador com o maior numero de cartoes
        Optional<Map.Entry<String, Long>> jogadorComMaisCartoes = numeroDeCartoesAmarelosPorJogador.entrySet().stream()
                .max(Map.Entry.comparingByValue());
        System.out.println("Jogador que mais recebeu cartões amarelos: " + jogadorComMaisCartoes.get().getKey());

        List<Cartoes> listaCartoesVermelhos = cartoes.stream().filter(cartao -> Objects.equals(cartao.getCartao(), "Vermelho")).toList();
        // Agrupar jogadores e contar repeticoes
        Map<String, Long> numeroDeCartoesVermelhoPorJogador = listaCartoesVermelhos.stream()
                .collect(Collectors.groupingBy(Cartoes::getAtleta, Collectors.counting()));
        // Encontrar o jogador com o maior numero de cartoes
        Optional<Map.Entry<String, Long>> jogadorComMaisCartoesVermelhos = numeroDeCartoesVermelhoPorJogador.entrySet().stream()
                .max(Map.Entry.comparingByValue());
        System.out.println("Jogador que mais recebeu cartões amarelos: " + jogadorComMaisCartoesVermelhos.get().getKey());
    }
}
