package org.group12.model.dataHandler;

import org.group12.model.Container;
import org.group12.model.Items;

import java.io.*;

public class SaveLoad {
    private static SaveLoad instance = null;
    private Container containerInstance;
    private Items itemsInstance;

    private SaveLoad() {}

    public static SaveLoad getInstance() {
        if (instance == null) {
            instance = new SaveLoad();
        }
        return instance;
    }

    public Container getContainerInstance() {
        boolean saveExists = new File("containerData.ser").isFile();
        if (saveExists) {
            containerInstance = loadContainer();
        } else {
            containerInstance = Container.getInstance();
        }
        return containerInstance;
    }

    public Items getItemsInstance() {
        boolean saveExists = new File("itemsData.ser").isFile();
        if (saveExists) {
            itemsInstance = loadItems();
        } else {
            itemsInstance = Items.getInstance();
        }
        return itemsInstance;
    }

    public void save() {
        try {
            FileOutputStream containerFileOut = new FileOutputStream("containerData.ser");
            FileOutputStream itemsFileOut = new FileOutputStream("itemsData.ser");

            ObjectOutputStream containerOut = new ObjectOutputStream(containerFileOut);
            ObjectOutputStream itemsOut = new ObjectOutputStream(itemsFileOut);

            containerOut.writeObject(containerInstance);
            itemsOut.writeObject(itemsInstance);

            containerOut.close();
            itemsOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Container loadContainer() {
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

    private Items loadItems() {
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
