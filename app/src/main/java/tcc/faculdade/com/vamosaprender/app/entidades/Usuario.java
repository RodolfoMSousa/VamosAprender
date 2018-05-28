/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.faculdade.com.vamosaprender.app.entidades;

/**
 *
 * @author Rodolfo
 */
public class Usuario {
    protected int userId;
    protected String nome, sobrenome;
    protected int cpf, dataNascimento, ativo,dataCadastro;
    
    public Usuario(){
        
}
    public Usuario(int userId, String nome,String sobrenome, int cpf,
            int dataNascimento, int ativo, int dataCadastro){
        setUserId(userId);
        setNome(nome);
        setSobrenome(sobrenome);
        setCpf(cpf);
        setDataNascimento(dataNascimento);
        setAtivo(ativo);
        setDataCadastro(dataCadastro);
    }
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the sobrenome
     */
    public String getSobrenome() {
        return sobrenome;
    }

    /**
     * @param sobrenome the sobrenome to set
     */
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    /**
     * @return the cpf
     */
    public int getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the dataNascimento
     */
    public int getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @param dataNascimento the dataNascimento to set
     */
    public void setDataNascimento(int dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * @return the ativo
     */
    public int getAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    /**
     * @return the dataCadastro
     */
    public int getDataCadastro() {
        return dataCadastro;
    }

    /**
     * @param dataCadastro the dataCadastro to set
     */
    public void setDataCadastro(int dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    /**
     * @return the usuarioId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param usuarioId the usuarioId to set
     */
    public void setUserId(int usuarioId) {
        this.userId = usuarioId;
    }
    
    
    
}
