package com.ck.controller.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class UploadUtils {

    public static Object[] uploadFile(MultipartFile fileUpload, String folder) throws IOException {
        Boolean isFalse = false;
        String originalFileName = "";
        Long fileSize = null;
        String location = "fileupload" + File.separator + folder;

        try{
            createFolderNotExist(location);
            File uploadFile = new File(location + File.separator + fileUpload.getOriginalFilename());
            if(uploadFile.exists()){
                if(!uploadFile.delete()){
                  isFalse = true;
                }
            }
            else{
                uploadFile.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(uploadFile);
            fileOutputStream.write(fileUpload.getBytes());
            fileOutputStream.close();
            originalFileName = fileUpload.getOriginalFilename();
            fileSize = fileUpload.getSize();

        }catch (FileNotFoundException e){
            isFalse = true;
        }
        catch (IOException e){
            isFalse = true;
        }finally {

        }
       return new Object[]{isFalse,fileSize,originalFileName};
    }
    public static File findFile(String fileName,String folder){
        File file = new File("fileupload" + File.separator + folder + File.separator + fileName);
        if(file.exists()){
            return file;
        }
        else
        throw new com.ck.exceptionhandler.FileNotFoundException(fileName);
    }
    private static void createFolderNotExist(String location) {
        File fileLocation = new File(location);
        if(!fileLocation.exists()){
            fileLocation.mkdirs();
        }
    }
}
