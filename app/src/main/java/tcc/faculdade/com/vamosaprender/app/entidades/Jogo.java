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
public class Jogo extends Categoria{
    private int jogoId;
    private String nome;
    
    public Jogo(){
        
    }
    public Jogo(int jogoId, String nome, int categoriaId){
        super.setCategoriaId(categoriaId);
        this.jogoId = jogoId;
        this.nome = nome;
    }

    /**
     * @return the jogoId
     */
    public int getJogoId() {
        return jogoId;
    }

    /**
     * @param jogoId the jogoId to set
     */
    public void setJogoId(int jogoId) {
        this.jogoId = jogoId;
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
}
