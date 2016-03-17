package hEntity;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class LayeredAnimation {
    
    protected ArrayList<Animation> animSetList;
    protected Sprite shadow;
    
    public LayeredAnimation(){
        animSetList = new ArrayList<>();
    }
    
    public void Draw(Graphics2D g2d, Entity.Facing face){
        
        if(!animSetList.isEmpty()){
            switch(face){
                case BACK:
                    try{
                        shadow.Draw(g2d, 1, 1);
                    }catch(NullPointerException e){}
                    for(int i = 0; i < animSetList.size(); i++){
                        try {
                            animSetList.get(i).Draw(g2d,1);
                        } catch(NullPointerException e){}
                    }
                    break;
                case FORWARD:
                    try{
                        shadow.Draw(g2d, 1, 1);
                    }catch(NullPointerException e){}
                    for(int i = 0; i < animSetList.size(); i++){
                        try {
                            animSetList.get(i).Draw(g2d,2);
                        } catch(NullPointerException e){}
                    }
                    break;
                case LEFT:
                    try{
                        shadow.Draw(g2d, 1, 1);
                    }catch(NullPointerException e){}
                    for(int i = 0; i < animSetList.size(); i++){
                        try {
                            animSetList.get(i).Draw(g2d,3);
                        } catch(NullPointerException e){}
                    }
                    break;
                case RIGHT:
                    try{
                        shadow.Draw(g2d, 1, 1);
                    }catch(NullPointerException e){}
                    for(int i = 0; i < animSetList.size(); i++){
                        try {
                            animSetList.get(i).Draw(g2d,4);
                        } catch(NullPointerException e){}
                    }
                    break;
                default:
                    break;
            }
        }
        
    }
}
