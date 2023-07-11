package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashSet;

public class FuncionarioController implements Crud<Funcionario>{
    @Override
    public LinkedHashSet<Funcionario> getAll() {
        LinkedHashSet<Funcionario> funcionarios = new LinkedHashSet<Funcionario>();

        try{
            Connection conexao = Conexao.conectar();

            String sql = "SELECT * FROM Funcionario";
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

    public LinkedHashSet<Funcionario> getAllEquipamentos(String cpf){
        LinkedHashSet<Funcionario> funcionarios = new LinkedHashSet<Funcionario>();

        try{
            Connection conexao = Conexao.conectar();

            String sql = "SELECT * FROM Usofinal AS f INNER JOIN Usuarios AS u ON f.id=u.id WHERE u.cpf="+cpf;
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

    @Override
    public Funcionario get(int id){
        return null;
    }

    public Funcionario get(String cpf) {
        try{
            Connection conexao = Conexao.conectar();
            String sql = "SELECT * FROM Funcionario WHERE cpf="+cpf;
            Statement statement = conexao.createStatement();
            ResultSet resultado = statement.executeQuery(sql);

            if(resultado.next()){
                Funcionario funcionario = new Funcionario(resultado.getString("cpf"),resultado.getString("nome"),resultado.getString("cargo"));
                conexao.close();
                return funcionario;
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
    public int post(Funcionario dado) {
        try{
            Connection conexao = Conexao.conectar();
            String sql = "INSERT INTO Funcionario (cpf, nome, cargo) VALUES ('" + dado.getCpf() + "', '" + dado.getNome() + "', '" + dado.getCargo() + "')";
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
        return 0;
    }

    public int delete(String cpf){
        try{
            Connection conexao = Conexao.conectar();
            String sqlEquipamentos = "DELETE FROM Usuario WHERE cpf='"+cpf+"'";
            String sql = "DELETE FROM Funcionario WHERE cpf='"+cpf+"'";
            Statement statement = conexao.createStatement();
            int rowsUsuarios = statement.executeUpdate(sqlEquipamentos);
            int rows = statement.executeUpdate(sql);
            conexao.close();
            return rows;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
