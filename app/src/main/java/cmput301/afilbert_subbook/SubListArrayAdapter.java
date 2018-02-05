package cmput301.afilbert_subbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Beta on 2018-02-04.
 * Based on https://stackoverflow.com/questions/20886795/populate-listview-from-listobject
 */

public class SubListArrayAdapter extends ArrayAdapter {

    List<Subscription> subList;
    public SubListArrayAdapter(Context context, List<Subscription> list)
    {
        super(context, 0, list);
        subList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            //convertView = mInflater.inflate(R.layout.content_scrolling,parent,false);
            // inflate custom layout called row
            holder = new ViewHolder();
            //holder.tv =(TextView) convertView.findViewById(R.is.textView1);
            // initialize textview
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        Subscription in = (Subscription)subList.get(position);
        holder.tv.setText(in.getName());
        // set the name to the text;

        return convertView;

    }

    static class ViewHolder
    {

        TextView tv;
    }
}
