package Thread.TaskReturnValue.common;

import java.util.List;

public class BestMatchingData {

    private List<String> matchData;

    private int distance;

    public BestMatchingData() {
    }

    public BestMatchingData(List<String> matchData, int distance) {
        this.matchData = matchData;
        this.distance = distance;
    }

    public List<String> getMatchData() {
        return matchData;
    }

    public void setMatchData(List<String> matchData) {
        this.matchData = matchData;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
