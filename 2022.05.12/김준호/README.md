# 코드 단축 문법 

## 재귀 함수

**재귀 함수란?**

- 함수가 직접 또는 간접적으로 자신을 호출하는 프로세스를 재귀함수라고 합니다

```kotlin
fun main(){
    helloWorld(5)
}
fun helloWorld(n : Int){
  	//n이 0이되면 return으로 종료
    if(n == 0){
        return
    }
    println("HelloWorld!")
    helloWorld(n-1) //재귀함수 시작
}

//출력
/*
HelloWorld!
HelloWorld!
HelloWorld!
HelloWorld!
HelloWorld!
*/
```

- 배열에서 최대값을 찾기

```kotlin
fun main(){
    val arr = intArrayOf(0, 80, 60, 40, 20, 100)
    println(arraySort(arr, arr.size))
}
fun arraySort(arr:IntArray,n:Int) : Int{
    val x : Int
    if(n == 1)
        return arr[0]
    else
        x = arraySort(arr,n-1)
    if(x>arr[n-1])
        return x
    else
        return arr[n-1]
}

//출력
//100
```

- 원리 테스트

```kotlin
fun main(){
    val arr = intArrayOf(0, 80, 60, 40, 100, 20)
    println(arraySort(arr, arr.size))
}
fun arraySort(arr:IntArray,n:Int) : Int{
    val x : Int
    if(n == 1){
        println("n1 "+arr[0])
        return arr[0]
    }
    else{
        println("n2 "+arr[n-1])
        x = arraySort(arr,n-1)
    }
    if(x>arr[n-1]){
        println("n3 $x")
        return x
    }
    else{
        println("n4 "+arr[n-1])
        return arr[n-1]
    }
}

//출력
/*
n2 20
n2 100
n2 40
n2 60
n2 80
n1 0
n4 80
n3 80
n3 80
n4 100
n3 100
100
*/
```

**재귀 함수를 기준으로 앞, 뒤 구분하여서 본 코드 실행 순서**

- arraySort(6) -앞
  - arraySort(5) -앞
    - arraySort(4) -앞
      - arraySort(3) -앞
        - arraySort(2) -앞
          - arraySort(1) -앞 (return으로 인해 앞부문만 실행)
        - arraySort(2) -뒤
      - arraySort(3) -뒤
    - arraySort(4) -뒤
  - arraySort(5) -뒤
- arraySort(6) -뒤

**예제 : 백준 2193번**

- 문제

  0과 1로만 이루어진 수를 이진수라 한다. 이러한 이진수 중 특별한 성질을 갖는 것들이 있는데, 이들을 이친수(pinary number)라 한다. 이친수는 다음의 성질을 만족한다.

  1. 이친수는 0으로 시작하지 않는다.
  2. 이친수에서는 1이 두 번 연속으로 나타나지 않는다. 즉, 11을 부분 문자열로 갖지 않는다.

  예를 들면 1, 10, 100, 101, 1000, 1001 등이 이친수가 된다. 하지만 0010101이나 101101은 각각 1, 2번 규칙에 위배되므로 이친수가 아니다.

  N(1 ≤ N ≤ 90)이 주어졌을 때, N자리 이친수의 개수를 구하는 프로그램을 작성하시오.

- 내가 작성해 본 코드

  ```kotlin
  fun main(args: Array<String>){
      val getNum = readLine()!!.toInt()
      var useNum = 1
      var count = 0
      var check = true
      var j = 0
  
      var arr = ArrayList<Int>()
  
      useNum = (2.0).pow(getNum-1).toInt()
      val vsNum = (2.0).pow(getNum).toInt()
  
      while (useNum < vsNum){
          val countNum = Integer.toBinaryString(useNum)
          val intCountNum = countNum.toInt()
          for(i in countNum.indices){
              var initNum = intCountNum%(10.0).pow(i+1).toInt()
              initNum /= (10.0).pow(i).toInt()
              arr.add(initNum)
          }
          for (i in countNum.indices){
              if(i+j>0){
                  if(arr[i+j] != 0 && arr[i+j-1] == arr[i+j] && (i+j)%getNum!=0){
                      check = false
                  }
              }
          }
          j+=getNum
          if(check){
              count++
          }
          useNum++
          check = true
      }
      println(count)
  }
  ```

- 재귀함수를 이용한 코드

  ```kotlin
  fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
      val n = readLine().toInt()
      val check = LongArray(n + 1)
  
      println(dp(n, check))
  }
  
  fun dp(n: Int, check: LongArray): Long {
      return when {
          n == 1 -> 1
          n == 2 -> 1
          check[n] != 0L -> check[n]
          else -> {
              check[n] = dp(n - 1, check) + dp(n - 2, check)
              check[n]
          }
      }
  }
  ```



---



## return when



- 잘못된 예시

  ```kotlin
   fun main(){
      println(dp(1))
  }
  fun dp(a : Int) : String{
      return when{
          a == 1 -> "a는 1이다"
      }
  }
  //예외 처리(else)가 되지 않았다
  ```

