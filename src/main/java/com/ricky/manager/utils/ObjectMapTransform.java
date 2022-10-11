package com.ricky.manager.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author RickyCharles
 * @apiNote 转换帮助类
 */
public class ObjectMapTransform {
    /**
     * 将JavaBean转换成Map
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map beanToMap(Object obj) throws Exception {
        // 创建map集合
        Map map = new HashMap();
        // 获取JavaBean中所有属性
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field fie : fields) {

            // 将属性第一个字母转换成大写
            String frist = fie.getName().substring(0, 1).toUpperCase();
            // 获取属性的类型
            Class<?> type = fie.getType();
            // 封装属性的get
            String getter = "";
            if ("boolean".equals(type.getName())) {
                getter = "is" + frist + fie.getName().substring(1);
            } else {
                getter = "get" + frist + fie.getName().substring(1);
            }
            // 获取JavaBean的方法
            Method method = obj.getClass().getMethod(getter, new Class[] {});
            // 调用方法,并接收返回值
            Object objec = method.invoke(obj, new Object[] {});
            // 判断返回值不为空
            if (objec != null) {
                map.put(fie.getName(), objec);
            } else {
                map.put(fie.getName(), "");
            }
        }
        return map;
    }

    /**
     * 将Map转换为JavaBean
     * @param map
     * @param obj
     * @return
     * @throws Exception
     */
    public static Object mapToBean(Map<String, Object> map, Object obj) throws Exception {
        // 获取JavaBean中的所有属性
        Field[] field = obj.getClass().getDeclaredFields();
        for (Field fi : field) {
            // 判断key值是否存在
            if (map.containsKey(fi.getName())) {
                // 获取key的value值
                String value = map.get(fi.getName()).toString();
                // 将属性的第一个字母转换为大写
                String frist = fi.getName().substring(0, 1).toUpperCase();
                // 属性封装set方法
                String setter = "set" + frist + fi.getName().substring(1);
                // 获取当前属性类型
                Class<?> type = fi.getType();
                // 获取JavaBean的方法,并设置类型
                Method method = obj.getClass().getMethod(setter, type);

                // 判断属性为double类型
                if ("java.lang.String".equals(type.getName())) {
                    // 调用当前Javabean中set方法，并传入指定类型参数
                    method.invoke(obj, value);
                } else if ("int".equals(type.getName())) {
                    method.invoke(obj, Integer.parseInt(value));
                }else if ("double".equals(type.getName())) {
                    method.invoke(obj, Double.valueOf(value));
                } else if ("char".equals(type.getName())) {
                    method.invoke(obj, value);
                }
            }
        }
        return obj;
    }

    /**
     * 将List<Map<String,Object>>转换成List<javaBean>
     * @param listm
     * @param obj
     * @return
     * @throws Exception
     */
    public static List<Object> ListMapToListBean(List<Map<String, Object>> listm, Object obj) throws Exception {
        List<Object> list = new ArrayList<Object>();
        // 循环遍历出map对象
        for (Map<String, Object> m : listm) {
            // 调用将map转换为JavaBean的方法
            Object objs = mapToBean(m, obj);
            // 添加进list集合
            list.add(objs);
        }
        return list;
    }

    /**
     * 将list<javabean>转换为List<Map>
     * @param list
     * @return
     * @throws Exception
     */
    public static List<Map<String, Object>> ListBeanToListMap(List<Object> list) throws Exception {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        for (Object ob : list) {
            listmap.add(beanToMap(ob));
        }
        return listmap;
    }

}
