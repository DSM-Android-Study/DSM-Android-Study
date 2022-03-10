# JetpackCompose



더 이상의 XML은 필요가 없어질지도..?



-----------------

최근에 안드로이드 프로젝트 생성창에 이상한게 생겼다.. 
![image](https://user-images.githubusercontent.com/90879448/155987542-26cbc23a-0e32-49e9-b4be-629f6d022d9f.png)

ComposeActivity라는..무슨 이상한..그것은 바로!! 제트팩 컴포즈다. 이제 우리 한번 맛보자!



## JetpackCompose..? 그게 뭐에요??



![img](https://lh3.googleusercontent.com/Y1w82YIZ8fFbKStzWpgTgwkz9v0OU9vWWyoXXCjMRzPJWCXW8TxLCAlemoYD4KNhOfh4fcurgP0AwDSampD1a6jAqOuARrl48Dd7_iE9aLfcM0yuRfvCzzhp-4C28hZlfAKdko77xDyh)



원래 코틀린으로 안드로이드 앱 개발할 시에는 XML로 UI를 그립니다. 위에 그림은 XML로 UI를 그릴 시에 나타내는 패턴을 의미합니다. View와 데이터 사이에 중간 계층이 하나 끼어들어야만 하는 형태이다. UI를 표현하기 위해 XML을 사용하고 있음에도 코틀린 역시 UI에 무슨 id 값이 있고, 어떤 UI를 포함하려 하는지를 알고 있어야 하기 때문에 둘은 강하게 의존하게 됩니다. ***\*시간이 많이 흐른 뒤 view를 수정하고 싶어서 xml을 수정하게 되면, 어쩔 수 없이 kotlin 코드도 수정해야 하며, 이때 하나라도 놓치면 런타임 에러가 나게 됩니다.\**** 결국 코틀린 개발자와 코틀린 코드가 어떠한 안전장치도 없이 UI에 개념적으로 의존해야 한다는 의미이기도 합니다.**MVVM과 databinding 조합은 그나마 상황이 낫지만 여전히 xml파일을 사용해야한다는 점이 있습니다**



![img](https://lh5.googleusercontent.com/KFGsn2peLSkStctye-VPH6xnJ3gkZpIFdmv8GjJV16c3_Z1OopdhyDNng2X3Hi5F4g0Td3etBPqKgO1DiZZcwifIGifgugp24KyEYWBHIK9EW2E8cJ-r-kPQ9kTxe8DK3qC-myy7wVYw)



하지만!!! 젵팩컴풔즈는 위 그림은, 선언형 UI toolkit인 Jetpack Compose(only kotlin)로 구현할 경우 나타나는 패턴입니다. 즉, 데이터를 입력받으면 UI를 그려주는 Kotlin - composable 함수를 사용하여 UI를 그리게 되는 상황인 것입니다.



## Fragment가 필요없어지는 시대가 온다.



![img](https://lh5.googleusercontent.com/C9Z5jtVKQYIWuk8wPuXuiwk9AFBwwRI60kuhueluW9nBSMmy-yn_sYQzl_xzdt8_xxKDc3ql3N8QfyFEaMIVkteVxkiMnvDZbY55EGaBmjTlx0AZfNduWvc33dj2ivZQ06iEdm4J5epd)



예시를 들자면 하나의 Activity에 성격이 다른 화면(View)들이 두 개 필요할 때, Fragment를 사용하면 findViewById와 기타 로직이 Activity 하나에 너무 많아지는 걸 방지할 수 있게 되었습니다. 그러나 결국 생명주기를 추종하는 View 일 뿐이기 때문에 UI Toolkit이 Jetpack Compose로 바뀌면 View를 위한 Fragment의 사용성은 사라집니다. findViewById는 역사속으로 사라지고, 기타 로직은 ViewModel에서 수행되기 때문에 위의 그림 처럼 Fragment가 설 자리는 없어집니다.



실제로 리액트나 플러터같은 **선언형 UI** 들을 보면 fragment같은 개념이 없습니다.





\## 선언형, 명령형 UI





![img](https://lh3.googleusercontent.com/t8HX9pXapCM0nD5bsTQaTsw_KzvSdTbZzzC-LJciIi7cYn5pB2cwqc2keGbtcH2J5-f1KdBpgVQT3Celp-eeePjW_MwrNqNTjOuhcWqUcQGJYCmIzkGhyVTwLNuvIYLaDav5RV3ZiW6Z)



명령형 프로그래밍과 선언형 프로그래밍이란 단어를 아시나요? 명령형 프로그래밍은 우리가 평소에 코드를 싸듯이 , 컴퓨터에게 하나하나 명령을 하는 형태로 코딩을 하는 것입니다. 반면에 선언형 프로그래밍은 우리가 원하는바를 선언 또는 표현하듯이 코딩하는 것입니다.

컴퓨터에게 구체적으로 하나하나 명령한 위의 코드를 보면, List에서 짝수만 필터링하는 기능이라는 것을 알 수 있습니다. 우리는 방금 리스트에서 짝수만 필터링하는 그 과정, **즉 필터링을 하기 위해 어떻게(how) 하는지를 본 것**입니다.이같은 프로그래밍 방법이 **명령형 프로그래밍**입니다.



![img](https://lh4.googleusercontent.com/IRjOgxcB86jsrS_dLbg0ktZn9AVk1amTqFh83OdHV6Cuu6J30Qk93IPvtu1vr4vtmER9u8SvFyr2zwbIoDnq1OyT4cZvAWfaFyZKndmb-AfB1QIfBjJ0Y4t4PofBCWw05mbxfc7t8pUe)



위의 코드와 같은 선언형 UI로 짠 필터링 기능인데, 세부 동작 하나하나를 명령한 느낌이 아닙니다. “나는 이 List에서 이것들을 필터링 할거야”라고 선언한 것에 불과합니다. 내부적으로 어떻게(how) 할지는 관심이 없습니다. 단지 “이렇게 표현할래”를 나타낸 것뿐입니다.



![img](https://lh6.googleusercontent.com/foihYozwG7bpHBo9LwZbeMjrFWUyac_9JDgszW7fY-KHoVkJb26Y_4_boZbiEoIyAX_Ze7cOnTmAb5v7T5-00nZIy2RjAbBuZaKIAj4fa5zvYQnKAjqpDqhsp7slJsPOL_fXlfnayRg6)



우리는 컴퓨터가 아니라 사람입니다. 어렸을 때를 떠올려 보면, “엄마, 게임 더 하고싶어요”라고 표현했지, “엄마, 첫번째로 컴퓨터를 조립하고 콘센트를 연결해 전기를 연결해서 무슨 무슨 버튼을 눌러서 컴퓨터를 킬께...”이렇게 명령하지 않았습니다. 우리는 특정 개발자가 컴퓨터에게 명령한 로직들을 보고 그게 무슨 기능인지 이해하는 것보단, 개발자가 원했던 기능의 선언을 보고 이해하는 게 훨씬 익숙합니다. 그게 바로 선언형 UI가 대세가 된 이유라고 생각합니다.

참고 및 출처 및 변화 : https://wooooooak.github.io/jetpack%20compose/2021/05/18/%EC%BB%B4%ED%8F%AC%EC%A6%88%EA%B0%80%ED%95%84%EC%9A%94%ED%95%9C%EC%9D%B4%EC%9C%A0/

## 마치며..



![img](https://lh6.googleusercontent.com/SZDohfkidZenRJUUA69E92Kn48Q3nUo4VOzxaBLQZUmmFkf8wxStsfB1ZtiuGB9U6lxxKfvRArKZa4yUkNsan64sHAFDxTBFDFIHj-lwTp45BCER8wD73osZOw0Arnxkw3_uLs0VUHPB)



Android는 다른 플랫폼에 비해 선언형 UI ToolKit 도입이 많이 늦은 편입니다. 늦은 만큼 좋은 UI toolkit(React.js, Flutter 등등)을 많이 참고했다고 하니 충분히 기대해봐도 좋을 것 같고, github에 이미 꾀나 훌륭하게 만들어진 샘플들도 많으니 참고하셔서 학습하시면 많은 도움이 될 것 같습니다.





