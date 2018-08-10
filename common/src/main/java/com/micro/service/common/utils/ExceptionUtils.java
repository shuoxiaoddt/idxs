package com.micro.service.common.utils;

/**
 * Created by xiaos 2018/7/17
 * //TODO 写点注释吧
 */
public class ExceptionUtils {

    public static void main(String[] args) {
        ExceptionUtils utils = new ExceptionUtils();
        try {
            utils.tryCatch();
        }catch (Exception e){
            System.out.println("catched!!");
        }
    }

    public void tryCatch() throws Exception{
        int i = 0;
        if(i != 0){

        }else{
            try {
                throw new Exception("hahaha");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
