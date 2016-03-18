package src.gen;

import java.io.File;
import java.io.IOException;

import src.GenerateFile;
import src.extend.Constants;
import src.extend.GenFilesUtils;

public class GenDeposit {

	private static int NO_OF_ACC = 0, NO_OF_POSITION = 0, NO_OF_TRANSACTION = 0, 
			NO_OF_ACC_FILE = 0, NO_OF_POSITION_FILE = 0, NO_OF_TRANSACTION_FILE = 0;
	
	private static StringBuffer 
			ACC_SB = new StringBuffer(), 
			POSITION_SB = new StringBuffer(), 
			TRANSACTION_SB = new StringBuffer();

	private final static String PATH_ACC = Constants.DIR + Constants.DIR_DEP_ACC;
	private final static String PATH_POS = Constants.DIR + Constants.DIR_DEP_POS;
	private final static String PATH_TX= Constants.DIR + Constants.DIR_DEP_TX;
	
//	Security Code = CA, FCDCA
	public static void generateDepositAccount() throws IOException {
		File logPath = new File(PATH_ACC);
		if(!logPath.exists()){
			logPath.mkdirs();
		}
		
		for (int i = 1; i <= GenerateFile.NO_OF_ACC; i++) {
			for (int seq = 1; seq <= GenerateFile.NO_OF_CUSTOMER; seq++) {
				NO_OF_ACC++;
				String cifCode = "P" + String.format("%06d", seq);
				String accNo = "ACC_" + cifCode + "_" + String.format("%02d", i);
//				String record = cifCode + "|" + cifCode + "|SA|0001|" + accNo + "|NAME_ACC_" + accNo + "|19841228|19841228|THB|2";
				String record = "SA|THB|BAY|NAME_" + accNo + "|" +accNo+ "|BAY|00001||||||||||"+cifCode+"||||||||||SA-001|";
				
				
//				0	1	Deposit Type	STRING		Y		
//				1	2	Currency Code	STRING		Y		
//				2	3	Issuer Code	STRING		Y		
//				3	4	Bank Account Name	STRING		N		
//				4	5	Bank Account No	STRING		Y		
//				5	6	Bank Code	STRING		Y		
//				6	7	Branch Code	STRING		Y		
//				7	9	Open Account Date	DATE	yyyyMMdd	N		
//				8	10	Close Account Date	DATE	yyyyMMdd	N		
//				9	5	Sub Account No	STRING		Y		
//				10	16	Account Type	INTEGER		N		
//				11	17	Main CIF Code(Layout2)	STRING		N		
//				12	18	Joint CIF1(Layout2)	STRING		N		
//				13	19	Joint CIF2(Layout2)	STRING		N		
//				14	20	Joint CIF3(Layout2)	STRING		N		
//				15	21	Joint CIF4(Layout2)	STRING		N		
//				16	22	Joint CIF5(Layout2)	STRING		N		
//				17	23	Joint CIF6(Layout2)	STRING		N		
//				18	24	Joint CIF7(Layout2)	STRING		N		
//				19	25	Joint CIF8(Layout2)	STRING		N		
//				20	26	Joint CIF9(Layout2)	STRING		N	
//				22	27	Security Code	STRING		N
//				
//				
				ACC_SB.append(record).append(Constants.DEFAULT_LINE_SEPARATOR);
				
				generateDepositPosition(accNo);
				
				if(NO_OF_ACC%GenerateFile.LIMIT_DEP_PER_FILE == 0){
					writeAcc();
				}
			}
		}
		

		if(ACC_SB.length() > 0){
			writeAcc();
		}
		if(POSITION_SB.length() > 0){
			writePos();
		}
		if(TRANSACTION_SB.length() > 0){
			writeTx();
		}

		System.out.println("Generate Deposit Account Done");
	}
	
	public static void generateDepositPosition(String accNo) throws IOException {
		File logPath = new File(PATH_POS);
		if(!logPath.exists()){
			logPath.mkdirs();
		}

		NO_OF_POSITION ++;
		String record = accNo + "|||"+GenerateFile.CURRENT_DATE_FORMAT+"|||80||";
		
		
//		0	1	Bank Account No	   	STRING				Y		
//		1	1	Sub Account No		STRING				Y		
//		2	3	Accrued Interest	BIGDECIMAL	20|2	N		
//		3	4	As of Date	DATE	yyyyMMdd			Y		
//		4	5	Announced Rate		BIGDECIMAL	17|6	N		
//		5	6	Effective Rate		BIGDECIMAL	17|6	N		
//		6	7	Market Value		BIGDECIMAL	20|2	Y		
//		7	8	Hold All Fund Flag	STRING				N		
//		8	9	Hold Amount			BIGDECIMAL	20|2	N		
//		9	0	Source				STRING				Y	BAY-CA
//		
		POSITION_SB.append(record).append(Constants.DEFAULT_LINE_SEPARATOR);
		
		generateDepositTransaction(accNo);
		
		if(NO_OF_POSITION%GenerateFile.LIMIT_DEP_PER_FILE == 0){
			writePos();
		}
	}
	
	public static void generateDepositTransaction(String accNo) throws IOException {
//		String path = Constants.DIR + Constants.DIR_DEP_TX;
		File logPath = new File(PATH_TX);
		if(!logPath.exists()){
			logPath.mkdirs();
		}
		
		for (int j = 1; j <= GenerateFile.NO_OF_DEP_TX_PER_POS; j++) {
			NO_OF_TRANSACTION++;
			String txNo = "DEPTXNO" + String.format("%05d", NO_OF_TRANSACTION);
			String record = accNo + "||DEPOSIT|6|81|"+GenerateFile.CURRENT_DATE_FORMAT
					+"|" +GenerateFile.CURRENT_DATE_FORMAT
					+"|||" + txNo + "|";
			
			
//			0	1	Bank Account No	STRING		N		
//			1	1	Sub Account No	STRING		Y		
//			2	3	Transaction Type	STRING		Y		
//			3	4	Sub Transaction Type	STRING		N		
//			4	5	Cost Amount	BIGDECIMAL	20|2	Y		
//			5	6	Settle Date	DATE	yyyyMMdd	Y		
//			6	7	Trade Date	DATE	yyyyMMdd	Y		
//			7	8	Status	STRING		N		
//			8	9	Reference Account	STRING		N		
//			9	10	Sequence	STRING		N		
//			10	0	Source	STRING		Y	BAY-CA
			
			
			TRANSACTION_SB.append(record).append(Constants.DEFAULT_LINE_SEPARATOR);
			
			if(NO_OF_TRANSACTION%GenerateFile.LIMIT_DEP_PER_FILE == 0){
				writeTx();
			}
		}
	}

	private static void writeAcc () throws IOException{
		NO_OF_ACC_FILE++;
		File file = new File(PATH_ACC + Constants.FILE_NAME_DEP_ACC + NO_OF_ACC_FILE + ".txt");
		GenFilesUtils.writeFile(file, ACC_SB, true);
	}
	
	private static void writePos () throws IOException{
		NO_OF_POSITION_FILE++;
		File file = new File(PATH_POS + Constants.FILE_NAME_DEP_POS + "_" + NO_OF_POSITION_FILE + ".txt");
		GenFilesUtils.writeFile(file, POSITION_SB, true);
	}
	
	private static void writeTx () throws IOException{
		NO_OF_TRANSACTION_FILE++;
		File file = new File(PATH_TX + Constants.FILE_NAME_DEP_TX + "_" + NO_OF_TRANSACTION_FILE + ".txt");
		GenFilesUtils.writeFile(file, TRANSACTION_SB, true);
	}
}
