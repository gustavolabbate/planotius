package com.planotius.websandbox;

import org.planotius.controller.Controller;
import org.planotius.controller.functions.Element;
import org.planotius.controller.functions.ElementDiscover;

public class MainDashboard extends Controller {

    //Users
    @ElementDiscover("active_count")
    private Element activeCount;

    @ElementDiscover("inactive_count")
    private Element inactiveCount;
    
    @ElementDiscover("pending_count")
    private Element pendingCount;
    
    
    public int getPendingCount(){
        return Integer.parseInt(pendingCount.getText());
    }
    
    public int getInactiveCount(){
        return Integer.parseInt(inactiveCount.getText());
    }
    
    public int getActiveCount(){
        return Integer.parseInt(activeCount.getText());
    }
}
