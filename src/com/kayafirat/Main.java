package com.kayafirat;

import com.kayafirat.model.Subtitle;
import com.kayafirat.service.SubtitleService;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Subtitle> list = new SubtitleService.Builder()
                .path("/home/kaya/test.srt")
                .targetPath("/home/kaya")
                .fileName("xxx3")
                .isJson(true)
                .isCompressed(false)
                .logging(true)
                .build();




    }


}
