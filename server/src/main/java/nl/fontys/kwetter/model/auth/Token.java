package nl.fontys.kwetter.model.auth;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Token {
    String token;

    public Token(String token) {

        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
