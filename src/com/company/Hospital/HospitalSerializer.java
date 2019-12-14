package com.company.Hospital;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class HospitalSerializer{
    public void serialize(HospitalSerializable hospitalData, String filePath) {
        XMLEncoder encoder=null;
        try{
            encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filePath)));
        }catch(FileNotFoundException fileNotFound){
            System.out.println("Error");
        }
        encoder.writeObject(hospitalData);
        encoder.close();
    }
    public HospitalSerializable deserialize(String filePath) {
        XMLDecoder decoder=null;
        try {
            decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(filePath)));
            HospitalSerializable hospitalData = (HospitalSerializable) decoder.readObject();
            return hospitalData;
        } catch (FileNotFoundException e) {
            //System.out.println("File not found");
        }
        return null;
        //System.out.println(hospitalData);
    }
}
