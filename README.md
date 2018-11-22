---


---

<h1 id="sqlite_example">SQlite_Example</h1>
<p>A simple example about SQlite for Android</p>
<h2 id="what-is-sqlite">What is SQlite?</h2>
<ul>
<li>
<p>SQLite is a database that can be implemented within an Android device.</p>
</li>
<li>
<p>It is basically a two-dimensional table and is used like a table.</p>
</li>
<li>
<p>It is stored in .db file units, so it is easy to back up and restore data to the Internet later.</p>
</li>
<li>
<p>SQlite는 안드로이드 디바이스 내에서 구현할 수 있는 데이터베이스입니다.</p>
</li>
<li>
<p>기본적으로 2차원 테이블로 구성되어 있으며 표처럼 사용하는 방식입니다.</p>
</li>
<li>
<p>SQlite는 .db 파일단위로 저장되어 있으므로 추후에 인터넷에 데이터를 백업했다가 다시 복구하기 용이합니다.</p>
</li>
</ul>
<h2 id="why-do-i-create-this-example-file">Why do I create this example file?</h2>
<ul>
<li>
<p>SQlite is a very good database construction method that is very convenient and has a low probability of error.</p>
</li>
<li>
<p>But creating this requires a lot of process and is actually very complicated.</p>
</li>
<li>
<p>SQliteExample is a practical and powerful example with three units of data.</p>
</li>
<li>
<p>SQlite는 매우 편하고 낮은 오류 확률을 가진 좋은 데이터 베이스 구축방법 입니다.</p>
</li>
<li>
<p>그러나 이것을 만들기 위해서는 많은 과정이 필요하고 실제로 매우 복잡합니다.</p>
</li>
<li>
<p>SQliteExample은 3개의 데이터단위로 되어있는 실용적이고 강력한 예제입니다.</p>
</li>
</ul>
<h2 id="code">Code</h2>
<h3 id="information.java">Information.java</h3>
<ul>
<li>This class is created to send data to and from parcel.</li>
<li>I created three data forms (content1, content2, content3).</li>
<li>parcel로 데이터를 주고받기위해 만든 클래스입니다.</li>
<li>3개의 데이터 양식을 만들었습니다.(content1, content2, content3)</li>
</ul>
<h3 id="dbopenhelper.java">DBOpenHelper.java</h3>
<ul>
<li>It is a class that runs DB to open the DB to insert the data received through the information into the .db file (CustomDATABASE (SQLITE) .db).</li>
<li>Write data through InsertColumn and update the database in real time via UpdateColumn.</li>
<li>Information.java을 통해 받은 양식에 맞는 데이터를 .db파일(CustomDATABASE(SQLITE).db)에 넣기 위해 DB를 열어주는 역할을 하는 클래스입니다.</li>
<li>InsertColumn을 통해 데이터를 쓰고 UpdateColumn을 통해 데이터베이스를 실시간으로 업데이트해줍니다.</li>
</ul>
<h3 id="databasefile.java">DataBaseFile.java</h3>
<ul>
<li>A class that demonstrates how to write data to a real db from a form.</li>
<li>양식을 통해 받은 데이터를 실제 db에 어떻게 쓸지 보여주는 클래스입니다.</li>
</ul>
<h3 id="mainactivity">MainActivity</h3>
<p><img src="https://user-images.githubusercontent.com/44791701/48912876-23fe5b80-eeba-11e8-81b6-f996db69d7e1.jpg" width="216" height="405"></center></p>
<ul>
<li>
<p>It consists of two buttons, each with a list of tasks to write a new database and to check the database you wrote.</p>
</li>
<li>
<p>‘Information s1’ section provides an ‘empty container’ of what data to actually contain.</p>
<pre><code>     Button newfile = (Button) findViewById(R.id.NewBtn);  
 		newfile.setOnClickListener(new View.OnClickListener() {  
 	    @Override  
 		  public void onClick(View view) {  
 	        Intent intent = new Intent(getApplicationContext(),New_File .class);  
 	        Information s1 = new Information("","","");  
 	        intent.putExtra("inputdata",s1); // 내용을 담기위한 그릇, 세가지 데이터 그릇을 담아서 간다.  
 			  startActivity(intent);  
 	    }  
 	});
</code></pre>
</li>
<li>
<p>두가지 버튼으로 구성되며, 각각 새로운 데이터베이스를 작성하는 버튼,  저장된 데이터베이스를 확인하기 위한 리스트 버튼으로 이루어져 있습니다.</p>
</li>
<li>
<p>Information s1 부분은 실제로 어떤 데이터를 담을 것인지에 관한 '빈 그릇’을 제공합니다.</p>
</li>
</ul>
<h2 id="new_file">New_File</h2>
<p><img src="https://user-images.githubusercontent.com/44791701/48912589-62474b00-eeb9-11e8-9f75-aad248b9f4b1.jpg" alt="3" width="216" height="405"></center>></p>
<ul>
<li>
<p>It is actually a class to write. There are two buttons to save and close. The rest has three EditText sections to hold three pieces of data.</p>
</li>
<li>
<p>The most important function saveToList is to save the data as an object and send it to Saved_List.java.</p>
</li>
<li>
<p>It is executed when SAVE button is pressed, and the activity is turned off and the list menu is called.</p>
<pre><code>  public void saveToList(){  

      //객체로 저장 후 객체를 Saved_List로 전달  
  	  Information inputed = getIntent().getParcelableExtra("inputdata");  
      inputed.information(mycontent1.getText().toString(),mycontent2.getText().toString(),mycontent3.getText().toString());  

  	  mDbOpenHelper.insertColumn(inputed.getContent1(),inputed.getContent2(),inputed.getContent3());  

      Intent intent2 = new Intent(this, Saved_List.class);  
      startActivity(intent2);  

      //액티비티가 종료 될 때 디비를 닫아준다  
  	  finish();  

  }
