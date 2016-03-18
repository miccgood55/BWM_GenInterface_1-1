package src.gen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import src.GenerateFile;
import src.bean.CustomerMapper;
import src.extend.Constants;
import src.rm.GenRMMapCif;

public class GenCifMapBranch {
	
//	@SuppressWarnings("resource")
	public static void generateCifMapBranch() throws IOException {
		String path = Constants.DIR + Constants.DIR_CIF_MAP_BRANCH;
		File logPath = new File(path);
		if(!logPath.exists()){
			logPath.mkdirs();
		}
		
		String header = "Branch Code   |CIF Code";
		StringBuffer sb = new StringBuffer(header).append(Constants.DEFAULT_LINE_SEPARATOR);
//		
//		int indexBranchList = 0, branchListSize = GenerateFile.LIST_BRANCH.size(); // 600
//		int startCode = GenerateFile.NO_OF_RM * GenerateFile.MAX_RM_MAP_CUS;
		for (String branchCode : GenerateFile.LIST_BRANCH) {
			for (int i = 0; i < GenerateFile.MAX_CIF_MAP_BRANCH; i++) {
				String cifCode = "P" + String.format("%06d", GenRMMapCif.CUSTOMER_SEQ++);
				CustomerMapper cm = GenerateFile.CM_MAP.get(cifCode);

				if(cm != null && cm.getRmCode() == null){
				
					cm.setBranchCode(branchCode);
					
					sb.append(cm.getBranchCode()).append("|").append(cifCode);
					
					sb.append(Constants.DEFAULT_LINE_SEPARATOR);
					
				} 
//				else if(cm == null){
//					cm = new CustomerMapper("");
//				}
			}
		}
		
		File file = new File(path + Constants.FILE_NAME_CIF_MAP_BRANCH + ".txt");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile()), Constants.ENCODING));
		bw.write(sb.toString());
		bw.close();

		System.out.println("Generate Cif Map Branch Done");
	}
	
}
