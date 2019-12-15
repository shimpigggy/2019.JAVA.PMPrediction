package com.sejong.shim.DTO;

import weka.classifiers.Classifier;

public class ClassifierDTO {
    /**
     * name: algorithm 이름
     * alg: 해당 alg
     * MAE, RMSE: 측정 값
     * **/
    private String name;
    private Classifier alg;
    private double MAE;
    private double RMSE;

    public ClassifierDTO(){
    }

    public ClassifierDTO(String name, Classifier alg, double MAE, double RMSE) {
        this.name = name;
        this.alg = alg;
        this.MAE = MAE;
        this.RMSE = RMSE;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlg(Classifier alg) {
        this.alg = alg;
    }

    public String getName() {
        return name;
    }

    public Classifier getAlg() {
        return alg;
    }

    public void setMAE(double MAE) {
        this.MAE = MAE;
    }

    public void setRMSE(double RMSE) {
        this.RMSE = RMSE;
    }

    public double getMAE() {
        return MAE;
    }

    public double getRMSE() {
        return RMSE;
    }
}
