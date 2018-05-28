/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.faculdade.com.vamosaprender.app.entidades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author Rodolfo
 */
public class Login {
    private int usuarioId;
    private String userName, senha;
    private int dataLogin;
    
    public Login(){
        
    }

    /**
     * @return the usuarioId
     */
    public int getUsuarioId() {
        return usuarioId;
    }

    /**
     * @param usuarioId the usuarioId to set
     */
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the dataLogin
     */
    public int getDataLogin() {
        return dataLogin;
    }

    /**
     * @param dataLogin the dataLogin to set
     */
    public void setDataLogin(int dataLogin) {
        this.dataLogin = dataLogin;
    }

    public String toString(){
        return "{" +
                "usuarioId" +
                ":" +usuarioId+
                "userName" +
                ":" +userName +
                "senha" +
                ":" +senha+
                "dataLogin" +
                ":"+dataLogin;
    }
    public String toJson(Login l){
        Gson gson = new Gson();
        return gson.toJson(l);
    }
    
    
    
}
