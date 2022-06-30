# Databinding ?
- xml 파일에 Data를 연결해서 사용할 수 있게 도와줌
- Android JetPack 라이브러리 기능 중 하나

- 애플리케이션 로직과 레이아웃을 binding하는 데 필요한 글루코드를 최소화함
	- findViewByld를 사용하지 않아도 됨
	- MVVM 패턴을 구현 할 때 **LiveData**와 함께 사용
	>글루 코드?
	>: 프로그램의 요구사항 구현에는 기여하지 않지만, 본래 호환성이 없는 부분끼리 결합하기 위해 작동되는 코드



# 기본 사용법
### 1. Gradle
```kotlin
apply plugin: 'kotlin-kapt'

android {

	....

	dataBinding {

		enabled = true

	}

}
```

### 2. Layout
   - ConstraintLayout을 layout 아래로 두고 **data**, **variable**을 추가
```kotlin
<layout xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:app="http://schemas.android.com/apk/res-auto">
        <data>
            <variable
                name="viewmodel"
                type="com.myapp.data.ViewModel" />
        </data>
        <ConstraintLayout... /> <!-- UI layout's root element -->
    </layout>
```

### 3. View에서 레이아웃을 초기화하는 코드
```kotlin
//기존 setContentView 메서드를 onCreate에서 호출하는 형태
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
}

//데이터 바인딩을 사용할때는 다른 형식으로 바꿔줘야한다. 
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding 세팅
        val binding: ActivityMainBinding = DatabindingUtil.setContentView(this,R.layout.activity_main)
}
```

