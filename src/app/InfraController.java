package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashSet;

public class InfraController implements Crud<Infra> {

    @Override
    public LinkedHashSet<Infra> getAll() {

        LinkedHashSet<Infra> equipamentos = new LinkedHashSet<Infra>();

        try{
            Connection conexao = Conexao.conectar();

            String sql = "SELECT * FROM infra ORDER BY id ASC";
            Statement statement = conexao.createStatement();
            ResultSet resultado = statement.executeQuery(sql);


            while (resultado.next()){
                Infra infra = new Infra(resultado.getInt("id"),resultado.getString("nome"),resultado.getString("mac"),resultado.getString("ip"));
                equipamentos.add(infra);
            }
            conexao.close();
            return equipamentos;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return equipamentos;
        }
    }

    @Override
    public int post(Infra i){
        try{
            Connection conexao = Conexao.conectar();
            String sql = "INSERT INTO Infra (nome, mac, ip) VALUES ('" + i.getNome() + "', '" + i.getMac() + "', '" + i.getIp() + "')";
            Statement statement = conexao.createStatement();
            int rows = statement.executeUpdate(sql);
            conexao.close();
            return  rows;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public Infra get(int id){
        try{
            Connection conexao = Conexao.conectar();
            String sql = "SELECT * FROM Infra WHERE id="+id;
            Statement statement = conexao.createStatement();
            ResultSet resultado = statement.executeQuery(sql);

            if(resultado.next()){
                Infra infra = new Infra(resultado.getInt("id"),resultado.getString("nome"),resultado.getString("mac"),resultado.getString("ip"));
                conexao.close();
                return infra;
            }
            else{
                conexao.close();
                return null;
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override public int delete(int id){
        try{
            Connection conexao = Conexao.conectar();
            String sqlFalhas = "DELETE FROM Falha WHERE infra_id="+id;
            String sql = "DELETE FROM Infra WHERE id="+id;
            Statement statement = conexao.createStatement();
            int rowsFalhas = statement.executeUpdate(sqlFalhas);
            int rows = statement.executeUpdate(sql);
            conexao.close();
            return rows;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int updateIp(int id, String novoIp){
        try{
            Connection conexao = Conexao.conectar();
            String sql = "UPDATE Infra SET ip = '" + novoIp + "' WHERE id="+id;
            Statement statement = conexao.createStatement();
            int rows = statement.executeUpdate(sql);
            conexao.close();
            return  rows;

        } catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int deleteIp(int id){
        try{
            Connection conexao = Conexao.conectar();
            String sql = "UPDATE Infra SET ip = null WHERE id="+id;
            Statement statement = conexao.createStatement();
            int rows = statement.executeUpdate(sql);
            conexao.close();
            return  rows;

        } catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
