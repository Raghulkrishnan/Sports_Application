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
 *prePersist and preUpdate methods are defined here.
 * They both are used to set the created and updated time stamp before a record is persisted and updated respectively in the database.
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
