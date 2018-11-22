package kdkproject.com.sqliteexample.DataBase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;

import kdkproject.com.sqliteexample.Saved_List;
import kdkproject.com.sqliteexample.R;
public class MyAdapter1 extends BaseAdapter {

    Context context;
    ArrayList<Information> list_itemArrayList;
    ViewHolder viewHolder = new ViewHolder();

    public MyAdapter1(Saved_List saved_list, ArrayList<Information> list_itemArrayList) {
        this.context = context;
        this.list_itemArrayList=list_itemArrayList;
    }

    @Override
    public int getCount() {
        if(list_itemArrayList != null)
            return this.list_itemArrayList.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        return this.list_itemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext(); // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item,null);
            viewHolder.title = (TextView) convertView.findViewById(R.id.textname);
            viewHolder.title2 = (TextView) convertView.findViewById(R.id.textname2);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Information one = list_itemArrayList.get(position);  //데이타 가져오기

        if(one != null) {
            viewHolder.title.setText(one.getContent1());
            viewHolder.title2.setText(one.getContent2());
        }

        return convertView;
    }

    //ArrayList Getter And Setter
    public void setArrayList(ArrayList<Information> arrays) {
        this.list_itemArrayList = arrays;
    }

    public ArrayList<Information> getArrayList(){
        return list_itemArrayList;
    }

    static class ViewHolder {
        TextView title;
        TextView title2;
        ImageView imageView;
    }
    Comparator<Information> nameAsc = new Comparator<Information>() {
        @Override
        public int compare(Information o1, Information o2) {
            return o1.getContent1().compareTo(o2.getContent1());
        }
    };
}
