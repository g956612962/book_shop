/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: GetCurrentTime
 * Author:   Lenovo
 * Date:     2019/6/26 16:04
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.thirdgroup.util;


import java.sql.Timestamp;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Songxiang
 * @create 2019/6/26
 * @since 1.0.0
 */
public class GetCurrentTime {
    public static Timestamp getcurrentTime() {
        return new Timestamp(System.currentTimeMillis());       //取得当前时间，内容为：年月日时分秒毫秒
    }


}
