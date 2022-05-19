# SplashActivity



### 앱 실행 시 로딩 화면

### MainActivity와 구분하여 액티비티를 따로 생성한다.

## 문제점 
### 안드로이드 12부터는 기본적용 스플래시 화면과 함께 중복되는 오류가 발생한다.
### 앱이 리소스가 되어있는 상태에서도 스플래시 화면이 나오게 된다

# SplashAPI

```
맨 처음 뜨는 로고 - Splash API
두 번째로 작게 뜨는 로고 - SplashActivity
```

### Splash API를 사용하지 않으면 안드로이드 12 시스템에 의해 자동으로 앱의 로고가 보이는 스플래시가 삽입된다.
### SplashActivity까지 총 2번의 Splash화면이 보이게 되므로 UI가 이쁘지 않다.

## 적용
### 라이브러리 추가
```xml
implementation("androidx.core:core-splashscreen:${Versions.Ui.Splash}")
```

### 테마 만들기
```xml
<resources xmlns:tools="http://schemas.android.com/tools">
    <!-- Base application theme. -->
    <style name="Theme.SplashAPI" parent="Theme.MaterialComponents.DayNight.NoActionBar">
        <!-- Primary brand color. -->
        <item name="colorPrimary">@color/purple_500</item>
        <item name="colorPrimaryVariant">@color/purple_700</item>
        <item name="colorOnPrimary">@color/white</item>
        <!-- Secondary brand color. -->
        <item name="colorSecondary">@color/teal_200</item>
        <item name="colorSecondaryVariant">@color/teal_700</item>
        <item name="colorOnSecondary">@color/black</item>
        <!-- Status bar color. -->
        <item name="android:statusBarColor" tools:targetApi="l">?attr/colorPrimaryVariant</item>
        <!-- Customize your theme here. -->
    </style>

    <style name="Theme.SplashAPI.Splash" parent="Theme.SplashScreen" >
        <item name="windowSplashScreenBackground">@color/white</item>
        <item name="windowSplashScreenAnimatedIcon">@drawable/ic_baseline_camera_24</item>
        <item name="postSplashScreenTheme">@style/Theme.SplashAPI</item>

        <item name="android:statusBarColor">@color/white</item>
        <item name="android:navigationBarColor">@color/white</item>
    </style>
</resources>
```

<img src="C:\Users\msp05\Downloads\KakaoTalk_20220512_193338826.jpg" style="zoom:50%;" />

<img src="C:\Users\msp05\Downloads\KakaoTalk_20220512_193338826_01.jpg" alt="KakaoTalk_20220512_193338826_01" style="zoom:50%;" />