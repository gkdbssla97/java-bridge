package bridge;

import bridge.controller.GameMachine;
import bridge.model.*;
import bridge.view.InputView;
import bridge.view.OutputView;

public class Application {

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        BridgeRandomNumberGenerator numberGenerator = new BridgeRandomNumberGenerator();
        GameStatistics gameStatistics = new GameStatistics();
        Bridge bridge = new Bridge();
        BridgeGame bridgeGame = new BridgeGame(new BridgeMaker(numberGenerator), new Player(), gameStatistics, bridge);
        OutputView outputView = new OutputView(gameStatistics, bridge);
        InputView inputView = new InputView(bridgeGame, new BridgeException(), gameStatistics, outputView);
        new GameMachine(inputView, outputView);
    }
}
