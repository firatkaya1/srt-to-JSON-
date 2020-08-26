package com.kayafirat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

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
