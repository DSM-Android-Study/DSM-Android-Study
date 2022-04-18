# SimpleDateFormat

- 날짜 또는 시간 등등 원하는 포맷으로 출력하고 싶을 때 간편하게 사용하게 해주는 클래스
- 쓰이는 문자에 따라 출력이 다르게 나옴

| Symbol | 뜻                           | 예시(/로 구분) |
| :----- | :--------------------------- | -------------- |
| **y**  | 년도                         | 2019, 19       |
| **M**  | 월 (1~12)                    | 4, 4월         |
| D      | 일 (1~366)                   | 139            |
| **d**  | 일 (1~31)                    | 24, 24일       |
| H      | 시간 (0~23)                  | 19             |
| h      | 시간 (1~12)                  | 12             |
| K      | 시간 (0~11)                  | 5              |
| k      | 시간 (1~24)                  | 23             |
| **m**  | 분 (0~59)                    | 23             |
| S      | 1/1000초 (0~999)             | 877            |
| **s**  | 초 (0~59)                    | 42             |
| z      | Time Zone                    | GMT+9:00       |
| **E**  | 요일                         | 화, 목         |
| W      | 해당 월의 몇 번째 주(1~5)    | 3              |
| w      | 해당 년도의 몇 번째 주(1~53) | 44             |
| F      | 해당 월의 몇 번째 요일(1~5)  | 2              |
| **a**  | 오전(AM) / 오후(PM)          | AM / PM        |

```대체적으로 많이 쓰는 문자 bold처리``` [오라클 공식 문서](https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html)





## 기본적 사용법

[Kotlin]

```kotlin
var sdf = SimpleDateFormat ("mm:ss") //선언
val time = sdf.format(시간값)
```

[Java]

```java
SimpleDateFormat sdf = new SimpleDateFormat ("mm:ss");
String time = sdf.format (System.currentTimeMillis());
println(time);
```

![화면 캡처 2022-04-14 155656](C:\Users\user\OneDrive\사진\Typora\화면 캡처 2022-04-14 155656.png)





## 예제

- 현재 시간 구하기

```java
SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.KOREAN);
String time = sdf.format(System.currentTimeMillis());
System.out.println(time);
```

[결과] 14:30:25

`currentTimeMillis : 현재 시간과 UTC(협정세계시)인 1970년 1월 1일 자정과의 차이로 밀리세컨드(1/1000초) 값을 반환`

![화면 캡처 2022-04-14 144532](C:\Users\user\OneDrive\사진\Typora\화면 캡처 2022-04-14 144532.png)

![화면 캡처 2022-04-14 144628](C:\Users\user\OneDrive\사진\Typora\화면 캡처 2022-04-14 144628.png)



- 음원 길이 분, 초 구하기

```kotlin
var timeFormat = SimpleDateFormat("mm:ss") //선언

start_tv.text = timeFormat.format(mp.currentPosition) //음원 길이에 맞게 글자에 담기
end_tv.text = timeFormat.format(mp.duration - mp.currentPosition) //전체시간 - 진행시간
```





------





# Calendar

- 국가별, 시간대별 시간을 정확히 알수있도록 제공



```java
Calender 객체명 = Calendar.getInstance()
```

- 시스템의 현재 날짜와 시간정보를 얻기 위해 getinstance() 메소드 사용

- 추상 클래스, 직접 new 하여 객체 생성이 불가 (현재 자바 시간에 배우는 부분!?!)
- Calendar 클래스를 상속받는 GregorianCalendar 클래스를 이용해 객체를 생성 가능



```java
Calender cal = Calender.getInstance();
System.out.println(cal);
```

- 위와 같이 캘린더 객체의 getInstance() 만 해도 현재 날짜와 정보를 가져 올 수는 있음

![image-20220414193220673](C:\Users\user\OneDrive\사진\Typora\image-20220414193220673.png)

​		다만 결과가 오지게 길다 그래서 아래와 같이 필요한 부분만 가져다 쓴다

```java
Calendar today = Calendar.getInstance();
int year = today.get(Calendar.YEAR);
int month = today.get(Calendar.MONTH);
int date = today.get(Calendar.DATE);
```

|     변수      |                             내용                             |
| :-----------: | :----------------------------------------------------------: |
|     YEAR      |                          해당 연도                           |
|     MONTH     | 해당 월 (0~11 / 1월을 0으로 ~12월을 11로 반환 / 실제 표기를 위해선 +1을 해주어야 함) |
|     DATE      |                          해당 일자                           |
|     HOUR      |              현재 시간 (오전,오후 기준 / 0~11)               |
|  HOUR_OF_DAY  |                현재 시간 (24시간 기준 / 0~23)                |
|    MINUTE     |                           현재 분                            |
|    SECOND     |                           현재 초                            |
|  MILLISECOND  | 현재 밀리세컨즈 (전체 밀리세컨즈는 cal.getTimeInMillis()를 이용) |
|  DAY_OF_YEAR  |                    올해 1월 1일 기준 일자                    |
| WEEK_OF_MONTH |                   해당 월 기준 주차 (1~5)                    |
| WEEK_OF_YEAR  |                  해당 연도 기준 주차 (1~53)                  |



## 예제

- 날짜 더하고 빼기

```kotlin
    val cal = Calendar.getInstance()
    cal.time = Date()
    val df : DateFormat = SimpleDateFormat("yyyy-MM-dd")
    println("current: ${df.format(cal.time)}")
    // ${} : 문자열 안에 변수 바로 사용하기

    cal.add(Calendar.MONTH, 2) // 월에 2 더하기
    cal.add(Calendar.DATE, -12) // 일에 12 빼기
    println("after: ${df.format(cal.time)}")
```

![화면 캡처 2022-04-14 192402](C:\Users\user\OneDrive\사진\Typora\화면 캡처 2022-04-14 192402.png)



- D-Day 구하기 (두 날짜간 차이, 시작일자로부터 경과일 수)

```kotlin
    val format = SimpleDateFormat("yyyyMMdd")

    val start = format.parse("20220401").time
    val end = format.parse("20220503").time
    // parse : 시간을 의미하는 문자열을 해석해서 밀리세컨드를 리턴

    val today = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.time.time

    println("시작일과 목표일 차이 : ${(end - start) / (24 * 60 * 60 * 1000)}")
    println("시작일부터 경과 일 : ${(today - start) / (24 * 60 * 60 * 1000)}")
    println("D-DAY : ${(end - today) / (24 * 60 * 60 * 1000)}")
```

