package com.zhangsiyao.gateway.entity.vo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> {
    private Integer code;
    
    private String msg;
    
    private T data;

    /**
     * 成功的返回结果
     * @param <T>  返回值的泛型
     * @return 返回AjaxR1对象
     */
    public static <T> R<T> success() {
        return new R<T>(200, "操作成功", null);
    }
    

    /**
     * 成功的返回结果
     *
     * @param msg  成功信息
     * @param data 返回的值
     * @param <T>  返回值的泛型
     * @return 返回AjaxR1对象
     */
    public static <T> R<T> success(String msg, T data) {
        return new R<T>(200, msg, data);
    }

    /**
     * 成功的返回结果
     *
     * @param data 返回的值
     * @param <T>  返回值的泛型
     * @return 返回AjaxR1对象
     */
    public static <T> R<T> success(T data) {
        return new R<T>(200, "操作成功", data);
    }

    /**
     * 失败的返回结果
     *
     * @param msg  成功信息
     * @param data 返回的值
     * @param <T>  返回值的泛型
     * @return 返回AjaxR1对象
     */
    public static <T> R<T> error(String msg, T data) {
        return new R<T>(500, msg, data);
    }

    /**
     * 失败的返回结果
     *
     * @param msg  成功信息
     * @param <T>  返回值的泛型
     * @return 返回AjaxR1对象
     */
    public static <T> R<T> error(Integer code,String msg) {
        return new R<T>(code, msg, null);
    }

    /**
     * 失败的返回结果
     *
     * @param <T>  返回值的泛型
     * @return 返回AjaxR1对象
     */
    public static <T> R<T> error() {
        return new R<T>(500, "操作失败", null);
    }


    /**
     * 失败的返回结果
     *
     * @param data 返回的值
     * @param <T>  返回值的泛型
     * @return 返回AjaxR1对象
     */
    public static <T> R<T> error(T data) {
        return new R<T>(500, "操作失败", data);
    }

    /**
     * 失败的返回结果
     *
     * @param <T>  返回值的泛型
     * @return 返回AjaxR1对象
     */
    public static <T> R<T> error(String msg) {
        return new R<T>(500, msg, null);
    }

    /**
     * 认证失败的返回结果
     *
     * @param msg  成功信息
     * @param data 返回的值
     * @param <T>  返回值的泛型
     * @return 返回AjaxR1对象
     */
    public static <T> R<T> unAuth(String msg, T data) {
        return new R<T>(401, msg, data);
    }

    /**
     * 未授权
     * @param msg
     * @return
     * @param <T>
     */
    public static <T> R<T> unAuth(String msg) {
        return new R<T>(401, msg, null);
    }

    /**
     * 授权失败的返回结果
     *
     * @param msg  成功信息
     * @param data 返回的值
     * @param <T>  返回值的泛型
     * @return 返回AjaxR1对象
     */
    public static <T> R<T> forbidden(String msg, T data) {
        return new R<T>(403, msg, data);
    }

}