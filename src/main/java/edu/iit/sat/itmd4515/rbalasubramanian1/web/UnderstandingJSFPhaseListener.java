/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.web;

import java.util.logging.Logger;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *jsf faces stages logging
 * @author raghul
 */
public class UnderstandingJSFPhaseListener implements PhaseListener{

    private static final Logger LOG = Logger.getLogger(UnderstandingJSFPhaseListener.class.getName());

    @Override
    public void beforePhase(PhaseEvent event) {
        if(event.getPhaseId() == PhaseId.RESTORE_VIEW){
            LOG.info("=============NEW REQUEST STARTING=============");
        }
        LOG.info("Before JSF Phase..." + event.getPhaseId().toString());
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        if(event.getPhaseId() == PhaseId.RENDER_RESPONSE){
            LOG.info("=============REQUEST COMPLETE=============");
        }
        LOG.info("After JSF Phase..." + event.getPhaseId().toString());
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
    
}
