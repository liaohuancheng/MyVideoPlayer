package com.invoker;

import com.command.Command;
import com.command.PlayCommand;

public class Invoker {
	private Command fullScreenCommand;
	private Command originalScreenCommand;
	private Command exitCommand;
	private Command playCommand;
	private static Command jumpToCommand;
	private Command pauseCommand;
	private Command setVolCommand;
	private Command stopCommand; 
	public void setExitCommand(Command exitCommand) {
		this.exitCommand = exitCommand;
	}

	public void setPlayCommand(Command playCommand) {
		this.playCommand = playCommand;
	}

	public void setJumpToCommand(Command jumpToCommand) {
		this.jumpToCommand = jumpToCommand;
	}

	public void setPauseCommand(Command pauseCommand) {
		this.pauseCommand = pauseCommand;
	}

	public void setSetVolCommand(Command setVolCommand) {
		this.setVolCommand = setVolCommand;
	}

	public void setStopCommand(Command stopCommand) {
		this.stopCommand = stopCommand;
	}

	
	public void setFullScreenCommand(Command fullScreenCommand) {
		this.fullScreenCommand = fullScreenCommand;
	}
	
	public void setOriginalScreenCommand(Command originalScreenCommand) {
		this.originalScreenCommand=originalScreenCommand;
	}
	
	public void FullScreen() {
		fullScreenCommand.execute();
	}
	public void OriginalScreen() {
		originalScreenCommand.execute();
	}

	public void ExitCommand(){
		exitCommand.execute();
	}
	public void PlayCommand(){
		playCommand.execute();
	}
	public static void JumpToCommand(float to){
		jumpToCommand.execute(to);
	}
	public void PauseCommand(){
		pauseCommand.execute();
	}
	public void SetVolCommand(int v){
		setVolCommand.execute(v);
	}
	public void StopCommand(){
		stopCommand.execute();
	}
	
}
