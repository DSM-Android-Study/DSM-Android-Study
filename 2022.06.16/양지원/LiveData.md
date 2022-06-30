# LiveData ?
- LiveData는 Data의 변경을 관찰 할 수 있는 Data Holder 클래스
- LiveData는 활성상태(active)일때만 데이터를 업데이트함
>활성상태 : **STARTED** 또는 **RESUMED**를 의미
- 수명 주기를(Life Cycle)을 인식함
	- LifeCycleOwner : 안드로이드 생명주기를 알고 있는 클래스
	- Activity나 Fragment에서 이를 상속하고 있음
- LiveData 객체는 Observer 객체와 함께 사용됨
	- LiveData가 가지고 있는 데이터에 어떠한 변화가 일어날 경우, LiveData는 등록된 Observer 객체에 변화를 알려주고, Observer의 onChanged() 메소드가 실행



# 장점
### 1. Data와 UI간 동기화
   - LiveData는 Observer 패턴을 따른다
	   - LiveData는 안드로이드 생명주기에 데이터 변경이 일어날 때마다 Observer 객체에 알려준다
	   -  Observer 객체를 사용하면 데이터의 변화가 일어나는 곳마다 매번 UI를 업데이트하는 코드를 작성할 필요 없이 데이터의 상태와 UI를 일치시킨다

### 2. 메모리 누수 없음
   - Observer가 LifeCycle 객체에 결합되어 있어 앞서 말했듯 컴포넌트가 Destroy 될 경우 자동으로 삭제된다

### 3. 항상 최신 데이터를 유지
   - 화면 구성이 변경되어도 데이터를 유지한다
     ex) 디바이스를 회전하여 세로에서 가로로 화면이 변경될 경우에도 LiveData는 회전하기 전의 최신 상태를 받아옴

### 4. 생명주기에 대한 추가적인 handling을 하지 않아도 됨
   - LiveData가 관찰하는 동안 Observer의 수명 주기 상태 변경을 인식하기 때문에 자동으로 관리한다

### 5. 자원(Resource)을 공유할 수 있음
   - LiveData를 상속하여 자신만의 LiveData클래스를 구현 가능



# 기본 사용법

### 1. ViewModel 클래스 생성
- 특정 유형의 데이터를 보유할 LiveData의 인스턴스를 생성
```kotlin
class TestLiveDataViewModel : ViewModel(){

 private val _currentName = MutableLiveData<String>() //1.  liveData 초기화 작업

    val currentValue: LiveData<String>
        get() = _currentValue
 }
```

### 2. 메인 액티비에의 onCreate에 Observer를 세팅
```kotlin
class MainActivity : AppCompatActivity() {

    // 전역 변수로 ViewModel lateinit 세팅
    private lateinit var model: TestLiveDataViewModel
 
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
 
        // ViewModel을 가져옵니다.
        model = ViewModelProvider(this).get(TestLiveDataViewModel::class.java)
 
        // Observer를 생성한 뒤 UI에 업데이트 시켜 줍니다.
        val testObserver = Observer<String> { textValue ->
            // 현재 MainActivity에는 TextView가 하나만 존재합니다.
            // 다른 데이터를 받는 UI 컴포넌트가 있다면 같이 세팅 해줍니다.
            tv_livedata_test.text = textValue
        }
 
        // LiveData를 Observer를 이용해 관찰하고
        // 현재 Activity 및 Observer를 LifecycleOwner로 전달합니다.
        model.textValue.observe(this, testObserver)
    }
}
```