package com.sejong.shim;

import com.sejong.shim.lib.data.Convert;
import com.sejong.shim.lib.Train;

import java.util.ArrayList;

public class Init {
    public static void main(String[] args) {
        Convert convert = new Convert();
        ArrayList<String> arffFileList = convert.convertAllFile();

//        for(String fileName: arffFileList){
//            Train train = new Train(fileName);
//            train.prepareData();
//        }

        for(String fileName: arffFileList){
            Train train = new Train(fileName);
            train.prepareDataFold();
        }
    }
}
