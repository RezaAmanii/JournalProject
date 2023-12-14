package org.group12.model.dataHandler;

import org.group12.model.Container;
import org.group12.model.Items;

import java.io.*;

public class SaveLoad {
    public void save(Container container, Items items) {
        try {
            FileOutputStream containerFileOut = new FileOutputStream("containerData.ser");
            FileOutputStream itemsFileOut = new FileOutputStream("itemsData.ser");

            ObjectOutputStream containerOut = new ObjectOutputStream(containerFileOut);
            ObjectOutputStream itemsOut = new ObjectOutputStream(itemsFileOut);

            containerOut.writeObject(container);
            itemsOut.writeObject(items);

            containerOut.close();
            itemsOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Container loadContainer() {
        Container container = null;
        try {
            FileInputStream fileIn = new FileInputStream("containerData.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            container = (Container) in.readObject();

            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return container;
    }

    public Items loadItems() {
        Items items = null;
        try {
            FileInputStream fileIn = new FileInputStream("itemsData.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            items = (Items) in.readObject();

            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return items;
    }
}
