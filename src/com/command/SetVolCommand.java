package com.command;

import com.main.PlayerMain;

public class SetVolCommand implements Command{
	private PlayerMain playerMain;

	public SetVolCommand(PlayerMain playerMain) {
		super();
		this.playerMain = playerMain;
	}

	@Override
	public void execute(int x) {
		// TODO Auto-generated method stub
		playerMain.setVol(x);
	}

	@Override
	public void execute(float x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
	
}
