package com.company;
import java.io.*;
import java.nio.channels.FileChannel;
import java.time.Duration;
import java.time.Instant;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Loggirovanie {
    private static Logger log = Logger.getLogger(Loggirovanie.class.getName());
    public static void main(String[] args) throws InterruptedException, IOException {
        FileHandler fileh;
        fileh = new FileHandler("FileLog.txt"); //файл, в который будут записываться логи
        log.addHandler(fileh);
        SimpleFormatter formatter = new SimpleFormatter();
        fileh.setFormatter(formatter); //форматирование текста(будут записываться только нужные строки)
        log.info("Происходит копирование первого текста из одного файла в другой");
        File source = new File("source.txt"); //файл с текстом
        File dest = new File("dest.txt"); //файл, в который будет копироваться
        log.info("Происходит копирование второго текста из одного файла в другой");
        File source1 = new File("source1.txt"); //файл с текстом
        File dest1 = new File("dest1.txt"); //файл, в который будет копироваться
        copyFile(source,dest);
        copyFile(source1, dest1);
        Instant start = Instant.now();
        Thread.sleep(1000);
        Instant finish = Instant.now();
        long elapsed = Duration.between(start, finish).toMillis();
        System.out.println("Время выполнения кода:" + elapsed + "мс");
        log.info("Вывод времени вполнения копирования");
    }
    public static  void copyFile( File source,  File dest)  throws IOException {

        FileChannel is= null;
        FileChannel os = null;
        try {
            is = new FileInputStream(source).getChannel();
            os = new FileOutputStream(dest).getChannel();
            os.transferFrom(is, 0, is.size());
        } finally {
            is.close();
            os.close();
        }
    }
}

