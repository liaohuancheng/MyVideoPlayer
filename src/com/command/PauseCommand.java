package com.command;

import com.main.PlayerMain;

public class PauseCommand implements Command{
	private PlayerMain playerMain;
	
	
	public PauseCommand(PlayerMain playerMain) {
		super();
		this.playerMain = playerMain;
	}


	@Override
	public void execute() {
		// TODO Auto-generated method stub
		playerMain.pause();
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
