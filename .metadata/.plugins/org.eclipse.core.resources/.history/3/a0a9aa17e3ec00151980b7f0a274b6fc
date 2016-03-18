package src.gen;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import src.GenerateFile;
import src.extend.Constants;
import src.extend.GenFilesUtils;

public class GenUnitTrust {

	private final static Map<Integer, String> FUND_CODE_MAP = new HashMap<Integer, String>();
	
	private static int FUND_SEQ = 1, 
			NO_OF_UH = 0, NO_OF_POSITION = 0, NO_OF_TRANSACTION = 0, 
			NO_OF_UH_FILE = 0, NO_OF_POSITION_FILE = 0, NO_OF_TRANSACTION_FILE = 0;
	
	private static StringBuffer 
			UH_SB = new StringBuffer(), 
			POSITION_SB = new StringBuffer(), 
			TRANSACTION_SB = new StringBuffer();
	

	private final static String PATH_UH = Constants.DIR + Constants.DIR_UH;
	private final static String PATH_POS = Constants.DIR + Constants.DIR_UT_POS;
	private final static String PATH_TX= Constants.DIR + Constants.DIR_UT_TX;
	private final static String ISSUER_CODE = "BAY";

//	Security Code = THMMF,THFI,FIFFI,FFICE,THEQ,FIFEQ,THMIX,FIFMIX,COMM,PROP
	
	public static void generateFundCode() throws IOException {
		String path = Constants.DIR + Constants.DIR_FUNDCODE;
		File logPath = new File(path);
		if(!logPath.exists()){
			logPath.mkdirs();
		}
		int noOfFile = 0;
		StringBuffer total = new StringBuffer();
		for (int seq = 1; seq <= GenerateFile.NO_OF_FUND; seq++) {
			String fundCode = "FUND-BAY-" + String.format("%05d", seq);
//			String record = "||||||" + fundCode + "|||SEC-CAT-UNITTRUST|BAY" ;
			
			String record = ISSUER_CODE + "|"+fundCode+"|THMMF|||||||||THB" ;
			
			total.append(record).append(Constants.DEFAULT_LINE_SEPARATOR);
			
			FUND_CODE_MAP.put(seq, fundCode);
			
			if(seq%GenerateFile.LIMIT_FUND_PER_FILE == 0){
				noOfFile++;
				File file = new File(path + "\\" + Constants.FILE_NAME_FUNDCODE + noOfFile + ".txt");
				GenFilesUtils.writeFile(file, total, true);
			}
		}
		System.out.println("Generate FundCode Done");
	}
	
