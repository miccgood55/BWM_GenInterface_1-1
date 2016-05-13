package src.gen;

import java.io.File;
import java.io.IOException;

import src.GenerateFile;
import src.bean.CustomerMapper;
import src.extend.Constants;
import src.extend.GenFilesUtils;

public class GenCustomer {

	public static String getCifCode(int seq) {
		return "P" + String.format("%06d", seq);
	}
	public static void generateCustomer() throws IOException {
//		String header = "IP_ID|GNDR_ID|IDV_MAR_ST_TP_ID|ENG_SALUT|ENG_GVN_NM|ENG_SURNM|TH_SALUT|TH_GVN_NM|TH_SURNM|IP_CARD_ID|PSPT_NO|IDV_TAX_ID_NO|IDV_NTNLY|IDV_OCP_CODE|BUREAU_BUS_CD|BRTH_DT|RLG|IDV_EDCTN_LVL|IDV_ANUAL_SLRY_GRS|CTC_ADR_LINE_1|CTC_ADR_LINE_2|CTC_HS_NO|CTC_MOO|CTC_MOOBAN|CTC_BLD_NM|CTC_FLR_NO|CTC_SOI_NM|CTC_STR_NM|CTC_SUB_DSTC_ID|CTC_DSTC_ID|CTC_PROV|CTC_CTY_ID|CTC_PSTCD_AREA_ID|OFFC_ADR_LINE_1|OFFC_ADR_LINE_2|OFFC_HS_NO|OFFC_MOO|OFFC_MOOBAN|OFFC_BLD_NM|OFFC_FLR_NO|OFFC_SOI_NM|OFFC_STR_NM|OFFC_SUB_DSTC_ID|OFFC_DSTC_ID|OFFC_PROV|OFFC_CTY_ID|OFFC_PSTCD_AREA_ID|EMAIL_ADR_NM|HM_PH_NO|OFFC_PH_NO|MBL_PH_NO|HM_FAX_NO|OFFC_FAX_NO|CST_ST";
//		String header = "CIF Code|Gender|Marital Status|Title EN|Title TH |First Name EN|Last Name EN|First Name Other  |Last Name Other|Personal ID  |Passport ID|Card Type|Card ID|Nationality Code|Occupation Code|Business Type Code|Birthday|Education Code|Customer Type|CP Type |Registration Certificate No|Tax ID|Corporate Name EN|Corporate Name Other|Corporate Business Type Code|KYC|KYC Last Review Date|FATCA Type|Do Not Call List|RM Code|Contact Address 1|Contact Address 2|Contact Province|Contact Country|Contact Zip Code|Contact Telephone Home|Contact Mobile Phone|Contact Email|Contact Fax|Home Address 1|Home Address 2|Home Province|Home Country|Home Zip Code|Home Telephone|Home Mobile Phone|Home Email|Home Fax|Customer Subtype";

		String path = Constants.DIR + Constants.DIR_CUS;
		File logPath = new File(path);
		if(!logPath.exists()){
			logPath.mkdirs();
		}
		
		int noOfFile = 0;
		StringBuffer total = new StringBuffer();
//		total.append(header).append(Constants.DEFAULT_LINE_SEPARATOR);
		for (int seq = 1; seq <= GenerateFile.NO_OF_CUSTOMER; seq++) {
//			if(total.length() <= 0){
//				total.append(header).append(Constants.DEFAULT_LINE_SEPARATOR);
//			}
			
			String cifCode = getCifCode(seq);
			String record = cifCode+"|M|S|MR.|Title_th|NAME_"+cifCode+"|LASTNAME_"+cifCode+"|NAMETH_"+cifCode+"|LASTNAMETH_"+cifCode+"|CARD_"+cifCode+"|||||||19901220||1|1||||||1|20151220||Y||ContactAddress1|ContactAddress2|||10600|||ContactEmail|ContactFax|HomeAddress1|HomeAddress2|||10600|||||";

			total.append(record).append(Constants.DEFAULT_LINE_SEPARATOR);
			if(seq%GenerateFile.LIMIT_CUSTOMER_PER_FILE == 0 || seq == GenerateFile.NO_OF_CUSTOMER){
				noOfFile++;
				File file = new File(path + Constants.FILE_NAME_CUS + noOfFile + ".txt");
				GenFilesUtils.writeFile(file, total);
			}
			
			GenerateFile.CM_MAP.put(cifCode, new CustomerMapper(cifCode));
		}
		System.out.println("Generate Customer Done");
	}

}
