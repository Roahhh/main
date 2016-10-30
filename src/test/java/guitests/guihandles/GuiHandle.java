package guitests.guihandles;

import guitests.GuiRobot;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.Window;
import seedu.agendum.TestApp;
import seedu.agendum.commons.core.LogsCenter;

import java.util.logging.Logger;

/**
 * Base class for all GUI Handles used in testing.
 */
public class GuiHandle {
    final GuiRobot guiRobot;
    final Stage primaryStage;
    private final String stageTitle;

    private final Logger logger = LogsCenter.getLogger(this.getClass());

    GuiHandle(GuiRobot guiRobot, Stage primaryStage, String stageTitle) {
        this.guiRobot = guiRobot;
        this.primaryStage = primaryStage;
        this.stageTitle = stageTitle;
        focusOnSelf();
    }

    private void focusOnWindow(String stageTitle) {
        logger.info("Focusing " + stageTitle);
        java.util.Optional<Window> window = guiRobot.listTargetWindows()
                .stream()
                .filter(w -> w instanceof Stage && ((Stage) w).getTitle().equals(stageTitle)).findAny();

        if (!window.isPresent()) {
            logger.warning("Can't find stage " + stageTitle + ", Therefore, aborting focusing");
            return;
        }

        guiRobot.targetWindow(window.get());
        guiRobot.interact(() -> window.get().requestFocus());
        logger.info("Finishing focus " + stageTitle);
    }

    Node getNode(String query) {
        return guiRobot.lookup(query).tryQuery().get();
    }

    String getTextFieldText(String filedName) {
        return ((TextField) getNode(filedName)).getText();
    }

    void setTextField(String textFieldId, String newText) {
        guiRobot.clickOn(textFieldId);
        ((TextField)guiRobot.lookup(textFieldId).tryQuery().get()).setText(newText);
        guiRobot.sleep(500); // so that the texts stays visible on the GUI for a short period
    }

    void pressEnter() {
        guiRobot.type(KeyCode.ENTER).sleep(500);
    }

    String getTextFromLabel(String fieldId, Node parentNode) {
        return ((Label) guiRobot.from(parentNode).lookup(fieldId).tryQuery().get()).getText();
    }

    private void focusOnSelf() {
        if (stageTitle != null) {
            focusOnWindow(stageTitle);
        }
    }

    public void focusOnMainApp() {
        this.focusOnWindow(TestApp.APP_TITLE);
    }

    void closeWindow() {
        java.util.Optional<Window> window = guiRobot.listTargetWindows()
                .stream()
                .filter(w -> w instanceof Stage && ((Stage) w).getTitle().equals(stageTitle)).findAny();

        if (!window.isPresent()) {
            return;
        }

        guiRobot.targetWindow(window.get());
        guiRobot.interact(() -> ((Stage)window.get()).close());
        focusOnMainApp();
    }
}
