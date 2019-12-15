package com.sejong.shim.lib.ML;

import com.sejong.shim.DTO.ClassifierDTO;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.lazy.IBk;
import weka.classifiers.meta.Bagging;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.neighboursearch.LinearNNSearch;


import java.util.ArrayList;

public class MLAlgorithmBase implements MLAlgoithm {
    private ArrayList<ClassifierDTO> result;

    private Instances trainingDataSet;
    private Instances testingDataSet;

    public MLAlgorithmBase(Instances trainingDataSet, Instances testingDataSet) {
        result = new ArrayList<>();

        this.trainingDataSet = trainingDataSet;
        this.testingDataSet = testingDataSet;
    }

    public ArrayList<ClassifierDTO> getResult() {
        return result;
    }

    public void TrainMachineLearningPool() {
//        MultilayerPerceptronAlg();
//        LinearRegressionAlg();
//        KNNAlg();
//        RandomForestAlg();
        BaggingAlg();
    }

    public void RandomForestAlg() {
        try {
            RandomForest randomForest = new RandomForest();

            randomForest.setBatchSize(50 + "");
            randomForest.setMaxDepth(11);
            randomForest.setNumDecimalPlaces(5);

            randomForest.buildClassifier(trainingDataSet);
//            System.out.println("\n" + randomForest);

            Evaluation evaluation = new Evaluation(trainingDataSet);
//            evaluation.crossValidateModel(randomForest, trainingDataSet, 10, new Random(1));
            evaluation.evaluateModel(randomForest, testingDataSet);

//            System.out.println(evaluation.toSummaryString());
//            System.out.println("MAE: " + evaluation.meanAbsoluteError());
//            System.out.println("RMSE: " + evaluation.errorRate());

            ClassifierDTO value = new ClassifierDTO("RandomForest", randomForest, evaluation.meanAbsoluteError(), evaluation.errorRate());
            result.add(value);

            System.out.println(">>>>>>>>RandomForest DONE");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(801);
        }
    }

    public void LinearRegressionAlg() {
        try {
            LinearRegression linearRegression = new LinearRegression();
            linearRegression.setBatchSize(50 + "");

            linearRegression.setAttributeSelectionMethod(new SelectedTag(LinearRegression.SELECTION_GREEDY, LinearRegression.TAGS_SELECTION));
            linearRegression.setEliminateColinearAttributes(false);

            linearRegression.buildClassifier(trainingDataSet);
//            System.out.println("\n" + linearRegression);

            Evaluation evaluation = new Evaluation(trainingDataSet);
            evaluation.evaluateModel(linearRegression, testingDataSet);

//            System.out.println(evaluation.toSummaryString());
//            System.out.println("MAE: " + evaluation.meanAbsoluteError());
//            System.out.println("RMSE: " + evaluation.errorRate());

            ClassifierDTO value = new ClassifierDTO("LinearRegression", linearRegression, evaluation.meanAbsoluteError(), evaluation.errorRate());
            result.add(value);
            System.out.println(">>>>>>>>LinearRegression DONE");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(802);
        }
    }

    public void MultilayerPerceptronAlg() {
        try {
            MultilayerPerceptron multilayerPerceptron = new MultilayerPerceptron();
            multilayerPerceptron.setHiddenLayers(10 + "");
            multilayerPerceptron.setLearningRate(0.1);
            multilayerPerceptron.setBatchSize(50 + "");

            multilayerPerceptron.buildClassifier(trainingDataSet);
//            System.out.println("\n" + multilayerPerceptron);

            Evaluation evaluation = new Evaluation(trainingDataSet);
            evaluation.evaluateModel(multilayerPerceptron, testingDataSet);

//            System.out.println(evaluation.toSummaryString());
//            System.out.println("MAE: " + evaluation.meanAbsoluteError());
//            System.out.println("RMSE: " + evaluation.errorRate());

            ClassifierDTO value = new ClassifierDTO("MLP", multilayerPerceptron, evaluation.meanAbsoluteError(), evaluation.errorRate());
            result.add(value);
            System.out.println(">>>>>>>>MLP DONE");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(803);
        }
    }

    public void KNNAlg() {
        try {
            IBk knn = new IBk(15);

            LinearNNSearch linearNNSearch = new LinearNNSearch();
            knn.setBatchSize(50 + "");
            knn.setCrossValidate(false);
            knn.setDebug(false);
            knn.setDoNotCheckCapabilities(false);
            knn.setMeanSquared(false);
            knn.setNearestNeighbourSearchAlgorithm(linearNNSearch);
            knn.setNumDecimalPlaces(5);
            knn.setWindowSize(0);

            knn.buildClassifier(trainingDataSet);
//            System.out.println("\n" + knn);

            Evaluation evaluation = new Evaluation(trainingDataSet);
            evaluation.evaluateModel(knn, testingDataSet);

//            System.out.println(evaluation.toSummaryString());
//            System.out.println("MAE: " + evaluation.meanAbsoluteError());
//            System.out.println("RMSE: " + evaluation.errorRate());

            ClassifierDTO value = new ClassifierDTO("KNN", knn, evaluation.meanAbsoluteError(), evaluation.errorRate());
            result.add(value);
            System.out.println(">>>>>>>>KNN DONE");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(804);
        }
    }

    public void BaggingAlg() {
        try {
            Bagging bagging = new Bagging();
            LinearRegression linearRegression = new LinearRegression();
//            RandomForest randomForest = new RandomForest();
//            randomForest.setBatchSize(100 + "");
//            randomForest.setMaxDepth(5);
//            randomForest.setNumIterations(50);//trees
            bagging.setClassifier(linearRegression);
            bagging.setNumIterations(10);

            bagging.buildClassifier(trainingDataSet);
//            System.out.println("\n" + bagging);

            Evaluation evaluation = new Evaluation(trainingDataSet);
            evaluation.evaluateModel(bagging, testingDataSet);

//            System.out.println(evaluation.toSummaryString());
//            System.out.println("MAE: " + evaluation.meanAbsoluteError());
//            System.out.println("RMSE: " + evaluation.errorRate());

            ClassifierDTO value = new ClassifierDTO("Bagging", bagging, evaluation.meanAbsoluteError(), evaluation.errorRate());
            result.add(value);

            System.out.println(">>>>>>>>Bagging DONE");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(804);
        }
    }
}
