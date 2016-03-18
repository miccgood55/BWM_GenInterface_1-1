package src.gen;

import java.io.File;
import java.io.IOException;

import src.GenerateFile;
import src.extend.Constants;
import src.extend.GenFilesUtils;

public class GenBond {

	// public static void generateBond() throws IOException {
	// for (int seq = 1; seq <= GenerateFile.NO_OF_MASTER_BOND; seq++) {
	// generateBondMaster(GenerateFile.NO_OF_MASTER_BOND,
	// GenerateFile.NO_OF_MASTER_BOND);
	// }
	// System.out.println("Generate Bond Done");
	// }
	private static int CIF_SEQ = 1, 
			NO_OF_MASTER = 0, NO_OF_POSITION = 0, NO_OF_TRANSACTION = 0, 
			NO_OF_MASTER_FILE = 0, NO_OF_POSITION_FILE = 0, NO_OF_TRANSACTION_FILE = 0;
	
	private static StringBuffer 
			MASTER_SB = new StringBuffer(), 
			POSITION_SB = new StringBuffer(), 
			TRANSACTION_SB = new StringBuffer();
	
	public static void generateBondMaster() throws IOException {
		String path = Constants.DIR + Constants.DIR_BONDMASTER;
		File logPath = new File(path);
		if (!logPath.exists()) {
			logPath.mkdirs();
		}
//		int noOfFile = 0;
		
		for (int seq = 1; seq <= GenerateFile.NO_OF_MASTER_BOND; seq++) {

			String bond = "BOND-BAY-" + String.format("%05d", seq);
			String record = bond + "|||BAY||THB||BAY-DEBENTURE|20141107|20141108|10|||";

			MASTER_SB.append(record).append(Constants.DEFAULT_LINE_SEPARATOR);

			generateBondPosition(bond);
			NO_OF_MASTER++;
			
			if (NO_OF_MASTER % GenerateFile.LIMIT_BOND_PER_FILE == 0) {
				NO_OF_MASTER_FILE++;
				File file = new File(path + Constants.FILE_NAME_BONDMASTER + NO_OF_MASTER_FILE + ".txt");
				GenFilesUtils.writeFile(file, MASTER_SB);
			}
		}
		
		if(MASTER_SB.length() > 0){
			NO_OF_MASTER_FILE++;
			File file = new File(path + Constants.FILE_NAME_BONDMASTER + NO_OF_MASTER_FILE + ".txt");
			
			GenFilesUtils.writeFile(file, MASTER_SB, true);
		}
		if(POSITION_SB.length() > 0){
			NO_OF_POSITION_FILE++;

			String pathPos = Constants.DIR + Constants.DIR_BOND_POS;
			File file = new File(pathPos + Constants.FILE_NAME_BOND_POS + "_" + NO_OF_POSITION_FILE + ".txt");
			GenFilesUtils.writeFile(file, POSITION_SB, true);
		}
		if(TRANSACTION_SB.length() > 0){
			NO_OF_TRANSACTION_FILE++;
			String pathTx = Constants.DIR + Constants.DIR_BOND_TX;
			File file = new File(pathTx + Constants.FILE_NAME_BOND_TX + "_" + NO_OF_TRANSACTION_FILE + ".txt");
			GenFilesUtils.writeFile(file, TRANSACTION_SB, true);
		}

		System.out.println("Generate Bond Done");
	}

	public static void generateBondPosition(String bond) throws IOException {
		String path = Constants.DIR + Constants.DIR_BOND_POS;
		File logPath = new File(path);
		if (!logPath.exists()) {
			logPath.mkdirs();
		}

//		StringBuffer total = new StringBuffer();
		for (int seq = 1; seq <= GenerateFile.NO_OF_POSITION_PER_MASTER_BOND; seq++) {

			String cifCode = GenCustomer.getCifCode(CIF_SEQ++);

			if (GenerateFile.CM_MAP.containsKey(cifCode)) {

//				String record = "81.0|82.0|" + GenerateFile.CURRENT_DATE_FORMAT + "|" + cifCode
//						+ "|85.0|86||88.0|89.0|810.0|811.0|812.0|813.0|814.0|" + GenerateFile.CURRENT_DATE_FORMAT + "|" + bond + "||818.0";
				String record = bond + "|" + GenerateFile.CURRENT_DATE_FORMAT 
						+ "|" + cifCode + "|100|810|" + GenerateFile.CURRENT_DATE_FORMAT 
						+ "|81|10|BAY";
				
				POSITION_SB.append(record).append(Constants.DEFAULT_LINE_SEPARATOR);

				generateBondTransaction(bond, cifCode);
				
				NO_OF_POSITION++;
			}

			if (NO_OF_POSITION % GenerateFile.LIMIT_BOND_PER_FILE == 0) {
				NO_OF_POSITION_FILE++;
				File file = new File(path + Constants.FILE_NAME_BOND_POS + "_" + NO_OF_POSITION_FILE + ".txt");
				GenFilesUtils.writeFile(file, POSITION_SB, true);
			}
		}
//		System.out.println("Generate Bond Position Done");
	}

	public static void generateBondTransaction(String bond, String cifCode) throws IOException {
		// int records, int limit, int noOfBond, int noOfTx) throws IOException
		// {
		String path = Constants.DIR + Constants.DIR_BOND_TX;
		File logPath = new File(path);
		if (!logPath.exists()) {
			logPath.mkdirs();
		}

//		int txRunning = 0;
		for (int seq = 1; seq <= GenerateFile.NO_OF_TRANSACTION_PER_POSITION_BOND; seq++) {
//			txRunning++;
			NO_OF_TRANSACTION++;
			String txNo = "BOND_TX_NO_" + String.format("%08d", NO_OF_TRANSACTION);
//			String record = "1|2|||" + cifCode + "|6|7|" + txNo + "|ICCODE1||11|12|13|14|15|REFERRALCODE1|" + bond
//					+ "|" + GenerateFile.CURRENT_DATE_FORMAT + "||A|" + GenerateFile.CURRENT_DATE_FORMAT + "|BUY|23|RMCODE1";
			String record = GenerateFile.CURRENT_DATE_FORMAT + "|" + GenerateFile.CURRENT_DATE_FORMAT
					+ "|BAY|" + bond + "|" + cifCode + "|" + txNo + "|BUY|6|810|10|81|||||||DEBENTURE-BAY";
					
			TRANSACTION_SB.append(record).append(Constants.DEFAULT_LINE_SEPARATOR);
			
			if (NO_OF_TRANSACTION % GenerateFile.LIMIT_BOND_PER_FILE == 0) {
				NO_OF_TRANSACTION_FILE++;
				File file = new File(path + Constants.FILE_NAME_BOND_TX + "_" + NO_OF_TRANSACTION_FILE + ".txt");
				GenFilesUtils.writeFile(file, TRANSACTION_SB, true);
			}
		}
//		System.out.println("Generate Bond Transaction Done");
	}

}
