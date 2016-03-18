package src;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.bean.CustomerMapper;
import src.extend.Constants;
import src.gen.GenBM;
import src.gen.GenBond;
import src.gen.GenCifMapBranch;
import src.gen.GenCustomer;
import src.gen.GenDeposit;
import src.gen.GenUnitTrust;
import src.rm.GenRM;
import src.rm.GenRMBranch;
import src.rm.GenRMMapCif;

public class GenerateFile {


	public final static SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
	public final static String CURRENT_DATE_FORMAT = SDF.format(new Date());
	
	public final static List<String> LIST_BRANCH = new ArrayList<String>();
	public final static Map<String, CustomerMapper> CM_MAP = new HashMap<String, CustomerMapper>();

	
//	CUSTOMER 
	public final static int NO_OF_CUSTOMER = 37000 ;
	public final static int LIMIT_CUSTOMER_PER_FILE = 10000 ;
	
//	RM / BM
	public final static int NO_OF_RM = 800;
	public final static int NO_OF_BM = 600;

//	BOND
	public final static int NO_OF_MASTER_BOND = 5000;
	public final static int NO_OF_POSITION_PER_MASTER_BOND = 10;
	public final static int NO_OF_TRANSACTION_PER_POSITION_BOND = 1;
	public final static int LIMIT_BOND_PER_FILE = 10000 ;

//	FUND
	public final static int NO_OF_FUND = 30000;
	public final static int LIMIT_FUND_PER_FILE = 10000 ;

//	UNITHOLDER
	public final static int NO_OF_UH = 1;
	public final static int NO_OF_UT_POS_PER_UH = 1 ;
	public final static int NO_OF_UT_TX_PER_POS = 1 ;
	public final static int LIMIT_UH_PER_FILE = 10000 ;
	
//	DEPOSIT
	public final static int NO_OF_ACC = 5;
//	public final static int NO_OF_DEP_POS_PER_UH = 2 ;
	public final static int NO_OF_DEP_TX_PER_POS = 1 ;
	public final static int LIMIT_DEP_PER_FILE = 10000 ;

//	LiabilityPosition
	public final static int NO_OF_LIAB = 5;
	public final static int LIMIT_LIAB_PER_FILE = 100000 ;
	
	public static final int MAX_CIF_MAP_BRANCH = 42;
	public static final int MAX_RM_MAP_CUS = 35;
	
