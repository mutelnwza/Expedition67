package com.Expedition67.model;

import java.util.HashMap;

public class Animator {

    //config for each action
    public record AnimatorConfig(int row, int speed, int frameCount){}
    
    //store all the actions available for this animator
    private HashMap<String,AnimatorConfig>  animations = new HashMap<>();
    
    //current animation playing
    private String currentAction = "";
    private int tick,index,currentSpeed,currentRow,currentFrameCount;

    public void addAnimation(String name, int row, int speed, int frameCount){
        animations.put(name, new AnimatorConfig(row, speed, frameCount));
    }

    //this will be called from unit when a unit state changes
    public void play(String name){
        if(currentAction.equals(name)) return;
        else{
            AnimatorConfig animatorConfig = animations.get(name);
            if(animatorConfig!=null){
                this.currentAction = name;
                this.currentSpeed = animatorConfig.speed();
                this.currentFrameCount = animatorConfig.frameCount();
                this.currentRow = animatorConfig.row();
                this.index=0;
                this.tick=0;
            }
            else{
                System.err.println("Animation name not found");
            }
        }
    }

    /* update is call every frame from unit, for tracking the animation frames 
    if tick > animation speed, will change the animation index to the next frame.
    mod by currentframecount to loop back to the beginning if reaches endpoint.
    */
            
    public void update(){
        if(currentAction.isEmpty()) return;
        tick++;
        if(tick>=currentSpeed){
            tick=0;
            index = (index+1) % currentFrameCount;
        }
    }   

    public int getIndex() {return this.index;}
    public int getRow() {return this.currentRow;}
}
