# JetPackCompose 사용기 2

 ### 테마

- **Theme**

```kotlin
var darkTheme = false

//다크 테마 색 지정
private val DarkColorPalette = darkColors(
    primary = Black100,
    background = White

)

//일반 테마 색 지정
private val LightColorPalette = lightColors(
    primary = Mint
)

@Composable
fun DMSComposeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val systemUiController = rememberSystemUiController()

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

  //핸드폰 위 StatusBar 색상 적용
    if(darkTheme){
        systemUiController.setSystemBarsColor(
            color = Black100
        )
    }else{
        systemUiController.setSystemBarsColor(
            color = Mint
        )
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

//테마에서 지정가능한 색 목록
/*
		primary: Color 
    primaryVariant: Color
    secondary: Color 
    secondaryVariant: Color 
    background: Color 
    surface: Color 
    error: Color 
    onPrimary: Color 
    onSecondary: Color 
    onBackground: Color 
    onSurface: Color 
    onError: Color
*/	
```

- **MainActivity**

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
          //darkTeme = true이면 다크모드 적용
            DMSComposeTheme (darkTheme = false){
                MainScreenView()
            }
        }
    }
}
```

ex)

```kotlin
@Composable
fun CenterMintBar(text:String){
    Box(
        modifier = Modifier
            .height(30.dp)
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colors.primary, //배경에 현재 테마primary색 적용
                shape = RoundedCornerShape(15.dp)
            )
    ) {
        Text(
            text = text,
            color = Color.White,
            fontFamily = font,
            fontWeight = FontWeight.SemiBold,
            fontSize = 11.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .fillMaxHeight()
                .wrapContentHeight(Alignment.CenterVertically)
        )
    }
}
```

- darkTheme = false일 때, MaterialTheme.primary의 색은 Mint색
- darkTheme = true일 때, MaterialTheme.primary의 색은 Black100



---



### 프로그래스바 애니메이션

- ViewModel (delay를 걸어주기 위해 사용함)

```kotlin
class MainViewModel : ViewModel(){
    val loading = mutableStateOf(true)

    fun progressLoading(){
        viewModelScope.launch {
            delay(10000) //프로그래스바 실행시간 지정
            loading.value = false
            Log.d(TAG, "linearProgressLoading: "+loading.value)
        }
    }
}
```

**가로 프로그래스바**

- MainActivity

```kotlin
 @Composable
    fun LinearProgress(
        viewModel: MainViewModel = viewModel()
    ){
        val loading = viewModel.loading.value
        Column(modifier = Modifier.fillMaxWidth()) {
            viewModel.progressLoading() //delay 
            LinearProgressBar(loading) //실행, delay후 종료
        }
    }
```

- LoadingProgressBar

```kotlin
@Composable
fun LinearProgressBar(
    isBoolean: Boolean
){
    if(isBoolean){
      //LinearProgressIndicator는 컴포즈에서 제공
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp),
            backgroundColor = Color.LightGray,
            color = Color.Red
        )
    }
}
```

**원형 로딩 프로그래스바**

- MainActivity

```kotlin
@Composable
    fun CircleLoadingProgress(
        viewModel: MainViewModel = viewModel()
    ){
        val loading = viewModel.loading.value
        viewModel.progressLoading()
        CircleLoadingProgressBar(isDisplayed = loading)
    }
```

- LoadingProgressBar

```kotlin
@Composable
fun CircleLoadingProgressBar(
    isDisplayed: Boolean
){
    if(isDisplayed) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(),
            horizontalArrangement = Arrangement.Center
        ) {
          //CircularProgressIndicator는 컴포즈에서 제공
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,

            )
        }
    }
}
```

**원 3개로 된 프로그래스바**

- MainActivity

```kotlin
@Composable
    fun LinearLoadingProgress(
        viewModel: MainViewModel = viewModel()
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val loading = viewModel.loading.value
            viewModel.progressLoading()
            LinearLoadingProgressBar(isDisplayed = loading)
        }
    }
