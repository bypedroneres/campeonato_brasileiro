import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class MatchesAnalyzer {
    public static void main(String[] args) {
        String estatisticasCSVFile = "campeonato-brasileiro-full.csv";
        List<Partidas> partidas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(estatisticasCSVFile))) {
            String linha;

            // Pule o cabeçalho, se houver
            br.readLine();

            // Itere sobre as linhas do arquivo
            while ((linha = br.readLine()) != null) {
                String[] colunas = linha.split(",");

                if (colunas.length >= 5) {
                    // Acesse as colunas específicas
                    String id = colunas[0].trim().replace("\"", "");
                    String dataStr = (colunas[2].trim().replace("\"", ""));
                    String mandante = (colunas[4].trim().replace("\"", ""));
                    String visitante = (colunas[5].trim().replace("\"", ""));

                    String vencedor = colunas[10].trim().replace("\"", "");
                    String placarMandante = colunas[12].trim().replace("\"", "");
                    String placarVisitante = colunas[13].trim().replace("\"", "");
                    String estadoPartida = colunas[14].trim().replace("\"", "");

                    // Analise a data no formato específico
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                    LocalDate dataPartida = LocalDate.parse(dataStr, formatter);
                    //int ano = dataPartida.getYear();
                    // Crie um objeto Partida com as informações
                    Partidas partida = new Partidas();
                    partida.setId(id);
                    partida.setDataJogo(dataPartida);
                    partida.setMandante(mandante);
                    partida.setVisitante(visitante);
                    partida.setTimeVencedor(vencedor);
                    partida.setPlacarMandante(placarMandante);
                    partida.setPlacarVisitante(placarVisitante);
                    partida.setEstadoJogo(estadoPartida);

                    // Adicione o objeto à lista
                    partidas.add(partida);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Time mais vencedor de 2008
        // filtrar ano de 2008
        List<Partidas> partidas2008 = partidas.stream()
                .filter(partida -> partida.getDataJogo().getYear() == 2008 && !"-".equals(partida.getTimeVencedor())).toList();
        // Agrupar time e contar repeticoes
        Map<String, Long> vitoriasPorTime = partidas2008.stream()
                .collect(Collectors.groupingBy(Partidas::getTimeVencedor, Collectors.counting()));
        // Encontrar o time com o maior número de vitorias
        Optional<Map.Entry<String, Long>> timeMaisVencedor = vitoriasPorTime.entrySet().stream()
                .max(Map.Entry.comparingByValue());
        System.out.println("Time que mais venceu em 2008: " + timeMaisVencedor.get().getKey());

        //
        // filtrar jogos de 2003 a 2022
        List<Partidas> partidasEntre2003E2022 = partidas.stream()
                .filter(partida -> partida.getDataJogo().getYear() > 2002 && partida.getDataJogo().getYear() < 2023).toList();


        // Estado com menor numero de jogos

        // Agrupar Estado e contar repeticoes
        Map<String, Long> jogosPorEstado = partidasEntre2003E2022.stream()
                .collect(Collectors.groupingBy(Partidas::getEstadoJogo, Collectors.counting()));
        // Encontrar o time com o menor número de partidas
        Optional<Map.Entry<String, Long>> estadoMenosNumeroJogos = jogosPorEstado.entrySet().stream()
                .min(Map.Entry.comparingByValue());
        System.out.println("Estado com menos jogos entre 2003 e 2022: " + estadoMenosNumeroJogos.get().getKey());

        //
        Optional<Partidas> jogoComMaisGols = partidas.stream()
                .max(Comparator
                        .comparingInt(partida -> Integer.parseInt(partida.getPlacarMandante())
                                + Integer.parseInt(partida.getPlacarVisitante())));

        Partidas partidasComMaisGols = jogoComMaisGols.get();
        System.out.printf("%s %s x %s %s", partidasComMaisGols.getMandante(), partidasComMaisGols.getPlacarMandante()
                , partidasComMaisGols.getPlacarVisitante(), partidasComMaisGols.getVisitante());

    }
}
