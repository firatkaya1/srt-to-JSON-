package com.kayafirat.model;

public class Subtitle {

    private String startTime;
    private String endTime;
    private String line;
    private String message;

    public Subtitle() { }

    public Subtitle(String startTime, String endTime, String line, String message) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.line = line;
        this.message = message;
    }

    public String getStartTime() {
        return startTime;
    }

    public Subtitle setStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public Subtitle setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getLine() {
        return line;
    }

    public Subtitle setLine(String line) {
        this.line = line;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Subtitle setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "Subtitle{" +
                "startTime='" + startTime.trim() + '\'' +
                ", endTime='" + endTime.trim() + '\'' +
                ", line='" + line.trim() + '\'' +
                ", message='" + message.trim() + '\'' +
                '}';
    }

    public String toString(String _startTime, String _endTime, String _line, String _message,boolean isJson) {
        _startTime = _startTime == null ? "startTime" : _startTime;
        _endTime = _endTime == null ? "endTime" : _endTime;
        _line = _line == null ? "line" : _line;
        _message = _message == null ? "message" : _message;
        if(isJson) {
            return "{" +
                    "\""+_startTime+"\":\"" + startTime.trim() + '\"' +
                    ",\""+_endTime+"\":\"" + endTime.trim() + '\"' +
                    ",\""+_line+"\":\"" + line.trim() +'\"'+
                    ",\""+_message+ "\":\"" + message.trim() + '\"' +
                    '}';
        } else {
            return "" +
                    ""+_startTime+":" + startTime.trim()  +
                    ", "+_endTime+":" + endTime.trim()  +
                    ", "+_line+":" + line.trim() +
                    ", "+_message+ ":" + message.trim();
        }

    }


}
