package io;

import barBossHouse.Order;

import java.io.*;
import java.time.ZoneOffset;

public class OrderManagerSerializedFileSource extends OrderManagerFileSource {
    @Override
    public void load(Order menuItems) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(getPath() + menuItems.getDateTime().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli() + ".bin"));
            Order order = (Order) in.readObject();
            in.close();
            menuItems.setCustomer(order.getCustomer());
            menuItems.setDateTime(order.getDateTime());
            menuItems.clear();
            menuItems.addAll(order);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void store(Order menuItems) {
        create(menuItems);
    }

    @Override
    public void delete(Order menuItems) {
        File file = new File(getPath() + menuItems.getDateTime().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli() + ".bin");
        file.delete();
    }

    @Override
    public void create(Order order) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(getPath() + order.getDateTime().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli() + ".bin"));
            out.writeObject(order);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
