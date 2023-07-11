package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashSet;

public class FalhaController{
    public static final int FALHA_INFRA = 1;
    public static final int FALHA_USOFINAL =2;

    public static LinkedHashSet<Falha> getFalhas(int tipo, int id){
        LinkedHashSet<Falha> falhas = new LinkedHashSet<Falha>();

        try{
            Connection conexao = Conexao.conectar();

            String sql = "";

            if(tipo==FalhaController.FALHA_INFRA)
                sql = "SELECT * FROM falha WHERE infra_id="+id+" ORDER BY id";
            if(tipo==FalhaController.FALHA_USOFINAL)
                sql = "SELECT * FROM falha WHERE usofinal_id="+id+" ORDER BY id";

            if(sql.equals(""))
                return falhas;

            Statement statement = conexao.createStatement();
            ResultSet resultado = statement.executeQuery(sql);

            while (resultado.next()){
                Falha f = new Falha(resultado.getInt("id"),resultado.getDate("datafalha").toLocalDate(),resultado.getString("descricao"));
                falhas.add(f);
            }
            conexao.close();
            return falhas;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return falhas;
        }
    }

    public static int registrarFalha(int tipo, int idEquipamento, int dia, int mes, int ano, String descricao){
        try{
            Connection conexao = Conexao.conectar();

            String sql = "";

            if(tipo==FalhaController.FALHA_INFRA)
                sql = "INSERT INTO Falha (infra_id, dataFalha, descricao) VALUES (" + idEquipamento + ", '" + dia + "/" + mes + "/" + ano + "','" + descricao +"')";
            if(tipo==FalhaController.FALHA_USOFINAL)
                sql = "INSERT INTO Falha (usofinal_id, dataFalha, descricao) VALUES (" + idEquipamento + ", '" + dia + "/" + mes + "/" + ano + "','" + descricao +"')";

            if(sql.equals(""))
                return 0;

            Statement statement = conexao.createStatement();
            int rows = statement.executeUpdate(sql);
            conexao.close();
            return rows;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
