**발표 자료 웹으로 보기**
https://drive.google.com/file/d/1GCHdjsjv4bWar-7VG6y9LXeP_wh3BMS3/view?usp=sharing

## lateinit VS by lazy

lateinit와 by lazy의 차이는 선언을 var로 하느냐 val로 하느냐의 차이에 있다.

```
private lateinit var a : String

private val b : Int by lazy{
     1
}
```



## 동작 범위 지정 함수

let & with & run & apply & also 란?



### let

`let` 함수는 매개변수화된 타입 T의 확장 함수이다.(extension) 자기 자신을 받아서 R을 반환하는(`(T) -> R`) 람다 식을 입력으로 받고, 블럭 함수의 반환값 `R`을 반환한다. 여기서는 Person 클래스의 확장 함수로 사용되어 `person.let` 의 형태가 가능해진다.



### with

`with`는 일반 함수이기 때문에 객체 receive를 직접 입력받고, 객체를 사용하기 위한 두 번째 파라미터 블럭을 받는다. `T.()`를 람다 리시버라고 하는데, 입력을 받으면 함수 내에서 `this`를 사용하지 않고도 입력받은 객체(receiver)의 속성을 변경할 수 있다.



### run 

`run`은 with처럼 인자로 람다 리시버를 받고, 반환 형태도 비슷하게 생겼지만 T의 확장함수라는 점에서 차이가 있다. 확장함수이기 때문에 safe call(`.?`)을 붙여 non-null 일 때에만 실행할 수 있다. 어떤 값을 계산할 필요가 있거나 여러 개의 지역변수 범위를 제한할 때 사용한다.



### apply

`apply`는 T의 확장 함수이고, 블럭 함수의 입력을 람다 리시버로 받았기 때문에 블럭 안에서 객체의 프로퍼티를 호출할 때 it이나 this를 사용할 필요가 없다. run과 유사하지만 블럭에서 return 값을 받지 않으며 자기 자신인 T를 반환한다는 점이 다르다.



### also

블럭 함수의 입력으로 T를 받았기 때문에 `it`을 사용해 프로퍼티에 접근하는 것을 볼 수 있다. 그래서 객체의 속성을 전혀 사용하지 않거나 변경하지 않고 사용하는 경우에 also를 사용한다. 객체의 데이터 유효성을 확인하거나, 디버그, 로깅 등의 부가적인 목적으로 사용할 때에 적합하다.
