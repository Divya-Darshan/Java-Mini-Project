import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main extends JFrame implements ActionListener {

    // UI components
    JLabel questionLabel;
    JRadioButton option1, option2, option3, option4;
    ButtonGroup optionsGroup;
    JButton nextButton;
    JLabel resultLabel;

    // Questions and answers
    String[] questions = {
            "What is the capital city of India?",
            "What is the currency of India?",
            "What is the official language of the Indian state of Tamil Nadu?",
            "Which festival is known as the Festival of Lights in India?",
            "Who is known as the Iron Man of India?",
            "What is the national animal of India?"
    };

    String[][] optionsData = {
            {"Bengaluru", "Mumbai", "New Delhi", "Kolkata"},
            {"Euro", "Indian Rupee", "Peso", "Yen"},
            {"Kannada", "Tamil", "Hindi", "Bengali"},
            {"Diwali", "Holi", "Eid", "Navratri"},
            {"Sardar Vallabhbhai Patel", "Bhagat Singh", "Mahatma Gandhi", "Jawaharlal Nehru"},
            {"Elephant", "Bengal Tiger", "Lion", "Peacock"}
    };

    int[] correctAnswers = {3, 2, 2, 1, 1, 2}; // Correct answers (1-based index)

    int currentQuestionIndex = 0;
    int score = 0;

    // Constructor to set up the UI and display the first question
    public Main() {
        // Frame settings
        setTitle("Quiz Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        // Create question label and option buttons
        questionLabel = new JLabel("Question", JLabel.CENTER);

        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();

        optionsGroup = new ButtonGroup();
        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);

        nextButton = new JButton("Next");
        nextButton.addActionListener(this);

        resultLabel = new JLabel("", JLabel.CENTER);

        // Add components to the frame
        add(questionLabel);
        add(option1);
        add(option2);
        add(option3);
        add(option4);
        add(nextButton);
        add(resultLabel);

        // Load the first question
        loadQuestion();

        setVisible(true);
    }

    // Load the current question and options
    public void loadQuestion() {
        questionLabel.setText(questions[currentQuestionIndex]);
        option1.setText(optionsData[currentQuestionIndex][0]);
        option2.setText(optionsData[currentQuestionIndex][1]);
        option3.setText(optionsData[currentQuestionIndex][2]);
        option4.setText(optionsData[currentQuestionIndex][3]);
        resultLabel.setText("");
        optionsGroup.clearSelection(); // Clear any previous selections
    }

    // Handle button click for "Next"
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check which option is selected
        int selectedAnswer = -1;
        if (option1.isSelected()) selectedAnswer = 1;
        if (option2.isSelected()) selectedAnswer = 2;
        if (option3.isSelected()) selectedAnswer = 3;
        if (option4.isSelected()) selectedAnswer = 4;

        // If no option is selected, show a message
        if (selectedAnswer == -1) {
            resultLabel.setText("Please select an option!");
            return;
        }

        // Check if the answer is correct
        if (selectedAnswer == correctAnswers[currentQuestionIndex]) {
            score++;
            resultLabel.setText("Correct!");
        } else {
            resultLabel.setText("Wrong! Correct answer: " + optionsData[currentQuestionIndex][correctAnswers[currentQuestionIndex] - 1]);
        }

        currentQuestionIndex++;

        // Load the next question or show the final score
        if (currentQuestionIndex < questions.length) {
            loadQuestion();
        } else {
            JOptionPane.showMessageDialog(this, "Quiz Over! Your score: " + score + "/" + questions.length);
            System.exit(0); // Close the application
        }
    }

    public static void main(String[] args) {
        new Main(); 
    }
}
