package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Falha {

    private int id;

    private LocalDate dataFalha;

    private String descricao;

    public LocalDate getDataFalha() {
        return dataFalha;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }

    public Falha(int id, int dia, int mes, int ano, String descricao){
        this.id = id;
        this.dataFalha = LocalDate.of(ano, mes, dia);
        this.descricao = descricao;
    }

    public Falha(int id, LocalDate ld, String descricao){
        this.id = id;
        this.dataFalha = ld;
        this.descricao = descricao;
    }
}
