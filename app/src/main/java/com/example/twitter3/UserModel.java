package com.example.twitter3;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "user")
public class UserModel {

    @org.greenrobot.greendao.annotation.Id(autoincrement = true)
    private Long Id = 0L;
    @Property(nameInDb = "usuario")
    private String usuario;
    @Property(nameInDb = "Login")
    private String Login;
    @Property(nameInDb = "tweets")
    private String tweets;
    @Property(nameInDb = "prueba")
    private String prueba;
    @Generated(hash = 2143677987)
    public UserModel(Long Id, String usuario, String Login, String tweets,
            String prueba) {
        this.Id = Id;
        this.usuario = usuario;
        this.Login = Login;
        this.tweets = tweets;
        this.prueba = prueba;
    }
    @Generated(hash = 782181818)
    public UserModel() {
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    public String getUsuario() {
        return this.usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getLogin() {
        return this.Login;
    }
    public void setLogin(String Login) {
        this.Login = Login;
    }
    public String getTweets() {
        return this.tweets;
    }
    public void setTweets(String tweets) {
        this.tweets = tweets;
    }
    public String getPrueba() {
        return this.prueba;
    }
    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }
}
