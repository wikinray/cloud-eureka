package com.test.demo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyList<T> implements Iterable<T> {
    //定义list初始化长度
    private  int length=10;
    //建立数组用于存储元素
    private Object[] arr=new Object[length];
    //建立数组索引，用户记录当前有几个元素
    private int index=0;

    /**
     * 根据长度创建list
     * @param length
     */
    public MyList(int length) {
        this.length = length;
    }

    /**
     * 构造方法
     */
    public MyList() {
    }

    /**
     * 增加元素
     * @param object
     */
    public void add(T object){
        //判断当前
        if(index>=arr.length){
            Object[] newArr= Arrays.copyOf(arr,length+10);
            arr=newArr;
        }
        arr[index++]=object;
    }




    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }
}
