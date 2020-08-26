package com.kayafirat.service;

import com.kayafirat.exceptions.NotSupportFile;
import com.kayafirat.model.Subtitle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubtitleService {

    private String filename;
    private boolean isJson;
    private boolean isCompressed;
    private String path;
    private String targetPath;

    private String jsonKeyStartTime;
    private String jsonKeyEndTime;
    private String jsonKeyLine;
    private String jsonKeyMessage;

    private String startTime = "";
    private String endTime = "";
    private String message = "";
    private String line = "1";
    private String tmp = "-1";

    public static class Builder {
        private String filename;
        private boolean isJson;
        private boolean isCompressed;
        private boolean isLogging;
        private String path;
        private String targetPath;

        private String jsonKeyStartTime;
        private String jsonKeyEndTime;
        private String jsonKeyLine;
        private String jsonKeyMessage;

        public Builder path(String path){
            this.path = path;
            return this;
        }
        public Builder targetPath(String targetPath){
            this.targetPath = targetPath;
            return this;
        }
        public Builder fileName(String filename){
            this.filename = filename;
            return this;
        }
        public Builder isJson(boolean isJson){
            this.isJson = isJson;
            return this;
        }
        public Builder isCompressed(boolean isCompressed){
            this.isCompressed = isCompressed;
            return this;
        }
        public Builder logging(boolean logging){
            this.isLogging = logging;
            return this;
        }
        public Builder jsonKeyofStartTime(String startTime) {
            this.jsonKeyStartTime = startTime;
            return this;
        }
        public Builder jsonKeyofEndTime(String endTime) {
            this.jsonKeyEndTime = endTime;
            return this;

        }
        public Builder jsonKeyofLine(String line) {
            this.jsonKeyLine = line;
            return this;

        }
        public Builder jsonKeyofMessage(String message) {
            this.jsonKeyMessage = message;
            return this;

        }
        public List<Subtitle> build() {
             return new SubtitleService(this).srtReader();
        }



    }

    public SubtitleService(Builder builder){
            this.path = builder.path;
            this.targetPath = builder.targetPath;
            this.filename = builder.filename;
            this.isJson = builder.isJson;
            this.isCompressed = builder.isCompressed;
            this.jsonKeyStartTime = builder.jsonKeyStartTime;
            this.jsonKeyEndTime = builder.jsonKeyEndTime;
            this.jsonKeyMessage = builder.jsonKeyMessage;
            this.jsonKeyLine = builder.jsonKeyLine;
    }

    public List<Subtitle> srtReader(){
        List<Subtitle> list = new ArrayList<>();
        if (!isSrt(path)) throw new NotSupportFile(path);
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();
                if (data.length() > 0 ) {
                    if(isLine(data))
                            extractLine(data);
                    else if(isTime(data)){
                            extractStartTime(data);
                            extractEndTime(data);
                    }
                    else
                        extractMessage(data,myReader);

                    if( message.length()>0 && startTime.length()>0)
                        list.add(new Subtitle(startTime,endTime,line,message));
                    clearValues();
                }

            }

            myReader.close();
            writeToFile(list);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return list;
    }

    private void writeToFile(List<Subtitle> list){

        try {
            File myWriter = new File(targetPath+"/"+filename+".json");
            FileOutputStream fos = new FileOutputStream(myWriter);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write("[");
            for (int i=0;i<list.size();i++){
                if(list.size()-1 != i) {
                    bw.write(list.get(i).toString(this.jsonKeyStartTime,this.jsonKeyEndTime,this.jsonKeyLine,this.jsonKeyMessage)+",");
                    if (!this.isCompressed){
                        bw.newLine();
                    }
                } else {
                    bw.write(list.get(i).toString(this.jsonKeyStartTime,this.jsonKeyEndTime,this.jsonKeyLine,this.jsonKeyMessage));
                }
            }
            bw.write("]");

            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    private boolean isLine(String data) {
        return Character.isDigit(data.charAt(0)) && data.length() <= 4;
    }

    private void extractLine(String data) {
        line = data;
        if(line.equals("1") && tmp != "-1") {
            line = tmp;
        }
    }

    private boolean isTime(String data) {
        return (Character.isDigit(data.charAt(0)) && Character.isDigit(data.charAt(1)) && data.charAt(2) == ':' ) && data.length()>=5 ;
    }

    private void extractStartTime(String data) {
        startTime = data.substring(0,data.indexOf("-")-1);
    }

    private void extractEndTime(String data) {
        endTime = data.substring(data.indexOf(">")+1);
    }

    private void extractMessage(String data,Scanner myReader) {
        message = data;
        if(myReader.hasNextLine()) {
            String test = myReader.nextLine();
            if(test.length() > 0) {
                if (!Character.isDigit(test.charAt(0))) {
                    message = message + " " + test;
                }
            }

        }
    }

    private void clearValues() {
        tmp = line;
        message = "";
    }

    private boolean isSrt(String  path) {
        boolean success = false;
        if (path.contains(".srt"))  success = true;
        return success;
     }


}
