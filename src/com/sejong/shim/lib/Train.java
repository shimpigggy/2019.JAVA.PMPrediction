package com.sejong.shim.lib;

import com.sejong.shim.lib.ML.MLAlgoithm;
import com.sejong.shim.lib.ML.MLAlgorithmBase;
import com.sejong.shim.lib.ML.MLAlgorithmFold;
import com.sejong.shim.DTO.ClassifierDTO;
import com.sejong.shim.lib.data.Preprocessing;
import weka.core.Instances;

/**
 * The type Train.
 */
public class Train {
    /**
     * filePath: 실험에 사용할 파일의 path
     * trainingSet: 실험할 dataSet
     * testSet: 시험할 testSet
     * dataSet: trainingSet+ testSet으로 분리하지 않은 원본 dataSet
     * <p>
     * MAE, RMSE: 실험한 alg 중 제일 오류가 낮게 나온 alg
     **/
    private String filePath;
    private Instances trainingSet;
    private Instances testSet;
    private Instances dataSet;

    private ClassifierDTO MAE;
    private ClassifierDTO RMSE;


    /**
     * Instantiates a new Train.
     *
     * @param path the path
     */
    public Train(String path) {
        this.filePath = path;
    }

    /**
     * trainingSet과 testSet으로 나누어 실험할 수 있도록 함
     */
    public void prepareData() {
        Preprocessing preprocessing = new Preprocessing(filePath, true);
        trainingSet = preprocessing.getTrainingSet();
        testSet = preprocessing.getTestSet();

        trainDataML();
    }

    /**
     * trainingSet과 testSet으로 하여 실험 시작
     */
    public void trainDataML() {
        MLAlgorithmBase MLAlgorithmBase = new MLAlgorithmBase(trainingSet, testSet);
        MLAlgorithmBase.TrainMachineLearningPool();

        selectClassifier(MLAlgorithmBase);
    }

    /**
     * trainingSet과 testSet으로 나누지 않게(dataSet 그대로) 실험할 수 있도록 함
     * K-Fold 사용.
     */
    public void prepareDataFold() {
        Preprocessing preprocessing = new Preprocessing(filePath, false);
        dataSet = preprocessing.getDataset();

        trainDataMLFold();
    }

    /**
     * dataSet을 이용하여 K-fold로 실험 시작
     */
    private void trainDataMLFold() {
        MLAlgorithmFold MLAlgorithmBase = new MLAlgorithmFold(dataSet);
        MLAlgorithmBase.TrainMachineLearningPool();

//        selectClassifier(MLAlgorithmBase);
        PrintOutput result = new PrintOutput(MLAlgorithmBase.getResult());
        result.printAllData();
    }

    /**
     * 실험 후 MAE와 RMSE에 대한 각각 최소 값을 가진 alg 찾기
     * @param mlAlgorithm 실험에 사용한 mlalg 이용
     * **/
    private void selectClassifier(MLAlgoithm mlAlgoithm) {
        PrintOutput result = new PrintOutput(mlAlgoithm.getResult());
        result.printAllData();

        MAE = result.selectLowMAE();
        RMSE = result.selectLowRMSE();

        System.out.println("MAE Lowest Value");
        result.printData(MAE);

        System.out.println("RMSE Lowest Value");
        result.printData(RMSE);
    }

    public ClassifierDTO getMAE() {
        return MAE;
    }

    public ClassifierDTO getRMSE() {
        return RMSE;
    }
}
