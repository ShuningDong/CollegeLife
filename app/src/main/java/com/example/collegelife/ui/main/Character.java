package com.example.collegelife.ui.main;

public class Character {

    private String Name;
    private int GPA;
    private double Debt;
    private String Major;

    // constructor
    public Character(String name) {
        Name = name;
        GPA = 4;
        Debt = 0;
        Major = "";
    }


    ///////// methods /////////
    public String getName(){
        return this.Name;
    }

    public String getGPA(){
        String gpa = "";
        gpa += this.GPA;
        return gpa;
    }

    public double getDebt(){
        return this.Debt;
    }

    public void addLoan(double amount){
        this.Debt += amount;
    }

    public void subtractDebt(double amount){
        this.Debt -= amount;
    }

    public String getMajor(){
        return this.Major;
    }
}
