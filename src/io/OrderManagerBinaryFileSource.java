package io;

import barBossHouse.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class OrderManagerBinaryFileSource extends OrderManagerFileSource {
    @Override
    public void load(Order menuItems) {
        DataInputStream stream;
        Order order = new TableOrder();
        try {
            stream = new DataInputStream(new FileInputStream(getPath() + menuItems.getDateTime().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli() + ".bin"));
            int size;
            if(stream.readUTF().equals("TableOrder")){
                size = stream.readInt();
                order = new TableOrder(size, Customer.parseCustomer(stream.readUTF()));
                order.setDateTime(LocalDateTime.parse(stream.readUTF()));
            } else {
                order = new InternetOrder();
                size = stream.readInt();
                order.setCustomer(Customer.parseCustomer(stream.readUTF()));
                order.setDateTime(LocalDateTime.parse(stream.readUTF()));
            }
            for(int i = 0; i < size; i++){
                order.add(MenuItem.parseMenuItem(stream.readUTF()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        menuItems.setCustomer(order.getCustomer());
        menuItems.setDateTime(order.getDateTime());
        menuItems.clear();
        menuItems.addAll(order);
    }

    @Override
    public void store(Order order) {
        create(order);
    }

    @Override
    public void delete(Order menuItems) {
        File file = new File(getPath() + menuItems.getDateTime().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli() + ".bin");
        file.delete();
    }

    @Override
    public void create(Order order) {
        try {
            DataOutputStream stream = new DataOutputStream(new FileOutputStream(getPath() + order.getDateTime().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli() + ".bin"));
            stream.writeUTF(order.getType());

            //Составляется строка заказчика в специальном формате для вывода в файл
            StringBuilder customer = new StringBuilder();
            customer.append(order.getCustomer().getSecondName()).append(";");
            customer.append(order.getCustomer().getFirstName()).append(";");
            customer.append(order.getCustomer().getBirthDate()).append(";");
            customer.append(order.getCustomer().getAddress().getCityName()).append(";");
            customer.append(order.getCustomer().getAddress().getZipCode()).append(";");
            customer.append(order.getCustomer().getAddress().getStreetName()).append(";");
            customer.append(order.getCustomer().getAddress().getBuildingNumber()).append(";");
            customer.append(order.getCustomer().getAddress().getBuildingLetter()).append(";");
            customer.append(order.getCustomer().getAddress().getApartmentNumber()).append(";");

            stream.writeInt(order.size());
            stream.writeUTF(customer.toString());
            stream.writeUTF(order.getDateTime().toString());
            StringBuilder item;

            for(MenuItem menuItem : order){
                item = new StringBuilder();
                item.append(menuItem.getName()).append(";");
                item.append(menuItem.getCost()).append(";");
                item.append(menuItem.getDescription()).append(";");
                if(menuItem instanceof Drink){
                    item.append(((Drink) menuItem).getType()).append(";");
                    item.append(((Drink) menuItem).getAlcoholVol()).append(";");
                }

                stream.writeUTF(item.toString());
            }
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
