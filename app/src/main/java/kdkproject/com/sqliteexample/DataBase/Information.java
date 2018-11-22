package kdkproject.com.sqliteexample.DataBase;

import android.os.Parcel;
import android.os.Parcelable;


public class Information implements Parcelable {
    public String content3 = "";
    public String content2 = "";
    public String content1 = "";

    protected Information(Parcel in) {
        content1 = in.readString();
        content2 = in.readString();
        content3 = in.readString();
    }

    public static final Creator<Information> CREATOR = new Creator<Information>() {
        @Override
        public Information createFromParcel(Parcel in) {
            return new Information(in);
        }

        @Override
        public Information[] newArray(int size) {
            return new Information[size];
        }
    };


    public Information(String content1, String content2, String content3){
        this.content1 = content1;
        this.content2 = content2;
        this.content3 = content3;
    }

    public void information(String content1, String content2, String content3){
        this.content1 = content1;
        this.content2 = content2;
        this.content3 = content3;
    }

    public String getContent1() {return content1;}
    public void setContent1(){
        this.content1=content1;
    }
    public String getContent2() {return content2;}
    public void setContent2(){
        this.content2=content2;
    }
    public String getContent3() {return content3;}
    public void setContent3(){
        this.content3=content3;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content1);
        dest.writeString(content2);
        dest.writeString(content3);
    }
}
