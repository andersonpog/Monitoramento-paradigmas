package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashSet;

public class UsoFinalController implements Crud<UsoFinal> {
    @Override
    public LinkedHashSet<UsoFinal> getAll() {
        LinkedHashSet<UsoFinal> equipamentos = new LinkedHashSet<UsoFinal>();

        try{
            Connection conexao = Conexao.conectar();

            String sql = "SELECT * FROM Usofinal ORDER BY id ASC";
            Statement statement = conexao.createStatement();
            ResultSet resultado = statement.executeQuery(sql);


            while (resultado.next()){
                UsoFinal usofinal = new UsoFinal(resultado.getInt("id"),resultado.getString("nome"),resultado.getString("mac"),resultado.getString("ip"));
                equipamentos.add(usofinal);
            }
            conexao.close();
            return equipamentos;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return equipamentos;
        }
    }

    @Override
    public UsoFinal get(int id) {
        try{
            Connection conexao = Conexao.conectar();
            String sql = "SELECT * FROM Usofinal WHERE id="+id;
            Statement statement = conexao.createStatement();
            ResultSet resultado = statement.executeQuery(sql);

            if(resultado.next()){
                UsoFinal usoFinal = new UsoFinal(resultado.getInt("id"),resultado.getString("nome"),resultado.getString("mac"),resultado.getString("ip"));
                conexao.close();
                return usoFinal;
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

    @Override
    public int post(UsoFinal u) {
        try{
            Connection conexao = Conexao.conectar();
            String sql = "INSERT INTO Usofinal (nome, mac, ip) VALUES ('" + u.getNome() + "', '" + u.getMac() + "', '" + u.getIp() + "')";
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
    public int delete(int id) {
        try{
            Connection conexao = Conexao.conectar();
            String sqlFalhas = "DELETE FROM Falha WHERE usofinal_id="+id;
            String sqlUsuarios = "DELETE FROM Usuario WHERE id="+id;
            String sql = "DELETE FROM Usofinal WHERE id="+id;
            Statement statement = conexao.createStatement();
            int rowsFalhas = statement.executeUpdate(sqlFalhas);
            int rowsUsuarios = statement.executeUpdate(sqlUsuarios);
            int rows = statement.executeUpdate(sql);
            conexao.close();
            return rows;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public LinkedHashSet<Funcionario> getAllUsuarios(int id){
        LinkedHashSet<Funcionario> funcionarios = new LinkedHashSet<Funcionario>();

        try{
            Connection conexao = Conexao.conectar();

            String sql = "SELECT * FROM Funcionario AS f INNER JOIN Usuario AS u ON f.cpf=u.cpf WHERE u.id="+id;
            Statement statement = conexao.createStatement();
            ResultSet resultado = statement.executeQuery(sql);


            while (resultado.next()){
                Funcionario funcionario = new Funcionario(resultado.getString("cpf"),resultado.getString("nome"),resultado.getString("cargo"));
                funcionarios.add(funcionario);
            }
            conexao.close();
            return funcionarios;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return funcionarios;
        }
    }

    public int updateIp(int id, String novoIp){
        try{
            Connection conexao = Conexao.conectar();
            String sql = "UPDATE Usofinal SET ip = '" + novoIp + "' WHERE id="+id;
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
            String sql = "UPDATE Usofinal SET ip = null WHERE id="+id;
            Statement statement = conexao.createStatement();
            int rows = statement.executeUpdate(sql);
            conexao.close();
            return  rows;

        } catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int addUsuario(String cpf, int id){
        try{
            Connection conexao = Conexao.conectar();
            String sql = "INSERT INTO Usuario (cpf, id) VALUES ('" + cpf + "', " + id + ")";
            Statement statement = conexao.createStatement();
            int rows = statement.executeUpdate(sql);
            conexao.close();
            return  rows;

        } catch (Exception e){
//            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int deleteUsuario(String cpf, int id){
        try{
            Connection conexao = Conexao.conectar();
            String sql = "DELETE FROM Usuario WHERE cpf='" + cpf + "' AND id=" + id;
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
