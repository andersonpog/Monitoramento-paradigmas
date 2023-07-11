package app;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class UsoFinal extends Equipamento{

    private LinkedHashSet<Funcionario> usuarios;

    public LinkedHashSet<Funcionario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(LinkedHashSet<Funcionario> usuarios) {
        this.usuarios = usuarios;
    }

    public UsoFinal(int id, String nome, String mac) {
        super(id, nome, mac);
        setFalhas(FalhaController.getFalhas(FalhaController.FALHA_USOFINAL, id));
        UsoFinalController uc = new UsoFinalController();
        setUsuarios(uc.getAllUsuarios(id));
    }

    public UsoFinal(int id, String nome, String mac, String ip) {
        super(id, nome, mac, ip);
        setFalhas(FalhaController.getFalhas(FalhaController.FALHA_USOFINAL, id));
        UsoFinalController uc = new UsoFinalController();
        setUsuarios(uc.getAllUsuarios(id));
    }

    public static LinkedHashSet<UsoFinal> getAll(){
        UsoFinalController uc = new UsoFinalController();
        return uc.getAll();
    }

    public static int salvarNovo(String nome, String mac, String ip)
    {
        UsoFinal i = new UsoFinal(-1,nome,mac,ip);
        UsoFinalController uc = new UsoFinalController();
        return uc.post(i);
    }

    public static UsoFinal buscar(int id){
        UsoFinalController uc = new UsoFinalController();
        return uc.get(id);
    }

    public int apagar(){
        UsoFinalController uc = new UsoFinalController();
        return uc.delete(getId());
    }

    @Override
    public int registrarFalha(int dia, int mes, int ano, String descricao){
        int rows = FalhaController.registrarFalha(FalhaController.FALHA_USOFINAL, getId(), dia, mes, ano, descricao);
        if(rows>0)
            setFalhas(FalhaController.getFalhas(FalhaController.FALHA_USOFINAL, getId()));
        return rows;
    }

    @Override
    public int registrarIp(String ip) {
        return 0;
    }

    @Override
    public int deletarIp() {
        return 0;
    }

    public int registrarUsuario(String cpf){
        UsoFinalController uc = new UsoFinalController();
        return uc.addUsuario(cpf, getId());
    }

    public int removerUsuario(String cpf){
        UsoFinalController uc = new UsoFinalController();
        return uc.deleteUsuario(cpf, getId());
    }
    public void listarUsuarios(){
        if(this.usuarios.isEmpty()){
            System.out.println("Sem usuarios registrados no equipamento "+ getNome());
            return;
        }

        System.out.println("Usuarios no equipamento "+ getNome());

        Iterator<Funcionario> i = this.usuarios.iterator();

        while (i.hasNext()){
            Funcionario f = i.next();
            System.out.println(f.getNome());
        }
    }
}

