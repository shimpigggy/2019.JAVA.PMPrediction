package com.sejong.shim.lib.data;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

import java.io.File;
import java.util.ArrayList;

/**
 * The type Convert.
 */
public class Convert {
    /**
     * CSV_PATH: csv 파일이 있는 폴더 path
     * ARFF_PATH: arff 파일이 있는 폴더 path
     **/
    private static final String MAIN_PATH = "./src/com/sejong/shim/data/";
    private static final String CITY = "Incheon";
    private static final String CSV_PATH = MAIN_PATH + CITY + "/csv/";
    private static final String ARFF_PATH = MAIN_PATH + CITY + "/arff/";
    private final String arffExtension = ".arff";

    /**
     * Instantiates a new Convert.
     */
    public Convert() {
    }

    /**
     * convertAllFile:
     * 해당 폴더에 arff 파일있는지 여부에 따라 csv->arff 및 파일 path arraylist 작성
     *
     * @return the array list
     */
    public ArrayList<String> convertAllFile() {
        ArrayList<String> fileList = new ArrayList<>();

        if (!isFile()) {
            File csvDir = new File(CSV_PATH);
            File[] csvFileList = csvDir.listFiles();

            ArrayList<String> fileName = new ArrayList<>();

            for (File file : csvFileList)
                fileName.add(getFileName(file.getName()));

            for (int i = 0; i < csvFileList.length; i++) {
                convertCSVtoArff(csvFileList[i].getPath(), ARFF_PATH + fileName.get(i) + arffExtension);
            }
        }

        fileList = getArffFileList();

        return fileList;
    }

    /**
     * getFileName:
     *
     * @param fileName: 파일 확장자를 포함한 파일 이름
     * @return 파일 확장자를 제외한 파일 이름
     **/
    private String getFileName(String fileName) {
        int pos = fileName.lastIndexOf(".");

        return fileName.substring(0, pos);
    }

    /**
     * isFile:
     * csv파일의 개수와 arff 파일의 개수 확인 후 서로 맞지 않으면
     * 해당 arff 파일 없다고 판단
     **/
    private boolean isFile() {
        File arffDir = new File(ARFF_PATH);
        File[] arffFileList = arffDir.listFiles();

        File csvDir = new File(CSV_PATH);
        File[] csvFileList = csvDir.listFiles();

        if (arffFileList.length == csvFileList.length) {
            return true;
        } else
            return false;
    }

    /**
     * getArffFileList:
     *
     * @return arff 폴더에 있는 파일 path의 arrayList
     **/
    private ArrayList<String> getArffFileList() {
        ArrayList<String> arffFileList = new ArrayList<>();

        File arffDir = new File(ARFF_PATH);
        File[] array = arffDir.listFiles();

        for (int i = 0; i < array.length; i++) {
            arffFileList.add(array[i].getPath());
        }

        return arffFileList;
    }

    /**
     * convertCSVtoArff:
     *
     * @param inputFilePath:  csv 파일의 path
     * @param outputFilePath: arff파일의 저장 path
     **/
    private void convertCSVtoArff(String inputFilePath, String outputFilePath) {
        File inputFile = new File(inputFilePath);
        File outputFile = new File(outputFilePath);

        try {
            // load the CSV file (input file)
            CSVLoader loader = new CSVLoader();
            loader.setSource(inputFile);
            Instances data = loader.getDataSet();

            // save as an  ARFF (output file)
            ArffSaver saver = new ArffSaver();
            saver.setInstances(data);
            saver.setDestination(outputFile);
            saver.setFile(outputFile);
            saver.writeBatch();

            System.out.println("Convert Complete: csv -> arff --->> " + outputFilePath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(700);
        }
    }//Covert
}
