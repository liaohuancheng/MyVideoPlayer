package com.command;

import com.main.PlayerMain;

public class FullScreenCommand implements Command {
	private PlayerMain playerMain;
	
	
	
	public FullScreenCommand(PlayerMain playerMain) {
		super();
		this.playerMain = playerMain;
	}



	@Override
	public void execute() {
		// TODO Auto-generated method stub
		PlayerMain.fullScreen();
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
