package team;

import battle.Formation;
import battle.Ground;
import creature.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BadTeam extends Team implements Serializable {
    private Snake cheerer = new Snake();
    private List<Bad> soldiers = new ArrayList<>();
    private static BadTeam instance;
    public static BadTeam getInstance(){return instance;}
    //TODO FORMATION
    public BadTeam(){
        soldiers.add(new Scorpion());
        for(int i = 0;i<7;i++){
            soldiers.add(new Lackey());
        }
        Formation.fangYuan(soldiers);
        Ground.getInstance().update(this);
    }
    public BadTeam(boolean b){
        this();
        instance = this;
    }
    public void run(){
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(cheerer);
        for(Bad soldier: soldiers){
            exec.execute(soldier);
        }
        exec.shutdown();
    }
    public List<Creature> getTeamMembers(){
        List<Creature> ret = new ArrayList<>();
        ret.add(cheerer);
        ret.addAll(1, soldiers);
        return ret;
    }
    public void copy(BadTeam t){
        cheerer.copy(t.cheerer);
        for(int i = 0;i<soldiers.size();i++){
            soldiers.get(i).copy(t.soldiers.get(i));
        }
    }
}
