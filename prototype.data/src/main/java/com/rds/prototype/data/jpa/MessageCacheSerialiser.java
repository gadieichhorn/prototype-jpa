/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rds.prototype.data.jpa;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Gadi
 */
public class MessageCacheSerialiser {

    private static final Logger logger = LoggerFactory.getLogger(MessageCacheSerialiser.class);

    public byte[] serialiseMessages(Map<String, Message> messages) throws IOException {
        logger.info("Input: {}", messages.toString());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(messages);
        oos.close();
        logger.info("Output: {}", Arrays.toString(baos.toByteArray()));
        return baos.toByteArray();
    }

    public Map<String, Message> deserialiseMessages(byte[] data) throws IOException, ClassNotFoundException {
        logger.info("Input: {}",Arrays.toString(data));
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        HashMap output = (HashMap) ois.readObject();
        logger.info("Output: {}",output.toString());
        return output;
    }

}