	public static void main(String[] args) {
		try {
			System.out.println("Start");
//			int noOfCustomer = 53900;
//			generateCustomer(noOfCustomer, 11667);
			
//			generateDepositAccount(noOfCustomer, 33334, 6, 400000);
//			generateDepositPosition(noOfCustomer, 33334, 6, 400000);
//			generateDepositTransaction(noOfCustomer, 333334, 6, 10, 4000000);
			
//			generateFundCode(30000,5000);
//			generateUnitholder(noOfCustomer, 5834, 1, 70000);
//			generateUnitTrustPosition(noOfCustomer, 116667, 1, 20, 1400000);
//			generateUnitTrustTransaction(noOfCustomer, 116667, 1, 20, 1, 1400000);
			
//			generateBondMaster(1000,1000);
//			generateBondPosition(noOfCustomer, 100000, 10);
//			generateBondTransaction(noOfCustomer, 100000, 10, 5);
			
//			generateLiabilityPosition(noOfCustomer, 100000, 5);
//			generateExchangeRate();
			
//			RM Branch & Product Branch
			GenRMBranch.generateRmBranch(850);

//			BM Profile
//			generateBM(600);
			GenBM.generateBMPipe();

//			RM Profile
			GenRM.generateRM();

//			Customer Profile 
			GenCustomer.generateCustomer();

//			RM Map CIF
			GenRMMapCif.generateRMMapCif();

//			CIF Map Branch 
			GenCifMapBranch.generateCifMapBranch();

//			Bond
			GenBond.generateBondMaster();

//			Fund
			GenUnitTrust.generateFundCode();
			
//			UnitHolder
			GenUnitTrust.generateUnitholder();

//			Deposit
			GenDeposit.generateDepositAccount();

			generateExchangeRate();

			generateLiabilityPosition(NO_OF_CUSTOMER, LIMIT_LIAB_PER_FILE, NO_OF_LIAB);
			
			System.out.println("DONE");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void generateLiabilityPosition(int records, int limit, int noOfLiability) throws IOException {
		String path = Constants.DIR + Constants.DIR_LIABILITY_POS;
		File logPath = new File(path);
		if(!logPath.exists()){
			logPath.mkdirs();
		}
		
		int noOfFile = 0;
		StringBuffer total = new StringBuffer();
		for (int seq = 1; seq <= records; seq++) {
			for (int i = 1; i <= noOfLiability; i++) {
				String cifCode = "P" + String.format("%06d", seq);
				String liability = "LIA-BAY-" + String.format("%05d", i);
				String accNo = "BOND_ACC_" + cifCode + "_" + String.format("%02d", i);
				String record = accNo + "|20150223||" + liability + "||" +  cifCode + "|2";
				total.append(record).append(Constants.DEFAULT_LINE_SEPARATOR);
			}
			
			if((seq*noOfLiability)%limit == 0){
				noOfFile++;
				File file = new File(path + Constants.FILE_NAME_LIABILITY_POS + noOfFile + ".txt");
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile()), Constants.ENCODING));
				bw.write(total.toString());
				bw.close();
				total = new StringBuffer();
			}
		}
		System.out.println("Generate Liability Position Done");
	}
	
	private static void generateExchangeRate() throws IOException {
		String path = Constants.DIR + Constants.DIR_FX;
		File logPath = new File(path);
		if(!logPath.exists()){
			logPath.mkdirs();
		}
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		StringBuffer total = new StringBuffer();
		total.append("USD|THB|100|").append(CURRENT_DATE_FORMAT).append(Constants.DEFAULT_LINE_SEPARATOR);
		total.append("EUR|THB|100|").append(CURRENT_DATE_FORMAT).append(Constants.DEFAULT_LINE_SEPARATOR);
		total.append("JPY|THB|100|").append(CURRENT_DATE_FORMAT).append(Constants.DEFAULT_LINE_SEPARATOR);
		total.append("GBP|THB|100|").append(CURRENT_DATE_FORMAT).append(Constants.DEFAULT_LINE_SEPARATOR);
		total.append("AUD|THB|100|").append(CURRENT_DATE_FORMAT).append(Constants.DEFAULT_LINE_SEPARATOR);
		total.append("CHF|THB|100|").append(CURRENT_DATE_FORMAT).append(Constants.DEFAULT_LINE_SEPARATOR);
		total.append("CAD|THB|100|").append(CURRENT_DATE_FORMAT).append(Constants.DEFAULT_LINE_SEPARATOR);
//		total.append("MXN|THB|100|").append(DATE_FORMAT).append(Constants.DEFAULT_LINE_SEPARATOR);
		total.append("CNY|THB|100|").append(CURRENT_DATE_FORMAT).append(Constants.DEFAULT_LINE_SEPARATOR);
		total.append("NZD|THB|100|").append(CURRENT_DATE_FORMAT).append(Constants.DEFAULT_LINE_SEPARATOR);
		total.append("SEK|THB|100|").append(CURRENT_DATE_FORMAT).append(Constants.DEFAULT_LINE_SEPARATOR);
//		total.append("RUB|THB|100|").append(DATE_FORMAT).append(Constants.DEFAULT_LINE_SEPARATOR);
		total.append("HKD|THB|100|").append(CURRENT_DATE_FORMAT).append(Constants.DEFAULT_LINE_SEPARATOR);
		total.append("NOK|THB|100|").append(CURRENT_DATE_FORMAT).append(Constants.DEFAULT_LINE_SEPARATOR);
		total.append("SGD|THB|100|").append(CURRENT_DATE_FORMAT).append(Constants.DEFAULT_LINE_SEPARATOR);
//		total.append("TRY|THB|100|").append(DATE_FORMAT).append(Constants.DEFAULT_LINE_SEPARATOR);
		total.append("KRW|THB|100|").append(CURRENT_DATE_FORMAT).append(Constants.DEFAULT_LINE_SEPARATOR);
		total.append("ZAR|THB|100|").append(CURRENT_DATE_FORMAT).append(Constants.DEFAULT_LINE_SEPARATOR);
		total.append("BRL|THB|100|").append(CURRENT_DATE_FORMAT).append(Constants.DEFAULT_LINE_SEPARATOR);
		total.append("INR|THB|100|").append(CURRENT_DATE_FORMAT).append(Constants.DEFAULT_LINE_SEPARATOR);
		
		File file = new File(path + Constants.FILE_NAME_FX + ".txt");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile()), Constants.ENCODING));
		bw.write(total.toString());
		bw.close();
		total = new StringBuffer();
		System.out.println("Generate Exchange Rate Done");
	}
}


