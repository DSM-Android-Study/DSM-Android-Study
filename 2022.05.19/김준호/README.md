# JetpackCompose 사용기 1

- **JetpackCompose란?**

  Jetpack Compose는 네이티브 Android UI를 빌드하기 위한 최신 도구 키트입니다. Jetpack Compose는 더 적은 수의 코드, 강력한 도구, 직관적인 Kotlin API로 Android에서의 UI 개발을 간소화하고 가속화합니다.( xml파일이 사라짐!!! )

### 기본으로 알아야 하는 것

- **Preview**

  화면 미리보기

  ```kotlin
  @Preview(showBackground = true)
  @Composable
  fun DefaultPreview() {
     ComposePracticeTheme {
         Show()
     }
  }
  ```

  ```kotlin
  @Preview(showBackground = true, showSystemUi = true)
      @Composable
      fun DefaultPreview() {
          ComposePracticeTheme {
              Show()
          }
      }
      //showSystemUi = true 추가
  ```

​		![스크린샷 2022-05-19 오후 4.00.33](/Users/kimjunho/Desktop/스크린샷 2022-05-19 오후 4.00.33.png)

​			![스크린샷 2022-05-19 오후 4.03.00](/Users/kimjunho/Desktop/스크린샷 2022-05-19 오후 4.03.00.png)

- **Column**

  항목을 화면에 <u>세로</u>로 배치

  ![스크린샷 2022-05-19 오후 4.05.57](/Users/kimjunho/Desktop/스크린샷 2022-05-19 오후 4.05.57.png)

  ```kotlin
  @Composable
      fun Show(){
          Column() {
              Text(text = "컴")
              Text(text = "포")
              Text(text = "즈")
          }
      }
  ```

  

- **Row**

  항목을 화면에 <u>가로</u>로 배치

  ![스크린샷 2022-05-19 오후 4.06.38](/Users/kimjunho/Desktop/스크린샷 2022-05-19 오후 4.06.38.png)

  ```kotlin
  @Composable
      fun Show(){
          Row() {
              Text(text = "컴")
              Text(text = "포")
              Text(text = "즈")
          }
      }
  ```

  *추가로

  ```kotlin
  @Composable
      fun Show(name: String){
          (Text(text = name))
      }
  
      @Preview(showBackground = true, showSystemUi = true)
      @Composable
      fun DefaultPreview() {
          ComposePracticeTheme {
              Show("컴포즈")
          }
      }
  ```

  - 이런 식으로 매개변수를 이용하여 데이터 전달이 가능
  - 이를 이용해서 특정 기능을 함수화하여 중복을 줄이고 코드를 단축 시킬 수 있다

  

- **Box**

  여러 항목을 <u>곂쳐서</u> 배치

