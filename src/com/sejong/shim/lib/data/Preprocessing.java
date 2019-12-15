package com.sejong.shim.lib.data;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * The type Preprocessing.
 */
public class Preprocessing {
    /**
     * arffFilePath: 실험에 사용할 파일의 path
     * <p>
     * trainingSet: 실험할 dataSet
     * testSet: 시험할 testSet
     * dataSet: trainingSet+ testSet으로 분리하지 않은 원본 dataSet
     **/
    private String arffFilePath = "";

    private Instances trainingSet;
    private Instances testSet;
    private Instances dataset;

    /**
     * 전체 dataSet에서 trainingSet과 testData의 비율
     */
    final int PERCENTAGE_SPLIT = 70;

    /**
     * Instantiates a new Preprocessing.
     *
     * @param arffFilePath the arff file path
     * @param b            traingingSet과 testData으로 나눌 여부(true: 나눔)
     */
    public Preprocessing(String arffFilePath, Boolean b) {
        this.arffFilePath = arffFilePath;
        loadArffFile();

        if (b)
            splitDataSet();

    }

    /**
     * Load arff File
     */
    private void loadArffFile() {
        DataSource source = null;
        try {
            source = new DataSource(arffFilePath);
            dataset = source.getDataSet();
            dataset.setClassIndex(0);
            System.out.println("dataset Num: " + dataset.numInstances());
            System.out.println("dataset Class: " + dataset.classAttribute() + "\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(701);
        }
    }//loadArffData

    /**
     * Split data set.
     */
    private void splitDataSet() {
        int trainSize = (int) Math.round(dataset.numInstances() * PERCENTAGE_SPLIT / 100);
        int testSize = dataset.numInstances() - trainSize;

        this.trainingSet = new Instances(dataset, 0, trainSize);
        this.testSet = new Instances(dataset, trainSize, testSize);

        System.out.println("trainingSet Num: " + trainingSet.numInstances());
        System.out.println("testSet Num: " + testSet.numInstances());
    }

    /**
     * Gets dataset.
     *
     * @return the dataset
     */
    public Instances getDataset() {
        return this.dataset;
    }

    /**
     * Gets training set.
     *
     * @return the training set
     */
    public Instances getTrainingSet() {
        return this.trainingSet;
    }

    /**
     * Gets test set.
     *
     * @return the test set
     */
    public Instances getTestSet() {
        return this.testSet;
    }
}
