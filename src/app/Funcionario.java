package app;

import java.util.LinkedHashSet;

public class Funcionario {

    private String nome;

    private String cpf;

    private String cargo;

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Funcionario(String cpf, String nome, String cargo){
        this.cpf = cpf;
        this.nome = nome;
        this.cargo = cargo;
    }

    public static int SalvarNovo(String cpf, String nome, String cargo){
        Funcionario f = new Funcionario(cpf, nome, cargo);
        FuncionarioController fc = new FuncionarioController();
        return fc.post(f);
    }

    public int apagar(){
        FuncionarioController fc = new FuncionarioController();
        return fc.delete(getCpf());
    }

    public static LinkedHashSet<Funcionario> getAll(){
        FuncionarioController fc = new FuncionarioController();
        return fc.getAll();
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
