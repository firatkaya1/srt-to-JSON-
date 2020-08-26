# srt-to-JSON-

## Nedir ? 

.srt uzantılı dosyaların JSON'a çevrilmesi için geliştirilmiş basit bir çeviricidir. 

## Nasıl Kullanılır ? 

```java

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

##### Bir Örnek 

Öncelikle bu örneğe başlamadan önce bir srt dosyasının iç yapısına kısa bir şekilde göz atmanızı istiyorum. Zahmet etmeyin, hizmet ayağınıza geldi. Aşağıda gördüğünüz bir srt dosyasında alınmış bir kaç satırı  : 

```
5
00:01:49,876 --> 00:01:52,213
- Kanı temizlemelisin.
- Anlaşıldı, patron.

6
00:01:52,246 --> 00:01:55,115
- Bu taraf çok dağınık.
- Evet.

7
00:01:55,149 --> 00:01:56,450
Öldürme atışı yaptım...

8
00:01:56,483 --> 00:01:59,819
Evet, dağınık.
Kafa, pençeler, deri?

9
00:01:59,852 --> 00:02:01,988
- Aynı gibi.
- Tamamdır.

10                                  //Satır numarası
00:02:02,021 --> 00:02:03,524       //Başlangıç ve bitiş saati
- Şimdi.                            //Altyazının kendisi
- Başka bir av daha mı?

```

Görebildiğimiz gibi bir srt dosyasında 4 farklı değer bulunuyor. Satır numarası, Altyazının başlangıç ve bitiş saati ve son olarakta altyazının kendisi bulunuyor.

Peki bir json dosyasına çevirdiğimizde nasıl bir değer elde edeceğiz ? 

Elde edeceğimiz sonuç şu şekilde olacaktır : 

```
[
  {"startTime":"00:01:49,876","endTime":" 00:01:52,213","line":"5","message":"- Kanı temizlemelisin. - Anlaşıldı, patron."},
  {"startTime":"00:01:52,246","endTime":" 00:01:55,115","line":"6","message":"- Bu taraf çok dağınık. - Evet."},
  {"startTime":"00:01:55,149","endTime":" 00:01:56,450","line":"7","message":"Öldürme atışı yaptım..."},
  {"startTime":"00:01:56,483","endTime":" 00:01:59,819","line":"8","message":"Evet, dağınık. Kafa, pençeler, deri?"},
  {"startTime":"00:01:59,852","endTime":" 00:02:01,988","line":"9","message":"- Aynı gibi. - Tamamdır."},
  {"startTime":"00:02:02,021","endTime":" 00:02:03,524","line":"10","message":"- Şimdi. - Başka bir av daha mı?"}
]

```
Yukarıda görebildiğin gibi key-value tipinde değerler çıkmaktadır. Key değerlerini değiştirmek, daha anlaşılabilir ya da daha kısa bir key değeri kullanmak istiyorsan tek yapman gereken aşağıdaki gibi kullanman. 
###### Tavsiye 
 key değerlerin ne kadar kısa olursa elde edeceğin json dosyasının boyutu daha az olacaktır. 

```
                .jsonKeyofStartTime("startTime")                  
                .jsonKeyofEndTime("endTime")
                .jsonKeyofLine("line")
                .jsonKeyofMessage("message")

```

##### isCompressed(true) nedir ? 

isCompressed değerine true vererek oluşturulucak dosyada var olan boşlukları engeller ve +5KB gibi bir yer kazandırır.

##### isJson(true) nedir ? 

isJson değerine true vererek oluşturulucak yeni dosyanın bir json formatında oldugunu belirtirsiniz. Text formatında almak istiyorsanız false değeri yapmanız yeterli. 
text formatında aşağıdaki gibi bir çıktı elde edersiniz : 

```

startTime:00:01:49,876,endTime: 00:01:52,213,line:5,message:- Kanı temizlemelisin. - Anlaşıldı, patron.
startTime:00:01:52,246,endTime: 00:01:55,115,line:6,message:- Bu taraf çok dağınık. - Evet.
startTime:00:01:55,149,endTime: 00:01:56,450,line:7,message:Öldürme atışı yaptım...
startTime:00:01:56,483,endTime: 00:01:59,819,line:8,message:Evet, dağınık. Kafa, pençeler, deri?
startTime:00:01:59,852,endTime: 00:02:01,988,line:9,message:- Aynı gibi. - Tamamdır.
startTime:00:02:02,021,endTime: 00:02:03,524,line:10,message:- Şimdi. - Başka bir av daha mı?


```
Son olarak, targetPath değerine sadece dosyanızın yeni yerini belirtmeniz gerekiyor, fileName ise yeni dosyanızın adı. Bu değerler girilmek zorunda.







