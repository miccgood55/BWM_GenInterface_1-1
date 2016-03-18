package src.rm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import src.bean.CustomerMapper;
import src.extend.Constants;
import src.GenerateFile;

public class GenRMMapCif extends GenRM{


//	private static final int RM_CHILD = 9;
	
//	private static Map<Integer, Integer> RM_RECORD_COUNT = null;
//	private static Map<Integer, StringBuffer> RM_CONTENT = null;

	public static Integer CUSTOMER_SEQ = null;

//	private static final int MAX_RM_MAP_CUS = 35;
	
//	@SuppressWarnings("resource")
	public static void generateRMMapCif() throws IOException {
		String path = Constants.DIR + Constants.DIR_RM_MAP_CIF;
		File logPath = new File(path);
		if(!logPath.exists()){
			logPath.mkdirs();
		}
		
		String header = "RMCODE   |CUSTIMER";
		StringBuffer sb = new StringBuffer(header).append(Constants.DEFAULT_LINE_SEPARATOR);
		
		int maxLevel = genRmRecordCount(GenerateFile.NO_OF_RM);
		CUSTOMER_SEQ = 1;
		
		sb.append(generateRMMapCifTree(maxLevel, 0, 0, null));
		
		File file = new File(path + "\\" + Constants.FILE_NAME_RM_MAP_CIF + ".txt");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile()), Constants.ENCODING));
		bw.write(sb.toString());
		bw.close();

		System.out.println("Generate RM Map Cif Done");
	}
	
//	@SuppressWarnings("resource")
	private static StringBuffer generateRMMapCifTree(final int maxLevel, int level, int seq, String hCode) throws IOException {
		
		Integer count = RM_MAP_CIF_COUNT.get(level);
		if(--count < 0){
			return null;
		}
		
		RM_MAP_CIF_COUNT.put(level, count);
		
		StringBuffer sb = new StringBuffer();
		String rmCode = GenRM.getRmCode(seq);
		
		for (int i = 0; i < GenerateFile.MAX_RM_MAP_CUS; i++) {

			String cifCode = "P" + String.format("%06d", CUSTOMER_SEQ++);

			CustomerMapper cm = GenerateFile.CM_MAP.get(cifCode);
			if(cm == null){
				break ;
			}
			
			sb.append(rmCode).append("|").append(cifCode);
			
			sb.append(Constants.DEFAULT_LINE_SEPARATOR);
			
			cm.setRmCode(rmCode);
		}
		
		for (int i = 0; i < RM_CHILD; i++) {
			int nextSeq = ( seq * 10 ) + (i + 1) ;//(seq == 0 ? seq + 1: seq + child) + i;
			if(maxLevel == level + 1)
				continue;
//			  			   maxLevel, nextlevel    , seq    , child, hCode;
			StringBuffer sbr = generateRMMapCifTree(maxLevel, level + 1, nextSeq, rmCode);
			
			if(sbr != null){
				sb.append( sbr );
			}
		}
		
		return sb;
	}
	
}
