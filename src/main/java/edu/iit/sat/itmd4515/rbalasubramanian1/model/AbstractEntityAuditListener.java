/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.model;

import java.time.LocalDateTime;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author raghul
 */
public class AbstractEntityAuditListener {
    @PrePersist
    private void prePersist(AbstractEntity ae) {
        ae.setCreatedTimeStamp(LocalDateTime.now());
    }

    @PreUpdate
    private void preUpdate(AbstractEntity ae) {
        ae.setUpdatedTimeStamp(LocalDateTime.now());
    }
}
