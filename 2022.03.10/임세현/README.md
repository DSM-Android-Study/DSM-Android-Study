## 확장함수 활용하기



### 확장함수란?

Extension functions(확장 함수)는 기존에 정의된 클래스에 함수를 추가하는 기능입니다. 자신이 만든 클래스는 새로운 함수가 필요할 때 쉽게 추가할 수 있습니다.



![image-20220310224742270](/Users/limsaehyun/Library/Application Support/typora-user-images/image-20220310224742270.png)



### 확장함수 활용하기

```kotlin
binding.view.visibility = View.VISIBLE
binding.view.visibility = View.INVISIBLE
binding.view.visibility = View.GONE
```



View의 visibility를 설정하는 코드입니다. 코드를 자세히 보시면 중복되는 부분이 존재합니다. 이 부분을 어떻게 해서 중복을 제거할 수 있을까요?



```kotlin
fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}
```



View 확장 함수를 사용하면 아레 코드와 같이 코드를 간결화 할 수 있습니다.



```
binding.view.gone()
binding.view.invisible()
binding.view.visible()
```



이 외에도 확장 함수는 여러가지 경우에 사용이 가능합니다.