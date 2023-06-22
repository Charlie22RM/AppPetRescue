package com.example.petrescue;

public class UserService {
    private static UserService instancia;
    private int userId;
    private UserService() {
        // Constructor privado para evitar la creación directa de instancias
    }

    public static UserService getInstancia() {
        if (instancia == null) {
            instancia = new UserService();
        }
        return instancia;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
