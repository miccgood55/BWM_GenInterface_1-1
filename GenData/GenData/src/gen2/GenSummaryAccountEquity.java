package src.gen2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import src.GenerateFile;
import src.extend.Constants;
import src.extend.GenFilesUtils;

public class GenSummaryAccountEquity {

	public static String getBmCode(int seq){
		return new StringBuffer("BM").append(String.format("%07d", seq)).toString();
	}
	
//	@SuppressWarnings("resource")
	public static void generateBMPipe() throws IOException {
		String path = Constants.DIR + Constants.DIR_BM;
		File logPath = new File(path);
		if(!logPath.exists()){
			logPath.mkdirs();
		}
		
		StringBuffer total = new StringBuffer("USERNAME |EMPCODE  |CONTRCODE|FNAME       |LNAME      |BranchCode")
				.append(Constants.DEFAULT_LINE_SEPARATOR);
		for (int seq = 1; seq <= GenerateFile.NO_OF_BM; seq++) {
			
//			20151230|000000025|
//			KSS-Equity|นาย J|JJJJJJ-1|20081117|99991231|0|1|01|CARD_ID_J|1|
//			KSS-Equity|นาย B|BBBBBB-1|20091111|99991231|0|1|01|CARD_ID_B|1|
//			KSS-Equity|นาย B|BBBBBB-2|20091111|99991231|0|2|01|CARD_ID_B|1|
//			KSS-Equity|นาย B|BBBBBB-9|20120525|99991231|0|9|01|CARD_ID_B|1|
//			KSS-Equity|นาย O|OOOOOO-1|20090917|99991231|0|1|06|CARD_ID_O|1|
//			KSS-Equity|นาย O|OOOOOO-2|20090922|99991231|0|2|06|CARD_ID_O|1|
//			KSS-Equity|นาย F|FFFFFF-1|20100315|99991231|0|1|01|CARD_ID_F|1|
//			KSS-Equity|นาย F|FFFFFF-9|20140602|99991231|0|9|01|CARD_ID_F|1|
//			KSS-Equity|นาย H|HHHHHH-1|20110817|99991231|0|1|01|CARD_ID_H|1|
//			KSS-Equity|นาย H|HHHHHH-2|20110817|99991231|0|2|01|CARD_ID_H|1|
//			KSS-Equity|นาย M|MMMMMM-1|20111118|99991231|0|1|01|CARD_ID_M|1|
//			KSS-Equity|นาย M|MMMMMM-2|20111118|99991231|0|2|01|CARD_ID_M|1|
//			KSS-Equity|นาย A|AAAAAA-1|20120704|99991231|0|1|01|CARD_ID_A|1|
//			KSS-Equity|นาย A|AAAAAA-2|20120704|99991231|0|2|01|CARD_ID_A|1|
//			KSS-Equity|นาย G|GGGGGG-2|20121015|99991231|0|2|01|CARD_ID_G|1|
//			KSS-Equity|นาย C|CCCCCC-1|20130225|99991231|0|1|01|CARD_ID_C|1|
//			KSS-Equity|นาย C|CCCCCC-2|20130225|99991231|0|2|01|CARD_ID_C|1|
//			KSS-Equity|นาย I|IIIIII-1|20140212|99991231|0|1|01|CARD_ID_I|1|
//			KSS-Equity|นาย D|DDDDDD-1|20140918|99991231|0|1|01|CARD_ID_D|1|
//			KSS-Equity|นาย D|DDDDDD-2|20140918|99991231|0|2|01|CARD_ID_D|1|
//			KSS-Equity|นาย K|KKKKKK-2|20140922|99991231|0|2|01|CARD_ID_K|1|
//			KSS-Equity|นาย N|NNNNNN-1|20141028|99991231|0|1|01|CARD_ID_N|1|
//			KSS-Equity|นาย N|NNNNNN-2|20141028|99991231|0|2|01|CARD_ID_N|1|
//			KSS-Equity|นาย L|LLLLLL-1|20150227|99991231|0|1|01|CARD_ID_L|1|
//			KSS-Equity|นาย L|LLLLLL-2|20150227|99991231|0|2|01|CARD_ID_L|1|
			
//			Source
			total.append("KSS-Equity").append(GenFilesUtils.pipe());

			total.append("Name").append(seq).append(GenFilesUtils.pipe());
			
			total.append("AAAAAA-").append(seq).append(GenFilesUtils.pipe());

			total.append(GenerateFile.CURRENT_DATE_FORMAT).append(GenFilesUtils.pipe());
			
			total.append("Name").append(seq);

			total.append(Constants.DEFAULT_LINE_SEPARATOR);
			
			total.append(Constants.DEFAULT_LINE_SEPARATOR);
			
			//System.out.println("0BM" + String.format("%07d", seq));
		}
		
		//System.out.println("Total: " + total.toString());
		File file = new File(path + Constants.FILE_NAME_BM + ".txt");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile()), Constants.ENCODING));
		bw.write(total.toString());
		bw.close();
		
		System.out.println("Generate BM PIPE Done");
	}
	
}
