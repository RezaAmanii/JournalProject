package org.group12.util;

import java.io.*;

public class SerializationUtil {

    public static void serializeObject(Serializable object, String fileName){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))){

                outputStream.writeObject(object);

            } catch (Exception e) {
                e.printStackTrace();
        }
    }



    public static Object deserializeObject(String fileName){
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))){
            return inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