- 수정

  ```kotlin
  fun dp(a : Int) : String{
      return when{
          a == 1 -> "a는 1이다"
          else -> "a는 1이 아니다"
      }
  }
  ```

- 응용(위에 나온 재귀함수 사용)

  ```kotlin
  un main(args:Array<String>){
      val a = readLine()!!.toInt()
      print(dp(a))
  }
  fun dp(a : Int) : String{
      return when{
          a == 1 -> "a는 1이다"
          else -> {
              if(a>1){
                  dp(a-1)
              }else{
                  dp(a+1)
              }
          }
      }
  }
  //무슨 수를 적는 출력이 일치
  //a는 1이다
  ```



---



## ListMapping

- 정의 : 다른 컬렉션의 아이템에 적용한 함수식의 결과로 새로운 컬렉션을 만듭니다.
- 함수식을 통해 list안에 값을 수정할 수 있다

```kotlin
fun main(){
    println(mapping(intArrayOf(1,2,3)))
}
fun mapping(arr : IntArray) = arr.map { it*3 }

//[3, 6, 9]
//메서드 뒤 '='은 return과 같은 의미이다

fun main(){
  val arr = arrayListOf(1,2,3)
  //arr을 var로 바꾼 뒤 값을 완전히 바꾸는 것도 가능하다
  //arr = mapping(arr) as ArrayList<Int> 
    println(mapping(arr))
}
fun mapping(arr : ArrayList<Int>) = arr.map { it*3 }
//[3, 6,  9]

//IntArray형태와 ArrayList<Int>형태 모두 사용 가능
```

- mapIndexed를 사용하여 순서값과 아이템값 사용하기

```kotlin
fun main(){
    val arr = arrayListOf(1,2,3)
    println(arr.mapIndexed{idx,value -> idx*value})
}

//[0, 2, 6]

//idx에 순서값 0,1,2이 전달됨
//value에 아이템값 1,2,3이 전달됨
```

- mapNotNull을 이용한 null값 제거

```kotlin
fun main(){
    val arr = arrayListOf(1,null,2,3)
    println(arr)
}
//[1, null, 2, 3]

fun main(){
    val arr = arrayListOf(1,null,2,3)
    println(arr.mapNotNull{it})
}
//[1, 2, 3]
```

- 그룹화

```kotlin
fun main(args: Array<String>) {
    val arr = arrayListOf(1,2,3,4,5)
    val groupArr = arr.groupBy { if(it%2 == 0) "even" else "old"}
    println(groupArr)
}

//{old=[1, 3, 5], even=[2, 4]}
```



**예제 : 프로그래머스 42748번**

배열 array의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수를 구하기

- 내가 작성한 코드

  ```kotlin
  fun main(){
      val array = intArrayOf(1, 5, 2, 6, 3, 7, 4)
      val commands = arrayOf(intArrayOf(2,5,3), intArrayOf(4,4,1), intArrayOf(1,7,3))
  
      val array1 = solution(array,commands)
      for(element in array1){
          println(element)
      }
  }
  fun solution(array: IntArray,commands:Array<IntArray>):IntArray{
      val answer = IntArray(commands.size)
  
      for(i in commands.indices){
          val getArrayList = ArrayList<Int>()
          for(j in commands[i][0]-1 until commands[i][1]){
              getArrayList.add(array[j])
          }
          getArrayList.sort()
          answer[i] = getArrayList[commands[i][2]-1]
      }
  
      return answer
  }
  
  /*
  5
  6
  3
  */
  ```

- 짧게 작성된 코드

  ```kotlin
  fun main(){
      val array = intArrayOf(1, 5, 2, 6, 3, 7, 4)
      val commands = arrayOf(intArrayOf(2,5,3), intArrayOf(4,4,1), intArrayOf(1,7,3))
  
      val array1 = solution(array,commands)
      for(element in array1){
          println(element)
      }
  }
  
  //it(commands)가 기존 it의 일정 범위 안에 수만 오름차순으로 정렬되고 정렬된 리스트에 [it[2]-1]인덱스에 있는 수를 불러온다
  fun solution(array: IntArray, commands: Array<IntArray>) = commands.map {
          it ->array.slice(IntRange(it[0] - 1, it[1] - 1)).sorted()[it[2] - 1]}
  
  /*
  5
  6
  3
  */
  
  ```
  
- slice, IntRange

  - 특정 데이터추출

    ```kotlin
    fun main(args: Array<String>) {
        var arr = arrayListOf(1,2,3,4,5)
    q
        println(arr.slice(1..3))
        println(arr.slice(IntRange(1,3))) //범위를 나타내는 IntRange를 사용해서도 똑같이 구현 가능
    
    }
    
    //[2, 3, 4]
    ```

- sorted()

  - 오름차순 정렬

  

---



## 참고

- https://crazykim2.tistory.com/591
- https://flow9.net/bbs/board.php?bo_table=kotlin&wr_id=39