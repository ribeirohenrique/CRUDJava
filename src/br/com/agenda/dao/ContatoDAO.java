package br.com.agenda.dao;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;
import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    /*
     * CRUD
     * CREATE
     * READ
     * UPDATE
     * DELETE
     */

    public void save(Contato contato) {

        String sql = "INSERT INTO contatos(nome, idade, datacadastro) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Criar uma conexão com o bando de dados
            conn = ConnectionFactory.createConnectionToMariaDB();

            //Criar uma PreparedStatement para executar uma query
            pstm = conn.prepareStatement(sql);

            //Adicionar os valores que são esperados pela query
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

            //Executar a query
            pstm.executeQuery();
            System.out.println("Contato salvo com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            //Fechar as conexões
            try {
                //Se a conexão do Statement for diferente de null
                if (pstm != null) {
                    pstm.close();
                }
                //Se a conexão com o banco for diferente de null
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Contato> getContatos() {
        String sql = "SELECT * FROM contatos";
        List<Contato> contatos = new ArrayList<Contato>();

        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que recupera os dados do Banco. ***SELECT***
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMariaDB();
            pstm = conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                //Cria o contato que vai organizar as informacoes
                Contato contato = new Contato();

                //Recupera o ID
                contato.setId(rset.getInt("id"));
                //Recuperar o nome
                contato.setNome(rset.getString("nome"));
                //Recuperar a idade
                contato.setIdade(rset.getInt("idade"));
                //Recuperar a data de cadastro
                contato.setDataCadastro(rset.getDate("datacadastro"));

                contatos.add(contato);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return contatos;
    }
}