</code></pre>
</li>
<li>
<p>실제로 글을 쓰는 파일입니다. 저장과 닫기를 수행하는 두개의 버튼이 있으며 나머지는 3개의 데이터를 담기 위한 3개의 EditText부가 있습니다.</p>
</li>
<li>
<p>saveToList가 가장 중요한 함수인데, 객체로 데이터를 저장 후 이것을 Saved_List.java로 보내는 역할을 합니다.</p>
</li>
<li>
<p>SAVE 버튼을 누를 시 실행되며, 이때 해당 액티비티는 꺼지고 리스트 메뉴가 호출되게 됩니다.</p>
</li>
</ul>
<h3 id="saved_list">Saved_list</h3>
<p><img src="https://user-images.githubusercontent.com/44791701/48912612-71c69400-eeb9-11e8-9cf2-d5d316ce7180.jpg" alt="4" width="216" height="405"></center></p>
<ul>
<li>
<p>Allows the saved data to be output in a list format.</p>
</li>
<li>
<p>A typical list view provides a somewhat “bored” view without photos, so I created a custom list view. (list_item.xml)</p>
</li>
<li>
<p>MyAdapter1 was created for a custom list view and inserts the following code to associate it with the list_item.xml file.</p>
<pre><code>  convertView = LayoutInflater.from(context).inflate(R.layout.list_item,null);
</code></pre>
</li>
<li>
<p>These data chunks are called ‘items’. We sometimes use the wrong data. To clear this, you can use ‘setOnItemLongClickListener’ to delete it by pressing and holding it.<br>
-<img src="https://user-images.githubusercontent.com/44791701/48912667-9458ad00-eeb9-11e8-9120-d22c111dbaa7.jpg" alt="6" width="216" height="406"></center></p>
</li>
<li>
<p>setListview is responsible for connecting the data to the screen, and it also acts to open Read_File.java, which allows you to view the contents when you click on the item.</p>
<pre><code>  public void setListview(){  
  //어댑터 만들기 (데이터와 화면을 연결)  
    mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {  
      @Override  
  	  public void onItemClick(AdapterView&lt;?&gt; parent, View view, int position, long id) {  
          Intent intent = new Intent(getApplicationContext(), Read_File.class);  
          intent.putExtra("saveddata",list_itemArrayList.get(position));  

      startActivity(intent);  
  }  
  });
</code></pre>
</li>
<li>
<p>Use saveddata to pass the clicked ID to Read_File.</p>
</li>
<li>
<p>저장된 데이터를 리스트 형태로 출력하도록 합니다.</p>
</li>
<li>
<p>일반적인 리스트뷰는 사진도 없는 다소 ‘심심한’ 뷰를 제공하는데, 이를 위해 커스텀 리스트뷰를 만들었습니다.</p>
</li>
<li>
<p>MyAdapter1은 커스텀 리스트뷰를 위해 만들어졌고, list_item.xml파일과 연결하기 위해 다음 코드를 삽입합니다.</p>
<pre><code>  convertView = LayoutInflater.from(context).inflate(R.layout.list_item,null);
</code></pre>
</li>
<li>
<p>이런 데이터 덩어리들을 'item’이라고 부릅니다. 우리는 간혹 잘못된 데이터를 쓸 때가 있습니다. 이를 지우기 위해 'setOnItemLongClickListener’를 이용하여 길게 누르면 지울 수 있도록 하였습니다.</p>
</li>
<li>
<p>setListview는 데이터와 화면을 연결하는 역할을 하며, 아이템을 클릭시 내용을 볼 수 있게 하는 Read_File.java를 여는 역할도 동시에 수행합니다.</p>
</li>
<li>
<p>saveddata를 이용해 클릭한 ID를 Read_File에 전달합니다.</p>
</li>
</ul>
<h3 id="read_file">Read_File</h3>
<p><img src="https://user-images.githubusercontent.com/44791701/48912641-81de7380-eeb9-11e8-9cdd-684357904b44.jpg" alt="5" width="216" height="406"></center></p>
<ul>
<li>
<p>Take the saveddata and navigate the database.</p>
<pre><code>  con1 = data.getContent1();  
  con2 = data.getContent2();  

  while (mCursor.moveToNext()) {  
      String Content1 = mCursor.getString(mCursor.getColumnIndex("content1"));  
      String Content2 = mCursor.getString(mCursor.getColumnIndex("content2"));  
  	    if (Content1.equals(con1) &amp;&amp; Content2.equals(con2)) {  
          content1.setText(mCursor.getString(mCursor.getColumnIndex("content1")));  
          content2.setText(mCursor.getString(mCursor.getColumnIndex("content2")));  
          content3.setText(mCursor.getString(mCursor.getColumnIndex("content3")));  
  }  
  }
</code></pre>
</li>
<li>
<p>con1 and con2 receive content1 and content2 from saveddata, respectively.</p>
</li>
<li>
<p>Then compares content1 and content2 in the db and outputs the exact matched columns.</p>
</li>
<li>
<p>saveddata를 받아 데이터베이스를 탐색합니다.</p>
</li>
<li>
<p>con1과 con2는 각각 content1과 content2를 saveddata에서 뽑아와 저장하는 변수입니다.</p>
</li>
<li>
<p>그 이후 db 내의 content1과 content2를 비교하여 정확히 일치하는 열을 출력합니다.</p>
</li>
</ul>

