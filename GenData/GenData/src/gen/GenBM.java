package src.gen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import src.GenerateFile;
import src.extend.Constants;
import src.extend.GenFilesUtils;

public class GenBM {

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
			

//			String rmSeq = String.format("%07d", seq);
			String bmCode = getBmCode(seq);
			
//			username
			total.append(bmCode).append(GenFilesUtils.pipe());
			
//			RM code
			total.append(bmCode).append(GenFilesUtils.pipe());

//			Controller Code
			total.append("BMDEPT").append(GenFilesUtils.pipe());
			
//			FName
			total.append("F_" + bmCode).append(GenFilesUtils.pipe());
			
//			LName
			total.append("L_" + bmCode).append(GenFilesUtils.pipe());
			
//			BRANCH
			total.append(GenerateFile.LIST_BRANCH.get(seq - 1));

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
	
	@SuppressWarnings("resource")
	public static void generateBM(int records) throws IOException {
		String path = Constants.DIR + Constants.DIR_BM;
		File logPath = new File(path);
		if(!logPath.exists()){
			logPath.mkdirs();
		}
		
		StringBuffer total = new StringBuffer();
		for (int seq = 1; seq <= records; seq++) {
			total.append("0BM" + String.format("%07d", seq));
			total.append("Mr." + GenFilesUtils.spaces(9));
			total.append("นาย" + GenFilesUtils.spaces(9));
			total.append("test" + GenFilesUtils.spaces(21));
			total.append("ทดสอบ" + GenFilesUtils.spaces(20));
			total.append("test" + GenFilesUtils.spaces(31));
			total.append("ทดสอบ" + GenFilesUtils.spaces(30));
			total.append(GenFilesUtils.spaces(17)).append(GenFilesUtils.spaces(10)).append(GenFilesUtils.spaces(150)).append(GenFilesUtils.spaces(150)).append(GenFilesUtils.spaces(1)).append(GenFilesUtils.spaces(5)).append(GenFilesUtils.spaces(5)).append(GenFilesUtils.spaces(5));
			total.append("0" + String.format("%04d", 5000+seq));
			total.append(GenFilesUtils.spaces(150)).append(GenFilesUtils.spaces(150)).append(GenFilesUtils.spaces(10)).append(GenFilesUtils.spaces(60)).append(GenFilesUtils.spaces(60));
			total.append("mail@gmail.com").append(GenFilesUtils.spaces(56));
			total.append(GenFilesUtils.spaces(24)).append(GenFilesUtils.spaces(11)).append(GenFilesUtils.spaces(50));
			total.append("A");
			total.append(GenFilesUtils.spaces(1)).append(GenFilesUtils.spaces(8)).append(GenFilesUtils.spaces(8)).append(GenFilesUtils.spaces(3));
			total.append(Constants.DEFAULT_LINE_SEPARATOR);
			//System.out.println("0BM" + String.format("%07d", seq));
		}
		
		//System.out.println("Total: " + total.toString());
		File file = new File(path + "\\" + "BM_INFO.txt");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile()), Constants.ENCODING));
		bw.write(total.toString());

		
		file = new File(path + "\\" + "PISD026_NEW.txt");
		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile()), Constants.ENCODING));
		bw.write("");
		
		
		total = new StringBuffer();
		for (int seq = 1; seq <= records; seq++) {
			total.append("BM" + String.format("%07d", seq));
			total.append(String.format("%04d", 0+seq));
			total.append(Constants.DEFAULT_LINE_SEPARATOR);
		}
		
		file = new File(path + "\\" + "Interbranch_new.txt");
		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile()), Constants.ENCODING));
		bw.write(total.toString());
		bw.close();
		System.out.println("Generate BM Done");
	}
	
}
