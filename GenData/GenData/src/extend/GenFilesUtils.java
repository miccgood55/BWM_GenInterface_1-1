package src.extend;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.CharBuffer;

import src.GenerateFile;

public class GenFilesUtils {

	public static String spaces( int spaces ) {
		  return CharBuffer.allocate( spaces ).toString().replace( '\0', ' ' );
	}

	public static String pipe() {
		  return "|";
	}

	public static void writeFile(String path, StringBuffer sb) throws IOException{
		writeFile(new File(path), sb);
	}

	public static void writeFile(String path, StringBuffer sb, boolean headerCount, boolean isDelete) throws IOException{
		writeFile(new File(path), sb, headerCount, isDelete);
	}

	public static void writeFile(File file, StringBuffer sb) throws IOException{
		writeFile(file, sb, false, true);
	}

	public static void writeFile(File file, StringBuffer sb, boolean headerCount) throws IOException{
		writeFile(file, sb, headerCount, true);
	}
	
	public static void writeFile(File file, StringBuffer sb, boolean headerCount, boolean isDelete) throws IOException{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile()), Constants.ENCODING));
		if(headerCount){
			int count = 0;
			String[] sbs = sb.toString().split(Constants.DEFAULT_LINE_SEPARATOR);
			for (String str : sbs) {
				if(!str.equals("")){
					count++;
				}
			}
			bw.write(GenerateFile.CURRENT_DATE_FORMAT + "|" + count + "" + Constants.DEFAULT_LINE_SEPARATOR);
		}
		bw.write(sb.toString());
		bw.close();
		if(isDelete){
			sb.delete(0, sb.length());
		}
	}
}
