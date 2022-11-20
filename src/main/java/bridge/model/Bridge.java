package bridge.model;

import bridge.BridgeRandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class Bridge {
    private ArrayList<String> upBridge;
    private ArrayList<String> downBridge;
    private int size;
    private GameStatistics gameStatistics;
    private BridgeMaker bridgeMaker;

    public Bridge(GameStatistics gameStatistics) {
        this.gameStatistics = gameStatistics;
        bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        initBridge();
    }

    public void initBridge() {
        upBridge = new ArrayList<>() {{
            add("[");
            add("]");
        }};
        downBridge = new ArrayList<>() {{
            add("[");
            add("]");
        }};
    }

    private void isFirstBridgeUpToDown(String markWithOX) {
        upBridge.add(1, makeSpace(markWithOX));
        downBridge.add(1, "   ");
    }

    private void isFirstBridgeDownToUp(String markWithOX) {
        downBridge.add(1, makeSpace(markWithOX));
        upBridge.add(1, "   ");
    }

    public void setUpBridge(String markWithOX) {
        if (Player.currentLocation == 0) {
            isFirstBridgeUpToDown(markWithOX);
            return;
        }
        if (isContains(markWithOX)) {
            checkBridgeSequence(markWithOX, upBridge, downBridge);
            return;
        }
        checkBridgeSequence(markWithOX, upBridge, downBridge);
    }

    private void checkBridgeSequence(String markWithOX, ArrayList<String> upBridge, ArrayList<String> downBridge) {
        moreThanFirstBridge(markWithOX, upBridge);
        moreThanFirstBridgeSpace(downBridge);
    }

    public void setDownBridge(String markWithOX) {
        if (Player.currentLocation == 0) {
            isFirstBridgeDownToUp(markWithOX);
            return;
        }
        if (isContains(markWithOX)) {
            checkBridgeSequence(markWithOX, downBridge, upBridge);
            return;
        }
        checkBridgeSequence(markWithOX, downBridge, upBridge);
    }

    private boolean isContains(String markWithOX) {
        return markWithOX.contains(" O ");
    }

    private void moreThanFirstBridgeSpace(ArrayList<String> bridge) {
        bridge.add(Player.currentLocation * 2, "|");
        bridge.add(Player.currentLocation * 2 + 1, "   ");
    }

    private void moreThanFirstBridge(String markWithOX, ArrayList<String> bridge) {
        bridge.add(Player.currentLocation * 2, "|");
        bridge.add(Player.currentLocation * 2 + 1, makeSpace(markWithOX));
    }

    public void resetBridge() {
        initBridge();
    }

    public void setSize(int size) {
        this.size = size - 1;
    }

    public boolean buildBridge() {
        List<Boolean> checkRoad = gameStatistics.getCheckRoad();
        Boolean answer = checkRoad.get(Player.currentLocation);
        if (answer) {
            return checkAnswerRoadForO();
        } return checkAnswerRoadForX();
    }

    private Boolean checkAnswerRoadForO() {
        if (gameStatistics.getAnswerRoad().get(Player.currentLocation).equals("D")) {
            setDownBridge("O");
        } else if (gameStatistics.getAnswerRoad().get(Player.currentLocation).equals("U")) {
            setUpBridge("O");
        }
        return true;
    }

    private Boolean checkAnswerRoadForX() {
        if (gameStatistics.getAnswerRoad().get(Player.currentLocation).equals("D")) {
            setUpBridge("X");
        } else if (gameStatistics.getAnswerRoad().get(Player.currentLocation).equals("U")) {
            setDownBridge("X");
        }
        return false;
    }

    public BridgeMaker getBridgeMaker() {
        return bridgeMaker;
    }

    public ArrayList<String> getUpBridge() {
        return upBridge;
    }

    public ArrayList<String> getDownBridge() {
        return downBridge;
    }

    public int getSize() {
        return size;
    }

    private String makeSpace(String markWithOX) {
        return " " + markWithOX + " ";
    }
}

