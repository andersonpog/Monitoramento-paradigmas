package app;

import java.util.LinkedHashSet;

public class Infra extends Equipamento{
    public Infra(int id, String nome, String mac) {
        super(id, nome, mac);
        setFalhas(FalhaController.getFalhas(FalhaController.FALHA_INFRA, id));
    }

    public Infra(int id, String nome, String mac, String ip) {
        super(id, nome, mac, ip);
        setFalhas(FalhaController.getFalhas(FalhaController.FALHA_INFRA, id));
    }

    public static LinkedHashSet<Infra> getAll(){
        InfraController ic = new InfraController();
        return ic.getAll();
    }

    public static int salvarNovo(String nome, String mac, String ip)
    {
        Infra i = new Infra(-1,nome,mac,ip);
        InfraController ic = new InfraController();
        return ic.post(i);
    }

    public static Infra buscar(int id){
        InfraController ic = new InfraController();
        return ic.get(id);
    }

    public int apagar(){
        InfraController ic = new InfraController();
        return ic.delete(getId());
    }

    @Override
    public int registrarFalha(int dia, int mes, int ano, String descricao){
        int rows = FalhaController.registrarFalha(FalhaController.FALHA_INFRA, getId(), dia, mes, ano, descricao);
        if(rows>0)
        setFalhas(FalhaController.getFalhas(FalhaController.FALHA_INFRA, getId()));
        return rows;
    }

    @Override
    public int registrarIp(String novoIp){
        InfraController ic = new InfraController();
        return ic.updateIp(getId(), novoIp);
    }

    @Override
    public int deletarIp(){
        if(getIp()==null){
            return 0;
        }
        else{
            InfraController ic = new InfraController();
            return ic.deleteIp(getId());
        }
    }
}
