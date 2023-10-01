import java.time.LocalDate;
import java.util.Optional;

public class Partidas {
    private String id;
    private LocalDate dataJogo;
    //private Optional<String> timeVencedor;
    private String timeVencedor;
    private String estadoJogo;

    public Partidas() {
    }

    public Partidas(String id, LocalDate dataJogo, String timeVencedor, String estadoJogo) {
        this.id = id;
        this.dataJogo = dataJogo;
        this.timeVencedor = timeVencedor;
        this.estadoJogo = estadoJogo;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDataJogo() {
        return dataJogo;
    }

    public void setDataJogo(LocalDate dataJogo) {
        this.dataJogo = dataJogo;
    }

    public String getTimeVencedor() {
        return timeVencedor;
    }

    public void setTimeVencedor(String timeVencedor) {
        this.timeVencedor = timeVencedor;
    }

    public String getEstadoJogo() {
        return estadoJogo;
    }

    public void setEstadoJogo(String estadoJogo) {
        this.estadoJogo = estadoJogo;
    }

    @Override
    public String toString() {
        return "Partidas{" +
                "id='" + id + '\'' +
                ", dataJogo=" + dataJogo +
                ", timeVencedor='" + timeVencedor + '\'' +
                ", estadoJogo='" + estadoJogo + '\'' +
                '}';
    }

    public int getYear(){
        return this.dataJogo.getYear();
    }
}
