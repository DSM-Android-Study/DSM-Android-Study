# Kotlin의 Null 처리

- 코틀린의 타입 : 기본적으로 **null 불가능 (non-nullable) **

- 코틀린의 큰 장점 중 하나 - Null 처리를 비교적 안전하고, 간결하게 쓸 수 있음





## Null ?

- null : 아무 값도 대입하지 않은 상태



- Null Pointer Exception ?
  : Null 상태의 변수는 할당된 메모리가 없어 변수에 접근을 시도 -> 당연히 에러, 이를 Null Pointer Exception (NPE)

- Null은 메모리 할당이 이루어지지 않은 상태를 표현하기 위한 용도지만 개발 때는 귀찮음
  -> NPE를 피하고자 Null인지 아닌지 확인하고 Null일 때의 처리, Null이 아닐 때의 처리를 구구절절 짜야함...





## Nullable과 Non-Nullable

```kotlin
var nullable: String? = null //컴파일 성공
var nonNull: String = null //컴파일 에러
```

```kotlin
var a: String = "abc"
a = null //에러

var b: String? = "abc"
b = null //성공
```

- 타입을 선언할 때
  ?를 붙이면 null을 할당할 수 있는 프로퍼티,
  ?가 붙지 않으면 null이 허용되지 않는 프로퍼티를 의미

- 따라서 nullable은 null을 할당할 수 있지만, nonNullable에 null을 할당하려고 하면 컴파일 에러가 발생

- 이처럼 코틀린만 사용한다면 Null Pointer Exception 같은 예외가 발생하지 않을 수 있음
  그래서 코틀린에서는 NPE가 발생하지 않을 것 같지만 자바의 라이브러리를 쓰는 경우 NPE가 발생



#### 코틀린에서 NullPointerException이 발생할 수 있는경우

- **throw NullPointerException();** 명시적 호출
- **!!** 연산자 사용
- 초기화에 관한 데이터 불일치
  - 생성자에서 사용할 수 있는 초기화되지 않은 this가 전달되어 어딘가에 사용되는경우 **("leaking this")**
  - superclass 생성자는 파생 클래스에서 구현이 초기화되지 않은 상태를 사용하는 open member를 호출
- 자바 상호 작용
  - platform type의 null 참조로 멤버에 대한 액세스 시도
  - Java에서 MutableList와 같은 non-Null type 에 null을 추가하려는 경우
  - 외부 Java 코드로 인한 기타 문제





## **if-else** 조건문

```kotlin
var nullableTest: String? = null

if (nullableTest != null) {
    println(nullableTest)
}
else {
    println("null")
}
```

- 가장 쉽게 떠올릴 수 있는 방법
- 제일 간단하기는 하나 뭔가 세련되어 보이지 않음





## **?.** 안전호출연산자 (safe calls)

```kotlin
var nullable: String? = null
println(nullable?.length)
```

- 코틀린에서 nullable한 변수를 처리할 때 가장 자주 사용되는 방법
- null값을 검사하여 null이면 건너뛰고 null이 아닐 때는 호출된 연산을 실행



```kotlin
 println(a?.b?.c?.d?.length)
```

- 하나라도 null이 있다면 결과는 null로 반환





## **?:** 엘비스(Elvis) 연산자

```kotlin
var nullable: String? = null
var len = nullable?.lenght ?: "반환할 값"
```

- 위에서 세이프콜을 사용했을 때, 널이 아닌 다른 값으로 반환하려면 엘비스 연산자 사용
- 삼항연산자와 비슷
- ?: 왼쪽의 객체가 null이 아니면 이 객체를 리턴 / null이라면 ?:의 오른쪽 객체를 리턴
- 이 연산자가 없다면 조건문을 이용해 따로 처리를 해줘야 하기에 훨씬 간결하게 사용 가능





## **!!** 예외 발생 연산자

- !! 연산자는 Null이면 NPE를 발생

```kotlin
val name1: String? = "과일"
val name2: String = name1 //에러
val name3: String? = name1 //정상
val name4: String = name1!! //정상
```

- 변수 뒤에 !!을 추가하면 null 값이 아님을 보증하는 것을 의미
- String? 타입의 변수를 다른 변수에 저장할 때
  같은 타입에 저장 / String? 타입의 변수의 뒤에 !!을 붙여서 String 타입으로 변환

- 주의 - NPE(NullPointerException)를 일으킬 수 도 있으므로 주의 깊게 사용해야 함





## **as?** 안전한 캐스팅 (Safe Cast)

- 캐스팅이 불가능한 경우는 ClassCastException이 발생
- ClassCastException이 발생해야 하는 상황에 에러 없이 null을 반환하고 싶을 때 as? 연산자를 활용
- 캐스팅이 실패하는 경우 null이 할당됨



```kotlin
val myName = "Jiwon"
val numer: Int? = myName as? Int
```

- myName은 숫자가 아니므로 캐스팅 에러가 발생하는데 에러가 발생하지 않고 null이 nubmer에 담김
- string은 문자열이지만 Any타입 -> as?를 이용하여 String과 Int로 형변환을 시도 -> Int는 타입이 맞지 않기 때문에 null을 리턴





## Collection의 Null 객체를 모두 제거

```kotlin
val nullableList: List<Int?> = ListOf(1, 2, null, 4)
val intList: List<Int> = nullableList.filterNotNull()
```

- List에 있는 null 객체를 filterNotNull 메소드를 이용하여 삭제하는 코드
- filterNotNull() - list에서 null이 아닌 데이터만 추출



- 실행 결과를 보면 보면 null이 제거된 나머지 아이템들만 출력 : [1, 2, 4]