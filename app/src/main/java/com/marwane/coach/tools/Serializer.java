package com.marwane.coach.tools;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * Bronnen:
 * https://developer.android.com/reference/java/io/Serializable
 * https://www.supinfo.com/articles/single/1550-serialisation-objet-android
 * http://www.coderzheaven.com/2012/07/25/serialization-android-simple-example/
 *
 */
public abstract class Serializer {

    /**
     * Serializatie an object
     * @param filename
     * @param object
     * @param context
     */
    public static void serialize(String filename, Object object, Context context){
        try{
            FileOutputStream file = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream oos;
            try{
                oos = new ObjectOutputStream(file);
                oos.writeObject(object);
                oos.flush();
                oos.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Deserialize an object
     * @param filename
     * @param context
     * @return
     */
    public static Object deSerialize(String filename,Context context){
        try{
            FileInputStream file = context.openFileInput(filename);
            ObjectInputStream ois;
            try {
                ois = new ObjectInputStream(file);
                try {
                    Object object = ois.readObject();
                    ois.close();
                    return object;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }catch (StreamCorruptedException e) {
            e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

}
