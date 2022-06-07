package com.company;

// Нужно создать класс с методом который вычисляет значение выражения y=x-sinx
//Необходимо сохранить в файл состояние объекта, результат и исходное значение, например после ввода слова "save"
//Затем сохраненные данные восстановить, например после ввода слова "upload"

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Чтобы найти y, введите значение x: ");
        String input;
        Scanner in = new Scanner(System.in);
        Function y = new Function();

        while(true) {
            input = in.nextLine();
            try {
                double x = Double.parseDouble(input);
                y.solvation(x);
            }
            catch (Exception ex) {
                if (input.equalsIgnoreCase("save")) {
                    try (ObjectOutputStream op = new ObjectOutputStream(new FileOutputStream("output"))) {
                        op.writeObject(y);
                        System.out.println("Ты сохранил, харош");
                    } catch (IOException IOex) {
                        IOex.getMessage();
                    }
                }
                else if (input.equalsIgnoreCase("upload")){
                    try (ObjectInputStream ip = new ObjectInputStream(new FileInputStream("output"))) {
                        y = (Function) ip.readObject();
                        System.out.println("Ты загрузил, харош");
                    } catch (Exception IOex) {
                        IOex.getMessage();
                    }
                }
                else if(input.equalsIgnoreCase("check")){
                    System.out.println("x: " + y.x + " y: "+ y.y);
                }
                else{
                    System.out.println("Залепуха браток, не харош");
                }
            }
        }
    }
}
class Function implements Serializable {
    double x;
    double y;
    Function(){
        x=0;
        y=0;
    }
    void solvation(double x){
        this.x = x;
        y = this.x - Math.sin(this.x);
        System.out.println(y);
    }
}
