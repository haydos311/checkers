package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class GUI extends JFrame
{
    private Container pane;
    private String currentPlayer;
    private JButton[][] board;
    private boolean hasWinner;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem newGame, quit;
    private JButton selectedPiece, checkedPiece;
    public GUI()
    {
        super();
        this.pane = getContentPane();
        pane.setLayout(new GridLayout(8, 8));
        setTitle("Checkers");
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        currentPlayer = "B";
        board = new JButton[8][8];
        hasWinner = false;

        IntializeBoard();
        InitiateMenuBar();

    }
    private void InitiateMenuBar()
    {
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        newGame = new JMenuItem("New Game");
        quit = new JMenuItem("Quit");
        newGame.addActionListener(e -> ResetBoard());
        quit.addActionListener(e -> System.exit(0));
        menu.add(newGame);
        menu.add(quit);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }
    private void ResetBoard()
    {
        currentPlayer = "X";
        hasWinner = false;
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8;j++)
            {
                board[i][j].setText("");
            }
        }
    }

    private void IntializeBoard()
    {
        ArrayList<JButton> butt = new ArrayList<>();
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8;j++)
            {
                JButton btn = new JButton();
                btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
                board[i][j] = btn;
                butt.add(btn);
                if(!hasWinner) {
                    btn.addActionListener(e -> {
                        System.out.println("X: " + ((JButton) e.getSource()).getX() + " Y: " + ((JButton) e.getSource()).getY());
                        if (((JButton) e.getSource()).getText().equals(currentPlayer)) {
                            selectedPiece = (JButton) e.getSource();
                            //selectedPiece.setBackground(Color.CYAN);
                        }

                        if (/*((JButton) e.getSource()).getText().equals("") && */selectedPiece != null) {
                            //Whites go
                            if (Objects.equals(currentPlayer, "W")) {
                                if (((JButton) e.getSource()).getX() == selectedPiece.getX() - 60 &&
                                        ((JButton) e.getSource()).getY() == selectedPiece.getY() + 55 ||
                                        ((JButton) e.getSource()).getX() == selectedPiece.getX() + 60 &&
                                                ((JButton) e.getSource()).getY() == selectedPiece.getY() + 55) {

                                    if (((JButton) e.getSource()).getY() == 385) {
                                        ((JButton) e.getSource()).setText("KW");
                                    } else {
                                        ((JButton) e.getSource()).setText(currentPlayer);
                                    }
                                    selectedPiece.setText("");
                                    currentPlayer = "B";
                                }

                                //checkedPiece =

                            }
                            //Blacks go
                            if (Objects.equals(currentPlayer, "B")) {
                                if (((JButton) e.getSource()).getX() == selectedPiece.getX() - 60 &&
                                        ((JButton) e.getSource()).getY() == selectedPiece.getY() - 55 ||
                                        ((JButton) e.getSource()).getX() == selectedPiece.getX() + 60 &&
                                                ((JButton) e.getSource()).getY() == selectedPiece.getY() - 55) {
                                    if (((JButton) e.getSource()).getY() == 0) {
                                        ((JButton) e.getSource()).setText("KB");
                                    } else {
                                        ((JButton) e.getSource()).setText(currentPlayer);
                                    }
                                    selectedPiece.setText("");
                                    currentPlayer = "W";
                                }
                            }
                        }
                        hasWinner();
                    });
                }

                pane.add(btn);

            }
        }
                //SetUpPieces(board);
    }

    private void SetUpPieces(JButton[][] btn)
    {
        btn[0][0].setText("W");
        btn[0][2].setText("W");
        btn[0][4].setText("W");
        btn[0][6].setText("W");

        btn[1][1].setText("W");
        btn[1][3].setText("W");
        btn[1][5].setText("W");
        btn[1][7].setText("W");

        btn[2][0].setText("W");
        btn[2][2].setText("W");
        btn[2][4].setText("W");
        btn[2][6].setText("W");



        btn[7][1].setText("B");
        btn[7][3].setText("B");
        btn[7][5].setText("B");
        btn[7][7].setText("B");

        btn[6][0].setText("B");
        btn[6][2].setText("B");
        btn[6][4].setText("B");
        btn[6][6].setText("B");

        btn[5][1].setText("B");
        btn[5][3].setText("B");
        btn[5][5].setText("B");
        btn[5][7].setText("B");
    }

    private void TogglePlayer()
    {
        if(currentPlayer.equals("W"))
        {
            currentPlayer = "B";
        }
        if (currentPlayer.equals("B"))
        {
            currentPlayer = "W";
        }
    }

    private void hasWinner()
    {
        for (int i = 0; i < 8;i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (board[i][j].getText().equals("B") && board[i][j].getText().equals("KB"))
                {
                    JOptionPane.showMessageDialog(null, "White wins");
                    hasWinner = true;
                }

                else if (board[i][j].getText().equals("W") && board[i][j].getText().equals("KW"))
                {
                    JOptionPane.showMessageDialog(null, "Black wins");
                    hasWinner = true;
                }
            }
        }
    }
}
