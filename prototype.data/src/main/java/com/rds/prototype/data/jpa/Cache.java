package com.rds.prototype.data.jpa;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Gadi
 */
@Embeddable
public class Cache implements Serializable {
    
    private static final Logger logger = LoggerFactory.getLogger(Cache.class);

    @Lob
    @Column(name = "cache")
    private byte[] cache;

    public Cache() {
    }
    
    public Cache(byte[] cache) {
        this.cache = cache;
    }
    
    public void setCache(byte[] cache) {
        this.cache = cache;
    }

    public byte[] getCache() {
        return cache;
    }

    public void serialise(Object input) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(input);
        oos.close();
        this.cache = baos.toByteArray();
    }

    public Object deserialise() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(cache));
        Object output =  ois.readObject();
        return output;
    }

    @Override
    public String toString() {
        return Arrays.toString(cache);
    }
     
}
