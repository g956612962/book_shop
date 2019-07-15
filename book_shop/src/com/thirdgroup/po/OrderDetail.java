/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: OrderDetail
 * Author:   ClarkSong
 * Date:     2019/7/10 16:09
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
public class OrderDetail {
    private int id;
    private int orderId;
    private int bookId;
    private float bookPrice;

    public OrderDetail() {
    }

    public OrderDetail(int id, int orderId, int bookId, float bookPrice) {
        this.id = id;
        this.orderId = orderId;
        this.bookId = bookId;
        this.bookPrice = bookPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public float getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(float bookPrice) {
        this.bookPrice = bookPrice;
    }
}
