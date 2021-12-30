# 2021. 12. 28(목)

### 웹뷰란?

- 웹페이지를 앱 안에서 보여주는 것

### 웹뷰 사용 과정

1. 퍼미션 추가

```
<uses-permmission android:name="android.permiss웨1ion.INTERNET"/>

android:usesCleartextTraffic="true"(<application> 태그 내부에 이 속성을 추가)
```

2. 웹뷰 초기화( 변수 선언 + 초기화 )

```kotlin
val webView : WebView = findViewById(R.id.webView)
```

3. 웹페이지 호출

```kotlin
webView.loadUrl("https://www.naver.co.kr/")
```



### 안드로이드 웹뷰 통신 예제

- 웹뷰의 자바스크립트와 안드로이드의 클라이언트 코드의 통신이 필요( javascriptInterface 사용 )

- 순서

  1) 웹뷰의 자바스크립트 기능 활성화
  2) JavascriptInterface를 추가
  3) 로딩

- 웹뷰에서 클라이언트 코드만을 호출하는 예제

  1. JavascriptEnabled를 활성화 시켜준다

  ```kotlin
  val webView:Wew = findViewById(R.id.webview)
  webView.settings.javaScriptEnabled = true
  ```

  2. @JavascriptInterface 함수 생성

  ```kotlin
  class WebAppInterface(private val mContext: Context) {
      @JavascriptInterface
      fun showToast(toast: String) {
          Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
      }
  }
  ```

  3. 위에서 생성한 클래스를 웹뷰에 추가하기

  ```kotlin
  webView.addJavascriptInterface(WebAppInterface(this), "BlackJin")
  ```

  4. 자바스크립트에서 안드로이드에 선언한 함수를 호출할 수 있다.

  ```html
  <input type="button" value="Say hello" onClick="showAndroidToast('Hello Android!')"/
  
  <script type="text/javascript">
  	function showAndroidToast(toast){우
      	Android.showToast(toast);
    }
  </script>
  ```

- 클라이언트의 값을 전달받아 이를 웹뷰에서 가공 후 출력하는 예제

  1) 위 순서와 같이 코드르 짠다
  2) 클라이언트 코드에서 웹뷰의 자바스크립트를 호출

  ```kotlin
  webView.loadUrl("javascript:exam_script.plus_num("+edittext.text+")")
  ```

  ```js
  var exam_script = {
      plus_num: function(num){
          try{
              var result = num * 2
              BlackJin.getDoubleNum(result)
          }catch(err){
              console.log(">> [exam_script.plus_num()] " + err)
          }
      }
  }
  ```

  



###  출처

https://black-jin0427.tistory.com/272

