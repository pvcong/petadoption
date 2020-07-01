package com.ck.controller.utils;

import com.ck.controller.commander.AbstractCommand;

public class ParaginationUtils {
    public static void caculationFirstItem(AbstractCommand command){
        if(command.getPage() <= 0){
            command.setPage(1);
        }
        command.setFirtItem((command.getPage() - 1) * command.getMaxItem());
    }
    public static void caculationToltalPage(AbstractCommand command){
        command.setTotalPage(Integer.parseInt(String.format("%.0f",Math.ceil((double)command.getTotalItem() / command.getMaxItem()))));
    }
}
