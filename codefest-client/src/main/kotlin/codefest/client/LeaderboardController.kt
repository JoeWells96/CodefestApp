package codefest.client

import codefest.common.data.Leaderboard
import codefest.common.data.Student
import javafx.application.Platform
import javafx.beans.value.ChangeListener
import javafx.fxml.FXML
import javafx.scene.control.ListView
import javafx.scene.text.Text

/**
 *
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
class LeaderboardController {

    @FXML
    private lateinit var listView: ListView<Student>

    @FXML
    private lateinit var leaderboardHeader: Text

    fun initialize() {
        listView.sceneProperty().addListener { _, _, current ->
            if(current != null) {
                requestServerLeaderboard()
            }
        }
    }

    private fun requestServerLeaderboard() {
        Server.requestLeaderboard {
            onSuccess = {
                updateListView(it)
            }
        }
    }

    private fun updateListView(leaderboard: Leaderboard) {
        Platform.runLater {
            leaderboardHeader.text = "Name:\tScore"
            listView.items.setAll(leaderboard.students)
        }
    }
}