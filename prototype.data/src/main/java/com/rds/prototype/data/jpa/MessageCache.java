/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rds.prototype.data.jpa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author Gadi
 */
@Entity(name = "MessageCache")
@Table(name = "MessageCache")
public class MessageCache implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "revision")
    @Version()
    private long revision;

    @Embedded
    private Cache cache;

    public long getId() {
        return id;
    }

    public long getRevision() {
        return revision;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRevision(long revision) {
        this.revision = revision;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public Cache getCache() {
        return cache;
    }
    
}
