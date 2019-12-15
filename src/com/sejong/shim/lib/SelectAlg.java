package com.sejong.shim.lib;

import com.sejong.shim.DTO.ClassifierDTO;

import java.util.ArrayList;

public class SelectAlg {
    private ArrayList<ClassifierDTO> value;

    /**
     * Instantiates a new Select alg.
     *
     * @param value the value
     */
    public SelectAlg(ArrayList<ClassifierDTO> value) {
        this.value = value;
    }

    /**
     * Print all data.
     */
    public void printAllData() {
        for (int i = 0; i < value.size(); i++) {
            System.out.println(value.get(i).getName());
            System.out.println(String.format("%.5f",value.get(i).getMAE()));
            System.out.println(String.format("%.5f\n",value.get(i).getRMSE()));
        }
    }

    /**
     * Print data.
     *
     * @param value the value
     */
    public void printData(ClassifierDTO value) {
        System.out.println(value.getName());
        System.out.println(value.getMAE());
        System.out.println(value.getRMSE() + "\n");
    }

    /**
     * Select low mae classifier dto.
     *
     * @return the classifier dto
     */
    public ClassifierDTO selectLowMAE() {
        ClassifierDTO result = value.get(0);
        double min = value.get(0).getMAE();

        for (int i = 1; i < value.size(); i++) {
            if (min > value.get(i).getMAE()) {
                min = value.get(i).getMAE();
                result = value.get(i);
            }//if
        }//for

        return result;
    }//selectLowMAE

    /**
     * Select low rmse classifier dto.
     *
     * @return the classifier dto
     */
    public ClassifierDTO selectLowRMSE() {
        ClassifierDTO result = value.get(0);
        double min = value.get(0).getRMSE();

        for (int i = 1; i < value.size(); i++) {
            if (min > value.get(i).getRMSE()) {
                min = value.get(i).getRMSE();
                result = value.get(i);
            }//if
        }//for

        return result;
    }//selectLowMAE
}
