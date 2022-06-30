# ListAdapter

### notifyDataSetChanged()

- recyclerView의 리스트를 업데이트할 때 가장 많이 사용되는 메서드이다
- recyclerVIew리스트의 데이터가 바뀐것을 알려주고 새롭게 다시 보여주도록 한다
- 바뀐 부분만이 아니라 전체를 다시 로딩 한다는 뜻

### AsynListDiffer 사용하기

- AsncListDifer 클래스를 통해 DiffUtil을 훨씬 편하게 사용 가능
- DiffUtil : 리스트를 싹 지우고 다시 처음부터 끝까지 객체를 하나하나 만들어 새로 렌더링하는 과정을 거치며 비용이 크게 발생하는 notifyDataSetChanged()를 통해 갱신하면 성능에 치명적인 악영향을 미치게 된다. DiffUtil클래스는 이와 다르게 이전 데이터 상태와 현재 데이터간의 상태 차이를 계산하고, 반드시 업데이트해야 할 최소한의 데이터에 대해서만 갱신하게 된다. 즉 데이터 업데이트 횟수를 최소한으로 가져갈 수 있다.

**사용한 DiffUtil 메서드**

- areItemsTheSame(매개변수, 매개변수) : 두 아이템이 같은 객체인지 여부를 반환

- areContentsTheSame(매개변수, 매개변수) : 두 아이템이 같은 데이터를 갖고 있는지 여부를 반환. areItemsTheSame()이 true를 반환할 때만 호출된다. 애초에 객체가 다르다면 데이터를 비교하는 것은 의미가 없다

  ```kotlin
  class MyDiffCallback : DiffUtil.ItemCallback<SearchFriendResponse>(){
      override fun areItemsTheSame(oldItem: SearchFriendResponse, newItem: SearchFriendResponse): Boolean {
          return oldItem.hashCode() == newItem.hashCode()
      }
  
      override fun areContentsTheSame(oldItem: SearchFriendResponse, newItem: SearchFriendResponse): Boolean {
          return oldItem == newItem
      }
  }
  ```



### currentList

- 데이터를 참조하여 현재 데이터를 불러올때 사용된다

  ```kotlin
  @NonNull
      public List<T> getCurrentList() {
          return mDiffer.getCurrentList();
      }
  ```

  

### submitList

- 리스트 데이터를 갱신할 때 사용된다.

  ```kotlin
  public void submitList(@Nullable List<T> list) {
          mDiffer.submitList(list);
      }
  ```

  

### AsyncListDiffer 사용

```kotlin
public abstract class ListAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
    final AsyncListDiffer<T> mDiffer;
    private final AsyncListDiffer.ListListener<T> mListener =
            new AsyncListDiffer.ListListener<T>() {
        @Override
        public void onCurrentListChanged(
                @NonNull List<T> previousList, @NonNull List<T> currentList) {
            ListAdapter.this.onCurrentListChanged(previousList, currentList);
        }
    };
```



### MutableList

- List는 읽기 전용이고 MutableList는 읽기/쓰기가 가능하다
- init, add, addAll, clear, iterator, listlterator, remove, removeAll, removeAt, retainAll, set, subList 메서드 사용 가능
- ArrayList와 MutableList는 차이가 거의 없고 클래스명의 차이가 있다. ArrayList는 MutableList 중에서도 ArrayList를 사용하겠다 라고 명시한 것이다. 현재 Kotlin에서는 mutableListst나 둘 다 ArrayList를 반환한다고도 하니 기능에 차이는 크게 없는 것으로 보인다. 나중에 ArrayList말고도 또다른 Mutable한 List가 생길 수 있으니 이 때 형변환의 자유로움을 위해서 상위 클래스로 만들어 놓았다고도 추측해 볼 수 있다.



### 참조

- https://zladnrms.tistory.com/140
- https://hwan-shell.tistory.com/247
- https://cliearl.github.io/posts/android/recyclerview-listadapter/
- https://velog.io/@haero_kim/Android-DiffUtil-사용법-알아보기
- https://s2choco.tistory.com/33
- [안디백](https://github.com/kimjjunho/Andibag)
- [ListAdapterPractice](https://github.com/kimjjunho/Kotlin_Study/tree/main/function/ListAdapterPractice)