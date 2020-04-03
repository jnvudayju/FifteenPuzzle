import java.awt.event.*;
import java.awt.*;

public class FifteenPuzzleSwingInput extends Frame implements ActionListener {
    private static final long serialVersionUID = 1L; // Default Serial ID
    public Graphics g;
    private int PUZZLE_SIZE = 4;
    public int input[][];
    public int target[][];
    private boolean submitInputFlag = false;
    private boolean submitTargetFlag = false;
    private boolean submitCalculateFlag = false;
    private int labelSetter = 0;
    Button buttonInput;
    Button buttonTarget;
    Button buttonCalculate;
    Button[] b;
    Label[] l;
    FifteenPuzzle fifteenPuzzle;

    FifteenPuzzleSwingInput(FifteenPuzzle fifteenPuzzle) {
        this.fifteenPuzzle = fifteenPuzzle;
        input = new int[PUZZLE_SIZE][PUZZLE_SIZE];
        target = new int[PUZZLE_SIZE][PUZZLE_SIZE];
        setVisible(true);
        setLayout(null);
        setSize(570, 700);
        setLocation(400, 50);
        setFont(new Font("Forte", Font.ITALIC, 28));
        setBackground(Color.WHITE);

        buttonInput = new Button("INPUT");
        buttonTarget = new Button("TARGET");
        buttonCalculate = new Button("CALCULATE");
        buttonInput.setBounds(40, 540, 150, 60);
        buttonCalculate.setBounds(200, 540, 180, 60);
        buttonTarget.setBounds(390, 540, 150, 60);
        add(buttonInput);
        add(buttonCalculate);
        add(buttonTarget);
        buttonInput.addActionListener(this);
        buttonCalculate.addActionListener(this);
        buttonTarget.addActionListener(this);

        b = new Button[16];
        l = new Label[16];
        int j = 0;

        for (int i = 0, k = 1; i < b.length; i++, k++) {
            if (i % 4 == 0) {
                j++;
                k = 1;
            }

            b[i] = new Button("");
            b[i].setBounds(k * 90, j * 90, 80, 80);
            add(b[i]);
            b[i].addActionListener(this);
        }

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void resetPuzzle() {
        labelSetter = 0;
        for (int i = 0; i < l.length; i++) {
            b[i].setLabel("");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonInput) {
            if (!submitInputFlag && labelSetter >= 15) {
                submitInputFlag = true;
                int k = 0;
                for (int i = 0; i < PUZZLE_SIZE; i++) {
                    for (int j = 0; j < PUZZLE_SIZE; j++) {
                        String ip = b[k].getLabel().toString();
                        if (ip.equals("")) {
                            ip = "0";
                        }
                        input[i][j] = Integer.parseInt(ip);
                        k++;
                    }
                }
                this.resetPuzzle();
            }
        }
        if (e.getSource() == buttonCalculate) {
            if (!submitCalculateFlag && submitInputFlag && submitTargetFlag) {
                submitCalculateFlag = true;

                synchronized (this) {
                    this.notify();

                }
                this.dispose();
            }
        }

        if (e.getSource() == buttonTarget) {
            if (!submitTargetFlag && labelSetter >= 15) {
                submitTargetFlag = true;
                int k = 0;
                for (int i = 0; i < PUZZLE_SIZE; i++) {
                    for (int j = 0; j < PUZZLE_SIZE; j++) {
                        String ip = b[k].getLabel().toString();
                        if (ip.equals("")) {
                            ip = "0";
                        }
                        target[i][j] = Integer.parseInt(ip);
                        k++;
                    }
                }
                this.resetPuzzle();
            }
        }

        for (int i = 0; i < l.length; i++) {
            if (e.getSource() == b[i]) {
                if (b[i].getLabel().equals("")) {
                    labelSetter++;

                    if (labelSetter < PUZZLE_SIZE * PUZZLE_SIZE) {
                        b[i].setLabel("" + labelSetter);
                    }
                    if (labelSetter == PUZZLE_SIZE * PUZZLE_SIZE) {
                        b[i].setLabel("");
                    }
                }
            }
        }
    }
}