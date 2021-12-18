import java.io.File;

class Main {
    public static void main(String[] args) {
        PollutionDataManager dataStorage = new PollutionDataManager(new File("./src/RiverPollutionData.txt"),new File("./src/RiverPollutionDataCounts.txt"));
        System.out.println(dataStorage.generateArsenicReport());
    }
}