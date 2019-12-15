package com.company.XMLStuff;

import com.company.Hospital.HospitalSerializable;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class XMLSerializer {
    public void serialize(Object objectData, String filePath) {
        XMLEncoder encoder=null;
        try{
            encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filePath)));
        }catch(FileNotFoundException fileNotFound){
            System.out.println("Error");
        }
        encoder.writeObject(objectData);
        encoder.close();
    }
    public Object deserialize(String filePath) throws FileNotFoundException {
        XMLDecoder decoder = null;
        decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filePath)));
        Object objectData = decoder.readObject();
        return objectData;
    }
}
