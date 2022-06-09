# 커스텀 뷰 만들기

### 커스텀 뷰를 만드는 이유

개발하는 애플리케이션의 요구 조건에 따라 화면 구성 요소에 요구하는 바도 조금씩의 차이가 발생한다. 안드로이드 프레임워크에서 제공하는 API를 사용하여 화면이 구성된다면 간단하고 좋겠지만, 현실은 그러지 못하다. 그렇기 때문에 커스텀 뷰가 필요하다. 화면을 구성할 적절한 뷰가 없을 때 개발자는 커스텀 뷰를 만들어 사용할 수 있다.

## 커스텀 속성 만들기

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <declare-styleable name="CustomView">
        <attr name="imgSymbol" format="reference|integer" />
        <attr name="bgColor" format="reference|integer" />
        <attr name="text" format="reference|string" />
        <attr name="textColor" format="reference|integer" />
    </declare-styleable>
</resources>
```



- declare-styleable 태그는 하위 속성들을 관리하는 단위를 의미한다.
  - `attr` 태그는 정의하는 커스텀 속성을 의미한다.
  - `name` 속성은 커스텀 속성의 이름을 나타낸다.
  - `format` 속성은 커스텀 속성 값의 타입을 나타낸다.

# 커스텀 layout 만들기

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/layout"
    android:layout_width="match_parent"
    android:layout_height="60dp"
    android:orientation="horizontal">

    <TextView
        android:id="@+id/tvName"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintLeft_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        android:layout_marginLeft="5dp"
        android:text="ababab"
        android:textSize="20sp" />
    
</androidx.constraintlayout.widget.ConstraintLayout>
```

```kotlin
class CustomView: ConstraintLayout {
    //커스텀 뷰 안에 들어가는 아이템들
    lateinit var layout : ConstraintLayout
    lateinit var tvName : TextView

    constructor(context: Context?) : super(context!!){
        init(context)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs){
        init(context)
        getAttrs(attrs)

    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr){
        init(context)
        getAttrs(attrs,defStyleAttr)
    }

    //아까 만들어둔 커스텀뷰 레이아웃을 inflate 해서 적용해준다.
    //그리고 layout에서 id를 참조해 위에 lateinit 으로 냅뒀던 변수들을 초기화 해준다.
    private fun init(context:Context?){
        val view = LayoutInflater.from(context).inflate(R.layout.customview,this,false)
        addView(view)

        layout = findViewById(R.id.layout)
        tvName = findViewById(R.id.tvName)
    }

    private fun getAttrs(attrs:AttributeSet?){
        //아까 만들어뒀던 속성 attrs 를 참조함
        val typedArray = context.obtainStyledAttributes(attrs,R.styleable.CustomView)
        setTypeArray(typedArray)
    }

    private fun getAttrs(attrs:AttributeSet?, defStyle:Int){
        val typedArray = context.obtainStyledAttributes(attrs,R.styleable.CustomView,defStyle,0)
        setTypeArray(typedArray)
    }

    //디폴트 설정
    private fun setTypeArray(typedArray : TypedArray){

        //레이아웃의 배경, attrs.xml 속성중 bgColor 를 참조함
        val bgResId = typedArray.getResourceId(R.styleable.CustomView_bgColor,R.drawable.ic_launcher_background)
        layout.setBackgroundResource(bgResId)

        //텍스트 색, attrs.xml 속성중 textColor 를 참조함
        val textColor = typedArray.getColor(R.styleable.CustomView_textColor,0)
        tvName.setTextColor(textColor)

        //텍스트 내용, LoginButton 이름으로 만든 attrs.xml 속성중 text 를 참조함
        val text = typedArray.getText(R.styleable.CustomView_text)
        tvName.text = text

        typedArray.recycle()
    }
}
```

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <com.example.customview.CustomView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:bgColor = "@color/black"
        app:text="텍스트"
        app:textColor="@color/teal_200"
        />

    <com.example.customview.CustomView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:bgColor = "@color/white"
        app:text="텍스트2222"
        app:textColor="@color/purple_500"
        />

    <com.example.customview.CustomView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:bgColor="@color/purple_200"
        app:text="텍스트33"
        app:textColor="@color/white"
        />

</LinearLayout>
```
