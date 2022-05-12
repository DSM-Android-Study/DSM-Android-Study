# 코드 분석 도구로 코드 퀄리티 향상시키기



우린 개발자입니다. 그렇기에 끊임없이 수많은 코드들을 작성합니다.



![image-20220512200348204](/Users/limsaehyun/Library/Application Support/typora-user-images/image-20220512200348204.png)



하지만 만약 작성한 코드들이 스타일 가이드에 위배되는 더러운 상태로 쌓이다 보면, 프로젝트의 규모가 커지거나 추후에 리펙토링이 필요할 때 더러운 코드들로 인해 차칠이 생길 것 입니다.



이러한 상황을 해결하기 위해 여러가지의 코드 정적 분석기가 존재합니다. 이를 해결하고자 코틀린 환경에서 가장 흔히 쓰이는 **ktlint**를 사용하는 방법에 대해 알아보겠습니다.



![GitHub - pinterest/ktlint: An anti-bikeshedding Kotlin linter with built-in  formatter](https://opengraph.githubassets.com/7c07aab40fe2ec328af2943de866cbf681154f475b6936a0fb1e294afeabe555/pinterest/ktlint)



## KtLint - Kotlin Linter



ktlint는 코틀린을 위한 정적 분석 도구로, 코틀린으로 작성한 코드의 스타일의 검사와, 형식에 맞지 않는 부분을 수정하는 기능을 제공합니다.



ktlin는 코드 스타일을 검사한다는 측면에서 우리가 흔히 사용하는 *checkstyle*과 유사하지만, *사용자가 직접 코드 스타일*을 정의하지 않고 하나의 규칙을 일괄적으로 적용합니다.



적용되는 규칙은 코틀린 공식 가이드 문서에 따른 *Coding conventions*을 따르며 이에 적용되어 있는 규칙은 다음과 같습니다.

```
1. 인덴트로 공백(Space) 4개 사용
2. 세이콜론(;) 사용 금지
3. 와일드카드(import.foo.*)를 사용한 import 및 사용하지 않는 import만 금지
4. 연속된 빈 줄 금지
5. 문장의 끝 부분에 빈 공간 금지
6. 키워드, 콤마, 콜론, 중괄호 등 ㅏㅅ이에 공백
```



적용을 다 마치고 나면 이런식으로 아래 명령어를 통해서 ktlint를 사용할 수 있습니다.

```
./gradlew ktlint
./gradlew ktlintFormat
```



성공적으로 분석이 완료된 것을 볼 수 있습니다.

![img](https://k.kakaocdn.net/dn/bcmHkR/btrtXstyhax/VE8khbyVh2QCF8lqDz0SO0/img.png)



**번외) android ci에 적용한 ktlint**

