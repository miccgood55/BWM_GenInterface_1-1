package src.rm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import src.extend.Constants;
import src.extend.GenFilesUtils;
import src.GenerateFile;

public class GenRM {

	
	protected static final int RM_CHILD = 6;
	
	protected static Map<Integer, Integer> RM_RECORD_COUNT = null;
	protected static Map<Integer, Integer> RM_MAP_CIF_COUNT = null;
	private static Map<Integer, StringBuffer> RM_CONTENT_MAP = null;
	private static StringBuffer RM_CONTENT = new StringBuffer();
	
//	@SuppressWarnings("resource")
	public static void generateRM() throws IOException {
		String path = Constants.DIR + Constants.DIR_RM;
		File logPath = new File(path);
		if(!logPath.exists()){
			logPath.mkdirs();
		}
		
		int maxLevel = genRmRecordCount(GenerateFile.NO_OF_RM);
//		String header = "USERNAME |EMPCODE  |CONTRCODE|FNAME       |LNAME      ";
//		StringBuffer sb = new StringBuffer("USERNAME |EMPCODE  |CONTRCODE|FNAME       |LNAME      ")
//				.append(Constants.DEFAULT_LINE_SEPARATOR)					
//				.append(generateRMTree(maxLevel, 0, 0, null));
		generateRMTree(maxLevel, 0, 0, null);
		
		for (int i = 0; i < RM_CONTENT_MAP.size(); i++) {
			File file = new File(path + Constants.FILE_NAME_RM + "_" + i + ".txt");
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile()), Constants.ENCODING));
//			StringBuffer sb = new StringBuffer(header).append(Constants.DEFAULT_LINE_SEPARATOR);
			bw.write(RM_CONTENT_MAP.get(i).toString());
			bw.close();
		}
		
		
//		File file = new File(path + Constants.FILE_NAME_RM + ".txt");
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile()), Constants.ENCODING));
//		bw.write(RM_CONTENT.toString());
//		bw.close();

		System.out.println("Generate RM Done");
	}
	
//	@SuppressWarnings("resource")
	private static StringBuffer generateRMTree(final int maxLevel, int level, int seq, String hCode) throws IOException {
		
		Integer count = RM_RECORD_COUNT.get(level);
		if(--count < 0){
			return null;
		}
		
		RM_RECORD_COUNT.put(level, count);
		
		StringBuffer sb = new StringBuffer();
		String rmSeq = String.format("%-7s", seq ).replace(' ', '0');
		
//		 rmSeq = String.format("%07d", seq);
		String rmCode = getRmCode(seq);
		hCode = (hCode == null ? "RMHead01" : hCode);
		
//		username
		sb.append(rmCode).append(GenFilesUtils.pipe());
		
//		RM code
		sb.append(rmCode).append(GenFilesUtils.pipe());

//		Controller Code
		sb.append(hCode).append(GenFilesUtils.pipe());
		
//		FName
		sb.append("FName" + rmSeq).append(GenFilesUtils.pipe());
		
//		LName
		sb.append("LName" + rmSeq);

		sb.append(Constants.DEFAULT_LINE_SEPARATOR);
		
		RM_CONTENT_MAP.get(level).append(sb);
		RM_CONTENT.append(sb);
		for (int i = 0; i < RM_CHILD; i++) {
			int nextSeq = ( seq * 10 ) + (i + 1) ;//(seq == 0 ? seq + 1: seq + child) + i;
			if(maxLevel == level + 1)
				continue;
//			maxLevel, nextlevel    , seq    , child, hCode;
			generateRMTree(maxLevel, level + 1, nextSeq, rmCode);
			
//			StringBuffer sbr = generateRMTree(maxLevel, level + 1, nextSeq, rmCode);
			
//			if(sbr != null){
//				sb.append( sbr );
//			}
		}
		
		return sb;
	}
	

	protected static int genRmRecordCount(int noOfRM){
		RM_RECORD_COUNT = new HashMap<Integer, Integer>();
		RM_MAP_CIF_COUNT = new HashMap<Integer, Integer>();
		RM_CONTENT_MAP = new HashMap<Integer, StringBuffer>();
		RM_CONTENT = new StringBuffer();
		
		int maxLevel = 0, noOfRMTemp = noOfRM;
		
		for (int i = 0; noOfRMTemp > 0; i++) {
			double pow = Math.pow(RM_CHILD, i);
			noOfRMTemp -= pow;
			maxLevel = i + 1;
			
			if(noOfRMTemp > 0){
				Integer intVal = BigDecimal.valueOf(pow).toBigInteger().intValue();
				RM_RECORD_COUNT.put(i, intVal);
				RM_MAP_CIF_COUNT.put(i, intVal);
			} else {
				Integer intVal = BigDecimal.valueOf(pow).toBigInteger().intValue() + noOfRMTemp;
				RM_RECORD_COUNT.put(i, intVal);
				RM_MAP_CIF_COUNT.put(i, intVal);
			}
			RM_CONTENT_MAP.put(i, new StringBuffer());
		}
		return maxLevel;
	}
	
	public static String getRmCode(int seq){
		return new StringBuffer("RM").append(String.format("%-7s", seq ).replace(' ', '0')).toString();
	}

}
