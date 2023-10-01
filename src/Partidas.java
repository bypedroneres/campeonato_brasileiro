import java.time.LocalDate;
import java.util.Optional;

public class Partidas {
    private String id;
    private LocalDate dataJogo;
    //private Optional<String> timeVencedor;
    private String mandante;
    private String visitante;
    private String timeVencedor;
    private String estadoJogo;
    private String placarMandante;
    private String placarVisitante;

    public Partidas() {
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

    public String getMandante() {
        return mandante;
    }

    public void setMandante(String mandante) {
        this.mandante = mandante;
    }

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
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

    public String getPlacarMandante() {
        return placarMandante;
    }

    public void setPlacarMandante(String placarMandante) {
        this.placarMandante = placarMandante;
    }

    public String getPlacarVisitante() {
        return placarVisitante;
    }

    public void setPlacarVisitante(String placarVisitante) {
        this.placarVisitante = placarVisitante;
    }

    @Override
    public String toString() {
        return "Partidas - " + id +
                "DataJogo: " + dataJogo +
                ", timeVencedor='" + timeVencedor + '\'' +
                ", estadoJogo='" + estadoJogo + '\'' +
                ", placarMandante='" + placarMandante + '\'' +
                ", placarVisitante='" + placarVisitante + '\'' +
                '}';
    }

    public int getYear(){
        return this.dataJogo.getYear();
    }
}
