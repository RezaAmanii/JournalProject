package org.group12.model.dataHandler;

import org.group12.model.Container;
import org.group12.model.Items;

import java.io.*;

/**
 * This class is responsible for saving and loading data using serialization.
 * It uses the Singleton design pattern to ensure only one instance of this class is created.
 */
public class SaveLoad {
    // Singleton instance
    private static SaveLoad instance = null;
    // Instance of Container class
    private Container containerInstance;
    // Instance of Items class
    private Items itemsInstance;

    // Private constructor to prevent instantiation
    private SaveLoad() {}

    /**
     * Returns the singleton instance of SaveLoad.
     * If the instance is null, a new instance is created and returned.
     * @return the singleton instance of SaveLoad
     */
    public static SaveLoad getInstance() {
        if (instance == null) {
            instance = new SaveLoad();
        }
        return instance;
    }

    /**
     * Returns the instance of Container.
     * If a save file exists, the data is loaded from the file.
     * Otherwise, a new instance is created and returned.
     * @return the instance of Container
     */
    public Container getContainerInstance() {
        boolean saveExists = new File("containerData.ser").isFile();
        if (saveExists) {
            containerInstance = loadContainer();
        } else {
            containerInstance = Container.getInstance();
        }
        return containerInstance;
    }

    /**
     * Returns the instance of Items.
     * If a save file exists, the data is loaded from the file.
     * Otherwise, a new instance is created and returned.
     * @return the instance of Items
     */
    public Items getItemsInstance() {
        boolean saveExists = new File("itemsData.ser").isFile();
        if (saveExists) {
            itemsInstance = loadItems();
        } else {
            itemsInstance = Items.getInstance();
        }
        return itemsInstance;
    }

    /**
     * Saves the instances of Container and Items to files.
     */
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

    /**
     * Loads the instance of Container from a file.
     * @return the loaded instance of Container, or null if an error occurred
     */
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

    /**
     * Loads the instance of Items from a file.
     * @return the loaded instance of Items, or null if an error occurred
     */
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