```

- LoadingProgressBar

```kotlin
@Composable
fun LinearLoadingProgressBar(
    isDisplayed: Boolean,
    modifier: Modifier = Modifier,
    circleSize: Dp = 25.dp,
    circleColor: Color = MaterialTheme.colors.primary,
    spaceBetween: Dp = 10.dp,
    travelDistance: Dp = 20.dp
){
    if(isDisplayed){
        val circles = listOf(
            remember { Animatable(initialValue = 0f) },
            remember { Animatable(initialValue = 0f) },
            remember { Animatable(initialValue = 0f) }
        )

        circles.forEachIndexed { index, animatable ->
            LaunchedEffect(key1 = animatable){
                delay(index * 100L)
                animatable.animateTo(
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = keyframes {
                            durationMillis = 1200
                            0.0f at 0 with LinearOutSlowInEasing
                            1.0f at 300 with LinearOutSlowInEasing
                            0.0f at 600 with LinearOutSlowInEasing
                            0.0f at 1200 with LinearOutSlowInEasing
                        },
                        repeatMode = RepeatMode.Restart
                    )
                )
            }
        }

        val circleValues = circles.map { it.value }
        val distance = with(LocalDensity.current){travelDistance.toPx()}
        val lastCircle = circleValues.size - 1

        Row(modifier = modifier) {
            circleValues.forEachIndexed { index, value ->
                Box(
                    modifier = Modifier
                        .size(circleSize)
                        .graphicsLayer {
                            translationY = -value * distance
                        }
                        .background(
                            color = circleColor,
                            shape = CircleShape
                        )
                )
                if(index != lastCircle){
                    Spacer(modifier = Modifier.width(spaceBetween))
                }
            }
        }
    }
}
```



---



### LazyColumn

- 화면에 보여지는 컴포저블만을 표시하기 때문에 아이템이 많이 표시되는 경우 필수적으로 사용되어야 하는 레이아웃
- xml의 리사이클러뷰를 대체한다고 생각하면 된다.

**ItemView**

```kotlin
@Composable
fun ItemCard(num:Int){
    Card(
        Modifier
            .padding(12.dp)
            .border(width = 4.dp, color = Color.Black) //테두리
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = "Android Study$num")
        }
    }
}
```

- LazyColumn에 사용될 아이템
- 배경색은 Material의 surface 색을 기본적으로 적용시킴

**Main**

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme (darkTheme = true){
            		//여기 입력
            }
        }
    }
```

- 주석 부분에 아래 함수들을 넣고 테스트 했음

**Item**

- LazyColumn 내부에 컴포저블을 직접 넣고 싶을때 사용한다

- <u>LazyColumnStyle1, LazyColumnStyle2</u>

  ```kotlin
   @Composable
      fun LazyColumnStyle1(){
          LazyColumn(){
              item {
                  ItemCard(num = 0)
                  ItemCard(num = 1)
              }
          }
      }
  @Composable
      fun LazyColumnStyle2(){
          LazyColumn(){
              item {
                  ItemCard(num = 2)
                  ItemCard(num = 3)
              }
          }
      }
  ```

  - 두개의 카드가 하나의 아이템으로 취급 된다
  - 주석 부분에 위 두개의 함수를 넣게 되면 더 아래에 있는 함수의 결과만 보인다

![스크린샷 2022-06-09 오전 9.07.48 Medium](/Users/kimjunho/Desktop/발표 자료/안드로이드 스터디/스크린샷 2022-06-09 오전 9.07.48 Medium.jpeg)

**Items**

- 컴포저블을 반복해서 나타내고 싶을때 사용한다

- <u>LazyColumnStyle3</u>

  ```kotlin
  @Composable
      fun LazyColumnStyle3(){
          LazyColumn(){
              items(20){ //(0~19)
                  ItemCard(num = it)
              }
          }
      }
  ```

  - index값을 it을 통해 내보내는데, 이를 이용해서 몇 번째 아이템인지 알 수 있다.

  - LazyColumn이 화면에 보이는 컴포저블만을 로딩하기 때문에 지연 시간이 전혀 없다고 한다.

    ![스크린샷 2022-06-09 오전 9.11.52 Medium](/Users/kimjunho/Desktop/발표 자료/안드로이드 스터디/스크린샷 2022-06-09 오전 9.11.52 Medium.jpeg)

**ItemsIndexed**

- 우리가 실제 개발할 때 가장 많이 쓰일 방식이다
- 아이템으로 커스텀한 클래스(객체)의 설정이 가능하다

- <u>LazyColumnStyle4</u>

  ```kotlin
  @Composable
      fun LazyColumnStyle4(){
          LazyColumn(){
              itemsIndexed(
                  listOf(100,200,300)
              ){index, item ->  
                  ItemCard(num = item)
              }
          }
      }
  ```

  - ItemsIndexed의 인자로 listof(100,200,300)을 넘김으로써 아이템 3개를 생성한다.

  ![스크린샷 2022-06-09 오전 9.24.53 Small](/Users/kimjunho/Desktop/발표 자료/안드로이드 스터디/스크린샷 2022-06-09 오전 9.24.53 Small.jpeg)

**클릭 기능**

- 추가로 클릭기능을 쉽게 구현할 수 있다

  ```kotlin
  
  @Composable
  fun ItemCard(num:Int, context: Context){
      Card(
          Modifier
              .padding(12.dp)
              .border(width = 4.dp, color = Color.Black)
              .fillMaxWidth()
              .height(100.dp)
              .clickable(onClick = { toast(num, context) }) //이 부분 추가
      ) {
          Box(contentAlignment = Alignment.Center) {
              Text(text = "Android Study$num")
          }
      }
  }
  
  fun toast(num:Int, context: Context){
      Toast.makeText(context,num.toString(),Toast.LENGTH_SHORT).show()
  }
  ```

  - ItemCard를 클릭하면 실행하면서 받아온 인자값 num을 toast라는 메서드를 통해 toast메시지로 출력 시켜준다



### 참고

- https://kotlinworld.com/210#recentEntries
