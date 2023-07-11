package app;

import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedHashSet;

public abstract class Equipamento {

    private String ip;
    private String mac;
    private String nome;
    private int id;
    private LinkedHashSet<Falha> falhas;


    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public LinkedHashSet<Falha> getFalhas() {
        return falhas;
    }
    public void setFalhas(LinkedHashSet<Falha> falhas) {
        this.falhas = falhas;
    }


    public Equipamento(int id, String nome, String mac) {
        this.id = id;
        this.nome = nome;
        this.mac = mac;
        this.ip = null;
        this.falhas = new LinkedHashSet<Falha>();
    }
    public Equipamento(int id, String nome, String mac, String ip) {
        this.id = id;
        this.nome = nome;
        this.mac = mac;
        this.ip = ip;
        this.falhas = new LinkedHashSet<Falha>();
    }

    public boolean instalado(){
        if (ip == null)
            return false;
        return true;
    }

    public void listarDados(){
        System.out.println("********************");
        System.out.println("ID: "+getId());
        System.out.println("Nome: "+getNome());
        System.out.println("MAC: "+getMac());
        System.out.println("IP: "+getIp());
        System.out.println("********************");
    }

    public void listarFalhas(){

        if(this.falhas.isEmpty()){
            System.out.println("Sem ocorrência de falhas no equipamento "+ this.nome);
            return;
        }

        System.out.println("Ocorrências no equipamento "+ this.nome);

        Iterator<Falha> i = this.falhas.iterator();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (i.hasNext()){
            Falha f = i.next();
            System.out.println(f.getDataFalha().format(dateTimeFormatter) + " " + f.getDescricao());
        }
    }

    @Override
    public String toString() {
        return this.nome;
    }

    public abstract int registrarFalha(int dia, int mes, int ano, String descricao);
    public abstract int registrarIp(String ip);
    public abstract int deletarIp();
}
