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
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", line='" + line + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String toString(String _startTime, String _endTime, String _line, String _message) {
        _startTime = _startTime == null ? "startTime" : _startTime;
        _endTime = _endTime == null ? "endTime" : _endTime;
        _line = _line == null ? "line" : _line;
        _message = _message == null ? "message" : _message;
        return "{" +
                 "\""+_startTime+"\":\"" + startTime + '\"' +
                ",\""+_endTime+"\":\"" + endTime + '\"' +
                ",\""+_line+"\":\"" + line +'\"'+
                ",\""+_message+ "\":\"" + message + '\"' +
                '}';
    }


}
