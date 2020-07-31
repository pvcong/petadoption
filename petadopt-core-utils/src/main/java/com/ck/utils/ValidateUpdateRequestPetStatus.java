package com.ck.utils;

public class ValidateUpdateRequestPetStatus {
    public static Boolean validate(Integer updateId, Integer currentId){
        Boolean isOk = false;
        switch (currentId){
            case 1: isOk = pendingStatusAccept(updateId); break;
            case 2: isOk =contactingStatusAccept(updateId); break;
            case 3: isOk = notContactStatusAccept(updateId); break;
//            case 5: isOk = appointmentStatusAccept(updateId); break;
//            case 9: isOk = notContactAppointmentStatusAccept(updateId); break;
        }
        return isOk;
    }

    private static Boolean pendingStatusAccept( Integer updateId) {
        if(updateId == 2 || updateId == 7){
             return true;
        }
        else{
            return false;
        }
    }

    private static Boolean contactingStatusAccept(Integer updateId){
        if(updateId == 3 || updateId == 5){
            return true;
        }
        else{
            return false;
        }
    }
    private static Boolean notContactStatusAccept(Integer updateId){
        if(updateId == 2 || updateId == 7){
            return true;
        }
        else{
            return false;
        }
    }


    private static Boolean appointmentStatusAccept(Integer updateId){
        if(updateId == 6 || updateId == 9  ){
            return true;
        }
        else{
            return false;
        }
    }
    private static Boolean notContactAppointmentStatusAccept(Integer updateId){
        if(updateId == 9 || updateId == 5  ){
            return true;
        }
        else{
            return false;
        }
    }

}