	public static void generateUnitholder() throws IOException {
		File logPath = new File(PATH_UH);
		if(!logPath.exists()){
			logPath.mkdirs();
		}
		
		for (int seq = 1; seq <= GenerateFile.NO_OF_CUSTOMER; seq++) {
			for (int i = 1; i <= GenerateFile.NO_OF_UH; i++) {
				NO_OF_UH++;
				String cifCode = "P" + String.format("%06d", seq);
				String uh = "UH_" + cifCode + "_" + String.format("%02d", i);
//				String record = uh + "|||||0|" + cifCode;
				String record = uh + "|UH_NAME|UH_NAME_TH|0|" + cifCode + "|||||||||||"
							+"|BAY|00001|"+ISSUER_CODE+"|||";
				
				UH_SB.append(record).append(Constants.DEFAULT_LINE_SEPARATOR);
				
				generateUnitTrustPosition(cifCode, uh);
				
				if(NO_OF_UH%GenerateFile.LIMIT_UH_PER_FILE == 0){
					NO_OF_UH_FILE++;
					File file = new File(PATH_UH + "\\" + Constants.FILE_NAME_UH + NO_OF_UH_FILE + ".txt");
					GenFilesUtils.writeFile(file, UH_SB, true);
				}
			}
		}
		if(UH_SB.length() > 0){
			NO_OF_UH_FILE++;
			File file = new File(PATH_UH + "\\" + Constants.FILE_NAME_UH + NO_OF_UH_FILE + ".txt");
			
			GenFilesUtils.writeFile(file, UH_SB, true);
		}
		if(POSITION_SB.length() > 0){
			NO_OF_POSITION_FILE++;
			File file = new File(PATH_POS + "\\" + Constants.FILE_NAME_UT_POS + NO_OF_POSITION_FILE + "_" + GenerateFile.CURRENT_DATE_FORMAT + ".txt");
			GenFilesUtils.writeFile(file, POSITION_SB, true);
		}
		if(TRANSACTION_SB.length() > 0){
			NO_OF_TRANSACTION_FILE++;
			File file = new File(PATH_TX + "\\" + Constants.FILE_NAME_UT_TX + "_" + NO_OF_TRANSACTION_FILE + ".txt");
			GenFilesUtils.writeFile(file, TRANSACTION_SB, true);
		}

		System.out.println("Generate Unitholder Done");
	}
	
//	public static void generateUnitTrustPosition(int records, int limit, int noOfUh, int noOfFund, int max) throws IOException {
	public static void generateUnitTrustPosition(String cifCode, String uh) throws IOException {
		File logPath = new File(PATH_POS);
		if(!logPath.exists()){
			logPath.mkdirs();
		}
		
		for (int j = 1; j <= GenerateFile.NO_OF_UT_POS_PER_UH; j++) {
			NO_OF_POSITION++;
			String fundCode = FUND_CODE_MAP.get(FUND_SEQ++);
			String record = GenerateFile.CURRENT_DATE_FORMAT + "|" + uh + "|" +ISSUER_CODE 
					+"|"+fundCode+ "|3333.333|444.4444||||||";
			
			
//			1	As of Date	Y	Y	à¸§à¸±à¸™à¸—à¸µà¹ˆà¸‚à¹‰à¸­à¸¡à¸¹à¸¥							Y	DATE	8	yyyyMMdd	 	N
//			2	Unit Holder No	Y	Y	Unit Holder No				Y	STRING	20	 	à¹ƒà¸Šà¹‰à¹ƒà¸™ look up unitholder (Account) à¸žà¸´à¸ˆà¸²à¸£à¸“à¸²à¸„à¸¹à¹ˆà¸�à¸±à¸š Issuer Code à¹�à¸¥à¸° Source	N
//			3	Issuer Code	Y	Y	à¸Šà¸·à¹ˆà¸­à¸¢à¹ˆà¸­ à¸šà¸¥à¸ˆ							Y	STRING	20	 	à¹ƒà¸Šà¹‰à¹ƒà¸™à¸�à¸²à¸£ look up à¸«à¸² unitholder à¹�à¸¥à¸° Fund Code	Y
//			4	Fund Code	Y	Y	à¸Šà¸·à¹ˆà¸­à¸¢à¹ˆà¸­à¸�à¸­à¸‡à¸—à¸¸à¸™							Y	STRING	50	 	 	Y
//			5	Outstanding Units	Y	Y	à¸¢à¸­à¸”à¸«à¸™à¹ˆà¸§à¸¢à¸ªà¸°à¸ªà¸¡				Y	BIGDECIMAL	23	22|6	 	N
//			6	Market Value	Y	Y	à¸¡à¸¹à¸¥à¸„à¹ˆà¸²à¸›à¸±à¸ˆà¸ˆà¸¸à¸šà¸±à¸™à¸‚à¸­à¸‡à¸¢à¸­à¸”à¸«à¸™à¹ˆà¸§à¸¢à¸ªà¸°à¸ªà¸¡			Y	BIGDECIMAL	21	20|2	 	N
//			7	MTM Date	Y	Y	à¸§à¸±à¸™à¸—à¸µà¹ˆà¸‚à¸­à¸‡à¸£à¸²à¸„à¸²à¸—à¸µà¹ˆà¹ƒà¸Šà¹‰à¹ƒà¸™à¸�à¸²à¸£à¸„à¸³à¸™à¸§à¸“à¸¡à¸¹à¸¥à¸„à¹ˆà¸²			N	DATE	8	yyyyMMdd	à¸§à¸±à¸™à¸—à¸µà¹ˆà¹ƒà¸Šà¹‰ nav à¹€à¸¡à¸·à¹ˆà¸­ as of date à¸•à¸£à¸‡à¸�à¸±à¸šà¸§à¸±à¸™à¸«à¸¢à¸¸à¸”à¸—à¸³à¸�à¸²à¸£	N
//			8	Market Price	Y	Y	à¸£à¸²à¸„à¸²à¸•à¸¥à¸²à¸”						N	BIGDECIMAL	21	20|2	 	N
//			9	Average Cost	Y	Y	à¸•à¹‰à¸™à¸—à¸¸à¸™à¹€à¸‰à¸¥à¸µà¹ˆà¸¢						N	BIGDECIMAL	23	22|6	à¸•à¹‰à¸™à¸—à¸¸à¸™à¹€à¸‰à¸¥à¸µà¹ˆà¸¢à¸—à¸µà¹ˆà¸‹à¸·à¹‰à¸­ à¸�à¸£à¸“à¸µ LTF KSAM à¹„à¸¡à¹ˆà¸ªà¹ˆà¸‡à¹€à¸žà¸£à¸²à¸°à¸­à¸²à¸ˆà¸œà¸´à¸”à¸�à¸¥à¸•	N
//			10	Cost	Y	Y	à¸•à¹‰à¸™à¸—à¸¸à¸™à¸„à¸‡à¹€à¸«à¸¥à¸·à¸­								N	BIGDECIMAL	21	20|2	 	N
//			11	First Investment Date	Y	Y	à¸§à¸±à¸™à¸—à¸µà¹ˆà¸¥à¸‡à¸—à¸¸à¸™à¸„à¸£à¸±à¹‰à¸‡à¹�à¸£à¸�			N	DATE	8	yyyyMMdd	 	N
//			12	Source	D	N	Source								Y	STRING	20	 	à¹ƒà¸Šà¹‰à¹ƒà¸™à¸�à¸²à¸£ look up à¸«à¸² unitholder	N

			
			
			POSITION_SB.append(record).append(Constants.DEFAULT_LINE_SEPARATOR);
			
			generateUnitTrustTransaction(uh, fundCode);
			
			if(NO_OF_POSITION% (GenerateFile.LIMIT_UH_PER_FILE * 2) == 0){
				NO_OF_POSITION_FILE++;
				File file = new File(PATH_POS + "\\" + Constants.FILE_NAME_UT_POS + NO_OF_POSITION_FILE + "_" + GenerateFile.CURRENT_DATE_FORMAT + ".txt");

				GenFilesUtils.writeFile(file, POSITION_SB, true);
			}
		}
	}
	
