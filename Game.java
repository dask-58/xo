import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class Game extends Application {

    private String currentPlayer = "X";
    private Button[][] buttons = new Button[3][3];
    private int movesCount = 0;

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setAlignment(Pos.CENTER);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button btn = new Button();
                btn.setPrefSize(100, 100);
                btn.setFont(new Font("Arial", 36));
                btn.setOnAction(e -> handleClick(btn));
                buttons[i][j] = btn;
                grid.add(btn, j, i);
            }
        }

        Scene scene = new Scene(grid, 320, 320);
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleClick(Button btn) {
        if (btn.getText().equals("") && !isGameOver()) {
            btn.setText(currentPlayer);
            movesCount++;

            if (checkWinner()) {
                showWinnerAlert();
            } else if (movesCount == 9) {
                showDrawAlert();
            }

            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        }
    }

    private boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(currentPlayer) && 
                buttons[i][1].getText().equals(currentPlayer) && 
                buttons[i][2].getText().equals(currentPlayer)) {
                return true;
            }
            if (buttons[0][i].getText().equals(currentPlayer) && 
                buttons[1][i].getText().equals(currentPlayer) && 
                buttons[2][i].getText().equals(currentPlayer)) {
                return true;
            }
        }

        if (buttons[0][0].getText().equals(currentPlayer) && 
            buttons[1][1].getText().equals(currentPlayer) && 
            buttons[2][2].getText().equals(currentPlayer)) {
            return true;
        }
        if (buttons[0][2].getText().equals(currentPlayer) && 
            buttons[1][1].getText().equals(currentPlayer) && 
            buttons[2][0].getText().equals(currentPlayer)) {
            return true;
        }

        return false;
    }

    private boolean isGameOver() {
        return movesCount == 9 || checkWinner();
    }

    private void showWinnerAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("Player " + currentPlayer + " Wins!");
        alert.setContentText("Congratulations! The game is over.");
        alert.showAndWait();
        resetGame();
    }

    private void showDrawAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("It's a Draw!");
        alert.setContentText("The game ended in a draw.");
        alert.showAndWait();
        resetGame();
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        currentPlayer = "X";
        movesCount = 0;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

