package io;

import barBossHouse.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Scanner;

public class OrderManagerTextFileSource extends OrderManagerFileSource {
    @Override
    public void load(Order menuItems) {
        Scanner scanner;
        Order order = new TableOrder();
        try {
            scanner = new Scanner(new File(getPath() + menuItems.getDateTime().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli() + ".txt"));
            if(scanner.nextLine().equals("TableOrder")){
                order = new TableOrder(Integer.parseInt(scanner.nextLine()), Customer.parseCustomer(scanner.nextLine()));
                order.setDateTime(LocalDateTime.parse(scanner.nextLine()));
            } else {
                order = new InternetOrder();
                scanner.nextLine();
                order.setCustomer(Customer.parseCustomer(scanner.nextLine()));
                order.setDateTime(LocalDateTime.parse(scanner.nextLine()));
            }
            while(scanner.hasNextLine()){
                order.add(MenuItem.parseMenuItem(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        menuItems.setCustomer(order.getCustomer());
        menuItems.setDateTime(order.getDateTime());
        menuItems.clear();
        menuItems.addAll(order);
    }

    @Override
    public void store(Order menuItems) {
        create(menuItems);
    }

    @Override
    public void delete(Order menuItems) {
        File file = new File(getPath() + menuItems.getDateTime().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli() + ".txt");
        file.delete();
    }

    @Override
    public void create(Order order) {
        try {
            PrintWriter writer = new PrintWriter(getPath() + order.getDateTime().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli() + ".txt");
            writer.println(order.getType());

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

            writer.println(order.size());
            writer.println(customer.toString());
            writer.println(order.getDateTime());

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

                writer.println(item.toString());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
