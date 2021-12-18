import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PollutionDataManager implements PollutionDataManagerInterface{

    private PollutionData[][][] data;
    private String[] riverNames;

    public PollutionDataManager(File dataFile, File countFile){
        setUpDataStorage(countFile);
        populateDataStorage(dataFile);
    }

    private void setUpDataStorage(File countFile){
        try {
            Scanner input = new Scanner(countFile);
            data = new PollutionData[Integer.parseInt(input.nextLine())][12][];
            riverNames = new String[data.length];
            int i =0;   // i keeps track of the current number of river we are on
            while(input.hasNextLine()){
                parseStorage(input.nextLine(), i);
                i++;
            }
            input.close();
        } catch (FileNotFoundException e) {
            data = new PollutionData[0][0][0];
        }
    }
    private void parseStorage(String str, int riverIndex){
        String[] temp = str.split(",");
        riverNames[riverIndex] = temp[0].toLowerCase();
        for(int i = 0; i < 12; i++){
            data[riverIndex][i] = new PollutionData[Integer.parseInt(temp[i+1])];
        }

    }

    private PollutionData parseData(String str){
        String[] temp = str.split(",");

        String riverName = temp[0];
        int month = Integer.parseInt(temp[1]);
        int day = Integer.parseInt(temp[2]);
        int arsenic = Integer.parseInt(temp[3]);
        int lead = Integer.parseInt(temp[4]);
        double fertilizer = Double.parseDouble(temp[5]);
        double pesticide = Double.parseDouble(temp[6]);

        return new PollutionData(riverName, month, day, arsenic, lead, fertilizer, pesticide);
    }

    private void populateDataStorage(File dataFile){
        try {
            Scanner input = new Scanner(dataFile);
            input.nextLine(); //skips the first line meant for human readability
            for(int i = 0; i < data.length; i++){
                for(int j = 0; j < data[i].length ; j++){
                    for(int k = 0; k < data[i][j].length; k++){
                        if(input.hasNext()){
                            PollutionData singleData = parseData(input.nextLine());
                            data[findRiver(singleData.riverName())][singleData.month()-1][k] = singleData; //-1 for offset
                        }
                    }
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getRiverName(int riverIndex) {
        return riverNames[riverIndex];
    }

    /**
     * {@inheritDoc}
     */
    public int findRiver(String riverName) {
        for (int i = 0; i < riverNames.length; i++){
            if(riverNames[i].equals(riverName.toLowerCase())){
                return i;
            }
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     */
    public int getRiverCount() {
        return riverNames.length;
    }

    /**
     * {@inheritDoc}
     */
    public int getPollutionReadingCount(int riverIndex, int monthNumber) {
        return data[riverIndex][monthNumber-1].length;
    }

    /**
     * {@inheritDoc}
     */
    public PollutionData getPollutionData(int riverIndex, int monthNumber, int dayIndex) {
        return data[riverIndex][monthNumber-1][dayIndex];
    }

    /**
     * {@inheritDoc}
     */
    public String generateArsenicReport() {
        //TODO : Finish the method report
        double[] monthlyRate = new double[12];
        double monthlyTemp = 0;
        String report = "ARSENIC REPORT\nRiver\t\t\t\t\t\t\t\t    1   \t2   \t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t\t11\t\t12\t\tAVG\n"
                +"-----------------------------        -----   -----   -----   -----   -----   -----   -----   -----   -----   -----   -----   -----     -----\n";
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data[i].length; j++){
                for(int k = 0; k < data[i][j].length; k++){
                    monthlyTemp = data[i][j][k].arsic();
                }
                monthlyRate[j] = monthlyTemp/data[i][j].length;
                monthlyTemp = 0;
            }
            report += riverNames[i] + formattedString(monthlyRate, riverNames[i].length()) + "\n";
        }
        return report;
    }
    private String formattedString(double[] monthlyRate, int length){
        String str = "";
        double average = 0;
        for(int i = length; i < 37; i++){
            str += " ";
        }
        for (int i = 0; i < monthlyRate.length; i++){
            str+="";
            if(String.format("%.2f", monthlyRate[i]).length() == 4){
                str += String.format("%.2f    ", monthlyRate[i]);
            }
            else if(String.format("%.2f", monthlyRate[i]).length() == 5){
                str += String.format("%.2f   ", monthlyRate[i]);
            }
            else if(String.format("%.2f", monthlyRate[i]).length() == 6){
                str += String.format("%.2f  ", monthlyRate[i]);
            }else{
                str += String.format("%.2f ", monthlyRate[i]);
            }
            average+=monthlyRate[i];
        }
        return str + "   " +String.format("%.2f",average/12);
    }

}
