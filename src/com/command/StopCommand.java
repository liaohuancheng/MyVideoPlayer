package com.command;

import com.main.PlayerMain;

public class StopCommand implements Command{
	private PlayerMain playerMain;

	public StopCommand(PlayerMain playerMain) {
		super();
		this.playerMain = playerMain;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		playerMain.stop();
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
