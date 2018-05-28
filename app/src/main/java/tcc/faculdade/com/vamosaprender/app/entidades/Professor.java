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
public class Professor extends Usuario{
    private int professorId, turmaId;
    
    public Professor(){
        
    }
    public Professor(int usuarioId, String nome,String sobrenome, int cpf,
            int dataNascimento, int ativo, int dataCadastro, int professorId, 
            int turmaId){
        super(usuarioId, nome, sobrenome, cpf, dataNascimento, ativo, dataCadastro);
        this.professorId = professorId;
        this.turmaId = turmaId;
    }

    /**
     * @return the professorId
     */
    public int getProfessorId() {
        return professorId;
    }

    /**
     * @param professorId the professorId to set
     */
    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    /**
     * @return the turmaId
     */
    public int getTurmaId() {
        return turmaId;
    }

    /**
     * @param turmaId the turmaId to set
     */
    public void setTurmaId(int turmaId) {
        this.turmaId = turmaId;
    }
}
