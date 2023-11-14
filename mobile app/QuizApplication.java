import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApplication extends JFrame {
    private int currentQuestion = 0;
    private int score = 0;

    private JLabel questionLabel;
    private ButtonGroup buttonGroup;
    private JRadioButton[] answerOptions;
    private JButton nextButton;

    // Define the quiz questions and answers
    private String[] questions = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "What is the largest mammal in the world?"
    };

    private String[] correctAnswers = {"Paris", "Mars", "Blue Whale"};

    private String[][] options = {
            {"Berlin", "Madrid", "Paris", "Rome"},
            {"Venus", "Mars", "Jupiter", "Saturn"},
            {"Elephant", "Giraffe", "Blue Whale", "Hippopotamus"}
    };

    public QuizApplication() {
        // Set up the frame
        setTitle("Quiz Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Create components
        questionLabel = new JLabel(questions[currentQuestion]);
        add(questionLabel, BorderLayout.NORTH);

        buttonGroup = new ButtonGroup();
        answerOptions = new JRadioButton[options[currentQuestion].length];

        JPanel optionsPanel = new JPanel(new GridLayout(options[currentQuestion].length, 1));
        for (int i = 0; i < options[currentQuestion].length; i++) {
            answerOptions[i] = new JRadioButton(options[currentQuestion][i]);
            buttonGroup.add(answerOptions[i]);
            optionsPanel.add(answerOptions[i]);
        }

        add(optionsPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        nextButton.addActionListener(new NextButtonClickListener());
        add(nextButton, BorderLayout.SOUTH);

        // Display the frame
        setVisible(true);
    }

    private class NextButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Check the selected answer
            for (int i = 0; i < answerOptions.length; i++) {
                if (answerOptions[i].isSelected()) {
                    if (options[currentQuestion][i].equals(correctAnswers[currentQuestion])) {
                        score++;
                    }
                }
            }

            // Move to the next question or display the final score
            if (currentQuestion < questions.length - 1) {
                currentQuestion++;
                updateQuestion();
            } else {
                displayFinalScore();
            }
        }

        private void updateQuestion() {
            questionLabel.setText(questions[currentQuestion]);
            for (int i = 0; i < answerOptions.length; i++) {
                answerOptions[i].setText(options[currentQuestion][i]);
                answerOptions[i].setSelected(false);
            }
        }

        private void displayFinalScore() {
            JOptionPane.showMessageDialog(null, "Quiz completed! Your score: " + score + "/" + questions.length,
                    "Quiz Result", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizApplication());
    }
}
