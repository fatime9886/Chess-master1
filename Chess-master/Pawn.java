import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Pawn extends Piece {
	boolean moved;
	boolean jumped; 

	public Pawn(int c) {
		super(c);
		this.moved = false;
		this.jumped = false;
	}

	public boolean isValidMove(int row, int col, HashMap<Cell, Piece> board,
			boolean attacking, boolean en_passant) {
		if (row > 0 && row < 9) {
			if (col > 0 && col < 9) {
				Cell c = new Cell(row, col);
				if (en_passant) {
					if (board.containsKey(c)) {
						try {
							Pawn pawn = (Pawn) board.get(c);
							if (pawn.jumped) {
								return true;
							}
						} catch (Exception e) {
						}
					}
				} else if (!attacking) {
					if (board.get(c) == null) {
						return true;
					}
				} else {
					if (board.get(c) == null) {
						return false;
					} else if (board.get(c).color == this.color * -1) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public List<Cell> getMoves(int row, int col, int color,
			HashMap<Cell, Piece> board) {
		List<Cell> moves = new ArrayList<>();

		if (isValidMove(row + color, col, board, false, false)) {
			moves.add(new Cell(row + color, col));
			if (!this.moved) { // first move
				if (isValidMove(row + color * 2, col, board, false, false)) {
					moves.add(new Cell(row + color * 2, col));
				}
			}
		}

				if (isValidMove(row + color, col - 1, board, true, false)) {
			moves.add(new Cell(row + color, col - 1));
		}

		
		if (isValidMove(row + color, col + 1, board, true, false)) {
			moves.add(new Cell(row + color, col + 1));
		}

		
		if (isValidMove(row, col - 1, board, true, true)) {
			moves.add(new Cell(row, col - 1));
		}

		
		if (isValidMove(row, col + 1, board, true, true)) {
			moves.add(new Cell(row, col + 1));
		}

		return moves;
	}
}
