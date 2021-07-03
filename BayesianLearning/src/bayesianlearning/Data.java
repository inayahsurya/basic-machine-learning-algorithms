package bayesianlearning;

import java.util.List;
import java.util.Scanner;

public class Data {

    private String dataTraining[][];
    private String dataTesting[];
    private final String fitur[] = new String[]{
        "Cuaca \t", "Temperatur", "Kecepatan angin"};
    private int jumlahYa;
    private int jumlahTidak;
    private float peluangYa;
    private float peluangTidak;

    public Data(List<List<String>> records) {
        setDataTraining(records);
        setPeluangLabel();
        setDataTesting();
    }

    public final void setDataTraining(List<List<String>> records) {
        dataTraining = new String[records.size()][];
        for (int i = 0; i < dataTraining.length; i++) {
            dataTraining[i] = new String[records.get(i).size()];
        }
        for (int i = 0; i < records.size(); i++) {
            for (int j = 0; j < records.get(i).size(); j++) {
                dataTraining[i][j] = records.get(i).get(j);
            }
        }
    }

    public final void setDataTesting() {
        dataTesting = new String[fitur.length];
        Scanner str = new Scanner(System.in);
        System.out.println("Bagaimana kondisi hari ini?");
        for (int i = 0; i < fitur.length; i++) {
            System.out.print(fitur[i] + "\t: ");
            dataTesting[i] = str.nextLine();
        }
    }

    public final void setPeluangLabel() {
        for (String[] dataTraining1 : dataTraining) {
            if (dataTraining1[dataTraining1.length - 1].equals("Ya")) {
                jumlahYa++;
            } else {
                jumlahTidak++;
            }
        }
        peluangYa = (float) jumlahYa / dataTraining.length;
        peluangTidak = (float) jumlahTidak / dataTraining.length;

    }

    public String[][] getDataTraining() {
        return dataTraining;
    }

    public String[] getDataTesting() {
        return dataTesting;
    }

    public float getPeluangYa() {
        return peluangYa;
    }

    public float getPeluangTidak() {
        return peluangTidak;
    }

    public int getJumlahYa() {
        return jumlahYa;
    }

    public int getJumlahTidak() {
        return jumlahTidak;
    }

}
