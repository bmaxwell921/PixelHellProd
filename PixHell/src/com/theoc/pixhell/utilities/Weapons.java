package com.theoc.pixhell.utilities;

public enum Weapons {
	WEAPON1(1,20),
	WEAPON2(2,10),
	WEAPON3(3,5);
	
	int oridnal,cost;
	
	Weapons(int oridnal,int cost){
		this.oridnal = oridnal;
		this.cost = cost;
	}

}
