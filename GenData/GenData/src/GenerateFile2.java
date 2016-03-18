package src;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.bean.CustomerMapper;

public class GenerateFile2 {


	public final static SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
	public final static String CURRENT_DATE_FORMAT = SDF.format(new Date());
	
	public final static List<String> LIST_BRANCH = new ArrayList<String>();
	public final static Map<String, CustomerMapper> CM_MAP = new HashMap<String, CustomerMapper>();

//	CUSTOMER 
	public final static int NO_OF_CUSTOMER = 10780 ;
	public final static int LIMIT_CUSTOMER_PER_FILE = 10780 ;
	
	
	public static void main(String[] args) {
//		try {
			System.out.println("Start");
			
//			1	Source				ชื่อย่อของผู้ออกผลิตภัณฑ์						Y	Y	Y	STRING	20	- 			Source Default ส่งค่า : KSS-Equity
//			2	Account Name		ชื่อบัญชี								N	Y	Y	STRING	200	- 			1. ข้อมูลใน text file ส่งข้อมูลชิดซ้าย
//																											2. นาย J 
//			3	Account No			หมายเลขบัญชี							Y	Y	Y	STRING	20	- 			EX. JJJJJJ-1
//			4	Open Account Date	วันเปิดบัญชี								N	Y	Y	DATE	8	yyyyMMdd	1. ระบุเป็นปี คศ. 
//																							2. Ex. 20160122
//			5	Close Account Date	วันปิดบัญชี								N	Y	Y	DATE	8	yyyyMMdd	1. ระบุเป็นปี คศ. 
//																							2. Ex. 99991231
//			6	Account Type		เป็นบัญชีเดี่ยวเสมอ ส่งค่า 0						N	Y	Y	INTEGER	1	 -	 		EX. 0
//			7	Margin Type			ประเภทของบัญชี Margin						N	Y	Y	INTEGER	1	 -			EX. 1
//									1  =  Cash Account							
//									2  =  Cash Balance Account							
//									4 =   Derivative							
//									7  =  Credit Balance Account(TSFC)							
//									9  =  Credit Balance Account		
//			8	Identification Type	Card Type:							Y	Y	Y	STRING	20	-			1.. Look up : CP_CARDTYPE[CardTypeCode]
//									01 = Personal ID Card,									2. Look up : CP_IDTYPE[IDNumber] 
//									02 = หนังสือต่างด้าว ,											3. เพื่อหาค่า CIF CODE
//									06 = Passport,											4. EX. 01
//									07 = ทะเบียนนิติบุคคล ,							
//									08 = ทะเบียนพาณิชย์ ,							
//									19 = องค์กรอื่นๆเช่นวัด,สมาคม,มูลนิธิ,...ฯลฯ ,							
//									99 = อื่นๆ 							
//			9 IdentificationNumber	Personal ID, Passport No, Other		Y	Y	Y	STRING	50	-	1. Look up : CP_IDTYPE[IDNumber] 
//																									2. EX. 3160400434770
//			10	CP Type				1 = บุคคลธรรมดา , 2 = นิติบุคคล				Y	Y	Y	STRING	1	-	1 = บุคคลธรรมดา
//																									2 = นิติบุคคล
//																									EX. 1
//			11	Agent Code			ชื่อย่อธนาคาร								N	Y	N	STRING	10	-	1. Default : ข้อมูลตาม Table CM_BANK[BANKCODE]
//																									2. Look up : CM_BANK[BANKCODE]
//																									3. EX. BAY
//			12	Agent Branch Code	รหัสสาขาที่เปิดบัญชี							N	Y	N	STRING	10	-	1. Default : ข้อมูลตาม Table CRM_BRANCH[BANKBRANCHCODE]
//																									2. Look up : CRM_BRANCH[BANKBRANCHCODE]
//																									3. EX. 00001

			
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
//			
			
			System.out.println("DONE");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
}


