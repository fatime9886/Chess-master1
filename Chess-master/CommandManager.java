package main_components;

import java.util.Stack;


public class CommandManager {
	public Stack<Command> undos = new Stack<Command>();	
	public Stack<Command> redos = new Stack<Command>();	
	
	
	public void executeCommand(Command command) {
		command.execute();
		undos.push(command);
		redos.clear();
	}

	
	public boolean undoAvailable() {
		return !undos.empty();
	}

	public void undo() {
		if (!undoAvailable())
			return;
		Command command = undos.pop();
		command.undo();
		redos.push(command);
	}

	
	public boolean redoAvailable() {
		return !redos.empty();
	}

	
	public void redo() {
		if (!redoAvailable())
			return;
		Command command = redos.pop();
		command.execute();
		undos.push(command);
	}
	
}
