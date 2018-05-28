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
public class Categoria {
    private int categoriaId;
    private String nome;
    private int ativo;
    private int dataCadastro;
    
    public Categoria(){
        
    }
    
    public Categoria(int categoriaId, String nome, int ativo, int dataCadastro){
        this.categoriaId = categoriaId;
        this.ativo = ativo;
        this.dataCadastro = dataCadastro;
        this.nome = nome;
    }

    /**
     * @return the categoriaId
     */
    public int getCategoriaId() {
        return categoriaId;
    }

    /**
     * @param categoriaId the categoriaId to set
     */
    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
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
    
}
