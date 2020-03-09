package main_components;

import listeners.ForfeitListener;
import listeners.RestartListener;
import listeners.MoveListener;
import listeners.NewGameListener;
import listeners.RedoListener;
import listeners.UndoListener;

import java.util.ArrayList;
import java.awt.Point;


public class Controller {
	public ChessBoard chessBoard; 					
	public CommandManager commandManager;			
	public boolean inMovement;						
	public ArrayList<Button> buttonsToHighlight; 	
	public ArrayList<Button> buttonsCanMoveTo;	
	public Point originPoint;						

	public View view;								
	public int whiteGamesWon = 0;					
	public int blackGamesWon = 0;					
	public boolean isClassicMode = true;			
	public Controller() {
		initializeVariables();
		view = new View(chessBoard, this);
		addMouseListeners();
	}
	
	
	public void reset(){
		initializeVariables();
		view.initialize(chessBoard, this);
		addMouseListeners();
	}
	
	
	public void initializeVariables(){
		chessBoard = new ChessBoard(8, 8, isClassicMode);	
		commandManager = new CommandManager();
		inMovement = false;
		buttonsToHighlight = new ArrayList<Button>(); 
		buttonsCanMoveTo = new ArrayList<Button>();
		originPoint = null;
	}
	
	
	public void addMouseListeners(){

		for (int i = 0; i < chessBoard.rows; i++){
			for (int j = 0; j < chessBoard.columns; j++){
				view.addMouseListener(new MoveListener(this), view.button[i][j]);
			}
		}
		view.addMouseListener(new UndoListener(this), view.undoButton);
		view.addMouseListener(new RedoListener(this), view.redoButton);
		view.addMouseListener(new ForfeitListener(this), view.whiteForfeit);
		view.addMouseListener(new ForfeitListener(this), view.blackForfeit);
		view.addMouseListener(new RestartListener(this), view.whiteRestart);
		view.addMouseListener(new RestartListener(this), view.blackRestart);	
		view.addMouseListener(new NewGameListener(this, true), view.classicMode);
		view.addMouseListener(new NewGameListener(this, false), view.customMode);
	}
}
