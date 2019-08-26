package com.command;

import com.main.PlayerMain;

public class OriginalScreenCommand implements Command{
private PlayerMain myPlayer;
	
	public OriginalScreenCommand(PlayerMain playerMain){
		playerMain=myPlayer;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		PlayerMain.originalScreen();
	}

	@Override
	public void execute(float x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(int x) {
		// TODO Auto-generated method stub
		
	}	
}
