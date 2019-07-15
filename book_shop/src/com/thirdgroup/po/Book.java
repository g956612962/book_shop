/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Book
 * Author:   ClarkSong
 * Date:     2019/7/10 16:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * ClarkSong         修改时间           版本号              描述
 */
package com.thirdgroup.po;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author ClarkSong
 * @create 2019/7/10
 * @since 1.0.0
 */
public class Book {
    private int id;
    private String name;
    private String express;
    private float price;
    private int classId;
    private String className;
    private String author;
    private String press;
    private int inventory;
    private String imagePath;

    public Book() {
    }

    public Book(int id, String name, String express, float price, int classId, String className, String author, String press, int inventory, String imagePath) {
        this.id = id;
        this.name = name;
        this.express = express;
        this.price = price;
        this.classId = classId;
        this.className = className;
        this.author = author;
        this.press = press;
        this.inventory = inventory;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
