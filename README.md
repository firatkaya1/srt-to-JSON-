# srt-to-JSON-

## Nedir ? 

.srt uzantılı dosyaların JSON'a çevrilmesi için geliştirilmiş basit bir çeviricidir. 

## Nasıl Kullanılır ? 

```

List<Subtitle>  subtitle = new SubtitleService.Builder()
                .path("/home/kaya/test.srt")                      // srt uzantılı dosyanın bulunduğu yer
                .targetPath("/home/kaya/Downloads")               // oluşucak json'un yeni yeri
                .fileName("yeni-dosyam")                          // yeni oluşucak json'un adı
                .isJson(true)                                     // json dosyasında mı oluşsun yoksa text şeklinde mi ? true ise json şeklinde oluşur.
                .logging(true)                                    //log'ları görmek istiyorsanız bu değeri true yapabilirsiniz. 
                .isCompressed(true)                               //elde edeceğimiz dosyanın sıkıştırılıp sıkıştırılmayacağına karar verebilirsin.
                .jsonKeyofStartTime("başlangiç")                  //opsiyonel seçenek : elde edeceğimiz json dosyasında key-value şeklinde saklanır burada key'lere 
                                                                  //kendi istediğimiz değerleri verebiliriz. Tavsiye olarak dosyanın vereceğiniz key isimleri ne kadar kısa
                                                                  // olursa elde edeceğiniz dosya boyutu bir o kadar az olacaktır.
                .jsonKeyofEndTime("bitiş")
                .jsonKeyofLine("satir")
                .jsonKeyofMessage("mesaj")
                .build();
```
