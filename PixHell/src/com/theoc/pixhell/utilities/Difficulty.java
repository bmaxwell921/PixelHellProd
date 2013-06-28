package com.theoc.pixhell.utilities;

public enum Difficulty {
	EASY(1), 
	MEDIUM(2),
	DIFFICULT(3),
	HELL(4);

	int ordinality;

	Difficulty(int value) {
		ordinality = value;
	}

}
