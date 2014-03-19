package com.filemanager;

import android.os.Bundle;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FilemanagerActivity extends ListActivity {
	private List<String> item = null;
	private List<String> path = null;
	private String root = "/";
	private TextView myPath;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		myPath = (TextView) findViewById(R.id.path);
		getDir(root); 

	}

	// Function to to get the files from directory
	private void getDir(String dirPath) {
		// TODO Auto-generated method stub

		myPath.setText(" Current Path : " + dirPath);
		item = new ArrayList<String>();
		path = new ArrayList<String>();
		File f = new File(dirPath);
		File[] files = f.listFiles();

		if (!dirPath.equals(root)) 
		{
			item.add(root);
			path.add(root);
			item.add("../");
			path.add(f.getParent());

		}

		for (int i = 0; i < files.length; i++) {
			File file = files[i]; 
			path.add(file.getPath()); 
			if (file.isDirectory()) {
				item.add(file.getName() + "/");
			} else {
				item.add(file.getName()); 
			}

		}
		
		ArrayAdapter<String> fileList = new ArrayAdapter<String>(this,
				R.layout.row, item);
		setListAdapter(fileList);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		File file = new File(path.get(position));
		if (file.isDirectory()) {
			if (file.canRead())
				getDir(path.get(position));
			else {
				Toast.makeText(FilemanagerActivity.this, " UnReadable folder",
						Toast.LENGTH_SHORT).show();

			}
		} 
		else 
		{
			Toast.makeText(FilemanagerActivity.this,
					"File Selected = " + file.getName(), Toast.LENGTH_LONG).show();

		}
	}
}