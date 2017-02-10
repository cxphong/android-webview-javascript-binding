package chroma.fiot.chroma;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by caoxuanphong on 2/10/17.
 */

public class Notification extends  RealmObject{
    @PrimaryKey
    public String name;

    public int imageResourceId;
    public int color;
    public boolean enabled;
}