	public static void generateUnitTrustTransaction(String uh, String fundCode) throws IOException {
	
		File logPath = new File(PATH_TX);
		if(!logPath.exists()){
			logPath.mkdirs();
		}

		for (int tx = 1; tx <= GenerateFile.NO_OF_UT_TX_PER_POS; tx++) {
			NO_OF_TRANSACTION++;
			String txNo = "UT_TX_NO_" + String.format("%08d", NO_OF_TRANSACTION);
//			String record = "11.11||20150223|11.11||" + txNo + "||||||20150223|11.11||||||||" + fundCode + "|A|BUY|" + uh;
			String record = ISSUER_CODE + "|" + fundCode + "|" + uh 
					+ "|" + GenerateFile.CURRENT_DATE_FORMAT 
					+ "|" + GenerateFile.CURRENT_DATE_FORMAT  
					+ "|" + GenerateFile.CURRENT_DATE_FORMAT 
					+ "|BUY|BAY|00001|11.11|11.11|11.11|11.11||||"+txNo+"|||||||";
			

//			1	Issuer Code	Y	Y	AIMC Code ex. KSAM				Y	STRING	20	 	 	Y
//			2	Fund Code	Y	Y	Fund Code ex. KFCASH			Y	STRING	50	 	 	Y
//			3	Unit Holder No	Y	Y	Unit Holder No				Y	STRING	20	 	 	Y
//			4	Order Date	Y	Y	à¸§à¸±à¸™à¸—à¸µà¹ˆà¸—à¸³à¸£à¸²à¸¢à¸�à¸²à¸£						Y	DATE	8	yyyyMMdd	 	N
//			5	Allot Date	Y	Y	à¸§à¸±à¸™à¸—à¸µà¹ˆà¸ˆà¸±à¸”à¸ªà¸£à¸£(à¸§à¸±à¸™à¸—à¸µà¹ˆà¸‚à¸­à¸‡à¸£à¸²à¸„à¸²à¸—à¸µà¹ˆà¹ƒà¸Šà¹‰à¹ƒà¸™à¸�à¸²à¸£à¸ˆà¸±à¸”à¸ªà¸£à¸£)		Y	DATE	8	yyyyMMdd	 	N
//			6	Settle Date	Y	Y	à¸§à¸±à¸™à¸—à¸µà¹ˆà¸£à¸±à¸šà¹€à¸‡à¸´à¸™ / à¸§à¸±à¸™à¸—à¸µà¹ˆà¸ˆà¹ˆà¸²à¸¢à¹€à¸‡à¸´à¸™					Y	DATE	8	yyyyMMdd	à¹€à¸‰à¸žà¸²à¸°à¸£à¸²à¸¢à¸�à¸²à¸£à¸‚à¸²à¸¢à¹ƒà¸«à¹‰à¸£à¸°à¸šà¸¸à¸§à¸±à¸™à¸—à¸µà¹ˆ Settle Date = à¸§à¸±à¸™à¸—à¸µà¹ˆà¸£à¸±à¸šà¹€à¸‡à¸´à¸™ à¹€à¸Šà¹ˆà¸™ T+3 à¸ªà¹ˆà¸§à¸™à¸£à¸²à¸¢à¸�à¸²à¸£à¸­à¸·à¹ˆà¸™à¹† à¸—à¸µà¹ˆà¹€à¸«à¸¥à¸·à¸­à¹ƒà¸«à¹‰à¸£à¸°à¸šà¸¸ Settle Date = AllotDate	N
//			7	Transaction Type	Y	Y	Transaction Type : BUY, SELL,SWIN, SWOUT, TRIN, TROUT, XSWIN, XSWOUT		Y	STRING	20	 	à¸£à¸°à¸šà¸š à¹€à¸�à¹‡à¸š BUY=1, SELL = 2 , SWIN = 3, SWOUT = 4, TRIN = 5, TROUT = 6, XSWIN = 7, XSWOUT = 8	N
//			8	Agent Code	D=KS	Y	à¸Šà¸·à¸­à¸¢à¹ˆà¸­à¸•à¸±à¸§à¹�à¸—à¸™à¸‚à¸²à¸¢					Y	STRING	20	KS,BAY	 	N
//			9	Agent Branch Code	Y	Y	à¸£à¸«à¸±à¸ªà¸ªà¸²à¸‚à¸²à¸•à¸±à¸§à¹�à¸—à¸™à¸‚à¸²à¸¢				Y	STRING	20	 	 	N
//			10	Allot Unit	Y	Y	à¸«à¸™à¹ˆà¸§à¸¢à¸—à¸µà¹ˆà¹„à¸”à¹‰à¸£à¸±à¸šà¸�à¸²à¸£à¸ˆà¸±à¸”à¸ªà¸£à¸£					Y	BIGDECIMAL	23	22|6	 	N
//			11	Balance Unit	Y	Y	à¸¢à¸­à¸”à¸«à¸™à¹ˆà¸§à¸¢à¸ªà¸°à¸ªà¸¡à¸›à¸±à¸ˆà¸ˆà¸¸à¸šà¸±à¸™				N	BIGDECIMAL	23	22|6	 	N
//			12	Price/Unit	Y	Y	à¸£à¸²à¸„à¸²à¸—à¸µà¹ˆà¹ƒà¸Šà¹‰à¹ƒà¸™à¸�à¸²à¸£à¸ˆà¸±à¸”à¸ªà¸£à¸£					Y	BIGDECIMAL	23	22|6	 	N
//			13	Allot Cost	Y	Y	à¸ˆà¸³à¸™à¸§à¸™à¹€à¸‡à¸´à¸™à¸—à¸µà¹ˆà¹„à¸”à¹‰à¹ƒà¸™à¸�à¸²à¸£à¸ˆà¸±à¸”à¸ªà¸£à¸£ Unit * Price /Unit			Y	BIGDECIMAL	21	20|2	 	N
			
//			14	Realized G/L	Y	Y	Realized Gain/Loss à¸�à¸³à¹„à¸£/à¸‚à¸²à¸”à¸—à¸¸à¸™à¸—à¸µà¹ˆà¹€à¸�à¸´à¸”à¸‚à¸¶à¹‰à¸™à¸ˆà¸£à¸´à¸‡ (Required à¸ªà¸³à¸«à¸£à¸±à¸š Tx. Type à¸—à¸µà¹ˆà¹€à¸›à¹‡à¸™ SELL, SO) à¸‚à¸µà¹‰à¸™à¸�à¸±à¸šà¸›à¸£à¸°à¹€à¸ à¸—à¸�à¸­à¸‡à¸—à¸¸à¸™ à¸«à¸²à¸�à¹€à¸›à¹‡à¸™ LTF = FIFO à¸—à¸µà¹ˆà¹€à¸«à¸¥à¸·à¸­à¹€à¸›à¹‡à¸™ average	N	BIGDECIMAL	21	20|2	 	N
//			15	Swin Allot Date	Y	Y	as of date à¸‚à¸­à¸‡à¸£à¸²à¸¢à¸�à¸²à¸£à¸ªà¸±à¸šà¹€à¸›à¸¥à¸µà¹ˆà¸¢à¸™à¹€à¸‚à¹‰à¸² [à¹„à¸¡à¹ˆà¹ƒà¸Šà¹ˆ MTM Date]
//			à¸ªà¹ˆà¸‡ field à¸™à¸µà¹‰à¸¡à¸²à¹€à¸‰à¸žà¸²à¸°à¸•à¸­à¸™à¸ªà¹ˆà¸‡à¸£à¸²à¸¢à¸�à¸²à¸£ Switch out										N	DATE	8	yyyyMMdd	 	N
//			16	Switching Fund Code	Y	Y	à¸Šà¸·à¹ˆà¸­à¸¢à¹ˆà¸­à¸�à¸­à¸‡à¸—à¸¸à¸™à¸‚à¸­à¸‡à¸£à¸²à¸¢à¸�à¸²à¸£à¸ªà¸±à¸šà¹€à¸›à¸¥à¸µà¹ˆà¸¢à¸™à¸•à¸£à¸‡à¸‚à¹‰à¸²à¸¡
//			à¸«à¸²à¸�à¹€à¸›à¹‡à¸™à¸£à¸²à¸¢à¸�à¸²à¸£ Switch-out à¹ƒà¸«à¹‰à¸£à¸°à¸š Switch-in Fund
//			à¸«à¸²à¸�à¹€à¸›à¹‡à¸™à¸£à¸²à¸¢à¸�à¸²à¸£ Switch-in à¹ƒà¸«à¹‰à¸£à¸°à¸šà¸¸ Switch-out Fund								N	STRING	50	 	 	N
//			17	Ext TX No	Y	Y	Transaction No of another system ( Reference No for cancel this transaction )		N	STRING	50	 	 	N
//			18	IC Code	Y	Y	à¸£à¸«à¸±à¸ª license à¸‚à¸­à¸‡à¸žà¸™à¸±à¸�à¸‡à¸²à¸™à¸œà¸¹à¹‰à¸—à¸³à¸£à¸²à¸¢à¸�à¸²à¸£						N	STRING	20	 	 	N
//			19	Sale Code	Y	Y	à¸£à¸«à¸±à¸ªà¸žà¸™à¸±à¸�à¸‡à¸²à¸™à¸œà¸¹à¹‰à¸—à¸³à¸£à¸²à¸¢à¸�à¸²à¸£								N	STRING	20	 	 	N
//			20	Referral Code	Y	Y	à¸£à¸«à¸±à¸ªà¸žà¸™à¸±à¸�à¸‡à¸²à¸™à¸œà¸¹à¹‰à¹�à¸™à¸°à¸™à¸³								N	STRING	20	 	 	N
//			21	Sequence	Y	Y	à¸¥à¸³à¸”à¸±à¸šà¸—à¸µà¹ˆà¸‚à¸­à¸‡à¸£à¸²à¸¢à¸�à¸²à¸£									N	STRING	15	 	 	N
//			22	Status	Y	Y	Status = N=New(unallot) D=Deleted transaction,A=Active transaction						N	STRING	10	 	 	N
//			23	Remark	Y	Y	à¸�à¸£à¸“à¸µ Transfer à¸šà¸­à¸� unithlder à¸—à¸µà¹ˆ reference				N	STRING	50	 	 	N
//			24	Source	D	N	Source											Y	STRING	20	 	 	N

			
			TRANSACTION_SB.append(record).append(Constants.DEFAULT_LINE_SEPARATOR);
			
			if(NO_OF_TRANSACTION%(GenerateFile.LIMIT_UH_PER_FILE * 2) == 0){
				NO_OF_TRANSACTION_FILE++;
				File file = new File(PATH_TX + "\\" + Constants.FILE_NAME_UT_TX + NO_OF_TRANSACTION_FILE + ".txt");
				GenFilesUtils.writeFile(file, TRANSACTION_SB, true);
			}
		}
	}
	
	
}
