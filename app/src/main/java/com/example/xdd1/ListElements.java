package com.example.xdd1;

public class ListElements {
    private String nombre;
    private String tel;
    private String num;

    public ListElements(String nombre, String tel, String num) {
        this.nombre = nombre;
        this.tel = tel;
        this.num = num;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
