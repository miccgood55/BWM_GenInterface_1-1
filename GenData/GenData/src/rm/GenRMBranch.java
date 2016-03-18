package src.rm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import src.extend.Constants;
import src.extend.GenFilesUtils;
import src.GenerateFile;

public class GenRMBranch {

	public static void generateRmBranch(int records) throws IOException {
		String path = Constants.DIR + Constants.DIR_RM_BRANCH;
		File logPath = new File(path);
		if(!logPath.exists()){
			logPath.mkdirs();
		}
		
		StringBuffer total = new StringBuffer();
		for (int seq = 1; seq <= records; seq++) {
			String branchCode = String.format("%04d", 0+seq);
			total.append(GenFilesUtils.spaces(9));

//			Org-1=Branch Number
			total.append(branchCode);
//			Org-0 = 0000
			total.append("0000");
			total.append(GenFilesUtils.spaces(20));
//			Head_Office_Branch = 1
			total.append("1");
			total.append(GenFilesUtils.spaces(15));
			
			total.append("0" + branchCode);
			total.append(GenFilesUtils.spaces(9));
			total.append(String.format("%-150s", "branch" + branchCode ));
			total.append(String.format("%-150s", "branch_th" + branchCode ));
			total.append(GenFilesUtils.spaces(80));
			total.append(Constants.DEFAULT_LINE_SEPARATOR);
			
			GenerateFile.LIST_BRANCH.add("0" + branchCode);
		}
		
		File file = new File(path + Constants.FILE_NAME_RM_BRANCH +  ".txt");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile()), Constants.ENCODING));
		bw.write(total.toString());
		bw.close();
		System.out.println("Generate Rm Branch Done");
	}
	
}
