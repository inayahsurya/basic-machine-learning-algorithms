package bayesianlearning;

import java.util.List;

public class NaiveBayesian {

    private float peluangFakta[][];
    private String classifierResult;
    private float resultYa = 1;
    private float resultTidak = 1;
    private int banyakLabel = 2;
    private Data data;

    public NaiveBayesian(List<List<String>> records) {
        data = new Data(records);
    }

    public void fakta() {
        peluangFakta = new float[data.getDataTesting().length][banyakLabel];

        for (int i = 0; i < data.getDataTesting().length; i++) {
            for (int j = 0; j < data.getDataTraining().length; j++) {
                if (data.getDataTesting()[i].equalsIgnoreCase(data.getDataTraining()[j][i])) {

                    if (data.getDataTraining()[j][data.getDataTraining()[j].length - 1].equalsIgnoreCase("Ya")) {
                        peluangFakta[i][0]++;
                    } else {
                        peluangFakta[i][1]++;
                    }
                }
            }
            peluangFakta[i][0] /= data.getJumlahYa(); // peluang fakta YA
            peluangFakta[i][1] /= data.getJumlahTidak(); // peluang fakta TIDAK

        }
    }

    public void classifier() {
        for (int i = 0; i < peluangFakta.length; i++) {
            if (!data.getDataTesting()[i].equals("")) {
                resultYa *= peluangFakta[i][0];
                resultTidak *= peluangFakta[i][1];
            }
        }
        resultYa *= data.getPeluangYa();
        resultTidak *= data.getPeluangTidak();
    }

    public void result() {
        if (resultYa > resultTidak) {
            classifierResult = "Ya";
        } else if (resultYa == resultTidak) {
            classifierResult = "Ya atau tidak";
            if(resultYa==0.0)
                classifierResult = "Bukan keduanya";
        } else {
            classifierResult = "Tidak";
        }
        System.out.println();
        System.out.println("Peluang olahraga \t: " + resultYa);
        System.out.println("Peluang tidak olahraga \t: " + resultTidak);
        System.out.println();
        System.out.println("Keputusan olahraga \t: " + classifierResult);
    }
}
