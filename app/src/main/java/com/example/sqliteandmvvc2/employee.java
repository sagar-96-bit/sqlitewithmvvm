package com.example.sqliteandmvvc2;

public class employee {
    private int id;
    private String empName;
    private String addr;
    private String birhtDate;
    private String gender;

    public employee(int id, String empName, String addr, String birhtDate, String gender) {
        this.id = id;
        this.empName = empName;
        this.addr = addr;
        this.birhtDate = birhtDate;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getBirhtDate() {
        return birhtDate;
    }

    public void setBirhtDate(String birhtDate) {
        this.birhtDate = birhtDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
