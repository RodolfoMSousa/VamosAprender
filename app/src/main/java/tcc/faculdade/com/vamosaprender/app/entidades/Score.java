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
public class Score {
    private int scoreId, jogoId,alunoId, pontuacao, categoriaId;
    
    public Score(){
        
    }
    
    public Score(int jogoId, int alunoId, int pontuacao, int categoriaId){
        setJogoId(jogoId);
        setAlunoId(alunoId);
        setPontuacao(pontuacao);
        setCategoriaId(categoriaId);
    }

    /**
     * @return the scoreId
     */
    public int getScoreId() {
        return scoreId;
    }

    /**
     * @param scoreId the scoreId to set
     */
    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
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
     * @return the alunoId
     */
    public int getAlunoId() {
        return alunoId;
    }

    /**
     * @param alunoId the alunoId to set
     */
    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }

    /**
     * @return the pontuacao
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * @param pontuacao the pontuacao to set
     */
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    /**
     * @return the datacadastro
     */
//    public int getDatacadastro() {
//        return datacadastro;
//    }
//
//    /**
//     * @param datacadastro the datacadastro to set
//     */
//    public void setDatacadastro(int datacadastro) {
//        this.datacadastro = datacadastro;
//    }

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
}
