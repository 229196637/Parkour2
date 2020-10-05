package me.snownetwork.parkour.Check;

import me.snownetwork.parkour.Command.Setting;
import me.snownetwork.parkour.Data.Dataload;

public class Check {
    public static boolean checkgame(){
        if(Dataload.getdata().getString("startlocation")!=null){
            if(Dataload.getdata().getString("pastlocation"+ Setting.getsize())!=null){
                if(Dataload.getdata().getString("endlocation")!=null){
                    return true;

                }else {
                    return false;
                }
            }else {
                return false;
            }


        }else {
            return false;
        }
    }
}
