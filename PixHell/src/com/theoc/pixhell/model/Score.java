package com.theoc.pixhell.model;

public class Score {
int Score;
public Score(int Score)
{
	this.Score =Score;
}

public void UpdateScore(int difference)
{
	Score=Score+difference;
}
}
