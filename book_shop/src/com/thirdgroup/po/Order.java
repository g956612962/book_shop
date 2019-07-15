/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Order
 * Author:   ClarkSong
 * Date:     2019/7/10 16:04
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
public class Order {
    private int id;
    private int userId;
    private float price;
    private String createTime;
    private String email;
    private String phone;
    private String address;
    private int isPay;
    private int isDeliver;
    private int isFinish;

    public Order() {
    }

    public Order(int id, int userId, float price, String createTime, String email, String phone, String address, int isPay, int isDeliver, int isFinish) {
        this.id = id;
        this.userId = userId;
        this.price = price;
        this.createTime = createTime;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.isPay = isPay;
        this.isDeliver = isDeliver;
        this.isFinish = isFinish;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIsPay() {
        return isPay;
    }

    public void setIsPay(int isPay) {
        this.isPay = isPay;
    }

    public int getIsDeliver() {
        return isDeliver;
    }

    public void setIsDeliver(int isDeliver) {
        this.isDeliver = isDeliver;
    }

    public int getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(int isFinish) {
        this.isFinish = isFinish;
    }
}
