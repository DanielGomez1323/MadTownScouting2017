package com.example.pollo.madtownscouting2017;

/**
 * Created by Pollo on 3/31/2017.
 */
public class MatchData implements Comparable {
    private int matchNumber;
    private Alliance blue;
    private Alliance red;

    public MatchData(int _matchNumber){
        matchNumber = _matchNumber;
        blue = new Alliance();
        red  = new Alliance();
    }
    public void addAlliance(teamColor _team, int _team1, int _team2, int _team3){
        switch(_team){
            case blue:
                blue.SetAlliance(_team,_team1,_team2,_team3);
                break;
            case red:
                red.SetAlliance(_team,_team1,_team2,_team3);
                break;
        }
    }

    @Override
    public int compareTo(Object another) {
        return 0;
    }

    public enum teamColor{
        red,blue
    }
    public class Alliance{
        private teamColor team;
        private int team1;
        private int team2;
        private int team3;
        public Alliance(){

        }
        public void SetAlliance(teamColor _team, int _team1, int _team2, int _team3){
            team = _team;
            team1 = _team1;
            team2 = _team2;
            team3 = _team3;
        }
    }

}
